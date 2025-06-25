package zielinskin.springboote2e.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import zielinskin.springboote2e.logic.EndpointTestService;
import zielinskin.springboote2e.view.EndpointConfiguration;
import zielinskin.springboote2e.view.EndpointRunRequest;
import zielinskin.springboote2e.view.EndpointTestResults;

@RestController
public class EndpointTestController {
    private static final Logger log = LoggerFactory.getLogger(EndpointTestController.class);
    private final EndpointTestService endpointTestService;

    public EndpointTestController(EndpointTestService endpointTestService) {
        this.endpointTestService = endpointTestService;
    }

    @PostMapping("/endpoints/run-test")
    public EndpointTestResults testEndpoint(@RequestBody EndpointRunRequest endpointRunRequest) {
        return endpointTestService.testEndpoint(endpointRunRequest);
    }
}
