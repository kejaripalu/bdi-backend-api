package id.go.kejaripalu.bdi.service;

import java.util.List;

import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMDTO;
import org.springframework.data.domain.Page;

public interface RegisterTamuPPHPPMService extends CrudGenericService<RegisterTamuPPHPPMDTO> {
	
	Page<RegisterTamuPPHPPMDTO> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterTamuPPHPPMDTO> findBySearching(String startDate, String endDate, String value, Integer pages, Integer sizes);
	
	List<Integer[]> countPPHPPM(String startDate, String endDate);
	
}
