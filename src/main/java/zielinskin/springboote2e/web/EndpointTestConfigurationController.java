package zielinskin.springboote2e.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zielinskin.springboote2e.logic.AbstractService;
import zielinskin.springboote2e.view.EndpointConfiguration;

@RestController
@RequestMapping("/endpoint-configurations")
public class EndpointTestConfigurationController extends AbstractCrudController<EndpointConfiguration, Integer> {
    public EndpointTestConfigurationController(AbstractService<?, EndpointConfiguration, Integer> service) {
        super(service);
    }
}
