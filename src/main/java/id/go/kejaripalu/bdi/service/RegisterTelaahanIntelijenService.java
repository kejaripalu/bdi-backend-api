package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenDTO;
import org.springframework.data.domain.Page;

public interface RegisterTelaahanIntelijenService extends CrudGenericService<RegisterTelaahanIntelijenDTO> {
	
	Page<RegisterTelaahanIntelijenDTO> findAll(String start, String end, Integer pages, Integer sizes);
	
	Page<RegisterTelaahanIntelijenDTO> findBySearching(String start, String end, String value, Integer pages, Integer sizes);

}
