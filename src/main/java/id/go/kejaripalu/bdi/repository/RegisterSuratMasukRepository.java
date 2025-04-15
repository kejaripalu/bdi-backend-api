package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.util.JenisSurat;

public interface RegisterSuratMasukRepository extends JpaRepository<RegisterSuratMasuk, Long> {

	@Query("SELECT r FROM RegisterSuratMasuk r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.tanggalPenerimaanSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterSuratMasuk> findSuratMasukAll(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);

	@Query("SELECT r FROM RegisterSuratMasuk r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND (LOWER(r.asal) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSurat) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalPenerimaanSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalPenerimaanSurat DESC, r.jamPenerimaanSurat DESC")
	Page<RegisterSuratMasuk> findSuratMasukBySearching(Date startDate, Date endDate, String value, JenisSurat jenisSurat, Pageable pageable);
	
	Optional<RegisterSuratMasuk> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterSuratMasuk r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.tanggalPenerimaanSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggalPenerimaanSurat DESC, r.jamPenerimaanSurat DESC")
	Page<RegisterSuratMasuk> findSuratMasukAllToPrint(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);

	
}
