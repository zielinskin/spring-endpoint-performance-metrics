package zielinskin.endpointtesting.view;

import java.util.List;

public record EndpointRunResult(
        Integer id,
        Integer configurationId,
        Double averageMs,
        Long maxMs,
        Long minMs,
        Double percentageSuccessful,
        Integer totalRan,
        List<EndpointRunResultIndividualRequest> results) {
}
