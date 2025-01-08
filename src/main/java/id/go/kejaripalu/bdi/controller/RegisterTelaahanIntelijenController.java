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

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterTelaahanIntelijenService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterTelaahanIntelijenController {

	private RegisterTelaahanIntelijenService service;
	
	@PostMapping("/lahin")
	public ResponseEntity<Void> create(
			@Valid @RequestBody RegisterTelaahanIntelijenRequest request) {
		service.create(request);
		return ResponseEntity.created(URI.create("/api/v1/lahin")).build();
	}
	
	@PutMapping("/lahin/{id}")
	public ResponseEntity<Void> update(
			@PathVariable String id,
			@Valid @RequestBody RegisterTelaahanIntelijenRequest request) {
		service.update(id, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/lahin/{id}/detail")
	public ResponseEntity<RegisterTelaahanIntelijenResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping("/lahin")
	public ResponseEntity<Page<RegisterTelaahanIntelijen>> findAll(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(service.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/lahin/search")
	public ResponseEntity<Page<RegisterTelaahanIntelijen>> findBySearch(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "value", required = true) String value,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(service.findBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@DeleteMapping("/lahin/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.accepted().build();
	}
	
}
