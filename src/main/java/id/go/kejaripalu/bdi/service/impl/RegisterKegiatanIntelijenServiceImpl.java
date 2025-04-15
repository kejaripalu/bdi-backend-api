package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterKegiatanIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterKegiatanIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterKegiatanIntelijenServiceImpl implements RegisterKegiatanIntelijenService {
	
	private final RegisterKegiatanIntelijenRepository repository;

	@Override
	@Transactional
	public void create(RegisterKegiatanIntelijenRequest request) {
		RegisterKegiatanIntelijen kegiatanIntelijen = new RegisterKegiatanIntelijen();
		kegiatanIntelijen.setBidangDirektorat(request.getBidangDirektorat());
		kegiatanIntelijen.setSektor(request.getSektor());
		kegiatanIntelijen.setTanggal(request.getTanggal());
		kegiatanIntelijen.setNomor(request.getNomor());
		kegiatanIntelijen.setPerihal(request.getPerihal());
		kegiatanIntelijen.setNamaPetugasPelaksana(request.getNamaPetugasPelaksana());
		kegiatanIntelijen.setHasilPelaksanaanKegiatan(request.getHasilPelaksanaanKegiatan());
		kegiatanIntelijen.setKeterangan(request.getKeterangan());
		kegiatanIntelijen.setUrlFile(request.getUrlFile());
		
		repository.save(kegiatanIntelijen);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Intelijen!!!");

	}

	@Override
	@Transactional
	public void update(String ids, RegisterKegiatanIntelijenRequest request) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		kegiatanIntelijen.setBidangDirektorat(
				request.getBidangDirektorat() == null ? 
						kegiatanIntelijen.getBidangDirektorat() : request.getBidangDirektorat());
		kegiatanIntelijen.setSektor(
				request.getSektor() == null ? 
						kegiatanIntelijen.getSektor() : request.getSektor());
		kegiatanIntelijen.setTanggal(
				request.getTanggal() == null ? 
						kegiatanIntelijen.getTanggal() : request.getTanggal());
		kegiatanIntelijen.setNomor(
				request.getNomor() == null || request.getNomor().isBlank() ? 
						kegiatanIntelijen.getNomor() : request.getNomor());
		kegiatanIntelijen.setPerihal(
				request.getPerihal() == null || request.getPerihal().isBlank() ? 
						kegiatanIntelijen.getPerihal() : request.getPerihal());
		kegiatanIntelijen.setNamaPetugasPelaksana(
				request.getNamaPetugasPelaksana() == null || request.getNamaPetugasPelaksana().isBlank() ? 
						kegiatanIntelijen.getNamaPetugasPelaksana() : request.getNamaPetugasPelaksana());
		kegiatanIntelijen.setHasilPelaksanaanKegiatan(request.getHasilPelaksanaanKegiatan());
		kegiatanIntelijen.setKeterangan(request.getKeterangan());
		kegiatanIntelijen.setUrlFile(request.getUrlFile());
		
		repository.save(kegiatanIntelijen);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Intelijen!!!");
	}

	@Override
	@Transactional
	public Page<RegisterKegiatanIntelijen> findAll(String start, String end, String stringBidangDirektorat, Integer pages, Integer sizes) {
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
		Page<RegisterKegiatanIntelijen> pagesKegiatanIntelijen = repository.findAllKegiatan(
				startDate, endDate, bidangDirektorat, pageRequest);
		
		return pagesKegiatanIntelijen;
	}

	@Override
	@Transactional
	public Page<RegisterKegiatanIntelijen> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages,
			Integer sizes) {
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
		
		log.info("🔎 Value for searching: " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.error("💀 Isi text pencarian kosong...");
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
		Page<RegisterKegiatanIntelijen> pageKegiatan = repository.findBySearching(
				startDate, endDate, bidangDirektorat, value, pageRequest);
		
		return pageKegiatan;
	}

	@Override
	@Transactional
	public RegisterKegiatanIntelijenResponse findByIds(String ids) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterKegiatanIntelijenResponse response = new RegisterKegiatanIntelijenResponse();
		response.setIds(kegiatanIntelijen.getIds());
		response.setBidangDirektorat(kegiatanIntelijen.getBidangDirektorat());
		response.setSektor(kegiatanIntelijen.getSektor());
		response.setNomor(kegiatanIntelijen.getNomor());
		response.setTanggal(kegiatanIntelijen.getTanggal());
		response.setPerihal(kegiatanIntelijen.getPerihal());
		response.setNamaPetugasPelaksana(kegiatanIntelijen.getNamaPetugasPelaksana());
		response.setHasilPelaksanaanKegiatan(kegiatanIntelijen.getHasilPelaksanaanKegiatan());
		response.setKeterangan(kegiatanIntelijen.getKeterangan());
		response.setUrlFile(kegiatanIntelijen.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		kegiatanIntelijen.setDeleted(true);
		kegiatanIntelijen.setNomor(kegiatanIntelijen.getIds() + " | " + kegiatanIntelijen.getNomor());
		repository.save(kegiatanIntelijen);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Kegiatan Intelijen!!!");
	}

}
