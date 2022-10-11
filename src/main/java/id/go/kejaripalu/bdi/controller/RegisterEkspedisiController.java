package id.go.kejaripalu.bdi.controller;

import java.net.URI;

import javax.validation.Valid;

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

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiRequest;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiResponse;
import id.go.kejaripalu.bdi.service.RegisterEkspedisiService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterEkspedisiController {

	private RegisterEkspedisiService ekspedisiService;
	
	@GetMapping("/ekspedisi")
	public ResponseEntity<Page<RegisterEkspedisi>> findEkspedisi(
				@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
				@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
				@RequestParam(name = "jenisSurat", required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(name = "startDate", required = true) String startDate,
				@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisi(startDate, endDate, jenisSurat, pages, sizes));		
	}
	
	@GetMapping("/ekspedisi/{id}/detail")
	public ResponseEntity<RegisterEkspedisiResponse> findEkspedisiById(@PathVariable String id) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisiById(id));
	}
	
	@GetMapping("/ekspedisi/search")
	public ResponseEntity<Page<RegisterEkspedisi>> findSuratMasukBySearch(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "jenisSurat", required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(name = "value", required = true) String value,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisiBySearching(
				startDate, endDate, value, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/ekspedisi")
	public ResponseEntity<Void> createNewEkspedisi(@Valid @RequestBody RegisterEkspedisiRequest request) {
		ekspedisiService.createEkspedisi(request);
		return ResponseEntity.created(URI.create("/api/v1/ekspedisi")).build();
	}
	
	@PutMapping("/ekspedisi/{id}")
	public ResponseEntity<Void> updateEkspedisi(@PathVariable String id,
			@RequestBody @Valid RegisterEkspedisiRequest request) {
		ekspedisiService.updateEkspedisi(id, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/ekspedisi/{id}")
	public ResponseEntity<Void> deleteEkspedisi(@PathVariable String id) {
		ekspedisiService.deleteEkspedisi(id);
		return ResponseEntity.accepted().build();
	}
	
}
