package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.domain.util.JenisSurat;

public interface RegisterSuratKeluarRepository extends JpaRepository<RegisterSuratKeluar, String> {
	
	@Query("SELECT r FROM RegisterSuratKeluar r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.tanggalSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalSurat DESC")
	Page<RegisterSuratKeluar> findSuratKeluar(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);

	@Query("SELECT r FROM RegisterSuratKeluar r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND (LOWER(r.nomorSurat) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.kepada) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalSurat DESC")
	Page<RegisterSuratKeluar> findSuratKeluarBySearch(Date startDate, Date endDate, String value, JenisSurat jenisSurat, Pageable pageable);
	
	Optional<RegisterSuratKeluar> findByIdAndDeletedFalse(String id);
	
}
