package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarDTO;
import id.go.kejaripalu.bdi.service.CrudGenericService;
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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterSuratKeluarController {
	
	private final CrudGenericService<RegisterSuratKeluarDTO> suratKeluarService;
	
	@GetMapping("/surat-keluar")
	public ResponseEntity<Page<RegisterSuratKeluarDTO>> findSuratKeluar(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(suratKeluarService.findAll(startDate, endDate, jenisSurat, pages, sizes));
	}
	
	@PostMapping("/surat-keluar")
	public ResponseEntity<RegisterSuratKeluarDTO> createNewSuratKeluar(@Valid @RequestBody RegisterSuratKeluarDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(suratKeluarService.create(request));
	}
	
	@GetMapping("/surat-keluar/{ids}/detail")
	public ResponseEntity<RegisterSuratKeluarDTO> findSuratKeluarByIds(@PathVariable String ids) {
		return ResponseEntity.ok(suratKeluarService.findByIds(ids));
	}
	
	@PutMapping("/surat-keluar/{ids}")
	public ResponseEntity<RegisterSuratKeluarDTO> updateSuratKeluar(@PathVariable String ids,
			@RequestBody @Valid RegisterSuratKeluarDTO request) {
		return ResponseEntity.ok(suratKeluarService.update(ids, request));
	}
	
	@DeleteMapping("/surat-keluar/{ids}")
	public ResponseEntity<Void> deleteSuratKeluar(@PathVariable String ids) {
		suratKeluarService.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/surat-keluar/search")
	public ResponseEntity<Page<RegisterSuratKeluarDTO>> findSuratKeluarBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true, defaultValue = "BIASA") String jenisSurat,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(suratKeluarService.findBySearching(
				startDate, endDate, value, jenisSurat, pages, sizes));
	}
}
