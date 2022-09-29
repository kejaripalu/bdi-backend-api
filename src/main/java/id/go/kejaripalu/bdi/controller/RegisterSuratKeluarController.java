package id.go.kejaripalu.bdi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarCreateRequest;
import id.go.kejaripalu.bdi.service.RegisterSuratKeluarService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterSuratKeluarController {
	
	private RegisterSuratKeluarService suratKeluarService;
	
	@GetMapping("/surat-keluar")
	public ResponseEntity<Page<RegisterSuratKeluar>> findSuratKeluar(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "jenisSurat", required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(suratKeluarService.findSuratMasuk(startDate, endDate, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/surat-keluar")
	public ResponseEntity<Void> createNewSuratKeluar(@Valid @RequestBody RegisterSuratKeluarCreateRequest request) {
		suratKeluarService.createSuratMasuk(request);
		return ResponseEntity.created(URI.create("/api/v1/surat-keluar")).build();
	}
	
}
