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
	
	private final RegisterArsipRepository arsipRepository;

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
		
		arsipRepository.save(arsip);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Register Arsip!!!");
	}

	@Override
	@Transactional
	public void update(String ids, RegisterArsipRequest request) {
		RegisterArsip arsip = arsipRepository.findByIdsAndDeletedFalse(ids)
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
		
		arsipRepository.save(arsip);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Arsip!!!");
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
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterArsip> pagesArsip = arsipRepository.findAllArsip(
				startDate, endDate, pageRequest);
		
		return pagesArsip;
	}

	@Override
	@Transactional
	public Page<RegisterArsip> findBySearching(
			String start, String end, String value, Integer pages, Integer sizes) {
		log.info("Value : " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.error("💀 Isi text pencarian kosong...");
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
		Page<RegisterArsip> pageArsip = arsipRepository.findBySearching(
				startDate, endDate, value, pageRequest);
		
		return pageArsip;
	}

	@Override
	@Transactional
	public RegisterArsipResponse findByIds(String ids) {
		RegisterArsip arsip = arsipRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterArsipResponse response = new RegisterArsipResponse();
		response.setIds(arsip.getIds());
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
	public void delete(String ids) {
		RegisterArsip arsip = arsipRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		arsip.setDeleted(true);
		arsip.setNomorSurat(arsip.getIds() + " | " + arsip.getNomorSurat());
		arsipRepository.save(arsip);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register Arsip!!!");
	}

}
