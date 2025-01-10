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

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraRequest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraResponse;
import id.go.kejaripalu.bdi.service.RegisterKegiatanIntelijenPamstraService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterKegiatanIntelijenPamstraController {

	private RegisterKegiatanIntelijenPamstraService kegiatanIntelijenService;
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
	@PostMapping("/kegiatan-pamstra")
	public ResponseEntity<Void> create(
			@Valid @RequestBody RegisterKegiatanIntelijenPamstraRequest request) {
		kegiatanIntelijenService.create(request);
		return ResponseEntity.created(URI.create("/api/v1/kegiatan-pamstra")).build();
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
	@PutMapping("/kegiatan-pamstra/{id}")
	public ResponseEntity<Void> update(
			@PathVariable String id,
			@Valid @RequestBody RegisterKegiatanIntelijenPamstraRequest request) {
		kegiatanIntelijenService.update(id, request);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/kegiatan-pamstra/{id}/detail")
	public ResponseEntity<RegisterKegiatanIntelijenPamstraResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(kegiatanIntelijenService.findById(id));
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/kegiatan-pamstra")
	public ResponseEntity<Page<RegisterKegiatanIntelijenPamstra>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(kegiatanIntelijenService.findAll(startDate, endDate, pages, sizes));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/kegiatan-pamstra/search")
	public ResponseEntity<Page<RegisterKegiatanIntelijenPamstra>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(kegiatanIntelijenService.findBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
	@DeleteMapping("/kegiatan-pamstra/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		kegiatanIntelijenService.delete(id);
		return ResponseEntity.accepted().build();
	}
	
}
