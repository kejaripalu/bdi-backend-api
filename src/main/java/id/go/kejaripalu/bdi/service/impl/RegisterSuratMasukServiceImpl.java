package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterSuratMasukDTO;
import id.go.kejaripalu.bdi.mapper.RegisterSuratMasukMapper;
import id.go.kejaripalu.bdi.service.RegisterSuratService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.util.JenisSurat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterSuratMasukRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterSuratMasukServiceImpl implements RegisterSuratService<RegisterSuratMasukDTO> {

	private final RegisterSuratMasukRepository suratMasukRepository;

	@Override
	@Transactional
	public RegisterSuratMasukDTO create(RegisterSuratMasukDTO suratMasukDTO) {
		RegisterSuratMasukDTO suratMasuk =
                RegisterSuratMasukMapper.INSTANCE.toDTO(
						suratMasukRepository.save(RegisterSuratMasukMapper.INSTANCE.toEntity(suratMasukDTO)));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ)  Surat Masuk!!!");
        return suratMasuk;
	}

	@Override
	@Transactional
	public RegisterSuratMasukDTO update(String ids, RegisterSuratMasukDTO suratMasukDTO) {
		RegisterSuratMasuk suratMasuk = suratMasukRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratMasuk.setTanggalPenerimaanSurat(suratMasukDTO.tanggalPenerimaanSurat());
		suratMasuk.setJamPenerimaanSurat(suratMasukDTO.jamPenerimaanSurat());
		suratMasuk.setAsal(suratMasukDTO.asal());
		suratMasuk.setPerihal(suratMasukDTO.perihal());
		suratMasuk.setTanggalSurat(suratMasukDTO.tanggalSurat());
		suratMasuk.setNomorSurat(suratMasukDTO.nomorSurat());
		suratMasuk.setJenisSurat(suratMasukDTO.jenisSurat());
		suratMasuk.setIsiDisposisi(suratMasukDTO.isiDisposisi());
		suratMasuk.setTindakLanjutDisposisi(suratMasukDTO.tindakLanjutDisposisi());
		suratMasuk.setKeterangan(suratMasukDTO.keterangan());
		suratMasuk.setUrlFile(suratMasukDTO.urlFile());
		
		RegisterSuratMasukDTO registerSuratMasukDTO =
                RegisterSuratMasukMapper.INSTANCE.toDTO(
						suratMasukRepository.save(suratMasuk));

        log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Surat Masuk!!!");
        return registerSuratMasukDTO;
	}

	@Override
	@Transactional
	public Page<RegisterSuratMasukDTO> findAll(String start, String end, String stringJenisSurat,
											   Integer pages, Integer sizes) {
        JenisSurat jenisSurat = JenisSurat.BIASA;
        if (stringJenisSurat.equals("RAHASIA")) {
            jenisSurat = JenisSurat.RAHASIA;
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

        return suratMasukRepository.findSuratMasukAll(
                startDate, endDate, jenisSurat, pageRequest);
    }

	@Override
	@Transactional
	public RegisterSuratMasukDTO findByIds(String ids) {
		RegisterSuratMasuk suratMasuk = suratMasukRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        return RegisterSuratMasukMapper.INSTANCE.toDTO(suratMasuk);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterSuratMasuk suratMasuk = suratMasukRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratMasuk.setDeleted(true);
		suratMasuk.setNomorSurat(suratMasuk.getIds() + " | " + suratMasuk.getNomorSurat());
		suratMasukRepository.save(suratMasuk);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ): Surat Masuk!!!");
	}

	@Override
	@Transactional
	public Page<RegisterSuratMasukDTO> findBySearching(
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
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
            log.error("\uD83D\uDC80 {}", e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
        return suratMasukRepository.findSuratMasukBySearching(
                startDate, endDate, value, jenisSurat, pageRequest);
	}
	
}
