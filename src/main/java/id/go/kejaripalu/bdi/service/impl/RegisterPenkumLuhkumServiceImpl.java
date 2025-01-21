package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.domain.util.JenisKegiatanPenkumLuhkum;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumRequest;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumResponse;
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
	public void create(RegisterPenkumLuhkumRequest request) {
		RegisterPenkumLuhkum penkumLuhkum = new RegisterPenkumLuhkum();
		penkumLuhkum.setJenisKegiatan(request.getJenisKegiatan());
		penkumLuhkum.setProgram(request.getProgram());
		penkumLuhkum.setNomorSuratPerintah(request.getNomorSuratPerintah());
		penkumLuhkum.setSasaranKegiatan(request.getSasaranKegiatan());
		penkumLuhkum.setTanggalKegiatan(request.getTanggalKegiatan());
		penkumLuhkum.setMateri(request.getMateri());
		penkumLuhkum.setJumlahPeserta(request.getJumlahPeserta());
		penkumLuhkum.setKeterangan(request.getKeterangan());
		penkumLuhkum.setUrlFoto1(request.getUrlFoto1());
		penkumLuhkum.setUrlFoto2(request.getUrlFoto2());
		penkumLuhkum.setUrlFoto3(request.getUrlFoto3());
		penkumLuhkum.setUrlFoto4(request.getUrlFoto4());
		
		repository.save(penkumLuhkum);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Penkum/Luhkum!!!");
	}

	@Override
	@Transactional
	public void update(String ids, RegisterPenkumLuhkumRequest request) {
		RegisterPenkumLuhkum penkumLuhkum = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		penkumLuhkum.setJenisKegiatan(
				request.getJenisKegiatan() == null ?
						penkumLuhkum.getJenisKegiatan() : request.getJenisKegiatan());
		penkumLuhkum.setProgram(
				request.getProgram() == null ?
						penkumLuhkum.getProgram() : request.getProgram());
		penkumLuhkum.setNomorSuratPerintah(
				request.getNomorSuratPerintah() == null || request.getNomorSuratPerintah().isBlank() ?
						penkumLuhkum.getNomorSuratPerintah() : request.getNomorSuratPerintah());		
		penkumLuhkum.setSasaranKegiatan(
				request.getSasaranKegiatan() == null || request.getSasaranKegiatan().isBlank() ?
						penkumLuhkum.getSasaranKegiatan() : request.getSasaranKegiatan());
		penkumLuhkum.setTanggalKegiatan(
				request.getTanggalKegiatan() == null ?
						penkumLuhkum.getTanggalKegiatan() : request.getTanggalKegiatan());
		penkumLuhkum.setMateri(
				request.getMateri() == null || request.getMateri().isBlank() ? 
						penkumLuhkum.getMateri() : request.getMateri());
		penkumLuhkum.setJumlahPeserta(
				request.getJumlahPeserta() == null ?
						penkumLuhkum.getJumlahPeserta() : request.getJumlahPeserta());
		penkumLuhkum.setKeterangan(request.getKeterangan());
		penkumLuhkum.setUrlFoto1(request.getUrlFoto1());
		penkumLuhkum.setUrlFoto2(request.getUrlFoto2());
		penkumLuhkum.setUrlFoto3(request.getUrlFoto3());
		penkumLuhkum.setUrlFoto4(request.getUrlFoto4());
		
		repository.save(penkumLuhkum);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Penkum/Luhkum!!!");
	}

	@Override
	@Transactional
	public Page<RegisterPenkumLuhkum> findAll(String start, String end, String stringJenisPenkumLuhkum,
			Integer pages, Integer sizes) {
		
		JenisKegiatanPenkumLuhkum jenisPenkumLuhkum = getJenisPenkumLuhkum(stringJenisPenkumLuhkum);		
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterPenkumLuhkum> pagesPenkumLuhkum = repository.findAllPenkumLuhkum(
				startDate, endDate, jenisPenkumLuhkum, pageRequest);
		
		return pagesPenkumLuhkum;
	}

	@Override
	@Transactional
	public Page<RegisterPenkumLuhkum> findBySearching(String start, String end, 
			String stringJenisPenkumLuhkum, String value, Integer pages, Integer sizes) {
		
		log.info("🔎 Value for searching: " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.error("💀 Isi text pencarian kosong...");
		}

		JenisKegiatanPenkumLuhkum jenisPenkumLuhkum = getJenisPenkumLuhkum(stringJenisPenkumLuhkum);
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterPenkumLuhkum> pagesPenkumLuhkum = repository.findBySearching(
				startDate, endDate, jenisPenkumLuhkum, value, pageRequest);
		
		return pagesPenkumLuhkum;
	}

	@Override
	@Transactional
	public RegisterPenkumLuhkumResponse findByIds(String ids) {
		RegisterPenkumLuhkum penkumLuhkum = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterPenkumLuhkumResponse response = new RegisterPenkumLuhkumResponse();
		response.setIds(penkumLuhkum.getIds());
		response.setJenisKegiatan(penkumLuhkum.getJenisKegiatan());
		response.setProgram(penkumLuhkum.getProgram());
		response.setNomorSuratPerintah(penkumLuhkum.getNomorSuratPerintah());
		response.setSasaranKegiatan(penkumLuhkum.getSasaranKegiatan());
		response.setTanggalKegiatan(penkumLuhkum.getTanggalKegiatan());
		response.setMateri(penkumLuhkum.getMateri());
		response.setJumlahPeserta(penkumLuhkum.getJumlahPeserta());
		response.setKeterangan(penkumLuhkum.getKeterangan());
		response.setUrlFoto1(penkumLuhkum.getUrlFoto1());
		response.setUrlFoto2(penkumLuhkum.getUrlFoto2());
		response.setUrlFoto3(penkumLuhkum.getUrlFoto3());
		response.setUrlFoto4(penkumLuhkum.getUrlFoto4());
		
		return response;
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
	
	private JenisKegiatanPenkumLuhkum getJenisPenkumLuhkum(String stringJenisPenkumLuhkum) {
		JenisKegiatanPenkumLuhkum jenisPenkumLuhkum = null;
		if (stringJenisPenkumLuhkum.equals("PENERANGAN_HUKUM")) {
			jenisPenkumLuhkum = JenisKegiatanPenkumLuhkum.PENERANGAN_HUKUM;
		} else if (stringJenisPenkumLuhkum.equals("PENYULUHAN_HUKUM")) {
			jenisPenkumLuhkum = JenisKegiatanPenkumLuhkum.PENYULUHAN_HUKUM;			
		} else {
			return null;
		}
		return jenisPenkumLuhkum;
	}

}
