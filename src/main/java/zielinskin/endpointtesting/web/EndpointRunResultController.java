package zielinskin.endpointtesting.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zielinskin.endpointtesting.logic.CrudService;
import zielinskin.endpointtesting.view.EndpointRunResult;

@RestController
@RequestMapping("/endpoint-run-results")
public class EndpointRunResultController extends AbstractCrudController<EndpointRunResult, Integer> {
    public EndpointRunResultController(CrudService<EndpointRunResult, Integer> service) {
        super(service);
    }
}
