package zielinskin.springboote2e.logic;


import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class AbstractService<T, V, ID> {
    private final CrudRepository<T, ID> repository;
    private final BiMapper<T, V> mapper;

    public AbstractService(CrudRepository<T, ID> repository, BiMapper<T, V> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public void save(V view) {
        repository.save(mapper.mapToEntity(view));
    }

    public void save(List<V> views) {
        repository.saveAll(views.stream()
                .map(mapper::mapToEntity)
                .collect(Collectors.toSet()));
    }

    public List<V> get() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    public V get(ID id) {
        return repository.findById(id)
                .map(mapper::mapToView)
                .orElseThrow(() ->
                        new RuntimeException("There wasn't one, duh."));
    }
}
