package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.JenisSurat;
import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukCreateRequest;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukResponse;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukUpdateRequest;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterSuratMasukRepository;
import id.go.kejaripalu.bdi.service.RegisterSuratMasukService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterSuratMasukServiceImpl implements RegisterSuratMasukService {

	private RegisterSuratMasukRepository suratMasukRepository;
	
	@Override
	@Transactional
	public void createSuratMasuk(RegisterSuratMasukCreateRequest request) {
		RegisterSuratMasuk suratMasuk = new RegisterSuratMasuk();
		suratMasuk.setWaktuPenerimaanSurat(request.getWaktuPenerimaanSurat());
		suratMasuk.setAsal(request.getAsal());
		suratMasuk.setPerihal(request.getPerihal());
		suratMasuk.setTanggalSurat(request.getTanggalSurat());
		suratMasuk.setNomorSurat(request.getNomorSurat());
		suratMasuk.setJenisSurat(request.getJenisSurat());
		suratMasuk.setIsiDisposisi(request.getIsiDisposisi());
		suratMasuk.setTindakLanjutDisposisi(request.getTindakLanjutDisposisi());
		suratMasuk.setKeterangan(request.getKeterangan());
		suratMasuk.setUrlFile(request.getUrlFile());
		
		log.info("Surat Masuk Request: " + suratMasuk);
		suratMasukRepository.save(suratMasuk);
		log.info("Saved Surat Masuk: " + suratMasuk);
	}

	@Override
	@Transactional
	public void updateSuratMasuk(String id, RegisterSuratMasukUpdateRequest request) {
		RegisterSuratMasuk suratMasuk = suratMasukRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID Not Found!!!"));
		suratMasuk.setWaktuPenerimaanSurat(
				request.getWaktuPenerimaanSurat() == null ? 
						suratMasuk.getWaktuPenerimaanSurat() : request.getWaktuPenerimaanSurat());
		suratMasuk.setAsal(
				request.getAsal() == null || request.getAsal().isBlank() ?
						suratMasuk.getAsal() : request.getAsal());
		suratMasuk.setPerihal(
				request.getPerihal() == null || request.getPerihal().isBlank() ?
						suratMasuk.getPerihal() : request.getPerihal());
		suratMasuk.setTanggalSurat(
				request.getTanggalSurat() == null ? 
						suratMasuk.getTanggalSurat() : request.getTanggalSurat());
		suratMasuk.setNomorSurat(
				request.getNomorSurat() == null || request.getNomorSurat().isBlank() ?
						suratMasuk.getNomorSurat() : request.getNomorSurat());
		suratMasuk.setJenisSurat(
				request.getJenisSurat() == null ?
						suratMasuk.getJenisSurat() : request.getJenisSurat());
		suratMasuk.setIsiDisposisi(request.getIsiDisposisi());
		suratMasuk.setTindakLanjutDisposisi(request.getTindakLanjutDisposisi());
		suratMasuk.setKeterangan(request.getKeterangan());
		suratMasuk.setUrlFile(request.getUrlFile());
		
		log.info("Surat Masuk Request: " + suratMasuk);
		suratMasukRepository.save(suratMasuk);
		log.info("Updated Surat Masuk: " + suratMasuk);
	}

	@Override
	@Transactional
	public Page<RegisterSuratMasuk> findSuratMasuk(String start, String end, String stringJenisSurat, 
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
			log.error(e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterSuratMasuk> pagesSuratMasuk = suratMasukRepository.findSuratMasukAll(
				startDate, endDate, jenisSurat, pageRequest);
		
		return pagesSuratMasuk;
	}
	
	@Override
	@Transactional
	public RegisterSuratMasukResponse findSuratMasukById(String id) {
		RegisterSuratMasuk suratMasuk = suratMasukRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID Not Found!!!"));
		
		RegisterSuratMasukResponse response = new RegisterSuratMasukResponse();
		response.setId(suratMasuk.getId());
		response.setWaktuPenerimaanSurat(suratMasuk.getWaktuPenerimaanSurat());
		response.setAsal(suratMasuk.getAsal());
		response.setNomorSurat(suratMasuk.getNomorSurat());
		response.setPerihal(suratMasuk.getPerihal());
		response.setTanggalSurat(suratMasuk.getTanggalSurat());
		response.setJenisSurat(suratMasuk.getJenisSurat());
		response.setIsiDisposisi(suratMasuk.getIsiDisposisi());
		response.setTindakLanjutDisposisi(suratMasuk.getTindakLanjutDisposisi());
		response.setKeterangan(suratMasuk.getKeterangan());
		response.setUrlFile(suratMasuk.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void deleteSuratMasuk(String id) {
		RegisterSuratMasuk suratMasuk = suratMasukRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID Not Found!!!"));
		suratMasuk.setDeleted(Boolean.TRUE);
		suratMasukRepository.save(suratMasuk);
		log.info("Soft Delete: " + suratMasuk);
	}
	
}
