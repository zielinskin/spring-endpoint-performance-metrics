package zielinskin.endpointtesting.logic;


import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
public abstract class AbstractCrudService<T, V, ID> implements CrudService<V, ID> {
    private final CrudRepository<T, ID> repository;
    private final BiMapper<T, V> mapper;

    public AbstractCrudService(CrudRepository<T, ID> repository, BiMapper<T, V> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public V save(V view) {
        return mapper.mapToView(repository.save(mapper.mapToEntity(view)));
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
