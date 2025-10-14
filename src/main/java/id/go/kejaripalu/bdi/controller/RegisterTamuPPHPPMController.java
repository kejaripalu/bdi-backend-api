package id.go.kejaripalu.bdi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMDTO;
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

import id.go.kejaripalu.bdi.service.RegisterTamuPPHPPMService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterTamuPPHPPMController {
	
	private final RegisterTamuPPHPPMService pphppmService;
	
	@GetMapping("/pphppm")
	public ResponseEntity<Page<RegisterTamuPPHPPMDTO>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(pphppmService.findAll(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/pphppm/{ids}/detail")
	public ResponseEntity<RegisterTamuPPHPPMDTO> findByIds(@PathVariable String ids) {
		return ResponseEntity.ok(pphppmService.findByIds(ids));
	}
	
	@GetMapping("/pphppm/search")
	public ResponseEntity<Page<RegisterTamuPPHPPMDTO>> findBySearching(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok(pphppmService.findBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@PostMapping("/pphppm")
	public ResponseEntity<RegisterTamuPPHPPMDTO> create(@Valid @RequestBody RegisterTamuPPHPPMDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pphppmService.create(request));
	}
	
	@PutMapping("/pphppm/{ids}")
	public ResponseEntity<RegisterTamuPPHPPMDTO> update(@PathVariable String ids,
			@RequestBody @Valid RegisterTamuPPHPPMDTO request) {
		return ResponseEntity.ok(pphppmService.update(ids, request));
	}
	
	@DeleteMapping("/pphppm/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		pphppmService.delete(ids);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/pphppm/count")
	public ResponseEntity<Map<String, Integer>> countsPphPpm(
			@RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
		List<Integer[]> dataCountPphPpm = pphppmService.countPPHPPM(startDate, endDate);
		Map<String, Integer> map = new HashMap<>();
		map.put("countPPH", (dataCountPphPpm.get(0))[0]);
		map.put("countPPM", (dataCountPphPpm.get(0))[1]);
		return ResponseEntity.ok().body(map);
	}
	
}
