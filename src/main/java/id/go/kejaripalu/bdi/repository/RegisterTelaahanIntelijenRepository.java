package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;

public interface RegisterTelaahanIntelijenRepository extends JpaRepository<RegisterTelaahanIntelijen, Long> {

	@Query("SELECT r FROM RegisterTelaahanIntelijen r WHERE r.deleted=false "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterTelaahanIntelijen> findAllLahin(Date startDate, Date endDate, Pageable pageable);
	
	@Query("SELECT r FROM RegisterTelaahanIntelijen r WHERE r.deleted=false "
			+ "AND (LOWER(r.nomor) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.pembuat) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tindakLanjut) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate ORDER BY r.tanggal  DESC")
	Page<RegisterTelaahanIntelijen> findBySearching(Date startDate, Date endDate, String value, Pageable pageable);
	
	Optional<RegisterTelaahanIntelijen> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterTelaahanIntelijen r WHERE r.deleted=false "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggal  DESC")
	Page<RegisterTelaahanIntelijen> findAllLahinToPrint(Date startDate, Date endDate, Pageable pageable);
	
}
