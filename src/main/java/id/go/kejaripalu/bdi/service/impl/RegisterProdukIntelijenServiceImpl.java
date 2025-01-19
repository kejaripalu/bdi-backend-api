package id.go.kejaripalu.bdi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenResponse;
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
    public void createProdukIntelijen(RegisterProdukIntelijenRequest request) {
        RegisterProdukIntelijen produkIntelijen = new RegisterProdukIntelijen();
        produkIntelijen.setJenisProdukIntelijen(request.getJenisProdukIntelijen());
        produkIntelijen.setNomorProduk(request.getNomorProduk());
        produkIntelijen.setTanggalProduk(request.getTanggalProduk());
        produkIntelijen.setSektor(request.getSektor());
        produkIntelijen.setPerihal(request.getPerihal());
        produkIntelijen.setDisposisiPimpinan(request.getDisposisiPimpinan());
        produkIntelijen.setKeterangan(request.getKeterangan());
        produkIntelijen.setUrlFile(request.getUrlFile());

        produkIntelijenRepository.save(produkIntelijen);
        log.info("✔️ Successfully saved!!! ദ്ദി(ᵔᗜᵔ) Produk Intelijen!!!");
    }

    @Override
    @Transactional
    public void updateProdukIntelijen(String ids, RegisterProdukIntelijenRequest request) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        produkIntelijen.setJenisProdukIntelijen(
                request.getJenisProdukIntelijen() == null ?
                        produkIntelijen.getJenisProdukIntelijen() : request.getJenisProdukIntelijen());
        produkIntelijen.setNomorProduk(
                request.getNomorProduk() == null || request.getNomorProduk().isBlank() ?
                        produkIntelijen.getNomorProduk() : request.getNomorProduk());
        produkIntelijen.setTanggalProduk(
                request.getTanggalProduk() == null ?
                        produkIntelijen.getTanggalProduk() : request.getTanggalProduk());
        produkIntelijen.setSektor(
                request.getSektor() == null ?
                        produkIntelijen.getSektor() : request.getSektor());
        produkIntelijen.setPerihal(
                request.getPerihal() == null || request.getPerihal().isBlank() ?
                        produkIntelijen.getPerihal() : request.getPerihal());
        produkIntelijen.setDisposisiPimpinan(request.getDisposisiPimpinan());
        produkIntelijen.setKeterangan(request.getKeterangan());
        produkIntelijen.setUrlFile(request.getUrlFile());

        produkIntelijenRepository.save(produkIntelijen);
        log.info("✔️ Successfully updated!!! ദ്ദി(ᵔᗜᵔ) Produk Intelijen!!!");
    }

    @Override
    @Transactional
    public Page<RegisterProdukIntelijen> findProdukIntelijen(String start, String end, Integer pages, Integer sizes) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        Pageable pageRequest = PageRequest.of(pages, sizes);
        Page<RegisterProdukIntelijen> pagesProdukIntelijen = produkIntelijenRepository.findProdukIntelijenAll(
                startDate, endDate, pageRequest);
        
        return pagesProdukIntelijen;
    }

    @Override
    @Transactional
    public Page<RegisterProdukIntelijen> findProdukIntelijenBySearching(String start, String end, String value, Integer pages, Integer sizes) {
        log.info("🔎 Value for searching: " + value);
        if (value.isBlank() || value.isEmpty() || value.equals("")) {
        	log.warn("💀 Isi text pencarian kosong...");
			return null;
        }

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        } catch (ParseException e) {
            log.error("💀 " + e.getMessage());
        }

        Pageable pageRequest = PageRequest.of(pages, sizes);
        Page<RegisterProdukIntelijen> pagesProdukIntelijen = produkIntelijenRepository.findProdukIntelijenBySearching(
                startDate, endDate, value, pageRequest);

        return pagesProdukIntelijen;
    }

    @Override
    @Transactional
    public RegisterProdukIntelijenResponse findProdukIntelijenByIds(String ids) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        RegisterProdukIntelijenResponse response = new RegisterProdukIntelijenResponse();
        response.setIds(produkIntelijen.getIds());
        response.setJenisProdukIntelijen(produkIntelijen.getJenisProdukIntelijen());
        response.setNomorProduk(produkIntelijen.getNomorProduk());
        response.setTanggalProduk(produkIntelijen.getTanggalProduk());
        response.setSektor(produkIntelijen.getSektor());
        response.setPerihal(produkIntelijen.getPerihal());
        response.setDisposisiPimpinan(produkIntelijen.getDisposisiPimpinan());
        response.setKeterangan(produkIntelijen.getKeterangan());
        response.setUrlFile(produkIntelijen.getUrlFile());

        return response;
    }

    @Override
    @Transactional
    public void deleteProdukIntelijen(String ids) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdsAndDeletedFalse(ids)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        produkIntelijen.setDeleted(true);
        produkIntelijen.setNomorProduk(produkIntelijen.getIds() + " | " + produkIntelijen.getNomorProduk());
        produkIntelijenRepository.save(produkIntelijen);
        log.info("✔️ Soft Delete Successfully!!! ദ്ദി(ᵔᗜᵔ) Produk Intelijen!!!");
    }

}
