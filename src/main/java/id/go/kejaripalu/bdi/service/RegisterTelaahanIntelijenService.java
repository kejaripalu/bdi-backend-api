package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenResponse;

public interface RegisterTelaahanIntelijenService {
	
	void create(RegisterTelaahanIntelijenRequest request);
	
	void update(String ids, RegisterTelaahanIntelijenRequest request);
	
	Page<RegisterTelaahanIntelijen> findAll(String start, String end, Integer pages, Integer sizes);
	
	Page<RegisterTelaahanIntelijen> findBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
	RegisterTelaahanIntelijenResponse findByIds(String ids); 
	
	void delete(String ids);

}
