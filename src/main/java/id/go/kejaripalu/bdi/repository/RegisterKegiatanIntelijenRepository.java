package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.util.BidangDirektorat;

public interface RegisterKegiatanIntelijenRepository extends JpaRepository<RegisterKegiatanIntelijen, Long> {

	@Query("SELECT r FROM RegisterKegiatanIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterKegiatanIntelijenDTO> findAllKegiatan(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
	@Query("SELECT r FROM RegisterKegiatanIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND (LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomor) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.hasilPelaksanaanKegiatan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaPetugasPelaksana) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggal  DESC")
	Page<RegisterKegiatanIntelijenDTO> findBySearching(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, String value, Pageable pageable);
	
	Optional<RegisterKegiatanIntelijen> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterKegiatanIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggal DESC")
	Page<RegisterKegiatanIntelijenDTO> findAllKegiatanToPrint(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
}
