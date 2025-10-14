package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenDTO;
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
	public ResponseEntity<RegisterTelaahanIntelijenDTO> create(
			@Valid @RequestBody RegisterTelaahanIntelijenDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}
	
	@PutMapping("/lahin/{ids}")
	public ResponseEntity<RegisterTelaahanIntelijenDTO> update(
			@PathVariable String ids,
			@Valid @RequestBody RegisterTelaahanIntelijenDTO request) {
		return ResponseEntity.ok(service.update(ids, request));
	}
	
	@GetMapping("/lahin/{ids}/detail")
	public ResponseEntity<RegisterTelaahanIntelijenDTO> findByIds(@PathVariable String ids) {
		return ResponseEntity.ok(service.findByIds(ids));
	}

	@GetMapping("/lahin")
	public ResponseEntity<Page<RegisterTelaahanIntelijenDTO>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(service.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/lahin/search")
	public ResponseEntity<Page<RegisterTelaahanIntelijenDTO>> findBySearching(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(service.findBySearching(startDate, endDate, value, pages, sizes));
	}
	
	@DeleteMapping("/lahin/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		service.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
}
