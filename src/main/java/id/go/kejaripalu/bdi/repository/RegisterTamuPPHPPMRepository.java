package id.go.kejaripalu.bdi.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;

public interface RegisterTamuPPHPPMRepository extends JpaRepository<RegisterTamuPPHPPM, Long> {

	@Query("SELECT r FROM RegisterTamuPPHPPM r WHERE r.deleted=false "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC")
	Page<RegisterTamuPPHPPM> findAllPPHPPM(Date startDate, Date endDate, Pageable pageable);
	
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
	
	Optional<RegisterTamuPPHPPM> findByIdsAndDeletedFalse(String ids);
	
	@Query("SELECT r FROM RegisterTamuPPHPPM r WHERE r.deleted=false "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate "
			+ "ORDER BY r.id DESC, r.tanggal DESC")
	Page<RegisterTamuPPHPPM> findAllPPHPPMtoPrint(Date startDate, Date endDate, Pageable pageable);
	
	@Query("SELECT "
			+ "(SELECT COUNT(r.jenisPelayanan) r FROM RegisterTamuPPHPPM r WHERE r.jenisPelayanan = 'PPH' "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate), "
			+ "(SELECT COUNT(r.jenisPelayanan) r FROM RegisterTamuPPHPPM r WHERE r.jenisPelayanan = 'PPM' "
			+ "AND r.tanggal BETWEEN :startDate AND :endDate)")
	List<Integer[]> countPPHPPM(Date startDate, Date endDate);
	
}
