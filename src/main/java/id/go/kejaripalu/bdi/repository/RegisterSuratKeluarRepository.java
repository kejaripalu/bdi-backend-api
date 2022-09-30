package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.JenisSurat;
import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;

public interface RegisterSuratKeluarRepository extends JpaRepository<RegisterSuratKeluar, String> {
	
	@Query("SELECT r FROM RegisterSuratKeluar r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.tanggalSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalSurat DESC")
	Page<RegisterSuratKeluar> findSuratKeluar(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);

	Optional<RegisterSuratKeluar> findByIdAndDeletedFalse(String id);
	
}
