package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterArsip;

public interface RegisterArsipRepository extends JpaRepository<RegisterArsip, String> {

	@Query("SELECT r FROM RegisterArsip r WHERE r.deleted=false "
			+ "AND r.tanggalPenerimaanArsip BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalPenerimaanArsip DESC, r.jamPenerimaanArsip DESC")
	Page<RegisterArsip> findAll(Date startDate, Date endDate, Pageable pageable);

	@Query("SELECT r FROM RegisterArsip r WHERE r.deleted=false "
			+ "AND (LOWER(r.diterimaDari) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.kodePenyimpanan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSurat) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalPenerimaanArsip BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalPenerimaanArsip DESC, r.jamPenerimaanArsip DESC")
	Page<RegisterArsip> findBySearching(Date startDate, Date endDate, String value, Pageable pageable);
	
	Optional<RegisterArsip> findByIdAndDeletedFalse(String id);
	
}
