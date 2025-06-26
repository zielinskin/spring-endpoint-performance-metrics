package zielinskin.endpointtesting.logic;

import java.util.List;

public interface CrudService<V, ID> {
    void delete(ID id);

    V save(V view);

    void save(List<V> views);

    List<V> get();

    V get(ID id);
}
