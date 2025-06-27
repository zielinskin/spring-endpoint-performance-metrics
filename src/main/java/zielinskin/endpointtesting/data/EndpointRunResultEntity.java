package zielinskin.endpointtesting.data;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class EndpointRunResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer threads;
    private Integer totalRequests;

    @OneToMany(
            mappedBy = "runResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<EndpointRunResultIndividualRequestEntity> results = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "configuration_id")
    private EndpointConfigurationEntity configuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Integer totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Set<EndpointRunResultIndividualRequestEntity> getResults() {
        return results;
    }

    public void setResults(Set<EndpointRunResultIndividualRequestEntity> results) {
        this.results = results;
    }

    public EndpointConfigurationEntity getConfiguration() {
        return configuration;
    }

    public void setConfiguration(EndpointConfigurationEntity configuration) {
        this.configuration = configuration;
    }
}
