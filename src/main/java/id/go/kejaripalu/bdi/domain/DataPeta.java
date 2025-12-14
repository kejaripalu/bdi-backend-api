package id.go.kejaripalu.bdi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.SektorPeta;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "data_peta")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DataPeta extends Siabidibam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "bidang_direktorat", nullable = false)
    @Enumerated(EnumType.STRING)
    private BidangDirektorat bidangDirektorat;

    @Column(name = "sektor", nullable = false)
    @Enumerated(EnumType.STRING)
    private SektorPeta sektor;

    @Column(name = "tanggal", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tanggal;

    @Column(name = "lokasi", columnDefinition="TEXT", nullable = false)
    private String lokasi;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "keterangan")
    private String keterangan;

}
