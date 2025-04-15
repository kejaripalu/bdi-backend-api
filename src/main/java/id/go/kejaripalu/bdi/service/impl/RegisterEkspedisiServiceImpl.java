package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.util.JenisSurat;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiRequest;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterEkspedisiRepository;
import id.go.kejaripalu.bdi.service.RegisterEkspedisiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterEkspedisiServiceImpl implements RegisterEkspedisiService {

	private final RegisterEkspedisiRepository ekspedisiRepository;
	
	@Override
	@Transactional
	public void createEkspedisi(RegisterEkspedisiRequest request) {
		RegisterEkspedisi ekspedisi = new RegisterEkspedisi();
		ekspedisi.setNomorSurat(request.getNomorSurat());
		ekspedisi.setTanggalSurat(request.getTanggalSurat());
		ekspedisi.setKepada(request.getKepada());
		ekspedisi.setPerihal(request.getPerihal());
		ekspedisi.setLampiran(request.getLampiran());
		ekspedisi.setTanggalTandaTerima(request.getTanggalTandaTerima());
		ekspedisi.setJamTandaTerima(request.getJamTandaTerima());
		ekspedisi.setNamaDanParaf(request.getNamaDanParaf());
		ekspedisi.setJenisSurat(request.getJenisSurat());
		ekspedisi.setKeterangan(request.getKeterangan());
		ekspedisi.setUrlFile(request.getUrlFile());
		
		ekspedisiRepository.save(ekspedisi);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Surat Masuk!!!");
	}

	@Override
	@Transactional
	public void updateEkspedisi(String ids, RegisterEkspedisiRequest request) {
		RegisterEkspedisi ekspedisi = ekspedisiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		ekspedisi.setNomorSurat(
				request.getNomorSurat() == null || request.getNomorSurat().isBlank() ?
						ekspedisi.getNomorSurat() : request.getNomorSurat());
		ekspedisi.setTanggalSurat(
				request.getTanggalSurat() == null ? 
						ekspedisi.getTanggalSurat() : request.getTanggalSurat());
		ekspedisi.setKepada(
				request.getKepada() == null || request.getKepada().isBlank() ?
						ekspedisi.getKepada() : request.getKepada());
		ekspedisi.setPerihal(
				request.getPerihal() == null || request.getPerihal().isBlank() ?
						ekspedisi.getPerihal() : request.getPerihal());
		ekspedisi.setLampiran(request.getLampiran());
		ekspedisi.setTanggalTandaTerima(
				request.getTanggalTandaTerima() == null ? 
						ekspedisi.getTanggalTandaTerima() : request.getTanggalTandaTerima());
		ekspedisi.setJamTandaTerima(
				request.getJamTandaTerima() == null ? 
						ekspedisi.getJamTandaTerima() : request.getJamTandaTerima());
		ekspedisi.setNamaDanParaf(request.getNamaDanParaf());
		ekspedisi.setKeterangan(request.getKeterangan());
		ekspedisi.setUrlFile(request.getUrlFile());
		ekspedisi.setJenisSurat(
				request.getJenisSurat() == null ?
						ekspedisi.getJenisSurat() : request.getJenisSurat());
		
		ekspedisiRepository.save(ekspedisi);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Ekspedisi!!!");
	}

	@Override
	@Transactional
	public Page<RegisterEkspedisi> findEkspedisi(String start, String end, String stringJenisSurat, 
			Integer pages, Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterEkspedisi> pagesEkspedisi = ekspedisiRepository.findEkspedisiAll(
				startDate, endDate, jenisSurat, pageRequest);
		
		return pagesEkspedisi;
	}
	
	@Override
	@Transactional
	public RegisterEkspedisiResponse findEkspedisiByIds(String ids) {
		RegisterEkspedisi ekspedisi = ekspedisiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterEkspedisiResponse response = new RegisterEkspedisiResponse();
		response.setIds(ekspedisi.getIds());
		response.setNomorSurat(ekspedisi.getNomorSurat());
		response.setTanggalSurat(ekspedisi.getTanggalSurat());
		response.setKepada(ekspedisi.getKepada());
		response.setPerihal(ekspedisi.getPerihal());
		response.setLampiran(ekspedisi.getLampiran());
		response.setTanggalTandaTerima(ekspedisi.getTanggalTandaTerima());
		response.setJamTandaTerima(ekspedisi.getJamTandaTerima());
		response.setNamaDanParaf(ekspedisi.getNamaDanParaf());
		response.setKeterangan(ekspedisi.getKeterangan());
		response.setUrlFile(ekspedisi.getUrlFile());
		response.setJenisSurat(ekspedisi.getJenisSurat());
		
		return response;
	}

	@Override
	@Transactional
	public void deleteEkspedisi(String ids) {
		RegisterEkspedisi ekspedisi = ekspedisiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		ekspedisi.setDeleted(true);
		ekspedisi.setNomorSurat(ekspedisi.getIds() + " | " + ekspedisi.getNomorSurat());
		ekspedisiRepository.save(ekspedisi);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Ekspedisi!!!");
	}

	@Override
	@Transactional
	public Page<RegisterEkspedisi> findEkspedisiBySearching(
			String start, String end, String value, String stringJenisSurat, Integer pages, Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}
		log.info("🔎 Value for searching: " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.warn("💀 Isi text pencarian kosong...");
			return null;
		}
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterEkspedisi> pagesEkspedisi = ekspedisiRepository.findEkspedisiBySearching(
				startDate, endDate, value, jenisSurat, pageRequest);
		return pagesEkspedisi;
	}
	
}
