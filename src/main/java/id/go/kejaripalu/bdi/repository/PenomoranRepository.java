package id.go.kejaripalu.bdi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.go.kejaripalu.bdi.domain.Penomoran;

public interface PenomoranRepository extends JpaRepository<Penomoran, Long> {

	Optional<Penomoran> findByKodeSurat(String kodeSurat);
	
}
