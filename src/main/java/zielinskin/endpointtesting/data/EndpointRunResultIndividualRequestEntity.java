package zielinskin.endpointtesting.data;


import jakarta.persistence.*;

@Entity
public class EndpointRunResultIndividualRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long requestMilliseconds;
    private Integer responseCode;

    @ManyToOne
    @JoinColumn(name = "run_result_id")
    private EndpointRunResultEntity runResult;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRequestMilliseconds() {
        return requestMilliseconds;
    }

    public void setRequestMilliseconds(Long requestMilliseconds) {
        this.requestMilliseconds = requestMilliseconds;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public EndpointRunResultEntity getRunResult() {
        return runResult;
    }

    public void setRunResult(EndpointRunResultEntity runResult) {
        this.runResult = runResult;
    }
}
