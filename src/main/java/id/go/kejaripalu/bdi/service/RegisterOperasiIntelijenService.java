package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenDTO;
import org.springframework.data.domain.Page;

public interface RegisterOperasiIntelijenService extends CrudGenericService<RegisterOperasiIntelijenDTO> {
	
	Page<RegisterOperasiIntelijenDTO> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<RegisterOperasiIntelijenDTO> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages, Integer sizes);
}
