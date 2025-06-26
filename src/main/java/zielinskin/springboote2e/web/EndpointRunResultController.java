package zielinskin.springboote2e.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zielinskin.springboote2e.logic.CrudService;
import zielinskin.springboote2e.view.EndpointRunResult;

@RestController
@RequestMapping("/endpoint-run-results")
public class EndpointRunResultController extends AbstractCrudController<EndpointRunResult, Integer> {
    public EndpointRunResultController(CrudService<EndpointRunResult, Integer> service) {
        super(service);
    }
}
