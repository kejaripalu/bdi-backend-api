package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenDTO;
import id.go.kejaripalu.bdi.mapper.RegisterTelaahanIntelijenMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterTelaahanIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterTelaahanIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterTelaahanIntelijenServiceImpl implements RegisterTelaahanIntelijenService {

	private final RegisterTelaahanIntelijenRepository repository;
	
	@Override
	@Transactional
	public RegisterTelaahanIntelijenDTO create(RegisterTelaahanIntelijenDTO request) {
		RegisterTelaahanIntelijenDTO lahin =
				RegisterTelaahanIntelijenMapper.INSTANCE.toDTO(
						repository.save(RegisterTelaahanIntelijenMapper.INSTANCE.toEntity(request)));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Telaahan Intelijen!!!");
		return lahin;
	}

	@Override
	@Transactional
	public RegisterTelaahanIntelijenDTO update(String ids, RegisterTelaahanIntelijenDTO request) {

		RegisterTelaahanIntelijen lahin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		lahin.setTanggal(request.tanggal());
		lahin.setNomor(request.nomor());
		lahin.setPembuat(request.pembuat());
		lahin.setPerihal(request.perihal());
		lahin.setLampiran(request.lampiran());
		lahin.setTindakLanjut(request.tindakLanjut());
		lahin.setKeterangan(request.keterangan());
		lahin.setUrlFile(request.urlFile());

		RegisterTelaahanIntelijenDTO telaahanIntelijen =
				RegisterTelaahanIntelijenMapper.INSTANCE.toDTO(
						repository.save(lahin));

		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ)Register Telaahan Intelijen!!!");
		return telaahanIntelijen;
	}

	@Override
	@Transactional
	public Page<RegisterTelaahanIntelijenDTO> findAll(String start, String end, Integer pages, Integer sizes) {

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findAllLahin(
                startDate, endDate, pageRequest);
	}

	@Override
	@Transactional
	public Page<RegisterTelaahanIntelijenDTO> findBySearching(String start, String end, String value, Integer pages,
			Integer sizes) {

        log.info("\uD83D\uDD0E Value for searching: {}", value);
		if (value.isBlank()) {
			log.error("💀 Isi text pencarian kosong...");
			return null;
		}

		Date startDate = ParserDateUtil.start(start);
		Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return repository.findBySearching(
                startDate, endDate, value, pageRequest);
	}

	@Override
	@Transactional
	public RegisterTelaahanIntelijenDTO findByIds(String ids) {
		RegisterTelaahanIntelijen lahin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

		return RegisterTelaahanIntelijenMapper.INSTANCE.toDTO(lahin);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterTelaahanIntelijen lahin = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		lahin.setDeleted(true);
		lahin.setNomor(lahin.getIds() + " | " + lahin.getNomor());
		repository.save(lahin);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Telaahan Intelijen");
	}

}
