package id.go.kejaripalu.bdi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukCreateRequest;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukResponse;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukUpdateRequest;
import id.go.kejaripalu.bdi.service.RegisterSuratMasukService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class RegisterSuratMasukController {

	private RegisterSuratMasukService suratMasukService;
	
	@GetMapping("/surat-masuk")
	public ResponseEntity<Page<RegisterSuratMasuk>> findSuratMasuk(
				@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
				@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
				@RequestParam(name = "jenisSurat", required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(name = "startDate", required = true) String startDate,
				@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(suratMasukService.findSuratMasuk(startDate, endDate, jenisSurat, pages, sizes));		
	}
	
	@GetMapping("/surat-masuk/{id}/detail")
	public ResponseEntity<RegisterSuratMasukResponse> findSuratMasukById(@PathVariable String id) {
		return ResponseEntity.ok().body(suratMasukService.findSuratMasukById(id));
	}
	
	@PostMapping("/surat-masuk")
	public ResponseEntity<Void> createNewSuratMasuk(@Valid @RequestBody RegisterSuratMasukCreateRequest request) {
		suratMasukService.createSuratMasuk(request);
		return ResponseEntity.created(URI.create("/api/v1/surat-masuk")).build();
	}
	
	@PutMapping("/surat-masuk/{id}")
	public ResponseEntity<Void> updateSuratMasuk(@PathVariable String id,
			@RequestBody @Valid RegisterSuratMasukUpdateRequest request) {
		suratMasukService.updateSuratMasuk(id, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/surat-masuk/{id}")
	public ResponseEntity<Void> deleteSuratMasuk(@PathVariable String id) {
		suratMasukService.deleteSuratMasuk(id);
		return ResponseEntity.accepted().build();
	}
	
}
