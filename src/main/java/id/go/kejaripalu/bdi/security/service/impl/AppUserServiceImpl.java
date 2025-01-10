package id.go.kejaripalu.bdi.security.service.impl;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.security.dto.AppUserDetailResponseDTO;
import id.go.kejaripalu.bdi.security.repository.AppUserRepository;
import id.go.kejaripalu.bdi.security.service.AppUserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

	private final AppUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("INVALID_USERNAME"));
	}

	@Override
	public AppUserDetailResponseDTO findUserDetail() {
		SecurityContext context = SecurityContextHolder.getContext();
		AppUserDetailResponseDTO dto = new AppUserDetailResponseDTO();
		String username = context.getAuthentication().getName();
		dto.setUsername(username);
		return dto;
	}

}
