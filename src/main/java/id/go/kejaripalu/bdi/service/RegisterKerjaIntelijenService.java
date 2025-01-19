package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenResponse;

public interface RegisterKerjaIntelijenService {
	
	void createRKI(RegisterKerjaIntelijenRequest request);

	void updateRKI(String ids, RegisterKerjaIntelijenRequest request);
	
	RegisterKerjaIntelijenResponse findRKIbyIds(String ids);
	
	Page<RegisterKerjaIntelijen> findRKI(String start, String end, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<RegisterKerjaIntelijen> findRKIBySearching(String start, String end, String value, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	void deleteRKI(String ids);

}
