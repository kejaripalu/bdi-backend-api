package id.go.kejaripalu.bdi.service;

import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DataPetaService extends CrudGenericService<DataPetaDTO> {

    Page<DataPetaDTO> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes);

    Page<DataPetaDTO> findBySearching(String startDate, String endDate, String stringBidangDirektorat, String value, Integer pages, Integer sizes);

    List<DataPetaDTO> findByBidangDirektoratAndTanggalBetween(String stringBidangDirektorat, String startDate, String endDate);

}
