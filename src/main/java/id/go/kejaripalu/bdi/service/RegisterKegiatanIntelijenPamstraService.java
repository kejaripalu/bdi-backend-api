package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

public interface RegisterKegiatanIntelijenPamstraService<T> extends CrudGenericService<T> {

	Page<T> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<T> findBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
}
