package zielinskin.endpointtesting.logic;

import org.springframework.stereotype.Component;
import zielinskin.endpointtesting.data.EndpointConfigurationEntity;
import zielinskin.endpointtesting.data.EndpointRunResultEntity;
import zielinskin.endpointtesting.data.EndpointRunResultIndividualRequestEntity;
import zielinskin.endpointtesting.view.EndpointRunResult;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EndpointRunResultMapper extends BiMapper<EndpointRunResultEntity, EndpointRunResult> {
    private final EndpointRunResultFactory endpointRunResultFactory;
    private final EndpointRunResultIndividualRequestMapper endpointRunResultIndividualRequestMapper;

    public EndpointRunResultMapper(EndpointRunResultFactory endpointRunResultFactory,
                                   EndpointRunResultIndividualRequestMapper endpointRunResultIndividualRequestMapper) {
        this.endpointRunResultFactory = endpointRunResultFactory;
        this.endpointRunResultIndividualRequestMapper = endpointRunResultIndividualRequestMapper;
    }

    @Override
    public EndpointRunResult mapToView(EndpointRunResultEntity entity) {
        return endpointRunResultFactory.create(entity.getId(),
                entity.getConfiguration().getId(),
                entity.getResults().stream()
                        .map(endpointRunResultIndividualRequestMapper::mapToView)
                        .toList());
    }

    @Override
    public EndpointRunResultEntity mapToEntity(EndpointRunResult view) {
        EndpointRunResultEntity entity = new EndpointRunResultEntity();

        EndpointConfigurationEntity configurationEntity = new EndpointConfigurationEntity();
        configurationEntity.setId(view.configurationId());

        entity.setConfiguration(configurationEntity);

        Set<EndpointRunResultIndividualRequestEntity> requestEntities = view.results().stream()
                .map(result -> {
                    EndpointRunResultIndividualRequestEntity resultEntity = endpointRunResultIndividualRequestMapper.mapToEntity(result);
                    resultEntity.setRunResult(entity);
                    return resultEntity;
                })
                .collect(Collectors.toSet());
        entity.setResults(requestEntities);

        return entity;
    }
}
