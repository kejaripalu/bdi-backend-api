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
import id.go.kejaripalu.bdi.domain.SuratMasuk;
import id.go.kejaripalu.bdi.dto.SuratMasukCreateRequest;
import id.go.kejaripalu.bdi.dto.SuratMasukResponse;
import id.go.kejaripalu.bdi.dto.SuratMasukUpdateRequest;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.SuratMasukRepository;
import id.go.kejaripalu.bdi.service.SuratMasukService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SuratMasukServiceImpl implements SuratMasukService {

	private SuratMasukRepository suratMasukRepository;
	
	@Override
	@Transactional
	public void createSuratMasuk(SuratMasukCreateRequest request) {
		SuratMasuk suratMasuk = new SuratMasuk();
		suratMasuk.setWaktuPenerimaanSurat(request.getWaktuPenerimaanSurat());
		suratMasuk.setAsal(request.getAsal());
		suratMasuk.setPerihal(request.getPerihal());
		suratMasuk.setTanggalSurat(request.getTanggalSurat());
		suratMasuk.setNomorSurat(request.getNomorSurat());
		suratMasuk.setJenisSurat(request.getJenisSurat());
		suratMasuk.setIsiDisposisi(request.getIsiDisposisi());
		suratMasuk.setTindakLanjutDisposisi(request.getTindakLanjutDisposisi());
		suratMasuk.setKeterangan(request.getKeterangan());
		
		log.info("Surat Masuk Request: " + suratMasuk);
		suratMasukRepository.save(suratMasuk);
		log.info("Saved Surat Masuk: " + suratMasuk);
	}

	@Override
	@Transactional
	public void updateSuratMasuk(String id, SuratMasukUpdateRequest request) {
		SuratMasuk suratMasuk = suratMasukRepository.findById(id)
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
		
		log.info("Surat Masuk Request: " + suratMasuk);
		suratMasukRepository.save(suratMasuk);
		log.info("Updated Surat Masuk: " + suratMasuk);
	}

	@Override
	@Transactional
	public Page<SuratMasuk> findSuratMasuk(String start, String end, String stringJenisSurat, 
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
		Page<SuratMasuk> pagesSuratMasuk = suratMasukRepository.findSuratMasukAll(
				startDate, endDate, jenisSurat, pageRequest);
		
		return pagesSuratMasuk;
	}
	
	@Override
	@Transactional
	public SuratMasukResponse findSuratMasukById(String id) {
		SuratMasuk suratMasuk = suratMasukRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID Not Found!!!"));
		
		SuratMasukResponse response = new SuratMasukResponse();
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
		
		return response;
	}

	@Override
	@Transactional
	public void deleteSuratMasuk(String id) {
		SuratMasuk suratMasuk = suratMasukRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID Not Found!!!"));
		suratMasuk.setDeleted(Boolean.TRUE);
		suratMasukRepository.save(suratMasuk);
		log.info("Soft Delete: " + suratMasuk);
	}
	
}
