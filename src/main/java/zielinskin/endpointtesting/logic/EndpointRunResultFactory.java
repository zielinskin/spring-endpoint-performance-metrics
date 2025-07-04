package zielinskin.endpointtesting.logic;

import org.springframework.stereotype.Component;
import zielinskin.endpointtesting.view.EndpointRunRequest;
import zielinskin.endpointtesting.view.EndpointRunResult;
import zielinskin.endpointtesting.view.EndpointRunResultIndividualRequest;

import java.util.List;

@Component
public class EndpointRunResultFactory {
    public EndpointRunResult create(Integer id,
                                    String name,
                                    Integer threads,
                                    Integer totalRequests,
                                    Integer endpointConfigurationId,
                                    List<EndpointRunResultIndividualRequest> results) {
        return new EndpointRunResult(
                id,
                name,
                threads,
                totalRequests,
                endpointConfigurationId,
                results.stream()
                        .mapToLong(EndpointRunResultIndividualRequest::requestMs)
                        .average()
                        .orElse(0d),
                results.stream()
                        .mapToLong(EndpointRunResultIndividualRequest::requestMs)
                        .max()
                        .orElse(0),
                results.stream()
                        .mapToLong(EndpointRunResultIndividualRequest::requestMs)
                        .min()
                        .orElse(0),
                (double) results.stream()
                        .filter(EndpointRunResultIndividualRequest::successful)
                        .count() / (long) results.size() * 100d,
                results.size(),
                results);
    }
}
