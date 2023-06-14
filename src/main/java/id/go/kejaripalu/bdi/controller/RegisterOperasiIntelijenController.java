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

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterOperasiIntelijenService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterOperasiIntelijenController {
	
	private RegisterOperasiIntelijenService opsinService;

	@PostMapping("/opsin")
	public ResponseEntity<Void> create(
			@Valid @RequestBody RegisterOperasiIntelijenRequest request) {
		opsinService.create(request);
		return ResponseEntity.created(URI.create("/api/v1/opsin")).build();
	}
	
	@PutMapping("/opsin/{id}")
	public ResponseEntity<Void> update(
			@PathVariable String id,
			@Valid @RequestBody RegisterOperasiIntelijenRequest request) {
		opsinService.update(id, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/opsin/{id}/detail")
	public ResponseEntity<RegisterOperasiIntelijenResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(opsinService.findById(id));
	}

	@GetMapping("/opsin")
	public ResponseEntity<Page<RegisterOperasiIntelijen>> findAll(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "bidangDirektorat", required = true) String bidangDirektorat,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(opsinService.findAll(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@GetMapping("/opsin/search")
	public ResponseEntity<Page<RegisterOperasiIntelijen>> findBySearch(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "bidangDirektorat", required = true) String bidangDirektorat,
			@RequestParam(name = "value", required = true) String value,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(opsinService.findBySearching(
				startDate, endDate, bidangDirektorat, value, pages, sizes));
	}
	
	@DeleteMapping("/opsin/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		opsinService.delete(id);
		return ResponseEntity.accepted().build();
	}

}
