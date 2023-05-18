package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraRequest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraResponse;

public interface RegisterKegiatanIntelijenPamstraService {

	void create(RegisterKegiatanIntelijenPamstraRequest request);
	
	void update(String id, RegisterKegiatanIntelijenPamstraRequest request);
	
	Page<RegisterKegiatanIntelijenPamstra> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterKegiatanIntelijenPamstra> findBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
	RegisterKegiatanIntelijenPamstraResponse findById(String id); 
	
	void delete(String id);
	
}
