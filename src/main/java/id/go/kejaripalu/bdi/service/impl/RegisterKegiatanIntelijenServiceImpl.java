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
import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;
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
	
	private RegisterKegiatanIntelijenRepository repository;

	@Override
	@Transactional
	public void create(RegisterKegiatanIntelijenRequest request) {
		RegisterKegiatanIntelijen kegiatanIntelijen = new RegisterKegiatanIntelijen();
		kegiatanIntelijen.setBidangDirektorat(request.getBidangDirektorat());
		kegiatanIntelijen.setSektor(request.getSektor());
		kegiatanIntelijen.setTanggal(request.getTanggal());
		kegiatanIntelijen.setNomor(request.getNomor());
		kegiatanIntelijen.setPeruntukan(request.getPeruntukan());
		kegiatanIntelijen.setNamaPetugasPelaksana(request.getNamaPetugasPelaksana());
		kegiatanIntelijen.setHasilPelaksanaanKegiatan(request.getHasilPelaksanaanKegiatan());
		kegiatanIntelijen.setKeterangan(request.getKeterangan());
		kegiatanIntelijen.setUrlFile(request.getUrlFile());
		
		log.info("Register Kegiatan Intelijen Request: " + kegiatanIntelijen);
		repository.save(kegiatanIntelijen);
		log.info("Saved Register Kegiatan Intelijen: " + kegiatanIntelijen);

	}

	@Override
	@Transactional
	public void update(String id, RegisterKegiatanIntelijenRequest request) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findById(id)
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
		kegiatanIntelijen.setPeruntukan(
				request.getPeruntukan() == null || request.getPeruntukan().isBlank() ? 
						kegiatanIntelijen.getPeruntukan() : request.getPeruntukan());
		kegiatanIntelijen.setNamaPetugasPelaksana(
				request.getNamaPetugasPelaksana() == null || request.getNamaPetugasPelaksana().isBlank() ? 
						kegiatanIntelijen.getNamaPetugasPelaksana() : request.getNamaPetugasPelaksana());
		kegiatanIntelijen.setHasilPelaksanaanKegiatan(request.getHasilPelaksanaanKegiatan());
		kegiatanIntelijen.setKeterangan(request.getKeterangan());
		kegiatanIntelijen.setUrlFile(request.getUrlFile());
		
		log.info("Register Kegiatan Intelijen Request: " + kegiatanIntelijen);
		repository.save(kegiatanIntelijen);
		log.info("Saved Register Kegiatan Intelijen: " + kegiatanIntelijen);
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
		Page<RegisterKegiatanIntelijen> pagesKegiatanInteiljen = repository.findAllKegiatan(
				startDate, endDate, bidangDirektorat, pageRequest);
		
		return pagesKegiatanInteiljen;
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
		Page<RegisterKegiatanIntelijen> pageArsip = repository.findBySearching(
				startDate, endDate, bidangDirektorat, value, pageRequest);
		
		return pageArsip;
	}

	@Override
	@Transactional
	public RegisterKegiatanIntelijenResponse findById(String id) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterKegiatanIntelijenResponse response = new RegisterKegiatanIntelijenResponse();
		response.setBidangDirektorat(kegiatanIntelijen.getBidangDirektorat());
		response.setSektor(kegiatanIntelijen.getSektor());
		response.setNomor(kegiatanIntelijen.getNomor());
		response.setTanggal(kegiatanIntelijen.getTanggal());
		response.setPeruntukan(kegiatanIntelijen.getPeruntukan());
		response.setNamaPetugasPelaksana(kegiatanIntelijen.getNamaPetugasPelaksana());
		response.setHasilPelaksanaanKegiatan(kegiatanIntelijen.getHasilPelaksanaanKegiatan());
		response.setKeterangan(kegiatanIntelijen.getKeterangan());
		response.setUrlFile(kegiatanIntelijen.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void delete(String id) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		kegiatanIntelijen.setDeleted(Boolean.TRUE);
		kegiatanIntelijen.setNomor(kegiatanIntelijen.getId() + " | " + kegiatanIntelijen.getNomor());
		repository.save(kegiatanIntelijen);
		log.info("Soft Delete: " + kegiatanIntelijen);
	}

}
