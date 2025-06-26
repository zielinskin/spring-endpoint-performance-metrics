package zielinskin.springboote2e.view;

public record EndpointRunResultIndividualRequest(Long requestMs,
                                                 Integer responseCode,
                                                 Boolean successful) {
}
