package zielinskin.springboote2e.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import zielinskin.springboote2e.logic.EndpointTestService;
import zielinskin.springboote2e.view.EndpointRunRequest;
import zielinskin.springboote2e.view.EndpointRunResult;

@RestController
public class EndpointTestController {
    private final EndpointTestService endpointTestService;

    public EndpointTestController(EndpointTestService endpointTestService) {
        this.endpointTestService = endpointTestService;
    }

    @PostMapping("/endpoints/run-test")
    public EndpointRunResult testEndpoint(@RequestBody EndpointRunRequest endpointRunRequest) {
        return endpointTestService.testEndpoint(endpointRunRequest);
    }
}
