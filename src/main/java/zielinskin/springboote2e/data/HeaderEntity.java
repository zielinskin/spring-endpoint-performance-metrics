package zielinskin.springboote2e.data;

import jakarta.persistence.*;

@Entity
@Table(name = "Header_Entities")
public class HeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //note: value is a reserved word in h2
    @Column(name = "header_value", length = 1_000_000)
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EndpointConfigurationEntity getConfiguration() {
        return configuration;
    }

    public void setConfiguration(EndpointConfigurationEntity configuration) {
        this.configuration = configuration;
    }
}
