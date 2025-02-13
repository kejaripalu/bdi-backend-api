package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;
import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukCreateRequest;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukResponse;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukUpdateRequest;

public interface RegisterSuratMasukService {
	
	void createSuratMasuk(RegisterSuratMasukCreateRequest request);
	
	void updateSuratMasuk(String ids, RegisterSuratMasukUpdateRequest request);
	
	Page<RegisterSuratMasuk> findSuratMasuk(String startDate, String endDate, String stringJenisSurat, Integer pages, Integer sizes);
	
	Page<RegisterSuratMasuk> findSuratMasukBySearching(String start, String end, String value, String jenisSurat, Integer pages, Integer sizes);
	
	RegisterSuratMasukResponse findSuratMasukByIds(String ids); 
	
	void deleteSuratMasuk(String ids);

}
