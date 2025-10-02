package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterArsipDTO;
import id.go.kejaripalu.bdi.mapper.RegisterArsipMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterArsip;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterArsipRepository;
import id.go.kejaripalu.bdi.service.RegisterArsipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterArsipServiceImpl implements RegisterArsipService<RegisterArsipDTO> {
	
	private final RegisterArsipRepository arsipRepository;

	@Override
	@Transactional
	public RegisterArsipDTO create(RegisterArsipDTO request) {
		RegisterArsipDTO registerArsip =
				RegisterArsipMapper.INSTANCE.toDTO(
						arsipRepository.save(RegisterArsipMapper.INSTANCE.toEntity(request)));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Register Arsip!!!");
		return registerArsip;
	}

	@Override
	@Transactional
	public RegisterArsipDTO update(String ids, RegisterArsipDTO request) {
		RegisterArsip arsip = arsipRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		arsip.setTanggalPenerimaanArsip(request.tanggalPenerimaanArsip());
		arsip.setJamPenerimaanArsip(request.jamPenerimaanArsip());
		arsip.setDiterimaDari(request.diterimaDari());
		arsip.setNomorSurat(request.nomorSurat());
		arsip.setTanggalSurat(request.tanggalSurat());
		arsip.setPerihal(request.perihal());
		arsip.setLampiran(request.lampiran());
		arsip.setKodePenyimpanan(request.kodePenyimpanan());
		arsip.setKeterangan(request.keterangan());
		arsip.setUrlFile(request.urlFile());

		RegisterArsipDTO registerArsip =
				RegisterArsipMapper.INSTANCE.toDTO(arsipRepository.save(arsip));

		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Arsip!!!");
		return registerArsip;
	}

	@Override
	@Transactional
	public Page<RegisterArsipDTO> findAll(String start, String end, Integer pages, Integer sizes) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
            log.error("\uD83D\uDC80 {}", e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return arsipRepository.findAllArsip(
                startDate, endDate, pageRequest);
	}

	@Override
	@Transactional
	public Page<RegisterArsipDTO> findBySearching(
			String start, String end, String value, Integer pages, Integer sizes) {
        log.info("Value : {}", value);
		if (value.isBlank()) {
			log.error("💀 Isi text pencarian kosong...");
			return null;
		}
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
            log.error("\uD83D\uDC80 {}", e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
        return arsipRepository.findBySearching(
                startDate, endDate, value, pageRequest);
	}

	@Override
	@Transactional
	public RegisterArsipDTO findByIds(String ids) {
		RegisterArsip arsip = arsipRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		return RegisterArsipMapper.INSTANCE.toDTO(arsip);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterArsip arsip = arsipRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		arsip.setDeleted(true);
		arsip.setNomorSurat(arsip.getIds() + " | " + arsip.getNomorSurat());
		arsipRepository.save(arsip);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Register Arsip!!!");
	}

}
