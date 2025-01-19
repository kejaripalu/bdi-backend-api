package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiRequest;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiResponse;

public interface RegisterEkspedisiService {
	
	void createEkspedisi(RegisterEkspedisiRequest request);
	
	void updateEkspedisi(String ids, RegisterEkspedisiRequest request);
	
	Page<RegisterEkspedisi> findEkspedisi(String startDate, String endDate, String stringJenisSurat, Integer pages, Integer sizes);
	
	Page<RegisterEkspedisi> findEkspedisiBySearching(String start, String end, String value, String jenisSurat, Integer pages, Integer sizes);
	
	RegisterEkspedisiResponse findEkspedisiByIds(String ids); 
	
	void deleteEkspedisi(String ids);

}
