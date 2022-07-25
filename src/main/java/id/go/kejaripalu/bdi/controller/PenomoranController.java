package id.go.kejaripalu.bdi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.kejaripalu.bdi.domain.Penomoran;
import id.go.kejaripalu.bdi.service.PenomoranService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("${app.api-url}")
@AllArgsConstructor
public class PenomoranController {

	private PenomoranService penomoranService;
	
	@GetMapping("/penomoran")
	public ResponseEntity<List<Penomoran>> findAllPenomoran() {
		return ResponseEntity.ok().body(penomoranService.findAlList());
	}
	
	@GetMapping("/penomoran/{kode}")
	public ResponseEntity<Penomoran> getById(@PathVariable String kode) {
		return ResponseEntity.ok().body(penomoranService.getByKodeSurat(kode));
	}
	
	@PostMapping("/penomoran")
	public ResponseEntity<Void> saveNewNomor(@RequestBody Penomoran penomoran) {
		penomoranService.createNewNomor(penomoran);
		return ResponseEntity.created(URI.create("${app.api-url}/penomoran")).build();
	}
	
	@PutMapping("/penomoran/{kode}")
	public ResponseEntity<Void> updateNomor(@PathVariable String kode, @RequestBody Penomoran penomoran) {
		penomoranService.updateNomor(kode, penomoran);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/penomoran/{kode}")
	public ResponseEntity<Void> deleteNomor(@PathVariable String kode) {
		penomoranService.deleteNomor(kode);
		return ResponseEntity.accepted().build();
	}
	
}
