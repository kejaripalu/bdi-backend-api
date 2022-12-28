package id.go.kejaripalu.bdi.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.BaseUUIDEntity;
import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;
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
public class RegisterKegiatanIntelijenPamstra extends BaseUUIDEntity {

	@Column(name = "bidang_direktorat", nullable = false)
	@Enumerated(EnumType.STRING)
	private BidangDirektorat bidangDirektorat;
	
	@Column(name = "sektor", nullable = false)
	@Enumerated(EnumType.STRING)
	private Sektor sektor;
	
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
	
	@Column(name = "tindak_lanjut_diterima", columnDefinition="TEXT")
	private String tindakLanjutDiterima;
	
	@Column(name = "tindak_lanjut_ditolak", columnDefinition="TEXT")
	private String tindakLanjutDitolak;
	
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
	
	@Column(name = "hasil_pelaksanaan_selesai", columnDefinition="TEXT")
	private String hasilPelaksanaanSelesai;
	
	@Column(name = "hasil_pelaksanaan_dihentikan", columnDefinition="TEXT")
	private String hasilPelaksanaanDihentikan;
	
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
