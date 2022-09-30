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
import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarCreateRequest;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarResponse;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarUpdateRequest;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterSuratKeluarRepository;
import id.go.kejaripalu.bdi.service.RegisterSuratKeluarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterSuratKeluarServiceImpl implements RegisterSuratKeluarService {
	
	private RegisterSuratKeluarRepository suratKeluarRepository;

	@Override
	@Transactional
	public Page<RegisterSuratKeluar> findSuratMasuk(String startDate, String endDate, String stringJenisSurat,
			Integer pages, Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("R")) {
			jenisSurat = JenisSurat.RAHASIA;
		}
		
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		Pageable pageRequest = PageRequest.of(pages, sizes);
		Page<RegisterSuratKeluar> pageSuratKeluar = suratKeluarRepository.findSuratKeluar(start, end, jenisSurat, pageRequest);
		return pageSuratKeluar;
	}

	@Override
	@Transactional
	public void createSuratMasuk(RegisterSuratKeluarCreateRequest request) {
		RegisterSuratKeluar suratKeluar = new RegisterSuratKeluar();
		suratKeluar.setTanggalSurat(request.getTanggalSurat());
		suratKeluar.setNomorSurat(request.getNomorSurat());
		suratKeluar.setKepada(request.getKepada());
		suratKeluar.setPerihal(request.getPerihal());
		suratKeluar.setLampiran(request.getLampiran());
		suratKeluar.setKeterangan(request.getKeterangan());
		suratKeluar.setJenisSurat(request.getJenisSurat());
		
		log.info("Surat Keluar Request: " + suratKeluar);
		suratKeluarRepository.save(suratKeluar);
		log.info("Saved Surat Keluar: " + suratKeluar);
	}

	@Override
	@Transactional
	public RegisterSuratKeluarResponse findSuratMasukById(String id) {
		RegisterSuratKeluar suratKeluar = suratKeluarRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterSuratKeluarResponse response = new RegisterSuratKeluarResponse();
		response.setId(suratKeluar.getId());
		response.setTanggalSurat(suratKeluar.getTanggalSurat());
		response.setNomorSurat(suratKeluar.getNomorSurat());
		response.setKepada(suratKeluar.getKepada());
		response.setPerihal(suratKeluar.getPerihal());
		response.setLampiran(suratKeluar.getLampiran());
		response.setKeterangan(suratKeluar.getKeterangan());
		response.setJenisSurat(suratKeluar.getJenisSurat());
		
		return response;
	}

	@Override
	@Transactional
	public void updateSuratMasuk(String id, RegisterSuratKeluarUpdateRequest request) {
		RegisterSuratKeluar suratKeluar = suratKeluarRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratKeluar.setTanggalSurat(
				request.getTanggalSurat() == null ?
						suratKeluar.getTanggalSurat() : request.getTanggalSurat());
		suratKeluar.setNomorSurat(
				request.getNomorSurat() == null ?
						suratKeluar.getNomorSurat() : request.getNomorSurat());
		suratKeluar.setKepada(
				request.getKepada() == null ?
						suratKeluar.getKepada() : request.getKepada());
		suratKeluar.setPerihal(
				request.getPerihal() == null ?
						suratKeluar.getPerihal() : request.getPerihal());
		suratKeluar.setJenisSurat(
				request.getJenisSurat() == null ?
						suratKeluar.getJenisSurat() : request.getJenisSurat());
		suratKeluar.setLampiran(request.getLampiran());
		suratKeluar.setKeterangan(request.getKeterangan());		
		
		log.info("Surat Keluar Request: " + suratKeluar);
		suratKeluarRepository.save(suratKeluar);
		log.info("Updated Surat Keluar: " + suratKeluar);
	}

	@Override
	@Transactional
	public void deleteSuratKeluar(String id) {
		RegisterSuratKeluar suratKeluar = suratKeluarRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratKeluar.setDeleted(Boolean.TRUE);
		suratKeluar.setNomorSurat(suratKeluar.getId() + " | " + suratKeluar.getNomorSurat());
		suratKeluarRepository.save(suratKeluar);
		log.info("Soft Delete success: " + suratKeluar);
	}

}
