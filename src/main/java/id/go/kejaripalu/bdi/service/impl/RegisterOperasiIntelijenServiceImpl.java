package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterOperasiIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterOperasiIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterOperasiIntelijenServiceImpl implements RegisterOperasiIntelijenService {
	
	private final RegisterOperasiIntelijenRepository repository;

	@Override
	@Transactional
	public void create(RegisterOperasiIntelijenRequest request) {
		RegisterOperasiIntelijen opsin = new RegisterOperasiIntelijen();
		opsin.setBidangDirektorat(request.getBidangDirektorat());
		opsin.setSektor(request.getSektor());
		opsin.setTanggal(request.getTanggal());
		opsin.setNomor(request.getNomor());
		opsin.setPerihal(request.getPerihal());
		opsin.setNamaPetugasPelaksana(request.getNamaPetugasPelaksana());
		opsin.setHasilPelaksanaanOperasi(request.getHasilPelaksanaanOperasi());
		opsin.setKeterangan(request.getKeterangan());
		opsin.setUrlFile(request.getUrlFile());
		
		repository.save(opsin);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Operasi Kegiatan Intelijen!!!");
	}

	@Override
	@Transactional
	public void update(String ids, RegisterOperasiIntelijenRequest request) {
		RegisterOperasiIntelijen opsin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		opsin.setBidangDirektorat(
				request.getBidangDirektorat() == null ? 
						opsin.getBidangDirektorat() : request.getBidangDirektorat());
		opsin.setSektor(
				request.getSektor() == null ? 
						opsin.getSektor() : request.getSektor());
		opsin.setTanggal(
				request.getTanggal() == null ? 
						opsin.getTanggal() : request.getTanggal());
		opsin.setNomor(
				request.getNomor() == null || request.getNomor().isBlank() ? 
						opsin.getNomor() : request.getNomor());
		opsin.setPerihal(
				request.getPerihal() == null || request.getPerihal().isBlank() ? 
						opsin.getPerihal() : request.getPerihal());
		opsin.setNamaPetugasPelaksana(
				request.getNamaPetugasPelaksana() == null || request.getNamaPetugasPelaksana().isBlank() ? 
						opsin.getNamaPetugasPelaksana() : request.getNamaPetugasPelaksana());
		opsin.setHasilPelaksanaanOperasi(request.getHasilPelaksanaanOperasi());
		opsin.setKeterangan(request.getKeterangan());
		opsin.setUrlFile(request.getUrlFile());
		
		repository.save(opsin);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Operasi Intelijen!!!");
	}

	@Override
	@Transactional
	public Page<RegisterOperasiIntelijen> findAll(String start, String end, String stringBidangDirektorat,
			Integer pages, Integer sizes) {
		BidangDirektorat bidangDirektorat = null;
		if (stringBidangDirektorat.equals("IPOLHANKAM")) {
			bidangDirektorat = BidangDirektorat.IPOLHANKAM;
		} else if (stringBidangDirektorat.equals("SOSBUDMAS")) {
			bidangDirektorat = BidangDirektorat.SOSBUDMAS;
		} else if (stringBidangDirektorat.equals("EKOKEU")) {
			bidangDirektorat = BidangDirektorat.EKOKEU;
		} else if (stringBidangDirektorat.equals("PAMSTRA")) {
			bidangDirektorat = BidangDirektorat.PAMSTRA;
		} else if (stringBidangDirektorat.equals("TIPRODIN")) {
			bidangDirektorat = BidangDirektorat.TIPRODIN;
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
		Page<RegisterOperasiIntelijen> pagesOpsin = repository.findAllOpsin(
				startDate, endDate, bidangDirektorat, pageRequest);
		
		return pagesOpsin;
	}

	@Override
	@Transactional
	public Page<RegisterOperasiIntelijen> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages, Integer sizes) {
		BidangDirektorat bidangDirektorat = null;
		if (stringBidangDirektorat.equals("IPOLHANKAM")) {
			bidangDirektorat = BidangDirektorat.IPOLHANKAM;
		} else if (stringBidangDirektorat.equals("SOSBUDMAS")) {
			bidangDirektorat = BidangDirektorat.SOSBUDMAS;
		} else if (stringBidangDirektorat.equals("EKOKEU")) {
			bidangDirektorat = BidangDirektorat.EKOKEU;
		} else if (stringBidangDirektorat.equals("PAMSTRA")) {
			bidangDirektorat = BidangDirektorat.PAMSTRA;
		} else if (stringBidangDirektorat.equals("TIPRODIN")) {
			bidangDirektorat = BidangDirektorat.TIPRODIN;
		}
		
		log.info("🔎 Value : " + value);
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
		Page<RegisterOperasiIntelijen> pageOpsin = repository.findBySearching(
				startDate, endDate, bidangDirektorat, value, pageRequest);
		
		return pageOpsin;
	}

	@Override
	@Transactional
	public RegisterOperasiIntelijenResponse findByIds(String ids) {
		RegisterOperasiIntelijen opsin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterOperasiIntelijenResponse response = new RegisterOperasiIntelijenResponse();
		response.setIds(opsin.getIds());
		response.setBidangDirektorat(opsin.getBidangDirektorat());
		response.setSektor(opsin.getSektor());
		response.setNomor(opsin.getNomor());
		response.setTanggal(opsin.getTanggal());
		response.setPerihal(opsin.getPerihal());
		response.setNamaPetugasPelaksana(opsin.getNamaPetugasPelaksana());
		response.setHasilPelaksanaanOperasi(opsin.getHasilPelaksanaanOperasi());
		response.setKeterangan(opsin.getKeterangan());
		response.setUrlFile(opsin.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterOperasiIntelijen opsin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		opsin.setDeleted(true);
		opsin.setNomor(opsin.getIds() + " | " + opsin.getNomor());
		repository.save(opsin);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register Opsin!!!");
	}

}
