package zielinskin.endpointtesting.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zielinskin.endpointtesting.logic.AbstractCrudService;
import zielinskin.endpointtesting.view.EndpointConfiguration;

@RestController
@RequestMapping("/endpoint-configurations")
public class EndpointConfigurationController extends AbstractCrudController<EndpointConfiguration, Integer> {
    public EndpointConfigurationController(AbstractCrudService<?, EndpointConfiguration, Integer> service) {
        super(service);
    }
}
