package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;

public interface RegisterKegiatanIntelijenPamstraRepository extends JpaRepository<RegisterKegiatanIntelijenPamstra, String> {

	@Query("SELECT r FROM RegisterKegiatanIntelijenPamstra r WHERE r.deleted=false "
			+ "AND r.tanggalSuratPermohonan BETWEEN :startDate AND :endDate ORDER BY r.tanggalSuratPermohonan  DESC")
	Page<RegisterKegiatanIntelijenPamstra> findAllKegiatan(Date startDate, Date endDate, Pageable pageable);
	
	@Query("SELECT r FROM RegisterKegiatanIntelijenPamstra r WHERE r.deleted=false "
			+ "AND (LOWER(r.instansi) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaKegiatan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.sumberDana) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSuratPermohonan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tempatPemaparan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.telaahanIntelijen) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.tindakLanjutKeterangan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorSprintWalpam) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.hasilPelaksanaanKeterangan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaPetugasPelaksana) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggalSuratPermohonan BETWEEN :startDate AND :endDate ORDER BY r.tanggalSuratPermohonan  DESC")
	Page<RegisterKegiatanIntelijenPamstra> findBySearching(Date startDate, Date endDate, String value, Pageable pageable);
	
	Optional<RegisterKegiatanIntelijenPamstra> findByIdAndDeletedFalse(String id);
	
}
