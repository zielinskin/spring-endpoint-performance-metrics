package zielinskin.endpointtesting.logic;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import zielinskin.endpointtesting.data.EndpointConfigurationEntity;
import zielinskin.endpointtesting.data.HeaderEntity;
import zielinskin.endpointtesting.view.EndpointConfiguration;

import java.util.stream.Collectors;

@Component
public class EndpointConfigurationMapper extends BiMapper<EndpointConfigurationEntity, EndpointConfiguration> {
    @Override
    public EndpointConfiguration mapToView(EndpointConfigurationEntity entity) {
        return new EndpointConfiguration(entity.getId(),
                entity.getUrl(),
                entity.getMethod(),
                entity.getHeaders().stream()
                        .collect(Collectors.groupingBy(
                                HeaderEntity::getName,
                                LinkedMultiValueMap::new,
                                Collectors.mapping(
                                        HeaderEntity::getValue,
                                        Collectors.toList()))),
                entity.getBody());
    }

    @Override
    public EndpointConfigurationEntity mapToEntity(EndpointConfiguration endpointConfiguration) {
        EndpointConfigurationEntity entity = new EndpointConfigurationEntity();

        entity.setId(endpointConfiguration.id());
        entity.setUrl(endpointConfiguration.url());
        entity.setMethod(endpointConfiguration.method());
        entity.setBody(endpointConfiguration.body());
        entity.setHeaders(endpointConfiguration.headers().entrySet().stream()
                .flatMap(entry ->
                        entry.getValue().stream()
                                .map(value -> {
                                    HeaderEntity headerEntity = new HeaderEntity();
                                    headerEntity.setName(entry.getKey());
                                    headerEntity.setValue(value);
                                    headerEntity.setConfiguration(entity);
                                    return headerEntity;
                                }))
                .collect(Collectors.toSet()));

        return entity;
    }
}
