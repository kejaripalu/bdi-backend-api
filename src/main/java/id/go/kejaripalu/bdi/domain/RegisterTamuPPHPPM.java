package id.go.kejaripalu.bdi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.BaseUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_tamu_ppm_pph")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTamuPPHPPM extends BaseUUIDEntity {

	@Column(name = "nama_petugas_penerima", nullable = false)
	private String namaPetugasPenerima;
	
	@Column(name = "tanggal", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggal;
	
	@Column(name = "jam", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jam;
	
	@Column(name = "nama_tamu", nullable = false)
	private String namaTamu;
	
	@Column(name = "tempat_lahir_tamu")
	private String tempatLahirTamu;
	
	@Column(name = "tanggal_lahir_tamu")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalLahirTamu;
	
	@Column(name = "alamat")
	private String alamat;
	
	@Column(name = "jenis_kelamin")
	private String jenisKelamin;
	
	@Column(name = "nomor_handphone")
	private String nomorHandphone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "pekerjaan")
	private String pekerjaan;
	
	@Column(name = "nomor_identitas")
	private String nomorIdentitas;
	
	@Column(name = "nama_organisasi")
	private String namaOrganisasi;
	
	@Column(name = "informasi_yang_disampaikan", columnDefinition="TEXT", nullable = false)
	private String informasiYangDisampaikan;
	
	@Column(name = "dokumen_yang_disampaikan", columnDefinition="TEXT")
	private String dokumenYangDisampaikan;
	
	@Column(name = "tanda_tangan", columnDefinition="TEXT")
	private String tandaTangan;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;
	
}
