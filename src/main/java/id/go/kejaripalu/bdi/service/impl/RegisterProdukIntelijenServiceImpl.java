package id.go.kejaripalu.bdi.service.impl;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.RegisterProdukIntelijenRepository;
import id.go.kejaripalu.bdi.service.RegisterProdukIntelijenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterProdukIntelijenServiceImpl implements RegisterProdukIntelijenService {

    private RegisterProdukIntelijenRepository produkIntelijenRepository;

    @Override
    @Transactional
    public void createProdukIntelijen(RegisterProdukIntelijenRequest request) {
        RegisterProdukIntelijen produkIntelijen = new RegisterProdukIntelijen();
        produkIntelijen.setJenisProdukIntelijen(request.getJenisProdukIntelijen());
        produkIntelijen.setNomorProduk(request.getNomorProduk());
        produkIntelijen.setTanggalProduk(request.getTanggalProduk());
        produkIntelijen.setBidang(request.getBidang());
        produkIntelijen.setPerihal(request.getPerihal());
        produkIntelijen.setDisposisiPimpinan(request.getDisposisiPimpinan());
        produkIntelijen.setKeterangan(request.getKeterangan());
        produkIntelijen.setUrlFile(request.getUrlFile());

        log.info("Produk Intelijen Request: " + produkIntelijen);
        produkIntelijenRepository.save(produkIntelijen);
        log.info("Saved Produk Intelijen: " + produkIntelijen);
    }

    @Override
    @Transactional
    public void updateProdukIntelijen(String id, RegisterProdukIntelijenRequest request) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findById(id)
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
        produkIntelijen.setBidang(
                request.getBidang() == null ?
                        produkIntelijen.getBidang() : request.getBidang());
        produkIntelijen.setPerihal(
                request.getPerihal() == null || request.getPerihal().isBlank() ?
                        produkIntelijen.getPerihal() : request.getPerihal());
        produkIntelijen.setDisposisiPimpinan(request.getDisposisiPimpinan());
        produkIntelijen.setKeterangan(request.getKeterangan());
        produkIntelijen.setUrlFile(request.getUrlFile());

        log.info("Produk Intelijen Request: " + produkIntelijen);
        produkIntelijenRepository.save(produkIntelijen);
        log.info("Update Produk Intelijen: " + produkIntelijen);
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
        log.info("Value : " + value);
        if (value.isBlank() || value.isEmpty() || value.equals("")) {
            log.error("Isi text pencarian kosong...");
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
        Page<RegisterProdukIntelijen> pagesProdukIntelijen = produkIntelijenRepository.findProdukIntelijenBySearching(
                startDate, endDate, value, pageRequest);

        return pagesProdukIntelijen;
    }

    @Override
    @Transactional
    public RegisterProdukIntelijenResponse findProdukIntelijenById(String id) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));

        RegisterProdukIntelijenResponse response = new RegisterProdukIntelijenResponse();
        response.setId(produkIntelijen.getId());
        response.setJenisProdukIntelijen(produkIntelijen.getJenisProdukIntelijen());
        response.setNomorProduk(produkIntelijen.getNomorProduk());
        response.setTanggalProduk(produkIntelijen.getTanggalProduk());
        response.setBidang(produkIntelijen.getBidang());
        response.setDeskripsiBidang(produkIntelijen.getBidang().getDescription());
        response.setPerihal(produkIntelijen.getPerihal());
        response.setDisposisiPimpinan(produkIntelijen.getDisposisiPimpinan());
        response.setKeterangan(produkIntelijen.getKeterangan());
        response.setUrlFile(produkIntelijen.getUrlFile());

        return response;
    }

    @Override
    @Transactional
    public void deleteProdukIntelijen(String id) {
        RegisterProdukIntelijen produkIntelijen = produkIntelijenRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOUND"));
        produkIntelijen.setDeleted(Boolean.TRUE);
        produkIntelijen.setNomorProduk(produkIntelijen.getId() + " | " + produkIntelijen.getNomorProduk());
        produkIntelijenRepository.save(produkIntelijen);
        log.info("Soft Delete: " + produkIntelijen);
    }

}
