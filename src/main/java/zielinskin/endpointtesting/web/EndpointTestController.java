package zielinskin.endpointtesting.web;

import org.springframework.web.bind.annotation.*;
import zielinskin.endpointtesting.logic.EndpointTestService;
import zielinskin.endpointtesting.view.EndpointRunRequest;
import zielinskin.endpointtesting.view.EndpointRunResult;

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
