package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenResponse;

public interface RegisterOperasiIntelijenService {
	
	void create(RegisterOperasiIntelijenRequest request);
	
	void update(String id, RegisterOperasiIntelijenRequest request);
	
	Page<RegisterOperasiIntelijen> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<RegisterOperasiIntelijen> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages, Integer sizes);
	
	RegisterOperasiIntelijenResponse findById(String id); 
	
	void delete(String id);

}
