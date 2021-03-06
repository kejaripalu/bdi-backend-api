package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.JenisSurat;
import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;

public interface RegisterSuratMasukRepository extends JpaRepository<RegisterSuratMasuk, String> {

	@Query("SELECT r FROM RegisterSuratMasuk r WHERE r.deleted=false AND r.jenisSurat=:jenisSurat "
			+ "AND r.waktuPenerimaanSurat BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.waktuPenerimaanSurat DESC")
	Page<RegisterSuratMasuk> findSuratMasukAll(Date startDate, Date endDate, JenisSurat jenisSurat, Pageable pageable);
		
	Optional<RegisterSuratMasuk> findByIdAndDeletedFalse(String id);
	
}
