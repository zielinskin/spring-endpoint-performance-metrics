package zielinskin.endpointtesting.logic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import zielinskin.endpointtesting.data.EndpointConfigurationEntity;
import zielinskin.endpointtesting.view.EndpointConfiguration;

@Service
public class EndpointConfigurationService extends AbstractCrudService<EndpointConfigurationEntity, EndpointConfiguration, Integer> {
    public EndpointConfigurationService(CrudRepository<EndpointConfigurationEntity, Integer> repository,
                                        BiMapper<EndpointConfigurationEntity, EndpointConfiguration> mapper) {
        super(repository, mapper);
    }
}
