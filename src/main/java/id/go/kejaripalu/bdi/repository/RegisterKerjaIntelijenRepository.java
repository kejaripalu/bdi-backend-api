package id.go.kejaripalu.bdi.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.domain.bidang.BidangDirektorat;

public interface RegisterKerjaIntelijenRepository extends JpaRepository<RegisterKerjaIntelijen, String> {

	@Query("SELECT r FROM RegisterKerjaIntelijen r WHERE r.deleted=false AND r.bidangDirektorat=:bidangDirektorat "
			+ "AND r.tanggalWaktuDiterima BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggalWaktuDiterima DESC")
	Page<RegisterKerjaIntelijen> findRKIAll(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);
	
}
