package id.go.kejaripalu.bdi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenDTO;
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

import id.go.kejaripalu.bdi.service.RegisterProdukIntelijenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class RegisterProdukIntelijenController {

    private final RegisterProdukIntelijenService produkIntelijenService;

    @GetMapping("/prodin")
    public ResponseEntity<Page<RegisterProdukIntelijenDTO>> findAll(
            @RequestParam(required = true, defaultValue = "0") Integer pages,
            @RequestParam(required = true, defaultValue = "20") Integer sizes,
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
        return ResponseEntity.ok(produkIntelijenService.findAll(startDate, endDate, pages, sizes));
    }

    @GetMapping("/prodin/{ids}/detail")
    public ResponseEntity<RegisterProdukIntelijenDTO> findByIds(@PathVariable String ids) {
        return ResponseEntity.ok(produkIntelijenService.findByIds(ids));
    }

    @GetMapping("/prodin/search")
    public ResponseEntity<Page<RegisterProdukIntelijenDTO>> findBySearching(
            @RequestParam(required = true, defaultValue = "0") Integer pages,
            @RequestParam(required = true, defaultValue = "20") Integer sizes,
            @RequestParam(required = true) String value,
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
        return ResponseEntity.ok(produkIntelijenService.findBySearching(
                startDate, endDate, value, pages, sizes));
    }
    
    @PostMapping("/prodin")
    public ResponseEntity<RegisterProdukIntelijenDTO> create(@Valid @RequestBody RegisterProdukIntelijenDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produkIntelijenService.create(request));
    }
    
    @PutMapping("/prodin/{ids}")
    public ResponseEntity<RegisterProdukIntelijenDTO> update(@PathVariable String ids,
                                                      @RequestBody @Valid RegisterProdukIntelijenDTO request) {
        return ResponseEntity.ok(produkIntelijenService.update(ids, request));
    }

    @DeleteMapping("/prodin/{ids}")
    public ResponseEntity<Void> delete(@PathVariable String ids) {
        produkIntelijenService.delete(ids);
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/prodin/count")
    public ResponseEntity<Map<String, Integer>> countsProdin(
    		@RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
    	List<Integer[]> dataCountLapin = produkIntelijenService.countJenisProdukIntelijen(startDate, endDate);
    	Map<String, Integer> map = new HashMap<>(); 
    	map.put("countLapinhar", (dataCountLapin.get(0))[0]);
    	map.put("countLapinsus", (dataCountLapin.get(0))[1]);
    	map.put("countLaphastug", (dataCountLapin.get(0))[2]);
    	map.put("countLapopsin", (dataCountLapin).get(0)[3]);    	    	
    	return ResponseEntity.ok().body(map);
    }

}
