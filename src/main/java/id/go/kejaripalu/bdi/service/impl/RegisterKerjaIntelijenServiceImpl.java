package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterKerjaIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterKerjaIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterKerjaIntelijenServiceImpl implements RegisterKerjaIntelijenService {

	private RegisterKerjaIntelijenRepository rkiRepository;
	
	@Override
	@Transactional
	public void createRKI(RegisterKerjaIntelijenRequest request) {
		RegisterKerjaIntelijen rki = new RegisterKerjaIntelijen();
		rki.setTanggalWaktuDiterima(request.getTanggalWaktuDiterima());;
		rki.setJamWaktuDiterima(request.getJamWaktuDiterima());
		rki.setSumberBapul(request.getSumberBapul());
		rki.setNilaiDataInformasi(request.getNilaiDataInformasi());
		rki.setUraianPeristiwaMasalah(request.getUraianPeristiwaMasalah());
		rki.setCatatan(request.getCatatan());
		rki.setDisposisiTindakan(request.getDisposisiTindakan());
		rki.setTindakLanjut(request.getTindakLanjut());
		rki.setKeterangan(request.getKeterangan());
		rki.setUrlFile(request.getUrlFile());
		rki.setBidangDirektorat(request.getBidangDirektorat());
		rki.setSektor(request.getSektor());
		
		log.info("RKI Request: " + rki);
		rkiRepository.save(rki);
		log.info("Saved RKI: " + rki);
	}

	@Override
	@Transactional
	public void updateRKI(String id, RegisterKerjaIntelijenRequest request) {
		RegisterKerjaIntelijen rki = rkiRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		rki.setTanggalWaktuDiterima(
				request.getTanggalWaktuDiterima() == null ?
						rki.getTanggalWaktuDiterima() : request.getTanggalWaktuDiterima());
		rki.setJamWaktuDiterima(
				request.getJamWaktuDiterima() == null ?
						rki.getJamWaktuDiterima() : request.getJamWaktuDiterima());
		rki.setSumberBapul(
				request.getSumberBapul() == null || request.getSumberBapul().isBlank() ?
						rki.getSumberBapul() : request.getSumberBapul());
		rki.setNilaiDataInformasi(
				request.getNilaiDataInformasi() == null || request.getNilaiDataInformasi().isBlank() ?
						rki.getNilaiDataInformasi() : request.getNilaiDataInformasi());
		rki.setUraianPeristiwaMasalah(
				request.getUraianPeristiwaMasalah() == null || request.getUraianPeristiwaMasalah().isBlank() ?
						rki.getUraianPeristiwaMasalah() : request.getUraianPeristiwaMasalah());
		rki.setBidangDirektorat(
				request.getBidangDirektorat() == null ?
						rki.getBidangDirektorat() : request.getBidangDirektorat());
		rki.setSektor(
				request.getSektor() == null ?
						rki.getSektor() : request.getSektor());
		rki.setCatatan(request.getCatatan());
		rki.setDisposisiTindakan(request.getDisposisiTindakan());
		rki.setTindakLanjut(request.getTindakLanjut());
		rki.setUrlFile(request.getUrlFile());
		rki.setKeterangan(request.getKeterangan());
		
		log.info("RKI Update Request: " + rki);
		rkiRepository.save(rki);
		log.info("RKI Updated!!! ");		
	}

	@Override
	@Transactional
	public RegisterKerjaIntelijenResponse findRKIbyId(String id) {
		RegisterKerjaIntelijen rki = rkiRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterKerjaIntelijenResponse response = new RegisterKerjaIntelijenResponse();
		response.setId(rki.getId());
		response.setTanggalWaktuDiterima(rki.getTanggalWaktuDiterima());
		response.setJamWaktuDiterima(rki.getJamWaktuDiterima());
		response.setSumberBapul(rki.getSumberBapul());
		response.setNilaiDataInformasi(rki.getNilaiDataInformasi());
		response.setUraianPeristiwaMasalah(rki.getUraianPeristiwaMasalah());
		response.setBidangDirektorat(rki.getBidangDirektorat());
		response.setDisposisiTindakan(rki.getDisposisiTindakan());
		response.setSektor(rki.getSektor());
		response.setCatatan(rki.getCatatan());
		response.setTindakLanjut(rki.getTindakLanjut());
		response.setKeterangan(rki.getKeterangan());
		response.setUrlFile(rki.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public Page<RegisterKerjaIntelijen> findRKI(String start, String end, String stringBidangDirektorat,
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
		Page<RegisterKerjaIntelijen> pagesRKI = rkiRepository.findRKIAll(startDate, endDate, bidangDirektorat, pageRequest);
		
		return pagesRKI;
	}

	@Override
	@Transactional
	public void deleteRKI(String id) {
		RegisterKerjaIntelijen rki = rkiRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		rki.setDeleted(Boolean.TRUE);
		rki.setUraianPeristiwaMasalah(rki.getId() + " | " + rki.getUraianPeristiwaMasalah());
		rkiRepository.save(rki);
		log.info("Soft Delete: " + rki);	
	}

	@Override
	@Transactional
	public Page<RegisterKerjaIntelijen> findRKIBySearching(String start, String end, String value, String stringBidangDirektorat,
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
		Page<RegisterKerjaIntelijen> pagesRKI = rkiRepository.findRKIBySearching(
				startDate, endDate, value, bidangDirektorat, pageRequest);
		return pagesRKI;
	}

}
