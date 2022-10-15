package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenResponse;
import org.springframework.data.domain.Page;

public interface RegisterProdukIntelijenService {
	
	void createProdukIntelijen(RegisterProdukIntelijenRequest request);
	
	void updateProdukIntelijen(String id, RegisterProdukIntelijenRequest request);
	
	Page<RegisterProdukIntelijen> findProdukIntelijen(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterProdukIntelijen> findProdukIntelijenBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
	RegisterProdukIntelijenResponse findProdukIntelijenById(String id);
	
	void deleteProdukIntelijen(String id);

}
