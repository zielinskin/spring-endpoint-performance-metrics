package zielinskin.springboote2e.logic;

public abstract class BiMapper<T, V> {
    public abstract V mapToView(T t);
    public abstract T mapToEntity(V v);
}
