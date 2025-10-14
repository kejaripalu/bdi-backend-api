package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraDTO;
import org.springframework.data.domain.Page;

public interface RegisterKegiatanIntelijenPamstraService extends CrudGenericService<RegisterKegiatanIntelijenPamstraDTO> {

	Page<RegisterKegiatanIntelijenPamstraDTO> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterKegiatanIntelijenPamstraDTO> findBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
}
