package id.go.kejaripalu.bdi.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterSuratMasukController {

	private final RegisterSuratMasukService suratMasukService;
	
	@GetMapping("/surat-masuk")
	public ResponseEntity<Page<RegisterSuratMasuk>> findSuratMasuk(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(suratMasukService.findSuratMasuk(startDate, endDate, jenisSurat, pages, sizes));		
	}
	
	@GetMapping("/surat-masuk/{ids}/detail")
	public ResponseEntity<RegisterSuratMasukResponse> findSuratMasukByIds(@PathVariable String ids) {
		return ResponseEntity.ok().body(suratMasukService.findSuratMasukByIds(ids));
	}
	
	@GetMapping("/surat-masuk/search")
	public ResponseEntity<Page<RegisterSuratMasuk>> findSuratMasukBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(suratMasukService.findSuratMasukBySearching(
				startDate, endDate, value, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/surat-masuk")
	public ResponseEntity<Void> createNewSuratMasuk(@Valid @RequestBody RegisterSuratMasukCreateRequest request) {
		suratMasukService.createSuratMasuk(request);
		return ResponseEntity.created(URI.create("/api/v1/surat-masuk")).build();
	}
	
	@PutMapping("/surat-masuk/{ids}")
	public ResponseEntity<Void> updateSuratMasuk(@PathVariable String ids,
			@RequestBody @Valid RegisterSuratMasukUpdateRequest request) {
		suratMasukService.updateSuratMasuk(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/surat-masuk/{ids}")
	public ResponseEntity<Void> deleteSuratMasuk(@PathVariable String ids) {
		suratMasukService.deleteSuratMasuk(ids);
		return ResponseEntity.accepted().build();
	}
	
}
