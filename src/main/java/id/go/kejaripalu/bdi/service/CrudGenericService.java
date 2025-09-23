package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

public interface CrudGenericService<T> {

    T create(T request);

    T update(String ids, T request);

    Page<T> findSuratMasuk(String startDate, String endDate, String stringJenisSurat, Integer pages, Integer sizes);

    Page<T> findSuratMasukBySearching(String start, String end, String value, String jenisSurat, Integer pages, Integer sizes);

    T findSuratMasukByIds(String ids);

    void delete(String ids);
}
