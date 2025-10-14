package id.go.kejaripalu.bdi.service;

import java.util.List;

import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenDTO;
import org.springframework.data.domain.Page;

public interface RegisterProdukIntelijenService extends CrudGenericService<RegisterProdukIntelijenDTO> {
	
	Page<RegisterProdukIntelijenDTO> findAll(String startDate, String endDate, Integer pages, Integer sizes);
	
	Page<RegisterProdukIntelijenDTO> findBySearching(String start, String end, String value, Integer pages, Integer sizes);
	
	List<Integer[]> countJenisProdukIntelijen(String startDate, String endDate);

}
