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

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterKerjaIntelijenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterKerjaIntelijenController {

	private final RegisterKerjaIntelijenService rkiService;
	
	@PostMapping("/rki")
	public ResponseEntity<Void> createNewRKI(
			@Valid @RequestBody RegisterKerjaIntelijenRequest request) {
		rkiService.createRKI(request);
		return ResponseEntity.created(URI.create("/api/v1/rki")).build();
	}
	
	@PutMapping("/rki/{ids}")
	public ResponseEntity<Void> updateRKI(
			@PathVariable String ids,
			@Valid @RequestBody RegisterKerjaIntelijenRequest request) {
		rkiService.updateRKI(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/rki/{ids}/detail")
	public ResponseEntity<RegisterKerjaIntelijenResponse> findRKIById(@PathVariable String ids) {
		return ResponseEntity.ok().body(rkiService.findRKIbyIds(ids));
	}
	
	@GetMapping("/rki")
	public ResponseEntity<Page<RegisterKerjaIntelijen>> findRKI(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(rkiService.findRKI(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@GetMapping("/rki/search")
	public ResponseEntity<Page<RegisterKerjaIntelijen>> findRKIBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(rkiService.findRKIBySearching(
				startDate, endDate, value, bidangDirektorat, pages, sizes));
	}

	@DeleteMapping("/rki/{ids}")
	public ResponseEntity<Void> deleteRKI(@PathVariable String ids) {
		rkiService.deleteRKI(ids);
		return ResponseEntity.accepted().build();
	}
	
}
