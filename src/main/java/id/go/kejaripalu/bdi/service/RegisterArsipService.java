package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterArsipDTO;
import org.springframework.data.domain.Page;

public interface RegisterArsipService extends CrudGenericService<RegisterArsipDTO> {

	Page<RegisterArsipDTO> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterArsipDTO> findBySearching(String start, String end, String value, Integer pages, Integer sizes);

}
