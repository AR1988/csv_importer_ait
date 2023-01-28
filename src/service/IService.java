package service;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public interface IService<T> {

    void save(T entity);

    T get(long id);
}
