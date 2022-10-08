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

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterKerjaIntelijenService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterKerjaIntelijenController {

	private RegisterKerjaIntelijenService rkiService;
	
	@PostMapping("/rki")
	public ResponseEntity<Void> createNewRKI(
			@Valid @RequestBody RegisterKerjaIntelijenRequest request) {
		rkiService.createRKI(request);
		return ResponseEntity.created(URI.create("/api/v1/rki")).build();
	}
	
	@PutMapping("/rki/{id}")
	public ResponseEntity<Void> updateRKI(
			@PathVariable String id,
			@Valid @RequestBody RegisterKerjaIntelijenRequest request) {
		rkiService.updateRKI(id, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/rki/{id}/detail")
	public ResponseEntity<RegisterKerjaIntelijenResponse> findRKIById(@PathVariable String id) {
		return ResponseEntity.ok().body(rkiService.findRKIbyId(id));
	}
	
	@GetMapping("/rki")
	public ResponseEntity<Page<RegisterKerjaIntelijen>> findRKI(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
			@RequestParam(name = "bidangDirektorat", required = true) String bidangDirektorat,
			@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return ResponseEntity.ok().body(rkiService.findRKI(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@DeleteMapping("/rki/{id}")
	public ResponseEntity<Void> deleteRKI(@PathVariable String id) {
		rkiService.deleteRKI(id);
		return ResponseEntity.accepted().build();
	}
	
}
