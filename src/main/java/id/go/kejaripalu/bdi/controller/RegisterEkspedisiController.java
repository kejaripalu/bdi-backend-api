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

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiRequest;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiResponse;
import id.go.kejaripalu.bdi.service.RegisterEkspedisiService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterEkspedisiController {

	private final RegisterEkspedisiService ekspedisiService;
	
	@GetMapping("/ekspedisi")
	public ResponseEntity<Page<RegisterEkspedisi>> findAll(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisi(startDate, endDate, jenisSurat, pages, sizes));		
	}
	
	@GetMapping("/ekspedisi/{ids}/detail")
	public ResponseEntity<RegisterEkspedisiResponse> findById(@PathVariable String ids) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisiByIds(ids));
	}
	
	@GetMapping("/ekspedisi/search")
	public ResponseEntity<Page<RegisterEkspedisi>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisiBySearching(
				startDate, endDate, value, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/ekspedisi")
	public ResponseEntity<Void> create(@Valid @RequestBody RegisterEkspedisiRequest request) {
		ekspedisiService.createEkspedisi(request);
		return ResponseEntity.created(URI.create("/api/v1/ekspedisi")).build();
	}
	
	@PutMapping("/ekspedisi/{ids}")
	public ResponseEntity<Void> update(@PathVariable String ids,
			@RequestBody @Valid RegisterEkspedisiRequest request) {
		ekspedisiService.updateEkspedisi(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/ekspedisi/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		ekspedisiService.deleteEkspedisi(ids);
		return ResponseEntity.accepted().build();
	}
	
}
