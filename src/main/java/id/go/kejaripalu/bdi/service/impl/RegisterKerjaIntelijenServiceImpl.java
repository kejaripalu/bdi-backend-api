package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenDTO;
import id.go.kejaripalu.bdi.mapper.RegisterKerjaIntelijenMapper;
import id.go.kejaripalu.bdi.util.GetBidangDirektorat;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterKerjaIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterKerjaIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterKerjaIntelijenServiceImpl implements RegisterKerjaIntelijenService {

	private final RegisterKerjaIntelijenRepository rkiRepository;
	
	@Override
	@Transactional
	public RegisterKerjaIntelijenDTO create(RegisterKerjaIntelijenDTO request) {
        RegisterKerjaIntelijenDTO rki =
                RegisterKerjaIntelijenMapper.INSTANCE.toDTO(
                        rkiRepository.save(RegisterKerjaIntelijenMapper.INSTANCE.toEntity(request)));

		log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) RKI!!!");
        return rki;
	}

	@Override
	@Transactional
	public RegisterKerjaIntelijenDTO update(String ids, RegisterKerjaIntelijenDTO request) {
		RegisterKerjaIntelijen rki = rkiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		rki.setTanggalWaktuDiterima(request.tanggalWaktuDiterima());
		rki.setJamWaktuDiterima(request.jamWaktuDiterima());
		rki.setSumberBapul(request.sumberBapul());
		rki.setNilaiDataInformasi(request.nilaiDataInformasi());
		rki.setUraianPeristiwaMasalah(request.uraianPeristiwaMasalah());
		rki.setBidangDirektorat(request.bidangDirektorat());
		rki.setSektor(request.sektor());
		rki.setCatatan(request.catatan());
		rki.setDisposisiTindakan(request.disposisiTindakan());
		rki.setTindakLanjut(request.tindakLanjut());
		rki.setUrlFile(request.urlFile());
		rki.setKeterangan(request.keterangan());

        RegisterKerjaIntelijenDTO registerKerjaIntelijen = RegisterKerjaIntelijenMapper.INSTANCE.toDTO(rkiRepository.save(rki));
		log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) RKI!!!");
        return registerKerjaIntelijen;
	}

	@Override
	@Transactional
	public RegisterKerjaIntelijenDTO findByIds(String ids) {
		RegisterKerjaIntelijen rki = rkiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

		return RegisterKerjaIntelijenMapper.INSTANCE.toDTO(rki);
	}

	@Override
	@Transactional
	public Page<RegisterKerjaIntelijenDTO> findAll(
            String start, String end, String stringBidangDirektorat, Integer pages, Integer sizes) {

		BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
        Pageable pageRequest = PageRequest.of(pages, sizes);

        return rkiRepository.findRKIAll(startDate, endDate, bidangDirektorat, pageRequest);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		RegisterKerjaIntelijen rki = rkiRepository.findByIdsAndDeletedFalse(ids)
				.orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
		rki.setDeleted(true);
		rki.setUraianPeristiwaMasalah(rki.getIds() + " | " + rki.getUraianPeristiwaMasalah());
		rkiRepository.save(rki);
        log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ): {}", rki);
	}

	@Override
	@Transactional
	public Page<RegisterKerjaIntelijenDTO> findBySearching(String start, String end, String value, String stringBidangDirektorat,
                                                           Integer pages, Integer sizes) {

		BidangDirektorat bidangDirektorat = GetBidangDirektorat.get(stringBidangDirektorat);
        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
		Pageable pageRequest = PageRequest.of(pages, sizes);

        return rkiRepository.findRKIBySearching(
                startDate, endDate, value, bidangDirektorat, pageRequest);
	}

}
