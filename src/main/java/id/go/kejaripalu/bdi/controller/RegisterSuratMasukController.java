package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterSuratMasukDTO;
import id.go.kejaripalu.bdi.service.CrudGenericService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterSuratMasukController {

	private final CrudGenericService<RegisterSuratMasukDTO> suratMasukService;
	
	@GetMapping("/surat-masuk")
	public ResponseEntity<Page<RegisterSuratMasukDTO>> findSuratMasuk(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(suratMasukService.findSuratMasuk(startDate, endDate, jenisSurat, pages, sizes));
	}
	
	@GetMapping("/surat-masuk/{ids}/detail")
	public ResponseEntity<RegisterSuratMasukDTO> findSuratMasukByIds(@PathVariable String ids) {
		return ResponseEntity.ok(suratMasukService.findSuratMasukByIds(ids));
	}
	
	@GetMapping("/surat-masuk/search")
	public ResponseEntity<Page<RegisterSuratMasukDTO>> findSuratMasukBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(suratMasukService.findSuratMasukBySearching(
				startDate, endDate, value, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/surat-masuk")
	public ResponseEntity<RegisterSuratMasukDTO> createNewSuratMasuk(@Valid @RequestBody RegisterSuratMasukDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(suratMasukService.create(request));
	}
	
	@PutMapping("/surat-masuk/{ids}")
	public ResponseEntity<RegisterSuratMasukDTO> updateSuratMasuk(@PathVariable String ids,
			@RequestBody @Valid RegisterSuratMasukDTO request) {
		return ResponseEntity.ok(suratMasukService.update(ids, request));
	}
	
	@DeleteMapping("/surat-masuk/{ids}")
	public ResponseEntity<Void> deleteSuratMasuk(@PathVariable String ids) {
		suratMasukService.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
}
