package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenResponse;

public interface RegisterKegiatanIntelijenService<T> extends CrudGenericService<T> {

	Page<T> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<T> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages, Integer sizes);

}
