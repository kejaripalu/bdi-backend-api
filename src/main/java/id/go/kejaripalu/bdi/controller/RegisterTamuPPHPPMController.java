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

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMResponse;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMResquest;
import id.go.kejaripalu.bdi.service.RegisterTamuPPHPPMService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterTamuPPHPPMController {
	
	private RegisterTamuPPHPPMService pphppmService;
	
	@GetMapping("/pphppm")
	public ResponseEntity<Page<RegisterTamuPPHPPM>> findAll(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(pphppmService.findRegisterTamu(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/pphppm/{id}/detail")
	public ResponseEntity<RegisterTamuPPHPPMResponse> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(pphppmService.findRegisterTamuById(id));
	}
	
	@GetMapping("/pphppm/search")
	public ResponseEntity<Page<RegisterTamuPPHPPM>> findBySearch(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "value", required = true) String value,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(pphppmService.findRegisterTamuBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@PostMapping("/pphppm")
	public ResponseEntity<Void> create(@Valid @RequestBody RegisterTamuPPHPPMResquest request) {
		pphppmService.createRegisterTamu(request);
		return ResponseEntity.created(URI.create("/api/v1/pphppm")).build();
	}
	
	@PutMapping("/pphppm/{id}")
	public ResponseEntity<Void> update(@PathVariable String id,
			@RequestBody @Valid RegisterTamuPPHPPMResquest request) {
		pphppmService.updateRegisterTamu(id, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/pphppm/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		pphppmService.deleteRegisterTamu(id);
		return ResponseEntity.accepted().build();
	}
	
}
