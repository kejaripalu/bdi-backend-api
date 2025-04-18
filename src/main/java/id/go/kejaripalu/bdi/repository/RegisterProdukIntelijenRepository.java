package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;

public interface RegisterProdukIntelijenRepository extends JpaRepository<RegisterProdukIntelijen, Long> {

	@Query("SELECT r FROM RegisterProdukIntelijen r WHERE r.deleted=false "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate " + "ORDER BY r.id DESC")
	Page<RegisterProdukIntelijen> findProdukIntelijenAll(Date startDate, Date endDate, Pageable pageable);

	@Query("SELECT r FROM RegisterProdukIntelijen r WHERE r.deleted=false "
			+ "AND (LOWER(r.nomorProduk) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.sektor) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate " + "ORDER BY r.tanggalProduk DESC")
	Page<RegisterProdukIntelijen> findProdukIntelijenBySearching(Date startDate, Date endDate, String value,
			Pageable pageable);

	Optional<RegisterProdukIntelijen> findByIdsAndDeletedFalse(String ids);

	@Query("SELECT r FROM RegisterProdukIntelijen r WHERE r.deleted=false "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggalProduk DESC, r.tanggalProduk DESC")
	Page<RegisterProdukIntelijen> findProdukIntelijenAllToPrint(Date startDate, Date endDate, Pageable pageable);

	@Query("SELECT "
			+ "(SELECT COUNT(r.jenisProdukIntelijen) r FROM RegisterProdukIntelijen r WHERE r.jenisProdukIntelijen = 'LAPINHAR' "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate), "
			+ "(SELECT COUNT(r.jenisProdukIntelijen) r FROM RegisterProdukIntelijen r WHERE r.jenisProdukIntelijen = 'LAPINSUS' "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate), "
			+ "(SELECT COUNT(r.jenisProdukIntelijen) r FROM RegisterProdukIntelijen r WHERE r.jenisProdukIntelijen = 'LAPHASTUG' "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate), "
			+ "(SELECT COUNT(r.jenisProdukIntelijen) r FROM RegisterProdukIntelijen r WHERE r.jenisProdukIntelijen = 'LAPOPSIN' "
			+ "AND r.tanggalProduk BETWEEN :startDate AND :endDate)")
	List<Integer[]> countLaporanIntelijen(Date startDate, Date endDate);

}
