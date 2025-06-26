package zielinskin.springboote2e.logic;

import org.springframework.stereotype.Component;
import zielinskin.springboote2e.view.EndpointConfiguration;
import zielinskin.springboote2e.view.EndpointRunResult;
import zielinskin.springboote2e.view.EndpointRunResultIndividualRequest;

import java.util.List;

@Component
public class EndpointRunResultFactory {
    public EndpointRunResult create(Integer id,
                                    Integer endpointConfigurationId,
                                    List<EndpointRunResultIndividualRequest> results) {
        return new EndpointRunResult(
                id,
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
