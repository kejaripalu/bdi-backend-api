package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;
import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;

public interface RegisterKegiatanIntelijenPamstraRepository {

	@Query("SELECT r FROM RegisterKegiatanIntelijenPamstra r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggalSuratPermohonan BETWEEN :startDate AND :endDate ORDER BY r.tanggalSuratPermohonan  DESC")
	Page<RegisterKegiatanIntelijenPamstra> findAllKegiatan(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
	@Query("SELECT r FROM RegisterKegiatanIntelijenPamstra r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND (LOWER(r.instansi) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSuratPermohonan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tempatPemaparan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.telaahanIntelijen) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tindakLanjutDiterima) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tindakLanjutDitolak) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSprintWalpam) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.hasilPelaksanaanSelesai) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.hasilPelaksanaanDihentikan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaPetugasPelaksana) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate ORDER BY r.tanggal  DESC")
	Page<RegisterKegiatanIntelijenPamstra> findBySearching(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, String value, Pageable pageable);
	
	Optional<RegisterKegiatanIntelijenPamstra> findByIdAndDeletedFalse(String id);
	
}
