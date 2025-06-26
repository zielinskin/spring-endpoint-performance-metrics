package zielinskin.endpointtesting.data;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class EndpointRunResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
