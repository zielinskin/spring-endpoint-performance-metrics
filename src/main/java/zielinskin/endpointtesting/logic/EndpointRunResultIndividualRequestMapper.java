package zielinskin.endpointtesting.logic;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import zielinskin.endpointtesting.data.EndpointRunResultIndividualRequestEntity;
import zielinskin.endpointtesting.view.EndpointRunResultIndividualRequest;

@Component
public class EndpointRunResultIndividualRequestMapper extends BiMapper<EndpointRunResultIndividualRequestEntity, EndpointRunResultIndividualRequest> {

    @Override
    public EndpointRunResultIndividualRequest mapToView(EndpointRunResultIndividualRequestEntity entity) {
        return new EndpointRunResultIndividualRequest(
                entity.getRequestMilliseconds(),
                entity.getResponseCode(),
                HttpStatus.resolve(entity.getResponseCode())
                        .is2xxSuccessful());
    }

    @Override
    public EndpointRunResultIndividualRequestEntity mapToEntity(EndpointRunResultIndividualRequest view) {
        EndpointRunResultIndividualRequestEntity entity = new EndpointRunResultIndividualRequestEntity();

        entity.setRequestMilliseconds(view.requestMs());
        entity.setResponseCode(view.responseCode());

        return entity;
    }
}
