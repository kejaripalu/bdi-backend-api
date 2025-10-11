package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.go.kejaripalu.bdi.dto.*;
import id.go.kejaripalu.bdi.mapper.RegisterSuratKeluarMapper;
import id.go.kejaripalu.bdi.service.RegisterSuratService;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.util.JenisSurat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterSuratKeluarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterSuratKeluarServiceImpl implements RegisterSuratService<RegisterSuratKeluarDTO> {
	
	private final RegisterSuratKeluarRepository suratKeluarRepository;

	@Override
	@Transactional
	public Page<RegisterSuratKeluarDTO> findAll(String startDate, String endDate, String stringJenisSurat,
											   Integer pages, Integer sizes) {

        JenisSurat jenisSurat = JenisSurat.BIASA;
		if (stringJenisSurat.equals("RAHASIA")) {
			jenisSurat = JenisSurat.RAHASIA;
		}

        Date start = ParserDateUtil.start(startDate);
        Date end = ParserDateUtil.end(endDate);
        Pageable pageRequest = PageRequest.of(pages, sizes);

        return suratKeluarRepository.findSuratKeluar(start, end, jenisSurat, pageRequest);
	}

	@Override
	@Transactional
	public RegisterSuratKeluarDTO create(RegisterSuratKeluarDTO request) {
		RegisterSuratKeluarDTO suratKeluar =
				RegisterSuratKeluarMapper.INSTANCE.toDTO(
						suratKeluarRepository.save(RegisterSuratKeluarMapper.INSTANCE.toEntity(request)));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Surat Keluar!!!");
		return suratKeluar;
	}

	@Override
	@Transactional
	public RegisterSuratKeluarDTO findByIds(String ids) {
		RegisterSuratKeluar suratKeluar = suratKeluarRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		return RegisterSuratKeluarMapper.INSTANCE.toDTO(suratKeluar);
	}

	@Override
	@Transactional
	public RegisterSuratKeluarDTO update(String ids, RegisterSuratKeluarDTO request) {
		RegisterSuratKeluar suratKeluar = suratKeluarRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratKeluar.setTanggalSurat(request.tanggalSurat());
		suratKeluar.setNomorSurat(request.nomorSurat());
		suratKeluar.setKepada(request.kepada());
		suratKeluar.setPerihal(request.perihal());
		suratKeluar.setJenisSurat(request.jenisSurat());
		suratKeluar.setLampiran(request.lampiran());
		suratKeluar.setKeterangan(request.keterangan());
		suratKeluar.setUrlFile(request.urlFile());

		RegisterSuratKeluarDTO registerSuratKeluarDTO =
				RegisterSuratKeluarMapper.INSTANCE.toDTO(suratKeluarRepository.save(suratKeluar));

		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Surat Keluar!!!");
		return registerSuratKeluarDTO;
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterSuratKeluar suratKeluar = suratKeluarRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		suratKeluar.setDeleted(true);
		suratKeluar.setNomorSurat(suratKeluar.getIds() + " | " + suratKeluar.getNomorSurat());
		suratKeluarRepository.save(suratKeluar);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Surat Keluar!!!");
	}

	@Override
	@Transactional
	public Page<RegisterSuratKeluarDTO> findBySearching(String start, String end, String value,
			String stringJenisSurat, Integer pages, Integer sizes) {

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

        return suratKeluarRepository.findSuratKeluarBySearch(
                startDate, endDate, value, jenisSurat, pageRequest);
	}
}
