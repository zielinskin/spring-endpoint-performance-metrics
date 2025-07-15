package zielinskin.endpointtesting.data;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class EndpointConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
    private String method;
    private String body;

    @OneToMany(
            mappedBy = "configuration",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<HeaderEntity> headers = new HashSet<>();

    @OneToMany(
            mappedBy = "configuration",
            cascade = CascadeType.REMOVE
    )
    private Set<EndpointRunResultEntity> results = new HashSet<>();

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<HeaderEntity> getHeaders() {
        return headers;
    }

    public void setHeaders(Set<HeaderEntity> headers) {
        this.headers = headers;
    }
}
