package zielinskin.endpointtesting.view;


import org.springframework.util.LinkedMultiValueMap;

public record EndpointConfiguration(
        Integer id,
        String name,
        String url,
        String method,
        LinkedMultiValueMap<String, String> headers,
        String body) {
}
