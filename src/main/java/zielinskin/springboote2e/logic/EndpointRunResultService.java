package zielinskin.springboote2e.logic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import zielinskin.springboote2e.data.EndpointRunResultEntity;
import zielinskin.springboote2e.view.EndpointRunResult;

@Service
public class EndpointRunResultService extends AbstractCrudService<EndpointRunResultEntity, EndpointRunResult, Integer> {
    public EndpointRunResultService(CrudRepository<EndpointRunResultEntity, Integer> repository,
                                    BiMapper<EndpointRunResultEntity, EndpointRunResult> mapper) {
        super(repository, mapper);
    }
}
