package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraDTO;
import id.go.kejaripalu.bdi.mapper.RegisterKegiatanIntelijenPamstraMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterKegiatanIntelijenPamstraRepository;
import id.go.kejaripalu.bdi.service.RegisterKegiatanIntelijenPamstraService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterKegiatanIntelijenPamstraServiceImpl implements RegisterKegiatanIntelijenPamstraService<RegisterKegiatanIntelijenPamstraDTO> {
	
	private final RegisterKegiatanIntelijenPamstraRepository repository;

	@Override
	@Transactional
	public RegisterKegiatanIntelijenPamstraDTO create(RegisterKegiatanIntelijenPamstraDTO request) {
		RegisterKegiatanIntelijenPamstraDTO pamstraDTO =
                RegisterKegiatanIntelijenPamstraMapper.INSTANCE.toDTO(
                        repository.save(RegisterKegiatanIntelijenPamstraMapper.INSTANCE.toEntity(request)));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ)  Register Kegiatan Pamstra Intelijen!!!");
        return pamstraDTO;
	}

	@Override
	@Transactional
	public RegisterKegiatanIntelijenPamstraDTO update(String ids, RegisterKegiatanIntelijenPamstraDTO request) {
		RegisterKegiatanIntelijenPamstra kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        kegiatanIntelijen.setSektor(request.sektor());
		kegiatanIntelijen.setNamaKegiatan(request.namaKegiatan());
		kegiatanIntelijen.setSumberDana(request.sumberDana());
		kegiatanIntelijen.setInstansi(request.instansi());
		kegiatanIntelijen.setPaguAnggaran(request.paguAnggaran());
		kegiatanIntelijen.setNomorSuratPermohonan(request.nomorSuratPermohonan());
		kegiatanIntelijen.setTanggalSuratPermohonan(request.tanggalSuratPermohonan());
		kegiatanIntelijen.setTempatPemaparan(request.tempatPemaparan());
		kegiatanIntelijen.setTanggalPemaparan(request.tanggalPemaparan());
		kegiatanIntelijen.setTelaahanIntelijen(request.telaahanIntelijen());
		kegiatanIntelijen.setTindakLanjut(request.tindakLanjut());
		kegiatanIntelijen.setTindakLanjutKeterangan(request.tindakLanjutKeterangan());
		kegiatanIntelijen.setNomorSprintWalpam(request.nomorSprintWalpam());
		kegiatanIntelijen.setTanggalSprintWalpam(request.tanggalSprintWalpam());
		kegiatanIntelijen.setNamaPetugasPelaksana(request.namaPetugasPelaksana());
		kegiatanIntelijen.setNilaiKontrak(request.nilaiKontrak());
		kegiatanIntelijen.setHasilPelaksanaan(request.hasilPelaksanaan());
		kegiatanIntelijen.setHasilPelaksanaanKeterangan(request.hasilPelaksanaanKeterangan());
		kegiatanIntelijen.setNomorKertasKerja(request.nomorKertasKerja());
		kegiatanIntelijen.setTanggalKertasKerja(request.tanggalKertasKerja());
		kegiatanIntelijen.setKeterangan(request.keterangan());
		kegiatanIntelijen.setUrlFile(request.urlFile());

        RegisterKegiatanIntelijenPamstraDTO pamstraDTO =
                RegisterKegiatanIntelijenPamstraMapper.INSTANCE.toDTO(repository.save(kegiatanIntelijen));

		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Intelijen Pamstra!!!");
        return pamstraDTO;
	}

	@Override
	@Transactional
	public Page<RegisterKegiatanIntelijenPamstraDTO> findAll(String start, String end, Integer pages, Integer sizes) {

        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findAllKegiatan(startDate, endDate, pageRequest);
	}

	@Override
	@Transactional
	public Page<RegisterKegiatanIntelijenPamstraDTO> findBySearching(String start, String end, String value, Integer pages,
			Integer sizes) {

        log.info("\uD83D\uDD0E Value for searching: {}", value);
		if (value.isBlank()) {
			log.warn("💀 Isi text pencarian kosong...");
			return null;
		}

        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findBySearching(startDate, endDate, value, pageRequest);
	}

	@Override
	@Transactional
	public RegisterKegiatanIntelijenPamstraDTO findByIds(String ids) {
		RegisterKegiatanIntelijenPamstra kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		return RegisterKegiatanIntelijenPamstraMapper.INSTANCE.toDTO(kegiatanIntelijen);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterKegiatanIntelijenPamstra kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		kegiatanIntelijen.setDeleted(true);
		kegiatanIntelijen.setNomorSprintWalpam(kegiatanIntelijen.getIds() + " | " + kegiatanIntelijen.getNomorSprintWalpam());
		kegiatanIntelijen.setNomorSuratPermohonan(kegiatanIntelijen.getIds() + " | " + kegiatanIntelijen.getNomorSuratPermohonan());
		kegiatanIntelijen.setNomorKertasKerja(kegiatanIntelijen.getIds() + " | " + kegiatanIntelijen.getNomorKertasKerja());
		repository.save(kegiatanIntelijen);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) RKI Pamstra!!!");
	}

}
