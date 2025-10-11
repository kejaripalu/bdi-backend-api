package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenDTO;
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
	public ResponseEntity<RegisterOperasiIntelijenDTO> create(
			@Valid @RequestBody RegisterOperasiIntelijenDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(opsinService.create(request));
	}
	
	@PutMapping("/opsin/{ids}")
	public ResponseEntity<RegisterOperasiIntelijenDTO> update(
			@PathVariable String ids,
			@Valid @RequestBody RegisterOperasiIntelijenDTO request) {
		return ResponseEntity.ok(opsinService.update(ids, request));
	}
	
	@GetMapping("/opsin/{ids}/detail")
	public ResponseEntity<RegisterOperasiIntelijenDTO> findById(@PathVariable String ids) {
		return ResponseEntity.ok(opsinService.findByIds(ids));
	}

	@GetMapping("/opsin")
	public ResponseEntity<Page<RegisterOperasiIntelijenDTO>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(opsinService.findAll(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@GetMapping("/opsin/search")
	public ResponseEntity<Page<RegisterOperasiIntelijenDTO>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(opsinService.findBySearching(
				startDate, endDate, bidangDirektorat, value, pages, sizes));
	}
	
	@DeleteMapping("/opsin/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		opsinService.delete(ids);
		return ResponseEntity.accepted().build();
	}
}
