package id.go.kejaripalu.bdi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.kejaripalu.bdi.security.dto.AppUserDetailResponseDTO;
import id.go.kejaripalu.bdi.security.service.AppUserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api-url}")
@CrossOrigin("${app.origin-url}")
public class UserController {
	
	private AppUserService service;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user")
	public ResponseEntity<AppUserDetailResponseDTO> findUserDetail() {
		return ResponseEntity.ok().body(service.findUserDetail());
	}

}
