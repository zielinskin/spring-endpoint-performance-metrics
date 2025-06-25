package zielinskin.springboote2e.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import zielinskin.springboote2e.view.EndpointConfiguration;
import zielinskin.springboote2e.view.EndpointRunRequest;
import zielinskin.springboote2e.view.EndpointTestResults;
import zielinskin.springboote2e.view.RequestResult;
import zielinskin.springboote2e.web.EndpointTestController;

import java.util.*;
import java.util.concurrent.*;

@Service
public class EndpointTestService {
    private static final Logger log = LoggerFactory.getLogger(EndpointTestController.class);
    private final RestTemplate restTemplate;
    private final EndpointConfigurationService endpointConfigurationService;

    public EndpointTestService(EndpointConfigurationService endpointConfigurationService) {
        this.restTemplate = new RestTemplate();
        this.endpointConfigurationService = endpointConfigurationService;
    }

    public EndpointTestResults testEndpoint(EndpointRunRequest testRequest) {
        EndpointConfiguration endpointConfiguration = endpointConfigurationService.get(testRequest.id());

        int defaultRequestsPerThread = testRequest.totalRequests() / testRequest.threads();
        int totalThreadsWithExtraRequest = testRequest.totalRequests() % testRequest.threads();

        List<Callable<List<RequestResult>>> tasks = new ArrayList<>(testRequest.threads());
        for (int i = 0; i < testRequest.threads(); i++) {
            if (i < totalThreadsWithExtraRequest) {
                tasks.add(() ->
                        run(defaultRequestsPerThread + 1, endpointConfiguration));
            } else {
                tasks.add(() ->
                        run(defaultRequestsPerThread, endpointConfiguration));
            }
        }
        List<RequestResult> results;

        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(testRequest.threads());
        try {
            results = threadPoolExecutor.invokeAll(tasks).stream()
                    .map(this::getFromFuture)
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .toList();
        } catch (Exception e) {
            log.error("Error in endpoint test service executing results:", e);
            results = new ArrayList<>();
        } finally {
            threadPoolExecutor.shutdown();
        }

        return new EndpointTestResults(
                results.stream()
                        .mapToLong(RequestResult::requestMs)
                        .average()
                        .orElse(0d),
                results.stream()
                        .mapToLong(RequestResult::requestMs)
                        .max()
                        .orElse(0),
                results.stream()
                        .mapToLong(RequestResult::requestMs)
                        .min()
                        .orElse(0),
                (double) results.stream()
                        .filter(RequestResult::successful)
                        .count() / (long) results.size() * 100d,
                results.size());
    }

    private List<RequestResult> run(Integer timesToRun, EndpointConfiguration testRequest) {
        List<RequestResult> results = new ArrayList<>();
        for (int i = 0; i < timesToRun; i++) {
            StopWatch stopWatch = new StopWatch();

            HttpEntity<String> entity = new HttpEntity<>(testRequest.body(), testRequest.headers());

            stopWatch.start();
            ResponseEntity<String> responseEntity = restTemplate.exchange(testRequest.url(),
                    HttpMethod.valueOf(testRequest.method()),
                    entity, String.class);
            stopWatch.stop();

            results.add(new RequestResult(
                    stopWatch.getTotalTimeMillis(),
                    responseEntity.getStatusCode().value(),
                    responseEntity.getStatusCode().is2xxSuccessful()));
        }
        return results;
    }

    private <T> T getFromFuture(Future<T> future) {
        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }
}