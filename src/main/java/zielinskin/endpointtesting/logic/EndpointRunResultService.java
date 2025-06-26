package zielinskin.endpointtesting.logic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import zielinskin.endpointtesting.data.EndpointRunResultEntity;
import zielinskin.endpointtesting.view.EndpointRunResult;

@Service
public class EndpointRunResultService extends AbstractCrudService<EndpointRunResultEntity, EndpointRunResult, Integer> {
    public EndpointRunResultService(CrudRepository<EndpointRunResultEntity, Integer> repository,
                                    BiMapper<EndpointRunResultEntity, EndpointRunResult> mapper) {
        super(repository, mapper);
    }
}
