package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMResponse;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMResquest;

public interface RegisterTamuPPHPPMService {
	
	void createRegisterTamu(RegisterTamuPPHPPMResquest request);
	
	void updateRegisterTamu(String id, RegisterTamuPPHPPMResquest resquest);
	
	Page<RegisterTamuPPHPPM> findRegisterTamu(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterTamuPPHPPM> findRegisterTamuBySearching(String startDate, String endDate, String value, Integer pages, Integer sizes);
	
	RegisterTamuPPHPPMResponse findRegisterTamuById(String id);
	
	void deleteRegisterTamu(String id);
	
}
