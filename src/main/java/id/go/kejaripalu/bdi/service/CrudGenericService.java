package id.go.kejaripalu.bdi.service;

public interface CrudGenericService<T> {

    T create(T request);

    T update(String ids, T request);

    T findByIds(String ids);

    void delete(String ids);
}
