package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;
import id.go.kejaripalu.bdi.domain.SuratMasuk;
import id.go.kejaripalu.bdi.dto.SuratMasukCreateRequest;
import id.go.kejaripalu.bdi.dto.SuratMasukResponse;
import id.go.kejaripalu.bdi.dto.SuratMasukUpdateRequest;

public interface SuratMasukService {
	
	void createSuratMasuk(SuratMasukCreateRequest request);
	
	void updateSuratMasuk(String id, SuratMasukUpdateRequest request);
	
	Page<SuratMasuk> findSuratMasuk(String startDate, String endDate, String stringJenisSurat, Integer pages, Integer sizes);
	
	SuratMasukResponse findSuratMasukById(String id); 
	
	void deleteSuratMasuk(String id);

}
