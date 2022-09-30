package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarCreateRequest;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarResponse;

public interface RegisterSuratKeluarService {

	void createSuratMasuk(RegisterSuratKeluarCreateRequest request);
	
	Page<RegisterSuratKeluar> findSuratMasuk(String start, String end, String stringJenisSurat, Integer pages, Integer sizes);

	RegisterSuratKeluarResponse findSuratMasukById(String id);
	
}
