package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;

public interface RegisterKerjaIntelijenRepository extends JpaRepository<RegisterKerjaIntelijen, Long> {

	@Query("SELECT r FROM RegisterKerjaIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggalWaktuDiterima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterKerjaIntelijen> findRKIAll(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
	@Query("SELECT r FROM RegisterKerjaIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND (LOWER(r.uraianPeristiwaMasalah) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalWaktuDiterima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalWaktuDiterima  DESC, r.jamWaktuDiterima DESC")
	Page<RegisterKerjaIntelijen> findRKIBySearching(Date startDate, Date endDate, String value, BidangDirektorat bidangDirektorat, Pageable pageable);

	Optional<RegisterKerjaIntelijen> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterKerjaIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggalWaktuDiterima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggalWaktuDiterima  DESC, r.jamWaktuDiterima DESC")
	Page<RegisterKerjaIntelijen> findRKIAllToPrint(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);

}
