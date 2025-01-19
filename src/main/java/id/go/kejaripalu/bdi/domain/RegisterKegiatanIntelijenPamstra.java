package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

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
import jakarta.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import id.go.kejaripalu.bdi.domain.util.BaseEntity;
import id.go.kejaripalu.bdi.domain.util.HasilPamstra;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_kegiatan_intelijen_pamstra")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class RegisterKegiatanIntelijenPamstra extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4023679460470876278L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "sektor", nullable = false)
	@Enumerated(EnumType.STRING)
	private Sektor sektor;
	
	@Column(name = "nama_kegiatan", nullable = false)
	private String namaKegiatan;
	
	@Column(name = "sumber_dana", nullable = false)
	private String sumberDana;

	@Column(name = "instansi", nullable = false)
	private String instansi;
	
	@Column(name = "pagu_anggaran", nullable = false)
	@DecimalMin(value = "0.0")
	private BigDecimal paguAnggaran;
	
	@Column(name = "nomor_surat_permohonan", nullable = false, unique = true)
	private String nomorSuratPermohonan;
	
	@Column(name = "tanggal_surat_permohonan", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSuratPermohonan;
	
	@Column(name = "tempat_pemaparan")
	private String tempatPemaparan;
	
	@Column(name = "tanggal_pemaparan")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalPemaparan;
	
	@Column(name = "telaahan_intelijen", columnDefinition="TEXT")
	private String telaahanIntelijen;
	
	@Column(name = "tindak_lanjut")
	private Boolean tindakLanjut = Boolean.TRUE;
	
	@Column(name = "tindak_lanjut_keterangan", columnDefinition="TEXT")
	private String tindakLanjutKeterangan;
	
	@Column(name = "nomor_sprint_walpam", unique = true)
	private String nomorSprintWalpam;
	
	@Column(name = "tanggal_sprint_walpam")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSprintWalpam;
	
	@Column(name = "nama_petugas_pelaksana", columnDefinition="TEXT")
	private String namaPetugasPelaksana;
	
	@Column(name = "nilai_kontrak")
	@DecimalMin(value = "0.0")
	private BigDecimal nilaiKontrak;
	
	@Column(name = "hasil_pelaksanaan")
	@Enumerated(EnumType.STRING)
	private HasilPamstra hasilPelaksanaan = HasilPamstra.ON_PROGRESS;
	
	@Column(name = "hasil_pelaksanaan_keterangan", columnDefinition="TEXT")
	private String hasilPelaksanaanKeterangan;
	
	@Column(name = "nomor_kertas_kerja", unique = true)
	private String nomorKertasKerja;
	
	@Column(name = "tanggal_kertas_kerja")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalKertasKerja;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;
	
}
