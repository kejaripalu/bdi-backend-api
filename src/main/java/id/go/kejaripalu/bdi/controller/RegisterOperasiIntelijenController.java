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

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterOperasiIntelijenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterOperasiIntelijenController {
	
	private final RegisterOperasiIntelijenService opsinService;

	@PostMapping("/opsin")
	public ResponseEntity<Void> create(
			@Valid @RequestBody RegisterOperasiIntelijenRequest request) {
		opsinService.create(request);
		return ResponseEntity.created(URI.create("/api/v1/opsin")).build();
	}
	
	@PutMapping("/opsin/{ids}")
	public ResponseEntity<Void> update(
			@PathVariable String ids,
			@Valid @RequestBody RegisterOperasiIntelijenRequest request) {
		opsinService.update(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/opsin/{ids}/detail")
	public ResponseEntity<RegisterOperasiIntelijenResponse> findById(@PathVariable String ids) {
		return ResponseEntity.ok().body(opsinService.findByIds(ids));
	}

	@GetMapping("/opsin")
	public ResponseEntity<Page<RegisterOperasiIntelijen>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(opsinService.findAll(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@GetMapping("/opsin/search")
	public ResponseEntity<Page<RegisterOperasiIntelijen>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(opsinService.findBySearching(
				startDate, endDate, bidangDirektorat, value, pages, sizes));
	}
	
	@DeleteMapping("/opsin/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		opsinService.delete(ids);
		return ResponseEntity.accepted().build();
	}

}
