package zielinskin.springboote2e.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zielinskin.springboote2e.logic.AbstractCrudService;
import zielinskin.springboote2e.view.EndpointConfiguration;

@RestController
@RequestMapping("/endpoint-configurations")
public class EndpointConfigurationController extends AbstractCrudController<EndpointConfiguration, Integer> {
    public EndpointConfigurationController(AbstractCrudService<?, EndpointConfiguration, Integer> service) {
        super(service);
    }
}
