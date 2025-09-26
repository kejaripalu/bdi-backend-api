package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterArsipDTO;
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

import id.go.kejaripalu.bdi.service.RegisterArsipService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterArsipController {
	
	private final RegisterArsipService<RegisterArsipDTO> arsipService;
	
	@GetMapping("/arsip")
	public ResponseEntity<Page<RegisterArsipDTO>> findAll(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(arsipService.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/arsip/{ids}/detail")
	public ResponseEntity<RegisterArsipDTO> findById(@PathVariable String ids) {
		return ResponseEntity.ok(arsipService.findByIds(ids));
	}
	
	@GetMapping("/arsip/search")
	public ResponseEntity<Page<RegisterArsipDTO>> findBySearch(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true) String value,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(arsipService.findBySearching(startDate, endDate, value, pages, sizes));
	}

	@PostMapping("/arsip")
	public ResponseEntity<RegisterArsipDTO> create(@Valid @RequestBody RegisterArsipDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(arsipService.create(request));
	}
	
	@PutMapping("/arsip/{ids}")
	public ResponseEntity<RegisterArsipDTO> update(@PathVariable String ids,
			@RequestBody @Valid RegisterArsipDTO request) {
		return ResponseEntity.ok(arsipService.update(ids, request));
	}
	
	@DeleteMapping("/arsip/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		arsipService.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
}
