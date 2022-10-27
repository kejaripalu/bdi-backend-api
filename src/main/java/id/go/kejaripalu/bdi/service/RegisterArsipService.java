package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterArsip;
import id.go.kejaripalu.bdi.dto.RegisterArsipRequest;
import id.go.kejaripalu.bdi.dto.RegisterArsipResponse;

public interface RegisterArsipService {
	
	void create(RegisterArsipRequest request);
	
	void update(String id, RegisterArsipRequest request);
	
	Page<RegisterArsip> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterArsip> findBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
	RegisterArsipResponse findById(String id); 
	
	void delete(String id);

}
