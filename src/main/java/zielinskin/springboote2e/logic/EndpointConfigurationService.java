package zielinskin.springboote2e.logic;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import zielinskin.springboote2e.data.EndpointConfigurationEntity;
import zielinskin.springboote2e.view.EndpointConfiguration;

@Service
@Transactional
public class EndpointConfigurationService extends AbstractService<EndpointConfigurationEntity, EndpointConfiguration, Integer> {
    public EndpointConfigurationService(CrudRepository<EndpointConfigurationEntity, Integer> repository,
                                        BiMapper<EndpointConfigurationEntity, EndpointConfiguration> mapper) {
        super(repository, mapper);
    }
}
