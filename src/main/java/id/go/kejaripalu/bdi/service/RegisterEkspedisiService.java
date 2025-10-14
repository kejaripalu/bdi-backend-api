package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterEkspedisiDTO;
import org.springframework.data.domain.Page;

public interface RegisterEkspedisiService extends CrudGenericService<RegisterEkspedisiDTO> {
	
	Page<RegisterEkspedisiDTO> findAll(String startDate, String endDate, String stringJenisSurat, Integer pages, Integer sizes);
	
	Page<RegisterEkspedisiDTO> findBySearching(String start, String end, String value, String jenisSurat, Integer pages, Integer sizes);

}
