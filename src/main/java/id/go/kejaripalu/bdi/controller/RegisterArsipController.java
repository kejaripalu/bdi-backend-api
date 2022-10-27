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

import id.go.kejaripalu.bdi.domain.RegisterArsip;
import id.go.kejaripalu.bdi.dto.RegisterArsipRequest;
import id.go.kejaripalu.bdi.dto.RegisterArsipResponse;
import id.go.kejaripalu.bdi.service.RegisterArsipService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterArsipController {
	
	private RegisterArsipService arsipService;
	
	@GetMapping("/arsip")
	public ResponseEntity<Page<RegisterArsip>> findAll(
				@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
				@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
				@RequestParam(name = "startDate", required = true) String startDate,
				@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(arsipService.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/arsip/{id}/detail")
	public ResponseEntity<RegisterArsipResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(arsipService.findById(id));
	}
	
	@GetMapping("/arsip/search")
	public ResponseEntity<Page<RegisterArsip>> findBySearch(
				@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
				@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
				@RequestParam(name = "value", required = true) String value,
				@RequestParam(name = "startDate", required = true) String startDate,
				@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(arsipService.findBySearching(startDate, endDate, value, pages, sizes));
	}

	@PostMapping("/arsip")
	public ResponseEntity<Void> create(@Valid @RequestBody RegisterArsipRequest request) {
		arsipService.create(request);
		return ResponseEntity.created(URI.create("/api/v1/arsip")).build();
	}
	
	@PutMapping("/arsip/{id}")
	public ResponseEntity<Void> update(@PathVariable String id,
			@RequestBody @Valid RegisterArsipRequest request) {
		arsipService.update(id, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/arsip/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		arsipService.delete(id);
		return ResponseEntity.accepted().build();
	}
	
}
