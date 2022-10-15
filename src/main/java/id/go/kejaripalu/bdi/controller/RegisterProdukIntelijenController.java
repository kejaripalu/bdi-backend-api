package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterProdukIntelijenService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterProdukIntelijenController {

    private RegisterProdukIntelijenService produkIntelijenService;

    @GetMapping("/prodin")
    public ResponseEntity<Page<RegisterProdukIntelijen>> findProdukIntelijen(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
            @RequestParam(name = "startDate", required = true) String startDate,
            @RequestParam(name = "endDate", required = true) String endDate) {
        return ResponseEntity.ok().body(produkIntelijenService.findProdukIntelijen(startDate, endDate, pages, sizes));
    }

    @GetMapping("/prodin/{id}/detail")
    public ResponseEntity<RegisterProdukIntelijenResponse> findProdukIntelijenById(@PathVariable String id) {
        return ResponseEntity.ok().body(produkIntelijenService.findProdukIntelijenById(id));
    }

    @GetMapping("/prodin/search")
    public ResponseEntity<Page<RegisterProdukIntelijen>> findProdukIntelijenBySearch(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "sizes", required = true, defaultValue = "20") Integer sizes,
            @RequestParam(name = "value", required = true) String value,
            @RequestParam(name = "startDate", required = true) String startDate,
            @RequestParam(name = "endDate", required = true) String endDate) {
        return ResponseEntity.ok().body(produkIntelijenService.findProdukIntelijenBySearching(
                startDate, endDate, value, pages, sizes));
    }

    @PostMapping("/prodin")
    public ResponseEntity<Void> createNewProdukIntelijen(@Valid @RequestBody RegisterProdukIntelijenRequest request) {
        produkIntelijenService.createProdukIntelijen(request);
        return ResponseEntity.created(URI.create("/api/v1//produk-intelijen")).build();
    }

    @PutMapping("/prodin/{id}")
    public ResponseEntity<Void> updateProdukIntelijen(@PathVariable String id,
                                                      @RequestBody @Valid RegisterProdukIntelijenRequest request) {
        produkIntelijenService.updateProdukIntelijen(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/prodin/{id}")
    public ResponseEntity<Void> deleteProdukIntelijen(@PathVariable String id) {
        produkIntelijenService.deleteProdukIntelijen(id);
        return ResponseEntity.accepted().build();
    }

}
