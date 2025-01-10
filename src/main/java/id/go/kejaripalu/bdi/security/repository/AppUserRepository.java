package id.go.kejaripalu.bdi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.go.kejaripalu.bdi.security.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	public Optional<AppUser> findByUsername(String username);

}
