package id.go.kejaripalu.bdi.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterTamuPPHPPMController {
	
	private final RegisterTamuPPHPPMService pphppmService;
	
	@GetMapping("/pphppm")
	public ResponseEntity<Page<RegisterTamuPPHPPM>> findAll(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(pphppmService.findRegisterTamu(startDate, endDate, pages, sizes));
	}
	
	@GetMapping("/pphppm/{ids}/detail")
	public ResponseEntity<RegisterTamuPPHPPMResponse> findByIds(@PathVariable String ids) {
		return ResponseEntity.ok().body(pphppmService.findRegisterTamuByIds(ids));
	}
	
	@GetMapping("/pphppm/search")
	public ResponseEntity<Page<RegisterTamuPPHPPM>> findBySearch(
			@RequestParam(required = true, defaultValue = "0") Integer pages,
			@RequestParam(required = true, defaultValue = "20") Integer sizes,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate) {
		return ResponseEntity.ok().body(pphppmService.findRegisterTamuBySearching(
				startDate, endDate, value, pages, sizes));
	}
	
	@PostMapping("/pphppm")
	public ResponseEntity<Void> create(@Valid @RequestBody RegisterTamuPPHPPMResquest request) {
		pphppmService.createRegisterTamu(request);
		return ResponseEntity.created(URI.create("/api/v1/pphppm")).build();
	}
	
	@PutMapping("/pphppm/{ids}")
	public ResponseEntity<Void> update(@PathVariable String ids,
			@RequestBody @Valid RegisterTamuPPHPPMResquest request) {
		pphppmService.updateRegisterTamu(ids, request);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/pphppm/{ids}")
	public ResponseEntity<Void> delete(@PathVariable String ids) {
		pphppmService.deleteRegisterTamu(ids);
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
