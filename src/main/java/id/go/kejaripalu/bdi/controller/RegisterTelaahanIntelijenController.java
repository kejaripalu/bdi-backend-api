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

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterTelaahanIntelijenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterTelaahanIntelijenController {

	private final RegisterTelaahanIntelijenService service;
	
	@PostMapping("/lahin")
	public ResponseEntity<Void> create(
			@Valid @RequestBody RegisterTelaahanIntelijenRequest request) {
		service.create(request);
		return ResponseEntity.created(URI.create("/api/v1/lahin")).build();
	}
	
	@PutMapping("/lahin/{ids}")
	public ResponseEntity<Void> update(
			@PathVariable String ids,
			@Valid @RequestBody RegisterTelaahanIntelijenRequest request) {
		service.update(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/lahin/{ids}/detail")
	public ResponseEntity<RegisterTelaahanIntelijenResponse> findById(@PathVariable String ids) {
		return ResponseEntity.ok().body(service.findByIds(ids));
	}

	@GetMapping("/lahin")
	public ResponseEntity<Page<RegisterTelaahanIntelijen>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(service.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/lahin/search")
	public ResponseEntity<Page<RegisterTelaahanIntelijen>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(service.findBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@DeleteMapping("/lahin/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		service.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
}
