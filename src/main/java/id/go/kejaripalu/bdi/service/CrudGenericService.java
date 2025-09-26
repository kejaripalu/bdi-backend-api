package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

public interface CrudGenericService<T> {

    T create(T request);

    T update(String ids, T request);

    T findByIds(String ids);

    void delete(String ids);
}
