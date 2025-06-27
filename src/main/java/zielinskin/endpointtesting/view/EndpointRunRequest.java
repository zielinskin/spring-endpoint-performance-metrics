package zielinskin.endpointtesting.view;

public record EndpointRunRequest(
        Integer id,
        String name,
        Integer totalRequests,
        Integer threads) {
}
