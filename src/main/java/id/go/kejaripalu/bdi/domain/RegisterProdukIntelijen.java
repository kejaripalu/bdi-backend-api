package id.go.kejaripalu.bdi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.domain.bidang.BidangDirektorat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "register_produk_intelijen")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProdukIntelijen extends BaseUUIDEntity {

    @Column(name = "jenis_produk_intelijen", nullable = false)
    @Enumerated(EnumType.STRING)
    private JenisProdukIntelijen jenisProdukIntelijen;

    @Column(name = "nomor_produk", nullable = false, unique = true)
    private String nomorProduk;

    @Column(name = "tanggal_produk", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
    private Date tanggalProduk;

    @Column(name = "bidang", nullable = false)
    @Enumerated(EnumType.STRING)
    private BidangDirektorat bidang;

    @Column(name = "perihal", nullable = false)
    private String perihal;

    @Column(name = "disposisi_pimpinan")
    private String disposisiPimpinan;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "url_file", columnDefinition="TEXT")
    private String urlFile;

}
