package id.go.kejaripalu.bdi.controller;

import java.net.URI;

import jakarta.validation.Valid;

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

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterKegiatanIntelijenService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterKegiatanIntelijenController {

	private RegisterKegiatanIntelijenService kegiatanIntelijenService;
	
	@PostMapping("/kegiatan")
	public ResponseEntity<Void> create(
			@Valid @RequestBody RegisterKegiatanIntelijenRequest request) {
		kegiatanIntelijenService.create(request);
		return ResponseEntity.created(URI.create("/api/v1/kegiatan")).build();
	}
	
	@PutMapping("/kegiatan/{id}")
	public ResponseEntity<Void> update(
			@PathVariable String id,
			@Valid @RequestBody RegisterKegiatanIntelijenRequest request) {
		kegiatanIntelijenService.update(id, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/kegiatan/{id}/detail")
	public ResponseEntity<RegisterKegiatanIntelijenResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(kegiatanIntelijenService.findById(id));
	}

	@GetMapping("/kegiatan")
	public ResponseEntity<Page<RegisterKegiatanIntelijen>> findAll(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "bidangDirektorat", required = true) String bidangDirektorat,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(kegiatanIntelijenService.findAll(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@GetMapping("/kegiatan/search")
	public ResponseEntity<Page<RegisterKegiatanIntelijen>> findBySearch(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "bidangDirektorat", required = true) String bidangDirektorat,
			@RequestParam(name = "value", required = true) String value,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(kegiatanIntelijenService.findBySearching(
				startDate, endDate, bidangDirektorat, value, pages, sizes));
	}
	
	@DeleteMapping("/kegiatan/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		kegiatanIntelijenService.delete(id);
		return ResponseEntity.accepted().build();
	}
	
}
