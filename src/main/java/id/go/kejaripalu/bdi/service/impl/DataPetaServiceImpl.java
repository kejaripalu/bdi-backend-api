package id.go.kejaripalu.bdi.service.impl;

import id.go.kejaripalu.bdi.domain.DataPeta;
import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.mapper.DataPetaMapper;
import id.go.kejaripalu.bdi.repository.DataPetaRepository;
import id.go.kejaripalu.bdi.service.DataPetaService;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.GetBidangDirektorat;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class DataPetaServiceImpl implements DataPetaService {

    private final DataPetaRepository repository;

    @Override
    public Page<DataPetaDTO> findAll(String startDate, String endDate, String stringBidangDirektorat, Integer pages, Integer sizes) {

        BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
        Date start = ParserDateUtil.start(startDate);
        Date end = ParserDateUtil.end(endDate);
        Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findAll(start, end, bidangDirektorat, pageRequest)
                .map(DataPetaMapper.INSTANCE::toDTO);
    }

    @Override
    public Page<DataPetaDTO> findBySearching(String startDate, String endDate, String stringBidangDirektorat, String value, Integer pages, Integer sizes) {

        log.info("\uD83D\uDD0E Value for searching: {}", value);
        if (value.isBlank()) {
            log.error("💀 Isi text pencarian kosong...");
            return null;
        }

        BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
        Date start = ParserDateUtil.start(startDate);
        Date end = ParserDateUtil.end(endDate);
        Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findBySearching(start, end, bidangDirektorat, value, pageRequest)
                .map(DataPetaMapper.INSTANCE::toDTO);
    }

    @Override
    public DataPetaDTO create(DataPetaDTO request) {
        DataPetaDTO dataPetaDTO =
                DataPetaMapper.INSTANCE.toDTO(Objects.requireNonNull(repository.save(
                        Objects.requireNonNull(DataPetaMapper.INSTANCE.toEntity(request)))));

        log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Data Peta!!!");
        return dataPetaDTO;
    }

    @Override
    public DataPetaDTO update(String ids, DataPetaDTO request) {
        DataPeta dataPeta = repository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        dataPeta.setBidangDirektorat(request.bidangDirektorat());
        dataPeta.setSektor(request.sektor());
        dataPeta.setTanggal(request.tanggal());
        dataPeta.setLokasi(request.lokasi());
        dataPeta.setLatitude(request.latitude());
        dataPeta.setLongitude(request.longitude());
        dataPeta.setSiapa(request.siapa());
        dataPeta.setApa(request.apa());
        dataPeta.setMengapa(request.mengapa());
        dataPeta.setBagaimana(request.bagaimana());
        dataPeta.setKeterangan(request.keterangan());

        DataPetaDTO dataPetaDTO =
                DataPetaMapper.INSTANCE.toDTO(Objects.requireNonNull(repository.save(dataPeta)));
        log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Data Peta!!!");
        return dataPetaDTO;
    }

    @Override
    public DataPetaDTO findByIds(String ids) {
        DataPeta dataPeta = repository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        return DataPetaMapper.INSTANCE.toDTO(dataPeta);
    }

    @Override
    public void delete(String ids) {
        DataPeta dataPeta = repository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        dataPeta.setDeleted(true);
        repository.save(dataPeta);
        log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Data Peta!!!");
    }

    @Override
    public List<DataPetaDTO> findByBidangDirektoratAndTanggalBetween(String stringBidangDirektorat, String startDate, String endDate) {
        BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
        Date start = ParserDateUtil.start(startDate);
        Date end = ParserDateUtil.end(endDate);

        return repository.findByBidangDirektoratAndTanggalBetween(bidangDirektorat, start, end)
                .stream()
                .map(DataPetaMapper.INSTANCE::toDTO)
                .toList();
    }
}
