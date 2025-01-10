package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenRequest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenResponse;
import id.go.kejaripalu.bdi.service.RegisterProdukIntelijenService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterProdukIntelijenController {

    private RegisterProdukIntelijenService produkIntelijenService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/prodin")
    public ResponseEntity<Page<RegisterProdukIntelijen>> findProdukIntelijen(
            @RequestParam(required = true, defaultValue = "0") Integer pages,
            @RequestParam(required = true, defaultValue = "20") Integer sizes,
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
        return ResponseEntity.ok().body(produkIntelijenService.findProdukIntelijen(startDate, endDate, pages, sizes));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/prodin/{id}/detail")
    public ResponseEntity<RegisterProdukIntelijenResponse> findProdukIntelijenById(@PathVariable String id) {
        return ResponseEntity.ok().body(produkIntelijenService.findProdukIntelijenById(id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/prodin/search")
    public ResponseEntity<Page<RegisterProdukIntelijen>> findProdukIntelijenBySearch(
            @RequestParam(required = true, defaultValue = "0") Integer pages,
            @RequestParam(required = true, defaultValue = "20") Integer sizes,
            @RequestParam(required = true) String value,
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
        return ResponseEntity.ok().body(produkIntelijenService.findProdukIntelijenBySearching(
                startDate, endDate, value, pages, sizes));
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @PostMapping("/prodin")
    public ResponseEntity<Void> createNewProdukIntelijen(@Valid @RequestBody RegisterProdukIntelijenRequest request) {
        produkIntelijenService.createProdukIntelijen(request);
        return ResponseEntity.created(URI.create("/api/v1//produk-intelijen")).build();
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @PutMapping("/prodin/{id}")
    public ResponseEntity<Void> updateProdukIntelijen(@PathVariable String id,
                                                      @RequestBody @Valid RegisterProdukIntelijenRequest request) {
        produkIntelijenService.updateProdukIntelijen(id, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @DeleteMapping("/prodin/{id}")
    public ResponseEntity<Void> deleteProdukIntelijen(@PathVariable String id) {
        produkIntelijenService.deleteProdukIntelijen(id);
        return ResponseEntity.accepted().build();
    }

}
