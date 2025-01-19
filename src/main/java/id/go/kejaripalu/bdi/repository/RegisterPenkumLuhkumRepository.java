package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.domain.util.JenisPenkumLuhkum;

public interface RegisterPenkumLuhkumRepository extends JpaRepository<RegisterPenkumLuhkum, Long> {

	@Query("SELECT r FROM RegisterPenkumLuhkum r WHERE r.deleted=false AND r.jenisPenkumLuhkum=:jenisPenkumLuhkum "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate ORDER BY r.id  DESC")
	Page<RegisterPenkumLuhkum> findAllPenkumLuhkum(Date startDate, Date endDate, JenisPenkumLuhkum jenisPenkumLuhkum, Pageable pageable);
	
	@Query("SELECT r FROM RegisterPenkumLuhkum r WHERE r.deleted=false AND r.jenisPenkumLuhkum=:jenisPenkumLuhkum "
			+ "AND (LOWER(r.nomorSuratPerintah) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.sasaranKegiatan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.materi) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalKegiatan DESC")
	Page<RegisterPenkumLuhkum> findBySearching(Date startDate, Date endDate, JenisPenkumLuhkum jenisPenkumLuhkum, String value, Pageable pageable);
	
	Optional<RegisterPenkumLuhkum> findByIds(String ids);
	
	Optional<RegisterPenkumLuhkum> findByIdsAndDeletedFalse(String ids);

	@Query("SELECT r FROM RegisterPenkumLuhkum r WHERE r.deleted=false AND r.jenisPenkumLuhkum=:jenisPenkumLuhkum "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate ORDER BY r.id  DESC, r.tanggalKegiatan DESC")
	Page<RegisterPenkumLuhkum> findAllPenkumLuhkumToPrint(Date startDate, Date endDate, JenisPenkumLuhkum jenisPenkumLuhkum, Pageable pageable);
	
}
