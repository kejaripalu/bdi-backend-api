package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;
import java.util.List;

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
                        repository.save(RegisterTamuPPHPPMMapper.INSTANCE.toEntity(request)));

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
                RegisterTamuPPHPPMMapper.INSTANCE.toDTO(repository.save(pphppm));
		
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Tamu PPH & PPM!!!");
        return registerTamuPPHPPM;
	}

	@Override
	@Transactional
	public Page<RegisterTamuPPHPPMDTO> findAll(String startDate, String endDate, Integer pages, Integer sizes) {

        Date start = ParserDateUtil.start(startDate);
        Date end = ParserDateUtil.end(endDate);
        Pageable pageable = PageRequest.of(pages, sizes);

        return repository.findAllPPHPPM(start, end, pageable);
	}

	@Override
	@Transactional
	public Page<RegisterTamuPPHPPMDTO> findBySearching(String startDate, String endDate, String value,
                                                    Integer pages, Integer sizes) {

        log.info("\uD83D\uDD0E Value for searching: {}", value);
		if (value.isBlank()) {
			log.warn("💀 Isi text pencarian kosong...");
			return null;
		}

        Date start = ParserDateUtil.start(startDate);
        Date end = ParserDateUtil.end(endDate);
        Pageable pageable = PageRequest.of(pages, sizes);

        return repository.findBySearching(start, end, value, pageable);
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
