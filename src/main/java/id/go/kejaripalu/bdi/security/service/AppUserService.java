package id.go.kejaripalu.bdi.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import id.go.kejaripalu.bdi.security.dto.AppUserDetailResponseDTO;

public interface AppUserService extends UserDetailsService {

	public AppUserDetailResponseDTO findUserDetail();
	
}
