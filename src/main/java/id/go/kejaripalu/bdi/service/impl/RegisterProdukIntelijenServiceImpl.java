package id.go.kejaripalu.bdi.service.impl;

import java.util.Date;
import java.util.List;

import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenDTO;
import id.go.kejaripalu.bdi.mapper.RegisterProdukIntelijenMapper;
import id.go.kejaripalu.bdi.util.ParserDateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterProdukIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterProdukIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterProdukIntelijenServiceImpl implements RegisterProdukIntelijenService {

    private final RegisterProdukIntelijenRepository produkIntelijenRepository;

    @Override
    @Transactional
    public RegisterProdukIntelijenDTO create(RegisterProdukIntelijenDTO request) {
        RegisterProdukIntelijenDTO produkIntelijen = RegisterProdukIntelijenMapper.INSTANCE.toDTO(
                produkIntelijenRepository.save(RegisterProdukIntelijenMapper.INSTANCE.toEntity(request)));

        log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Produk Intelijen!!!");
        return produkIntelijen;
    }

    @Override
    @Transactional
    public RegisterProdukIntelijenDTO update(String ids, RegisterProdukIntelijenDTO request) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        produkIntelijen.setJenisProdukIntelijen(request.jenisProdukIntelijen());
        produkIntelijen.setNomorProduk(request.nomorProduk());
        produkIntelijen.setTanggalProduk(request.tanggalProduk());
        produkIntelijen.setSektor(request.sektor());
        produkIntelijen.setPerihal(request.perihal());
        produkIntelijen.setDisposisiPimpinan(request.disposisiPimpinan());
        produkIntelijen.setKeterangan(request.keterangan());
        produkIntelijen.setUrlFile(request.urlFile());

        RegisterProdukIntelijenDTO prodin =
                RegisterProdukIntelijenMapper.INSTANCE.toDTO(produkIntelijenRepository.save(produkIntelijen));

        log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Produk Intelijen!!!");
        return prodin;
    }

    @Override
    @Transactional
    public Page<RegisterProdukIntelijenDTO> findAll(String start, String end, Integer pages, Integer sizes) {

        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
        Pageable pageRequest = PageRequest.of(pages, sizes);

        return produkIntelijenRepository.findProdukIntelijenAll(
                startDate, endDate, pageRequest);
    }

    @Override
    @Transactional
    public Page<RegisterProdukIntelijenDTO> findBySearching(String start, String end, String value, Integer pages, Integer sizes) {

        log.info("\uD83D\uDD0E Value for searching: {}", value);
        if (value.isBlank()) {
        	log.warn("💀 Isi text pencarian kosong...");
			return null;
        }

        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
        Pageable pageRequest = PageRequest.of(pages, sizes);

        return produkIntelijenRepository.findProdukIntelijenBySearching(
                startDate, endDate, value, pageRequest);
    }

    @Override
    @Transactional
    public RegisterProdukIntelijenDTO findByIds(String ids) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        return RegisterProdukIntelijenMapper.INSTANCE.toDTO(produkIntelijen);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        produkIntelijen.setDeleted(true);
        produkIntelijen.setNomorProduk(produkIntelijen.getIds() + " | " + produkIntelijen.getNomorProduk());
        produkIntelijenRepository.save(produkIntelijen);
        log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Produk Intelijen!!!");
    }

	@Override
	public List<Integer[]> countJenisProdukIntelijen(String start, String end) {
        Date startDate = ParserDateUtil.start(start);
        Date endDate = ParserDateUtil.end(end);
        return produkIntelijenRepository.countLaporanIntelijen(startDate, endDate);
	}

}
