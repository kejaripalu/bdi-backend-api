package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import id.go.kejaripalu.bdi.service.DataPetaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class DataPetaController {

    private final DataPetaService service;

    @PostMapping("/data-peta")
    public ResponseEntity<DataPetaDTO> create(
            @Valid @RequestBody DataPetaDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/data-peta/{ids}")
    public ResponseEntity<DataPetaDTO> update(
            @PathVariable String ids,
            @Valid @RequestBody DataPetaDTO request) {
        return ResponseEntity.ok(service.update(ids, request));
    }

    @GetMapping("/data-peta/{ids}")
    public ResponseEntity<DataPetaDTO> findByIds(@PathVariable String ids) {
        return ResponseEntity.ok(service.findByIds(ids));
    }

    @GetMapping("/data-peta")
    public ResponseEntity<Page<DataPetaDTO>> findAll(
            @RequestParam(defaultValue = "0") Integer pages,
            @RequestParam(defaultValue = "20") Integer sizes,
            @RequestParam String bidangDirektorat,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(service.findAll(startDate, endDate, bidangDirektorat, pages, sizes));
    }

    @GetMapping("/data-peta/search")
    public ResponseEntity<Page<DataPetaDTO>> findBySearching(
            @RequestParam(defaultValue = "0") Integer pages,
            @RequestParam(defaultValue = "20") Integer sizes,
            @RequestParam String bidangDirektorat,
            @RequestParam String value,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(service.findBySearching(startDate, endDate, bidangDirektorat, value, pages, sizes));
    }

    @DeleteMapping("/data-peta/{ids}")
    public ResponseEntity<Void> delete(@PathVariable String ids) {
        service.delete(ids);
        return ResponseEntity.accepted().build();
    }
}
