package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;
import java.util.Objects;

import id.go.kejaripalu.bdi.dto.RegisterSuratMasukDTO;
import id.go.kejaripalu.bdi.mapper.RegisterSuratMasukMapper;
import id.go.kejaripalu.bdi.service.RegisterSuratService;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.util.JenisSurat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterSuratMasukRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterSuratMasukServiceImpl implements RegisterSuratService<RegisterSuratMasukDTO> {

	private final RegisterSuratMasukRepository repository;
	
	@Override
	@Transactional
	public RegisterSuratMasukDTO create(RegisterSuratMasukDTO request) {

		RegisterSuratMasukDTO suratMasuk =
                RegisterSuratMasukMapper.INSTANCE.toDTO(
                        Objects.requireNonNull(repository.save(
                                Objects.requireNonNull(RegisterSuratMasukMapper.INSTANCE.toEntity(request)))));

        log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Surat Masuk!!!");
        return suratMasuk;
	}

	@Override
	@Transactional
	public RegisterSuratMasukDTO update(String ids, RegisterSuratMasukDTO request) {
		RegisterSuratMasuk suratMasuk = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratMasuk.setTanggalPenerimaanSurat(request.tanggalPenerimaanSurat());
		suratMasuk.setJamPenerimaanSurat(request.jamPenerimaanSurat());
		suratMasuk.setNomorSurat(request.nomorSurat());
		suratMasuk.setTanggalSurat(request.tanggalSurat());
		suratMasuk.setAsal(request.asal());
		suratMasuk.setPerihal(request.perihal());
		suratMasuk.setIsiDisposisi(request.isiDisposisi());
		suratMasuk.setTindakLanjutDisposisi(request.tindakLanjutDisposisi());
		suratMasuk.setKeterangan(request.keterangan());
		suratMasuk.setUrlFile(request.urlFile());
		suratMasuk.setJenisSurat(request.jenisSurat());

		RegisterSuratMasukDTO registerSuratMasukDTO =
                RegisterSuratMasukMapper.INSTANCE.toDTO(Objects.requireNonNull(repository.save(suratMasuk)));

        log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Surat Masuk!!!");
        return registerSuratMasukDTO;
	}

	@Override
	@Transactional
	public RegisterSuratMasukDTO findByIds(String ids) {
		RegisterSuratMasuk suratMasuk = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		return RegisterSuratMasukMapper.INSTANCE.toDTO(suratMasuk);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterSuratMasuk suratMasuk = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratMasuk.setDeleted(true);
		suratMasuk.setNomorSurat(suratMasuk.getIds() + " | " + suratMasuk.getNomorSurat());
		repository.save(suratMasuk);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Surat Masuk!!!");
	}

	@Override
	@Transactional
	public Page<RegisterSuratMasukDTO> findAll(String start, String end, String stringJenisSurat, Integer pages,
			Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

		return repository.findSuratMasukAll(startDate, endDate, jenisSurat, pageRequest)
				.map(RegisterSuratMasukMapper.INSTANCE::toDTO);
	}

	@Override
	@Transactional
	public Page<RegisterSuratMasukDTO> findBySearching(String start, String end, String stringJenisSurat, String value,
			Integer pages, Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}

		log.info("🔍 Value for searching: {}", value);
		if (value.isBlank()) {
			log.error("💀 Isi text pencarian kosong...");
			return null;
		}

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

		return repository.findSuratMasukBySearching(startDate, endDate, value, jenisSurat, pageRequest)
				.map(RegisterSuratMasukMapper.INSTANCE::toDTO);
	}
}
