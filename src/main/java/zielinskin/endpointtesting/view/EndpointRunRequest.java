package zielinskin.endpointtesting.view;

public record EndpointRunRequest(
        Integer id,
        Integer totalRequests,
        Integer threads) {
}
