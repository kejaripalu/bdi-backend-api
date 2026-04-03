package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumDTO;
import id.go.kejaripalu.bdi.mapper.RegisterPenkumLuhkumMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.util.JenisKegiatanPenkumLuhkum;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterPenkumLuhkumRepository;
import id.go.kejaripalu.bdi.service.RegisterPenkumLuhkumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterPenkumLuhkumServiceImpl implements RegisterPenkumLuhkumService {
	
	private RegisterPenkumLuhkumRepository repository;

	@Override
	@Transactional
	public RegisterPenkumLuhkumDTO create(RegisterPenkumLuhkumDTO request) {
		RegisterPenkumLuhkumDTO penkumLuhkum =
				RegisterPenkumLuhkumMapper.INSTANCE.toDTO(
						Objects.requireNonNull(repository.save(
								Objects.requireNonNull(RegisterPenkumLuhkumMapper.INSTANCE.toEntity(request)))));
		
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Penkum/Luhkum!!!");
		return penkumLuhkum;
	}

	@Override
	@Transactional
	public RegisterPenkumLuhkumDTO update(String ids, RegisterPenkumLuhkumDTO request) {
		RegisterPenkumLuhkum penkumLuhkum = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		penkumLuhkum.setJenisKegiatan(request.jenisKegiatan());
		penkumLuhkum.setProgram(request.program());
		penkumLuhkum.setNomorSuratPerintah(request.nomorSuratPerintah());
		penkumLuhkum.setTanggalSuratPerintah(request.tanggalSuratPerintah());
		penkumLuhkum.setSasaranKegiatan(request.sasaranKegiatan());
		penkumLuhkum.setTanggalKegiatan(request.tanggalKegiatan());
		penkumLuhkum.setTempat(request.tempat());
		penkumLuhkum.setMateri(request.materi());
		penkumLuhkum.setJumlahPeserta(request.jumlahPeserta());
		penkumLuhkum.setKeterangan(request.keterangan());
		penkumLuhkum.setUrlFoto1(request.urlFoto1());
		penkumLuhkum.setUrlFoto2(request.urlFoto2());
		penkumLuhkum.setUrlFoto3(request.urlFoto3());
		penkumLuhkum.setUrlFoto4(request.urlFoto4());

		RegisterPenkumLuhkumDTO registerPenkumLuhkum =
				RegisterPenkumLuhkumMapper.INSTANCE.toDTO(
						Objects.requireNonNull(repository.save(penkumLuhkum)));

		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Penkum/Luhkum!!!");
		return registerPenkumLuhkum;
	}

	@Override
	@Transactional
	public Page<RegisterPenkumLuhkumDTO> findAll(String start, String end, String stringJenisKegiatan, Integer pages,
			Integer sizes) {
		JenisKegiatanPenkumLuhkum jenisKegiatan = getJenisKegiatan(stringJenisKegiatan);

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

		return repository.findAllPenkumLuhkum(startDate, endDate, jenisKegiatan, pageRequest)
				.map(RegisterPenkumLuhkumMapper.INSTANCE::toDTO);
	}

	@Override
	@Transactional
	public Page<RegisterPenkumLuhkumDTO> findBySearching(String start, String end, String stringJenisKegiatan,
			String value, Integer pages, Integer sizes) {
		JenisKegiatanPenkumLuhkum jenisKegiatan = getJenisKegiatan(stringJenisKegiatan);

		log.info("🔍 Value for searching: {}", value);
		if (value.isBlank()) {
			log.error("💀 Isi text pencarian kosong...");
			return null;
		}

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

		return repository.findBySearching(startDate, endDate, jenisKegiatan, value, pageRequest)
				.map(RegisterPenkumLuhkumMapper.INSTANCE::toDTO);
	}

	@Override
	@Transactional
	public RegisterPenkumLuhkumDTO findByIds(String ids) {
		RegisterPenkumLuhkum penkumLuhkum = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        return RegisterPenkumLuhkumMapper.INSTANCE.toDTO(penkumLuhkum);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterPenkumLuhkum penkumLuhkum = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

		penkumLuhkum.setDeleted(true);
		penkumLuhkum.setNomorSuratPerintah(penkumLuhkum.getIds() + " | " + penkumLuhkum.getNomorSuratPerintah());
		repository.save(penkumLuhkum);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register Penkum Luhkum!!!");
	}
	
	private JenisKegiatanPenkumLuhkum getJenisKegiatan(String stringJenisKegiatan) {
       return switch (stringJenisKegiatan) {
			case "PENERANGAN_HUKUM" -> JenisKegiatanPenkumLuhkum.PENERANGAN_HUKUM;
			case "PENYULUHAN_HUKUM" -> JenisKegiatanPenkumLuhkum.PENYULUHAN_HUKUM;
			default -> null;
		};
	}

	@Override
	public List<Integer[]> countProgramPenkumLuhkum(String start, String end) {
		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		return repository.countProgramPenkumLuhkum(startDate, endDate);
	}

}
