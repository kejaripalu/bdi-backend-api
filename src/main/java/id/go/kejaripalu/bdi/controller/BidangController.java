package id.go.kejaripalu.bdi.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.kejaripalu.bdi.domain.bidang.Ekokeu;
import id.go.kejaripalu.bdi.domain.bidang.Ipolhankam;
import id.go.kejaripalu.bdi.domain.bidang.Pamstra;
import id.go.kejaripalu.bdi.domain.bidang.SektorModel;
import id.go.kejaripalu.bdi.domain.bidang.Sosbudmas;
import id.go.kejaripalu.bdi.domain.bidang.Tiprodin;

@RestController
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class BidangController {

	@GetMapping("/bidang-sektor/ipolhankam")
	public Map<Byte, SektorModel> getIpolhankam() {		
		Map<Byte, SektorModel> sektors = new LinkedHashMap<>();
		byte i = 0;
		for (var sektor : Ipolhankam.values()) {
			SektorModel sektorItem = new SektorModel();
			sektorItem.setSektorName(sektor.name());
			sektorItem.setSektorDescription(sektor.getDescription());		
			sektors.put(i, sektorItem);
			i++;
		}
		return sektors;
	}
	
	@GetMapping("/bidang-sektor/sosbudmas")
	public Map<Byte, SektorModel> getSosbudmas() {
		Map<Byte, SektorModel> sektors = new LinkedHashMap<>();
		byte i = 0;
		for (var sektor : Sosbudmas.values()) {
			SektorModel sektorItem = new SektorModel();
			sektorItem.setSektorName(sektor.name());
			sektorItem.setSektorDescription(sektor.getDescription());		
			sektors.put(i, sektorItem);
			i++;
		}
		return sektors;
	}

	@GetMapping("/bidang-sektor/ekokeu")
	public Map<Byte, SektorModel> getEkokeu() {
		Map<Byte, SektorModel> sektors = new LinkedHashMap<>();
		byte i = 0;
		for (var sektor : Ekokeu.values()) {
			SektorModel sektorItem = new SektorModel();
			sektorItem.setSektorName(sektor.name());
			sektorItem.setSektorDescription(sektor.getDescription());		
			sektors.put(i, sektorItem);
			i++;
		}
		return sektors;
	}
	
	@GetMapping("/bidang-sektor/pamstra")
	public Map<Byte, SektorModel> getPamstra() {
		Map<Byte, SektorModel> sektors = new LinkedHashMap<>();
		byte i = 0;
		for (var sektor : Pamstra.values()) {
			SektorModel sektorItem = new SektorModel();
			sektorItem.setSektorName(sektor.name());
			sektorItem.setSektorDescription(sektor.getDescription());		
			sektors.put(i, sektorItem);
			i++;
		}
		return sektors;
	}

	@GetMapping("/bidang-sektor/tiprodin")
	public Map<Byte, SektorModel> getTiprodin() {
		Map<Byte, SektorModel> sektors = new LinkedHashMap<>();
		byte i = 0;
		for (var sektor : Tiprodin.values()) {
			SektorModel sektorItem = new SektorModel();
			sektorItem.setSektorName(sektor.name());
			sektorItem.setSektorDescription(sektor.getDescription());		
			sektors.put(i, sektorItem);
			i++;
		}
		return sektors;
	}
		
}
