package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterEkspedisiDTO;
import id.go.kejaripalu.bdi.mapper.RegisterEkspedisiMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.util.JenisSurat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterEkspedisiRepository;
import id.go.kejaripalu.bdi.service.RegisterEkspedisiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterEkspedisiServiceImpl implements RegisterEkspedisiService {

	private final RegisterEkspedisiRepository ekspedisiRepository;
	
	@Override
	@Transactional
	public RegisterEkspedisiDTO create(RegisterEkspedisiDTO request) {

		RegisterEkspedisiDTO ekspedisi =
                RegisterEkspedisiMapper.INSTANCE.toDTO(
                        ekspedisiRepository.save(RegisterEkspedisiMapper.INSTANCE.toEntity(request)));

        log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Surat Masuk!!!");
        return ekspedisi;
	}

	@Override
	@Transactional
	public RegisterEkspedisiDTO update(String ids, RegisterEkspedisiDTO request) {
		RegisterEkspedisi ekspedisi = ekspedisiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		ekspedisi.setNomorSurat(request.nomorSurat());
		ekspedisi.setTanggalSurat(request.tanggalSurat());
		ekspedisi.setKepada(request.kepada());
		ekspedisi.setPerihal(request.perihal());
		ekspedisi.setLampiran(request.lampiran());
		ekspedisi.setTanggalTandaTerima(request.tanggalTandaTerima());
		ekspedisi.setJamTandaTerima(request.jamTandaTerima());
		ekspedisi.setNamaDanParaf(request.namaDanParaf());
		ekspedisi.setKeterangan(request.keterangan());
		ekspedisi.setUrlFile(request.urlFile());
		ekspedisi.setJenisSurat(request.jenisSurat());

        RegisterEkspedisiDTO ekspedisiDTO =
                RegisterEkspedisiMapper.INSTANCE.toDTO(ekspedisiRepository.save(ekspedisi));

		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Ekspedisi!!!");
        return ekspedisiDTO;
	}

	@Override
	@Transactional
	public Page<RegisterEkspedisiDTO> findAll(String start, String end, String stringJenisSurat,
			Integer pages, Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}

        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
        return ekspedisiRepository.findEkspedisiAll(
                startDate, endDate, jenisSurat, pageRequest);
	}
	
	@Override
	@Transactional
	public RegisterEkspedisiDTO findByIds(String ids) {
		RegisterEkspedisi ekspedisi = ekspedisiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		return RegisterEkspedisiMapper.INSTANCE.toDTO(ekspedisi);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterEkspedisi ekspedisi = ekspedisiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		ekspedisi.setDeleted(true);
		ekspedisi.setNomorSurat(ekspedisi.getIds() + " | " + ekspedisi.getNomorSurat());
		ekspedisiRepository.save(ekspedisi);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Ekspedisi!!!");
	}

	@Override
	@Transactional
	public Page<RegisterEkspedisiDTO> findBySearching(
			String start, String end, String value, String stringJenisSurat, Integer pages, Integer sizes) {
		JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}
        log.info("\uD83D\uDD0E Value for searching: {}", value);
		if (value.isBlank()) {
			log.warn("💀 Isi text pencarian kosong...");
			return null;
		}

        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
        return ekspedisiRepository.findEkspedisiBySearching(
                startDate, endDate, value, jenisSurat, pageRequest);
	}
	
}
