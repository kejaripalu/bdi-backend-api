package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMResponse;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMResquest;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterTamuPPHPPMRepository;
import id.go.kejaripalu.bdi.service.RegisterTamuPPHPPMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterTamuPPHPPMServiceImpl implements RegisterTamuPPHPPMService {

	private final RegisterTamuPPHPPMRepository repository;
	
	@Override
	@Transactional
	public void createRegisterTamu(RegisterTamuPPHPPMResquest request) {
		RegisterTamuPPHPPM pphppm = new RegisterTamuPPHPPM();
		pphppm.setJenisPelayanan(request.getJenisPelayanan());
		pphppm.setNamaPetugasPenerima(request.getNamaPetugasPenerima());
		pphppm.setTanggal(request.getTanggal());
		pphppm.setJam(request.getJam());
		pphppm.setNamaTamu(request.getNamaTamu());
		pphppm.setTempatLahirTamu(request.getTempatLahirTamu());
		pphppm.setTanggalLahirTamu(request.getTanggalLahirTamu());
		pphppm.setAlamat(request.getAlamat());
		pphppm.setJenisKelamin(request.getJenisKelamin());
		pphppm.setNomorHandphone(request.getNomorHandphone());
		pphppm.setEmail(request.getEmail());
		pphppm.setPekerjaan(request.getPekerjaan());
		pphppm.setNomorIdentitas(request.getNomorIdentitas());
		pphppm.setNamaOrganisasi(request.getNamaOrganisasi());
		pphppm.setInformasiYangDisampaikan(request.getInformasiYangDisampaikan());
		pphppm.setDokumenYangDisampaikan(request.getDokumenYangDisampaikan());
		pphppm.setKeterangan(request.getKeterangan());
		pphppm.setUrlFile(request.getUrlFile());
		
		repository.save(pphppm);
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ)  Register Tamu PPH & PPM!!!");
	}

	@Override
	@Transactional
	public void updateRegisterTamu(String ids, RegisterTamuPPHPPMResquest request) {
		RegisterTamuPPHPPM pphppm = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		pphppm.setJenisPelayanan(
				request.getJenisPelayanan() == null ?
						pphppm.getJenisPelayanan() : request.getJenisPelayanan());
		pphppm.setNamaPetugasPenerima(
				request.getNamaPetugasPenerima() == null || request.getNamaPetugasPenerima().isBlank() ?
						pphppm.getNamaPetugasPenerima() : request.getNamaPetugasPenerima());
		pphppm.setTanggal(
				request.getTanggal() == null ?
						pphppm.getTanggal() : request.getTanggal());
		pphppm.setJam(
				request.getJam() == null ?
						pphppm.getJam() : request.getJam());
		pphppm.setNamaTamu(
				request.getNamaTamu() == null || request.getNamaTamu().isBlank() ?
						pphppm.getNamaTamu() : request.getNamaTamu());
		pphppm.setTempatLahirTamu(
				request.getTempatLahirTamu() == null ?
						pphppm.getTempatLahirTamu() : request.getTempatLahirTamu());
		pphppm.setTanggalLahirTamu(
				request.getTanggalLahirTamu() == null ?
						pphppm.getTanggalLahirTamu() : request.getTanggalLahirTamu());
		pphppm.setAlamat(
				request.getAlamat() == null ?
						pphppm.getAlamat() : request.getAlamat());
		pphppm.setJenisKelamin(
				request.getJenisKelamin() == null ?
						pphppm.getJenisKelamin() : request.getJenisKelamin());
		pphppm.setNomorHandphone(
				request.getNomorHandphone() == null ?
						pphppm.getNomorHandphone() : request.getNomorHandphone());
		pphppm.setEmail(
				request.getEmail() == null ?
						pphppm.getEmail() : request.getEmail());
		pphppm.setPekerjaan(
				request.getPekerjaan() == null ?
						pphppm.getPekerjaan() : request.getPekerjaan());
		pphppm.setNomorIdentitas(
				request.getNomorIdentitas() == null ?
						pphppm.getNomorIdentitas() : request.getNomorIdentitas());
		pphppm.setNamaOrganisasi(
				request.getNamaOrganisasi() == null ? 
						pphppm.getNamaOrganisasi() : request.getNamaOrganisasi());
		pphppm.setInformasiYangDisampaikan(
				request.getInformasiYangDisampaikan() == null || request.getInformasiYangDisampaikan().isBlank() ?
						pphppm.getInformasiYangDisampaikan() : request.getInformasiYangDisampaikan());
		pphppm.setDokumenYangDisampaikan(
				request.getDokumenYangDisampaikan() == null ?
						pphppm.getDokumenYangDisampaikan() : request.getDokumenYangDisampaikan());
		pphppm.setKeterangan(
				request.getKeterangan() == null ?
						pphppm.getKeterangan() : request.getKeterangan());
		pphppm.setUrlFile(
				request.getUrlFile() == null ?
						pphppm.getUrlFile() : request.getUrlFile());
		
		repository.save(pphppm);
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Tamu PPH & PPM!!!");
	}

	@Override
	@Transactional
	public Page<RegisterTamuPPHPPM> findRegisterTamu(String startDate, String endDate, Integer pages, Integer sizes) {
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		Pageable pageable = PageRequest.of(pages, sizes);
		Page<RegisterTamuPPHPPM> pagesPPHPPM = repository.findAllPPHPPM(start, end, pageable);
				
		return pagesPPHPPM;
	}

	@Override
	@Transactional
	public Page<RegisterTamuPPHPPM> findRegisterTamuBySearching(String startDate, String endDate, String value,
			Integer pages, Integer sizes) {
		
		log.info("🔎 Value for searching: " + value);
		if (value.isBlank() || value.isEmpty() || value.equals("")) {
			log.warn("💀 Isi text pencarian kosong...");
			return null;
		}
		
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			log.error("💀 " + e.getMessage());
		}
		
		Pageable pageable = PageRequest.of(pages, sizes);
		Page<RegisterTamuPPHPPM> pagesPPHPPM = repository.findBySearching(start, end, value, pageable);
		
		return pagesPPHPPM;
	}

	@Override
	@Transactional
	public RegisterTamuPPHPPMResponse findRegisterTamuByIds(String ids) {
		RegisterTamuPPHPPM pphppm = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		RegisterTamuPPHPPMResponse response = new RegisterTamuPPHPPMResponse();
		response.setIds(pphppm.getIds());
		response.setJenisPelayanan(pphppm.getJenisPelayanan());
		response.setNamaPetugasPenerima(pphppm.getNamaPetugasPenerima());
		response.setTanggal(pphppm.getTanggal());
		response.setJam(pphppm.getJam());
		response.setNamaTamu(pphppm.getNamaTamu());
		response.setTempatLahirTamu(pphppm.getTempatLahirTamu());
		response.setTanggalLahirTamu(pphppm.getTanggalLahirTamu());
		response.setAlamat(pphppm.getAlamat());
		response.setJenisKelamin(pphppm.getJenisKelamin());
		response.setNomorHandphone(pphppm.getNomorHandphone());
		response.setEmail(pphppm.getEmail());
		response.setPekerjaan(pphppm.getPekerjaan());
		response.setNomorIdentitas(pphppm.getNomorIdentitas());
		response.setNamaOrganisasi(pphppm.getNamaOrganisasi());
		response.setInformasiYangDisampaikan(pphppm.getInformasiYangDisampaikan());
		response.setDokumenYangDisampaikan(pphppm.getDokumenYangDisampaikan());
		response.setKeterangan(pphppm.getKeterangan());
		response.setUrlFile(pphppm.getUrlFile());
		
		return response;
	}

	@Override
	@Transactional
	public void deleteRegisterTamu(String ids) {
		RegisterTamuPPHPPM pphppm = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		pphppm.setDeleted(true);
		repository.save(pphppm);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register PPH & PPM");
	}

	@Override
	public List<Integer[]> countPPHPPM(String start, String end) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		List<Integer[]> countPphPpm = repository.countPPHPPM(startDate, endDate);
		return countPphPpm;
	}

}
