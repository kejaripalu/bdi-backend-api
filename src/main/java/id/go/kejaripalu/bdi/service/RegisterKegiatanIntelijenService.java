package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenDTO;
import org.springframework.data.domain.Page;

public interface RegisterKegiatanIntelijenService extends CrudGenericService<RegisterKegiatanIntelijenDTO> {

	Page<RegisterKegiatanIntelijenDTO> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<RegisterKegiatanIntelijenDTO> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages, Integer sizes);

}
