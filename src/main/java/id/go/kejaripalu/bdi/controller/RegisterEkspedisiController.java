package id.go.kejaripalu.bdi.controller;

import java.net.URI;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/ekspedisi")
	public ResponseEntity<Page<RegisterEkspedisi>> findAll(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisi(startDate, endDate, jenisSurat, pages, sizes));		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/ekspedisi/{id}/detail")
	public ResponseEntity<RegisterEkspedisiResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(ekspedisiService.findEkspedisiById(id));
	}
	
	@PreAuthorize("isAuthenticated()")
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
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
	@PostMapping("/ekspedisi")
	public ResponseEntity<Void> create(@Valid @RequestBody RegisterEkspedisiRequest request) {
		ekspedisiService.createEkspedisi(request);
		return ResponseEntity.created(URI.create("/api/v1/ekspedisi")).build();
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
	@PutMapping("/ekspedisi/{id}")
	public ResponseEntity<Void> update(@PathVariable String id,
			@RequestBody @Valid RegisterEkspedisiRequest request) {
		ekspedisiService.updateEkspedisi(id, request);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
	@DeleteMapping("/ekspedisi/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		ekspedisiService.deleteEkspedisi(id);
		return ResponseEntity.accepted().build();
	}
	
}
