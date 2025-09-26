package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

public interface RegisterSuratService<T> extends CrudGenericService<T> {

    Page<T> findAll(String startDate, String endDate, String stringJenisSurat, Integer pages, Integer sizes);

    Page<T> findBySearching(String start, String end, String value, String jenisSurat, Integer pages, Integer sizes);

}
