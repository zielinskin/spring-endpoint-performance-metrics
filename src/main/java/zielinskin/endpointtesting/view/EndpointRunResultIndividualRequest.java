package zielinskin.endpointtesting.view;

public record EndpointRunResultIndividualRequest(Long requestMs,
                                                 Integer responseCode,
                                                 Boolean successful) {
}
