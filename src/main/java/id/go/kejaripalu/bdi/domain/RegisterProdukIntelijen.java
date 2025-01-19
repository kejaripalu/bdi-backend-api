package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import id.go.kejaripalu.bdi.domain.util.BaseEntity;
import id.go.kejaripalu.bdi.domain.util.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_produk_intelijen")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProdukIntelijen extends BaseEntity implements Serializable {

  	private static final long serialVersionUID = -5380788484933167186L;
  	
  	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "jenis_produk_intelijen", nullable = false)
    @Enumerated(EnumType.STRING)
    private JenisProdukIntelijen jenisProdukIntelijen;

    @Column(name = "nomor_produk", nullable = false, unique = true)
    private String nomorProduk;

    @Column(name = "tanggal_produk", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
    private Date tanggalProduk;

    @Column(name = "sektor", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sektor sektor;

    @Column(name = "perihal", nullable = false)
    private String perihal;

    @Column(name = "disposisi_pimpinan")
    private String disposisiPimpinan;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "url_file", columnDefinition="TEXT")
    private String urlFile;

}
