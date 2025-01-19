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

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumRequest;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumResponse;
import id.go.kejaripalu.bdi.service.RegisterPenkumLuhkumService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterPenkumLuhkumController {
	
	private final RegisterPenkumLuhkumService penkumLuhkumService;
	
	@GetMapping("/penkumluhkum")
	public ResponseEntity<Page<RegisterPenkumLuhkum>> findAll(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true) String jenisPenkumLuhkum,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(penkumLuhkumService.findAll(startDate, endDate, jenisPenkumLuhkum, pages, sizes));
	}

	@GetMapping("/penkumluhkum/{ids}/detail")
	public ResponseEntity<RegisterPenkumLuhkumResponse> findByIds(@PathVariable String ids) {
		return ResponseEntity.ok().body(penkumLuhkumService.findByIds(ids));
	}
	
	@GetMapping("/penkumluhkum/search")
	public ResponseEntity<Page<RegisterPenkumLuhkum>> findBySearch(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true) String jenisPenkumLuhkum,
				@RequestParam(required = true) String value,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(penkumLuhkumService.findBySearching(startDate, endDate, jenisPenkumLuhkum, value, pages, sizes));
	}

	@PostMapping("/penkumluhkum")
	public ResponseEntity<Void> create(@Valid @RequestBody RegisterPenkumLuhkumRequest request) {
		penkumLuhkumService.create(request);
		return ResponseEntity.created(URI.create("/api/v1/penkumluhkum")).build();
	}
	
	@PutMapping("/penkumluhkum/{ids}")
	public ResponseEntity<Void> update(@PathVariable String ids,
			@RequestBody @Valid RegisterPenkumLuhkumRequest request) {
		penkumLuhkumService.update(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/penkumluhkum/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		penkumLuhkumService.delete(ids);
		return ResponseEntity.accepted().build();
	}

}
