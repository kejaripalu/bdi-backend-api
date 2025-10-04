package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterEkspedisiDTO;
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

import id.go.kejaripalu.bdi.service.RegisterEkspedisiService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterEkspedisiController {

	private final RegisterEkspedisiService<RegisterEkspedisiDTO> ekspedisiService;
	
	@GetMapping("/ekspedisi")
	public ResponseEntity<Page<RegisterEkspedisiDTO>> findAll(
				@RequestParam(required = true, defaultValue = "0") Integer pages,
				@RequestParam(required = true, defaultValue = "20") Integer sizes,
				@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
				@RequestParam(required = true) String startDate,
				@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(ekspedisiService.findAll(startDate, endDate, jenisSurat, pages, sizes));
	}
	
	@GetMapping("/ekspedisi/{ids}/detail")
	public ResponseEntity<RegisterEkspedisiDTO> findById(@PathVariable String ids) {
		return ResponseEntity.ok(ekspedisiService.findByIds(ids));
	}
	
	@GetMapping("/ekspedisi/search")
	public ResponseEntity<Page<RegisterEkspedisiDTO>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(ekspedisiService.findBySearching(
				startDate, endDate, value, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/ekspedisi")
	public ResponseEntity<RegisterEkspedisiDTO> create(@Valid @RequestBody RegisterEkspedisiDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ekspedisiService.create(request));
	}
	
	@PutMapping("/ekspedisi/{ids}")
	public ResponseEntity<RegisterEkspedisiDTO> update(@PathVariable String ids,
			@RequestBody @Valid RegisterEkspedisiDTO request) {
		return ResponseEntity.ok(ekspedisiService.update(ids, request));
	}
	
	@DeleteMapping("/ekspedisi/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		ekspedisiService.delete(ids);
		return ResponseEntity.accepted().build();
	}

}
