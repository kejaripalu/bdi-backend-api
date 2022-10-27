package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterArsip;
import id.go.kejaripalu.bdi.dto.RegisterArsipRequest;
import id.go.kejaripalu.bdi.dto.RegisterArsipResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterArsipRepository;
import id.go.kejaripalu.bdi.service.RegisterArsipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterArsipServiceImpl implements RegisterArsipService {
	
	private RegisterArsipRepository arsipRepository;

	@Override
	@Transactional
	public void create(RegisterArsipRequest request) {
		RegisterArsip arsip = new RegisterArsip();
		arsip.setTanggalPenerimaanArsip(request.getTanggalPenerimaanArsip());
		arsip.setJamPenerimaanArsip(request.getJamPenerimaanArsip());
		arsip.setDiterimaDari(request.getDiterimaDari());
		arsip.setNomorSurat(request.getNomorSurat());
		arsip.setTanggalSurat(request.getTanggalSurat());
		arsip.setPerihal(request.getPerihal());
		arsip.setLampiran(request.getLampiran());
		arsip.setKodePenyimpanan(request.getKodePenyimpanan());
		arsip.setKeterangan(request.getKeterangan());
		arsip.setUrlFile(request.getUrlFile());
		
		log.info("Register Arsip Request: " + arsip);
		arsipRepository.save(arsip);
		log.info("Saved Register Arsip: " + arsip);
	}

	@Override
	@Transactional
	public void update(String id, RegisterArsipRequest request) {
		RegisterArsip arsip = arsipRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		arsip.setTanggalPenerimaanArsip(
				request.getTanggalPenerimaanArsip() == null ?
						arsip.getTanggalPenerimaanArsip() : request.getTanggalPenerimaanArsip());
		arsip.setJamPenerimaanArsip(
				request.getJamPenerimaanArsip() == null ?
						arsip.getJamPenerimaanArsip() : request.getJamPenerimaanArsip());
		arsip.setDiterimaDari(
				request.getDiterimaDari() == null || request.getDiterimaDari().isBlank() ?
						arsip.getDiterimaDari() : request.getDiterimaDari());
		arsip.setNomorSurat(
				request.getNomorSurat() == null || request.getNomorSurat().isBlank() ?
						arsip.getNomorSurat() : request.getNomorSurat());
		arsip.setTanggalSurat(
				request.getTanggalSurat() == null ? 
						arsip.getTanggalSurat() : request.getTanggalSurat());
		arsip.setPerihal(
				request.getPerihal() == null || request.getPerihal().isBlank() ?
						arsip.getPerihal() : request.getPerihal());
		arsip.setLampiran(request.getLampiran());
		arsip.setKodePenyimpanan(request.getKodePenyimpanan());
		arsip.setKeterangan(request.getKeterangan());
		arsip.setUrlFile(request.getUrlFile());
		
		log.info("Register Arsip Request: " + arsip);
		arsipRepository.save(arsip);
		log.info("Updated Register Arsip: " + arsip);
	}

	@Override
	@Transactional
	public Page<RegisterArsip> findAll(String start, String end, Integer pages, Integer sizes) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterArsip> pagesArsip = arsipRepository.findAll(
				startDate, endDate, pageRequest);
		
		return pagesArsip;
	}

	@Override
	@Transactional
	public Page<RegisterArsip> findBySearching(
			String start, String end, String value, Integer pages, Integer sizes) {
		log.info("Value : " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.error("Isi text pencarian kosong...");
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
		Page<RegisterArsip> pageArsip = arsipRepository.findBySearching(
				startDate, endDate, value, pageRequest);
		
		return pageArsip;
	}

	@Override
	@Transactional
	public RegisterArsipResponse findById(String id) {
		RegisterArsip arsip = arsipRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterArsipResponse response = new RegisterArsipResponse();
		response.setId(arsip.getId());
		response.setTanggalPenerimaanArsip(arsip.getTanggalPenerimaanArsip());
		response.setJamPenerimaanArsip(arsip.getJamPenerimaanArsip());
		response.setDiterimaDari(arsip.getDiterimaDari());
		response.setNomorSurat(arsip.getNomorSurat());
		response.setTanggalSurat(arsip.getTanggalSurat());
		response.setPerihal(arsip.getPerihal());
		response.setLampiran(arsip.getLampiran());
		response.setKodePenyimpanan(arsip.getKodePenyimpanan());
		response.setKeterangan(arsip.getKeterangan());
		response.setUrlFile(arsip.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void delete(String id) {
		RegisterArsip arsip = arsipRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		arsip.setDeleted(Boolean.TRUE);
		arsip.setNomorSurat(arsip.getId() + " | " + arsip.getNomorSurat());
		arsipRepository.save(arsip);
		log.info("Soft Delete: " + arsip);
	}

}
