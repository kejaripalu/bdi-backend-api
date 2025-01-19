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

	private final RegisterTelaahanIntelijenRepository repository;
	
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
		
		repository.save(lahin);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Telaahan Intelijen!!!");
	}

	@Override
	@Transactional
	public void update(String ids, RegisterTelaahanIntelijenRequest request) {
		RegisterTelaahanIntelijen lahin = repository.findByIdsAndDeletedFalse(ids)
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
		
		repository.save(lahin);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ)Register Telaahan Intelijen!!!");
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
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterTelaahanIntelijen> pagesLahin = repository.findAllLahin(
				startDate, endDate, pageRequest);
		
		return pagesLahin;
	}

	@Override
	@Transactional
	public Page<RegisterTelaahanIntelijen> findBySearching(String start, String end, String value, Integer pages,
			Integer sizes) {
		log.info("🔎 Value for searching: " + value);
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
			log.error(e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterTelaahanIntelijen> pageLahin = repository.findBySearching(
				startDate, endDate, value, pageRequest);
		
		return pageLahin;
	}

	@Override
	@Transactional
	public RegisterTelaahanIntelijenResponse findByIds(String ids) {
		RegisterTelaahanIntelijen lahin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterTelaahanIntelijenResponse response = new RegisterTelaahanIntelijenResponse();
		response.setIds(lahin.getIds());
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
	public void delete(String ids) {
		RegisterTelaahanIntelijen lahin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		lahin.setDeleted(true);
		lahin.setNomor(lahin.getIds() + " | " + lahin.getNomor());
		repository.save(lahin);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Telaahan Intelijen");
	}

}
