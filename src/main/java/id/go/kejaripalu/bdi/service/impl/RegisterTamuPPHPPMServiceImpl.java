package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMDTO;
import id.go.kejaripalu.bdi.mapper.RegisterTamuPPHPPMMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;
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
	public RegisterTamuPPHPPMDTO create(RegisterTamuPPHPPMDTO request) {
		RegisterTamuPPHPPMDTO pphppm =
                RegisterTamuPPHPPMMapper.INSTANCE.toDTO(
                        Objects.requireNonNull(repository.save(
                                Objects.requireNonNull(RegisterTamuPPHPPMMapper.INSTANCE.toEntity(request)))));

        log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ)  Register Tamu PPH & PPM!!!");
        return pphppm;
	}

	@Override
	@Transactional
	public RegisterTamuPPHPPMDTO update(String ids, RegisterTamuPPHPPMDTO request) {

        RegisterTamuPPHPPM pphppm = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		pphppm.setJenisPelayanan(request.jenisPelayanan());
		pphppm.setNamaPetugasPenerima(request.namaPetugasPenerima());
		pphppm.setTanggal(request.tanggal());
		pphppm.setJam(request.jam());
		pphppm.setNamaTamu(request.namaTamu());
		pphppm.setTempatLahirTamu(request.tempatLahirTamu());
		pphppm.setTanggalLahirTamu(request.tanggalLahirTamu());
		pphppm.setAlamat(request.alamat());
		pphppm.setJenisKelamin(request.jenisKelamin());
		pphppm.setNomorHandphone(request.nomorHandphone());
		pphppm.setEmail(request.email());
		pphppm.setPekerjaan(request.pekerjaan());
		pphppm.setNomorIdentitas(request.nomorIdentitas());
		pphppm.setNamaOrganisasi(request.namaOrganisasi());
		pphppm.setInformasiYangDisampaikan(request.informasiYangDisampaikan());
		pphppm.setDokumenYangDisampaikan(request.dokumenYangDisampaikan());
		pphppm.setKeterangan(request.keterangan());
		pphppm.setUrlFile(request.urlFile());

        RegisterTamuPPHPPMDTO registerTamuPPHPPM =
                RegisterTamuPPHPPMMapper.INSTANCE.toDTO(Objects.requireNonNull(repository.save(pphppm)));
		
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Tamu PPH & PPM!!!");
        return registerTamuPPHPPM;
	}

	@Override
	@Transactional
	public Page<RegisterTamuPPHPPMDTO> findAll(String start, String end, Integer pages, Integer sizes) {
		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

		return repository.findAllPPHPPM(startDate, endDate, pageRequest)
				.map(RegisterTamuPPHPPMMapper.INSTANCE::toDTO);
	}

	@Override
	@Transactional
	public Page<RegisterTamuPPHPPMDTO> findBySearching(String start, String end, String value, Integer pages,
			Integer sizes) {
		log.info("🔍 Value for searching: {}", value);
		if (value.isBlank()) {
			log.error("💀 Isi text pencarian kosong...");
			return null;
		}

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

		return repository.findBySearching(startDate, endDate, value, pageRequest)
				.map(RegisterTamuPPHPPMMapper.INSTANCE::toDTO);
	}

	@Override
	@Transactional
	public RegisterTamuPPHPPMDTO findByIds(String ids) {
		RegisterTamuPPHPPM pphppm = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        return RegisterTamuPPHPPMMapper.INSTANCE.toDTO(pphppm);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterTamuPPHPPM pphppm = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		pphppm.setDeleted(true);
		repository.save(pphppm);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register PPH & PPM");
	}

	@Override
	public List<Integer[]> countPPHPPM(String start, String end) {
        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
        return repository.countPPHPPM(startDate, endDate);
	}

}
