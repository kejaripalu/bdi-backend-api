package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenDTO;
import org.springframework.data.domain.Page;

public interface RegisterKerjaIntelijenService extends CrudGenericService<RegisterKerjaIntelijenDTO> {

	Page<RegisterKerjaIntelijenDTO> findAll(String start, String end, String stringBidangDirektorat, Integer pages, Integer sizes);
	
	Page<RegisterKerjaIntelijenDTO> findBySearching(String start, String end, String value, String stringBidangDirektorat, Integer pages, Integer sizes);

}
