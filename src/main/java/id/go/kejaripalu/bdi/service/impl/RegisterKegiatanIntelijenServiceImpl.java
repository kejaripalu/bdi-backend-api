package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenDTO;
import id.go.kejaripalu.bdi.mapper.RegisterKegiatanIntelijenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterKegiatanIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterKegiatanIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterKegiatanIntelijenServiceImpl implements RegisterKegiatanIntelijenService<RegisterKegiatanIntelijenDTO> {
	
	private final RegisterKegiatanIntelijenRepository repository;

	@Override
	@Transactional
	public RegisterKegiatanIntelijenDTO create(RegisterKegiatanIntelijenDTO request) {
		RegisterKegiatanIntelijenDTO rki =
                RegisterKegiatanIntelijenMapper.INSTANCE.toDTO(
                        repository.save(RegisterKegiatanIntelijenMapper.INSTANCE.toEntity(request)));
		
		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Intelijen!!!");
        return rki;
	}

	@Override
	@Transactional
	public RegisterKegiatanIntelijenDTO update(String ids, RegisterKegiatanIntelijenDTO request) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		kegiatanIntelijen.setBidangDirektorat(request.bidangDirektorat());
		kegiatanIntelijen.setSektor(request.sektor());
		kegiatanIntelijen.setTanggal(request.tanggal());
		kegiatanIntelijen.setNomor(request.nomor());
		kegiatanIntelijen.setPerihal(request.perihal());
		kegiatanIntelijen.setNamaPetugasPelaksana(request.namaPetugasPelaksana());
		kegiatanIntelijen.setHasilPelaksanaanKegiatan(request.hasilPelaksanaanKegiatan());
		kegiatanIntelijen.setKeterangan(request.keterangan());
		kegiatanIntelijen.setUrlFile(request.urlFile());
		
		RegisterKegiatanIntelijenDTO rki = 
                RegisterKegiatanIntelijenMapper.INSTANCE.toDTO(repository.save(kegiatanIntelijen));
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Register Kegiatan Intelijen!!!");        
        return rki;
	}

	@Override
	@Transactional
	public Page<RegisterKegiatanIntelijenDTO> findAll(String start, String end, String stringBidangDirektorat, Integer pages, Integer sizes) {
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
			log.error("\uD83D\uDC80 {}", e.getMessage());
		}
		
		Pageable pageRequest = PageRequest.of(pages, sizes);
        return repository.findAllKegiatan(
                startDate, endDate, bidangDirektorat, pageRequest);
	}

	@Override
	@Transactional
	public Page<RegisterKegiatanIntelijenDTO> findBySearching(String start, String end, String stringBidangDirektorat, String value, Integer pages,
			Integer sizes) {
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

        log.info("\uD83D\uDD0E Value for searching: {}", value);
		if (value.isBlank()) {
			log.error("💀 Isi text pencarian kosong...");
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
        return repository.findBySearching(
                startDate, endDate, bidangDirektorat, value, pageRequest);
	}

	@Override
	@Transactional
	public RegisterKegiatanIntelijenDTO findByIds(String ids) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

		return RegisterKegiatanIntelijenMapper.INSTANCE.toDTO(kegiatanIntelijen);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterKegiatanIntelijen kegiatanIntelijen = repository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		
		kegiatanIntelijen.setDeleted(true);
		kegiatanIntelijen.setNomor(kegiatanIntelijen.getIds() + " | " + kegiatanIntelijen.getNomor());
		repository.save(kegiatanIntelijen);
		log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Kegiatan Intelijen!!!");
	}

}
