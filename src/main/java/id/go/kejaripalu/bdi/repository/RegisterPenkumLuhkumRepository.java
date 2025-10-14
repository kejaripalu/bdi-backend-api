package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.util.JenisKegiatanPenkumLuhkum;

public interface RegisterPenkumLuhkumRepository extends JpaRepository<RegisterPenkumLuhkum, Long> {

	@Query("SELECT r FROM RegisterPenkumLuhkum r WHERE r.deleted=false AND r.jenisKegiatan=:jenisKegiatan "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterPenkumLuhkumDTO> findAllPenkumLuhkum(Date startDate, Date endDate, JenisKegiatanPenkumLuhkum jenisKegiatan, Pageable pageable);
	
	@Query("SELECT r FROM RegisterPenkumLuhkum r WHERE r.deleted=false "
			+ "AND r.jenisKegiatan=:jenisKegiatan "
			+ "AND (LOWER(r.nomorSuratPerintah) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.sasaranKegiatan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tempat) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.materi) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalKegiatan DESC")
	Page<RegisterPenkumLuhkumDTO> findBySearching(Date startDate, Date endDate, JenisKegiatanPenkumLuhkum jenisKegiatan, String value, Pageable pageable);
	
	Optional<RegisterPenkumLuhkum> findByIdsAndDeletedFalse(String ids);

	@Query("SELECT r FROM RegisterPenkumLuhkum r WHERE r.deleted=false AND r.jenisKegiatan=:jenisKegiatan "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id  DESC, r.tanggalKegiatan DESC")
	Page<RegisterPenkumLuhkumDTO> findAllPenkumLuhkumToPrint(Date startDate, Date endDate, JenisKegiatanPenkumLuhkum jenisKegiatan, Pageable pageable);
	
	@Query("SELECT "
			+ "(SELECT COUNT(r.program) r FROM RegisterPenkumLuhkum r WHERE r.program = 'BINMATKUM' "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate), "
			+ "(SELECT COUNT(r.program) r FROM RegisterPenkumLuhkum r WHERE r.program = 'JMS' "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate), "
			+ "(SELECT COUNT(r.program) r FROM RegisterPenkumLuhkum r WHERE r.program = 'JAKSA_MENYAPA' "
			+ "AND r.tanggalKegiatan BETWEEN :startDate AND :endDate)")
	List<Integer[]> countProgramPenkumLuhkum(Date startDate, Date endDate);
	
}
