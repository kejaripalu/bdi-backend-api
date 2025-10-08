package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenDTO;
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
	public ResponseEntity<RegisterKerjaIntelijenDTO> create(
            @Valid @RequestBody RegisterKerjaIntelijenDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(rkiService.create(request));
	}
	
	@PutMapping("/rki/{ids}")
	public ResponseEntity<RegisterKerjaIntelijenDTO> update(
			@PathVariable String ids,
			@Valid @RequestBody RegisterKerjaIntelijenDTO request) {
		return ResponseEntity.ok(rkiService.update(ids, request));
	}
	
	@GetMapping("/rki/{ids}/detail")
	public ResponseEntity<RegisterKerjaIntelijenDTO> findById(@PathVariable String ids) {
		return ResponseEntity.ok(rkiService.findByIds(ids));
	}
	
	@GetMapping("/rki")
	public ResponseEntity<Page<RegisterKerjaIntelijenDTO>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(rkiService.findAll(startDate, endDate, bidangDirektorat, pages, sizes));
	}
	
	@GetMapping("/rki/search")
	public ResponseEntity<Page<RegisterKerjaIntelijenDTO>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String bidangDirektorat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(rkiService.findBySearching(
				startDate, endDate, value, bidangDirektorat, pages, sizes));
	}

	@DeleteMapping("/rki/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		rkiService.delete(ids);
		return ResponseEntity.accepted().build();
	}
}
