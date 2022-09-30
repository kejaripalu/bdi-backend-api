package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarCreateRequest;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarResponse;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarUpdateRequest;

public interface RegisterSuratKeluarService {

	void createSuratMasuk(RegisterSuratKeluarCreateRequest request);
	
	void updateSuratMasuk(String id, RegisterSuratKeluarUpdateRequest request);
	
	Page<RegisterSuratKeluar> findSuratMasuk(String start, String end, String stringJenisSurat, Integer pages, Integer sizes);

	RegisterSuratKeluarResponse findSuratMasukById(String id);
	
}
