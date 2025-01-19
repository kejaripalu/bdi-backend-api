package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenResponse;

public interface RegisterProdukIntelijenService {
	
	void createProdukIntelijen(RegisterProdukIntelijenRequest request);
	
	void updateProdukIntelijen(String ids, RegisterProdukIntelijenRequest request);
	
	Page<RegisterProdukIntelijen> findProdukIntelijen(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterProdukIntelijen> findProdukIntelijenBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
	RegisterProdukIntelijenResponse findProdukIntelijenByIds(String ids);
	
	void deleteProdukIntelijen(String ids);
	
	int countByJenisProdukIntelijen(String stringJenisProdin, String startDate, String endDate);
	
}
