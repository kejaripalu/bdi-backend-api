package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;
import java.util.Objects;

import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenDTO;
import id.go.kejaripalu.bdi.mapper.RegisterOperasiIntelijenMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import id.go.kejaripalu.bdi.util.GetBidangDirektorat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterOperasiIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterOperasiIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterOperasiIntelijenServiceImpl implements RegisterOperasiIntelijenService {
	
	private final RegisterOperasiIntelijenRepository repository;

	@Override
	@Transactional
	public RegisterOperasiIntelijenDTO create(RegisterOperasiIntelijenDTO request) {
		RegisterOperasiIntelijenDTO opsin =
                RegisterOperasiIntelijenMapper.INSTANCE.toDTO(
                        Objects.requireNonNull(repository.save(
                                Objects.requireNonNull(RegisterOperasiIntelijenMapper.INSTANCE.toEntity(request)))));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Operasi Kegiatan Intelijen!!!");
        return opsin;
	}

	@Override
	@Transactional
	public RegisterOperasiIntelijenDTO update(String ids, RegisterOperasiIntelijenDTO request) {
		RegisterOperasiIntelijen opsin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		opsin.setBidangDirektorat(request.bidangDirektorat());
		opsin.setSektor(request.sektor());
		opsin.setTanggal(request.tanggal());
		opsin.setNomor(request.nomor());
		opsin.setPerihal(request.perihal());
		opsin.setNamaPetugasPelaksana(request.namaPetugasPelaksana());
		opsin.setHasilPelaksanaanOperasi(request.hasilPelaksanaanOperasi());
		opsin.setKeterangan(request.keterangan());
		opsin.setUrlFile(request.urlFile());
		
		RegisterOperasiIntelijenDTO operasiIntelijen =
                RegisterOperasiIntelijenMapper.INSTANCE.toDTO(Objects.requireNonNull(repository.save(opsin)));

        log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Operasi Intelijen!!!");
        return operasiIntelijen;
	}

	@Override
	@Transactional
	public Page<RegisterOperasiIntelijenDTO> findAll(String start, String end, String stringBidangDirektorat,
			Integer pages, Integer sizes) {

        BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findAllOpsin(
                startDate, endDate, bidangDirektorat, pageRequest);
	}

	@Override
	@Transactional
	public Page<RegisterOperasiIntelijenDTO> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages, Integer sizes) {

        log.info("\uD83D\uDD0E Value : {}", value);
		if (value.isBlank()) {
			log.warn("💀 Isi text pencarian kosong...");
			return null;
		}

        BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findBySearching(
                startDate, endDate, bidangDirektorat, value, pageRequest);
	}

	@Override
	@Transactional
	public RegisterOperasiIntelijenDTO findByIds(String ids) {
		RegisterOperasiIntelijen opsin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

		return RegisterOperasiIntelijenMapper.INSTANCE.toDTO(opsin);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterOperasiIntelijen opsin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		opsin.setDeleted(true);
		opsin.setNomor(opsin.getIds() + " | " + opsin.getNomor());
		repository.save(opsin);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register Opsin!!!");
	}
}
