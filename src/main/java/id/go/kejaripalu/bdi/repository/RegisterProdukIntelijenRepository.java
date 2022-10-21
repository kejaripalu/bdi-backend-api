package id.go.kejaripalu.bdi.repository;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface RegisterProdukIntelijenRepository extends JpaRepository<RegisterProdukIntelijen, String> {

    @Query("SELECT r FROM RegisterProdukIntelijen r WHERE r.deleted=false "
            + "AND r.tanggalProduk BETWEEN :startDate AND :endDate "
            + "ORDER BY r.tanggalProduk DESC, r.tanggalProduk DESC")
    Page<RegisterProdukIntelijen> findProdukIntelijenAll(Date startDate, Date endDate, Pageable pageable);

    @Query("SELECT r FROM RegisterProdukIntelijen r WHERE r.deleted=false "
            + "AND (LOWER(r.nomorProduk) LIKE LOWER(CONCAT('%', :value, '%')) "
            + "OR LOWER(r.sektor) LIKE LOWER(CONCAT('%', :value, '%')) "
            + "OR LOWER(r.perihal) LIKE LOWER(CONCAT('%', :value, '%'))) "
            + "AND r.tanggalProduk BETWEEN :startDate AND :endDate "
            + "ORDER BY r.tanggalProduk DESC")
    Page<RegisterProdukIntelijen> findProdukIntelijenBySearching(Date startDate, Date endDate, String value, Pageable pageable);

    Optional<RegisterProdukIntelijen> findByIdAndDeletedFalse(String id);

}
