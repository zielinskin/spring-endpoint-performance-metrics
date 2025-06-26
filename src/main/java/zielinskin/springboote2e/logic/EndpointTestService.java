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
import zielinskin.springboote2e.view.EndpointRunResult;
import zielinskin.springboote2e.view.EndpointRunResultIndividualRequest;

import java.util.*;
import java.util.concurrent.*;

@Service
public class EndpointTestService {
    private static final Logger log = LoggerFactory.getLogger(EndpointTestService.class);
    private final RestTemplate restTemplate;
    private final EndpointConfigurationService endpointConfigurationService;
    private final EndpointRunResultService endpointRunResultService;;
    private final EndpointRunResultFactory endpointRunResultFactory;

    public EndpointTestService(EndpointConfigurationService endpointConfigurationService,
                               EndpointRunResultService endpointRunResultService,
                               EndpointRunResultFactory endpointRunResultFactory) {
        this.restTemplate = new RestTemplate();
        this.endpointConfigurationService = endpointConfigurationService;
        this.endpointRunResultService = endpointRunResultService;
        this.endpointRunResultFactory = endpointRunResultFactory;
    }

    public EndpointRunResult testEndpoint(EndpointRunRequest testRequest) {
        EndpointConfiguration endpointConfiguration = endpointConfigurationService.get(testRequest.id());

        int defaultRequestsPerThread = testRequest.totalRequests() / testRequest.threads();
        int totalThreadsWithExtraRequest = testRequest.totalRequests() % testRequest.threads();

        List<Callable<List<EndpointRunResultIndividualRequest>>> tasks = new ArrayList<>(testRequest.threads());
        for (int i = 0; i < testRequest.threads(); i++) {
            if (i < totalThreadsWithExtraRequest) {
                tasks.add(() ->
                        run(defaultRequestsPerThread + 1, endpointConfiguration));
            } else {
                tasks.add(() ->
                        run(defaultRequestsPerThread, endpointConfiguration));
            }
        }
        List<EndpointRunResultIndividualRequest> results;

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

        EndpointRunResult result = endpointRunResultFactory.create(null,
                endpointConfiguration.id(),
                results);

        result = endpointRunResultService.save(result);

        return result;
    }

    private List<EndpointRunResultIndividualRequest> run(Integer timesToRun, EndpointConfiguration testRequest) {
        List<EndpointRunResultIndividualRequest> results = new ArrayList<>();
        for (int i = 0; i < timesToRun; i++) {
            StopWatch stopWatch = new StopWatch();

            HttpEntity<String> entity = new HttpEntity<>(testRequest.body(), testRequest.headers());

            stopWatch.start();
            ResponseEntity<String> responseEntity = restTemplate.exchange(testRequest.url(),
                    HttpMethod.valueOf(testRequest.method()),
                    entity, String.class);
            stopWatch.stop();

            results.add(new EndpointRunResultIndividualRequest(
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