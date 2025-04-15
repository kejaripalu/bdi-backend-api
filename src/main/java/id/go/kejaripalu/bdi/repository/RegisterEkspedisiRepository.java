package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.util.JenisSurat;

public interface RegisterEkspedisiRepository extends JpaRepository<RegisterEkspedisi, Long> {

	@Query("SELECT r FROM RegisterEkspedisi r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.tanggalTandaTerima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterEkspedisi> findEkspedisiAll(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);

	@Query("SELECT r FROM RegisterEkspedisi r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND (LOWER(r.kepada) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSurat) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalTandaTerima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalTandaTerima DESC, r.jamTandaTerima DESC")
	Page<RegisterEkspedisi> findEkspedisiBySearching(Date startDate, Date endDate, String value, JenisSurat jenisSurat, Pageable pageable);
	
	Optional<RegisterEkspedisi> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterEkspedisi r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.tanggalTandaTerima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggalTandaTerima DESC, r.jamTandaTerima DESC")
	Page<RegisterEkspedisi> findEkspedisiAllToPrint(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);
	
}
