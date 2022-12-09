package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenResponse;

public interface RegisterKegiatanIntelijenService {

	void create(RegisterKegiatanIntelijenRequest request);
	
	void update(String id, RegisterKegiatanIntelijenRequest request);
	
	Page<RegisterKegiatanIntelijen> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<RegisterKegiatanIntelijen> findBySearching(String start, String end, String value, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	RegisterKegiatanIntelijenResponse findById(String id); 
	
	void delete(String id);
	
}
