package zielinskin.springboote2e.view;

public record EndpointRunRequest(
        Integer id,
        Integer totalRequests,
        Integer threads) {
}
