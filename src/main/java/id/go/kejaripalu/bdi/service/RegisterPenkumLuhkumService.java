package id.go.kejaripalu.bdi.service;

import java.util.List;

import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumDTO;
import org.springframework.data.domain.Page;

public interface RegisterPenkumLuhkumService extends CrudGenericService<RegisterPenkumLuhkumDTO> {

	Page<RegisterPenkumLuhkumDTO> findAll(String start, String end, String stringJenisKegiatan, Integer pages, Integer sizes);

	Page<RegisterPenkumLuhkumDTO> findBySearching(String start, String end, String stringJenisKegiatan, String value, Integer pages, Integer sizes);

	List<Integer[]> countProgramPenkumLuhkum(String startDate, String endDate);

}
