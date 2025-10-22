package id.go.kejaripalu.bdi.repository;

import id.go.kejaripalu.bdi.domain.DataPeta;
import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface DataPetaRepository extends JpaRepository<DataPeta, Long> {

    @Query("""
        SELECT p FROM DataPeta p WHERE p.deleted=false AND p.bidangDirektorat=:bidangDirektorat \s
        AND p.tanggal BETWEEN :startDate AND :endDate \s
        ORDER BY p.tanggal DESC
        """)
    Page<DataPetaDTO> findAll(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, Pageable pageable);

    @Query("""
        SELECT p FROM DataPeta p WHERE p.deleted=false AND p.bidangDirektorat=:bidangDirektorat \s
        AND (LOWER(p.lokasi) LIKE LOWER(CONCAT('%', :value, '%')) \s
        OR LOWER(p.siapa) LIKE LOWER(CONCAT('%', :value, '%')) \s
        OR LOWER(p.apa) LIKE LOWER(CONCAT('%', :value, '%')) \s
        OR LOWER(p.mengapa) LIKE LOWER(CONCAT('%', :value, '%')) \s
        OR LOWER(p.bagaimana) LIKE LOWER(CONCAT('%', :value, '%'))) \s
        AND p.tanggal BETWEEN :startDate AND :endDate \s
        ORDER BY p.tanggal DESC
        """)
    Page<DataPetaDTO> findBySearching(Date startDate, Date endDate, BidangDirektorat bidangDirektorat, String value, Pageable pageable);

    Optional<DataPeta> findByIdsAndDeletedFalse(String ids);

}
