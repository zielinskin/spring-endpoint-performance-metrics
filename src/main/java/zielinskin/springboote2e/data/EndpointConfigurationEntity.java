package zielinskin.springboote2e.data;


import jakarta.persistence.*;
import org.springframework.util.LinkedMultiValueMap;

import java.util.HashSet;
import java.util.Set;

@Entity
public class EndpointConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;
    private String method;
    private String body;

    @OneToMany(
            mappedBy = "configuration",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<HeaderEntity> headers = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
