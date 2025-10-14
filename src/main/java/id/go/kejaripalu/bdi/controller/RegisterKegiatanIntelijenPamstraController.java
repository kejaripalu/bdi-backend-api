package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraDTO;
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

import id.go.kejaripalu.bdi.service.RegisterKegiatanIntelijenPamstraService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterKegiatanIntelijenPamstraController {

	private final RegisterKegiatanIntelijenPamstraService kegiatanIntelijenService;
	
	@PostMapping("/kegiatan-pamstra")
	public ResponseEntity<RegisterKegiatanIntelijenPamstraDTO> create(
			@Valid @RequestBody RegisterKegiatanIntelijenPamstraDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(kegiatanIntelijenService.create(request));
	}
	
	@PutMapping("/kegiatan-pamstra/{ids}")
	public ResponseEntity<RegisterKegiatanIntelijenPamstraDTO> update(
			@PathVariable String ids,
			@Valid @RequestBody RegisterKegiatanIntelijenPamstraDTO request) {
		return ResponseEntity.ok(kegiatanIntelijenService.update(ids, request));
	}
	
	@GetMapping("/kegiatan-pamstra/{ids}/detail")
	public ResponseEntity<RegisterKegiatanIntelijenPamstraDTO> findById(@PathVariable String ids) {
		return ResponseEntity.ok(kegiatanIntelijenService.findByIds(ids));
	}

	@GetMapping("/kegiatan-pamstra")
	public ResponseEntity<Page<RegisterKegiatanIntelijenPamstraDTO>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(kegiatanIntelijenService.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/kegiatan-pamstra/search")
	public ResponseEntity<Page<RegisterKegiatanIntelijenPamstraDTO>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(kegiatanIntelijenService.findBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@DeleteMapping("/kegiatan-pamstra/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		kegiatanIntelijenService.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
}
