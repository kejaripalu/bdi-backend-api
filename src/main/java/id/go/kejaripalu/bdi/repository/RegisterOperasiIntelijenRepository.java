package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;

public interface RegisterOperasiIntelijenRepository extends JpaRepository<RegisterOperasiIntelijen, Long> {

	@Query("SELECT r FROM RegisterOperasiIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterOperasiIntelijen> findAllOpsin(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
	@Query("SELECT r FROM RegisterOperasiIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND (LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomor) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.hasilPelaksanaanOperasi) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaPetugasPelaksana) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate ORDER BY r.tanggal  DESC")
	Page<RegisterOperasiIntelijen> findBySearching(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, String value, Pageable pageable);
	
	Optional<RegisterOperasiIntelijen> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterOperasiIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggal DESC")
	Page<RegisterOperasiIntelijen> findAllOpsinToPrint(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
}
