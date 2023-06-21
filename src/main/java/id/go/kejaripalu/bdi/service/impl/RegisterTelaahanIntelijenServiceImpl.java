package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterTelaahanIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterTelaahanIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterTelaahanIntelijenServiceImpl implements RegisterTelaahanIntelijenService {

	private RegisterTelaahanIntelijenRepository repository;
	
	@Override
	@Transactional
	public void create(RegisterTelaahanIntelijenRequest request) {
		RegisterTelaahanIntelijen lahin = new RegisterTelaahanIntelijen();
		lahin.setTanggal(request.getTanggal());
		lahin.setNomor(request.getNomor());
		lahin.setPembuat(request.getPembuat());
		lahin.setPerihal(request.getPerihal());
		lahin.setLampiran(request.getLampiran());
		lahin.setTindakLanjut(request.getTindakLanjut());
		lahin.setKeterangan(request.getKeterangan());
		lahin.setUrlFile(request.getUrlFile());
		
		log.info("Register Telaahan Intelijen Request: " + lahin);
		repository.save(lahin);
		log.info("Saved Telaahan Intelijen: " + lahin);
	}

	@Override
	@Transactional
	public void update(String id, RegisterTelaahanIntelijenRequest request) {
		RegisterTelaahanIntelijen lahin = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		lahin.setTanggal(
				request.getTanggal() == null ? 
						lahin.getTanggal() : request.getTanggal());
		lahin.setNomor(
				request.getNomor() == null || request.getNomor().isBlank() ? 
						lahin.getNomor() : request.getNomor());
		lahin.setPembuat(
				request.getPembuat() == null || request.getPembuat().isBlank() ? 
						lahin.getPembuat() : request.getPembuat());
		lahin.setPerihal(
				request.getPerihal() == null || request.getPerihal().isBlank() ? 
						lahin.getPerihal() : request.getPerihal());
		lahin.setLampiran(request.getLampiran());
		lahin.setTindakLanjut(request.getTindakLanjut());
		lahin.setKeterangan(request.getKeterangan());
		lahin.setUrlFile(request.getUrlFile());
		
		log.info("Register Telaahan Intelijen Request: " + lahin);
		repository.save(lahin);
		log.info("Saved Register Telaahan Intelijen: " + lahin);
	}

	@Override
	@Transactional
	public Page<RegisterTelaahanIntelijen> findAll(String start, String end, Integer pages, Integer sizes) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterTelaahanIntelijen> pagesLahin = repository.findAll(
				startDate, endDate, pageRequest);
		
		return pagesLahin;
	}

	@Override
	@Transactional
	public Page<RegisterTelaahanIntelijen> findBySearching(String start, String end, String value, Integer pages,
			Integer sizes) {
		log.info("Value : " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.warn("Isi text pencarian kosong...");
			return null;
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
		Page<RegisterTelaahanIntelijen> pageLahin = repository.findBySearching(
				startDate, endDate, value, pageRequest);
		
		return pageLahin;
	}

	@Override
	@Transactional
	public RegisterTelaahanIntelijenResponse findById(String id) {
		RegisterTelaahanIntelijen lahin = repository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterTelaahanIntelijenResponse response = new RegisterTelaahanIntelijenResponse();
		response.setId(lahin.getId());
		response.setTanggal(lahin.getTanggal());
		response.setNomor(lahin.getNomor());
		response.setPembuat(lahin.getPembuat());
		response.setLampiran(lahin.getLampiran());
		response.setPerihal(lahin.getPerihal());
		response.setTindakLanjut(lahin.getTindakLanjut());
		response.setKeterangan(lahin.getKeterangan());
		response.setUrlFile(lahin.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void delete(String id) {
		RegisterTelaahanIntelijen lahin = repository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		lahin.setDeleted(Boolean.TRUE);
		lahin.setNomor(lahin.getId() + " | " + lahin.getNomor());
		repository.save(lahin);
		log.info("Soft Delete: " + lahin);
	}

}
