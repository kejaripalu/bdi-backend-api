package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;

public interface RegisterTamuPPHPPMRepository extends JpaRepository<RegisterTamuPPHPPM, String> {

	@Query("SELECT r FROM RegisterTamuPPHPPM r WHERE r.deleted=false AND r.tanggal BETWEEN "
			+ ":startDate AND :endDate ORDER BY r.tanggal DESC")
	Page<RegisterTamuPPHPPM> findAll(Date startDate, Date endDate, Pageable pageable);
	
	@Query("SELECT r FROM RegisterTamuPPHPPM r WHERE r.deleted=false "
			+ "AND (LOWER(r.namaPetugasPenerima) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaTamu) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorHandphone) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.nomorIdentitas) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.namaOrganisasi) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.informasiYangDisampaikan) LIKE LOWER(CONCAT('%', :value, '%')) "
			+ "OR LOWER(r.dokumenYangDisampaikan) LIKE LOWER(CONCAT('%', :value, '%'))) "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.tanggal DESC")
	Page<RegisterTamuPPHPPM> findBySearching(Date startDate, Date endDate, String value, Pageable pageable);
	
	Optional<RegisterTamuPPHPPM> findByIdAndDeletedFalse(String id);	
	
}
