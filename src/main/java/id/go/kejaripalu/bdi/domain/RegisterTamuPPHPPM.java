package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import id.go.kejaripalu.bdi.util.JenisKelamin;
import id.go.kejaripalu.bdi.util.JenisPelayanan;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Register Register Tamu PPH & PPM ini memiliki
 * field yang tidak didefinisikan yaitu field Tanda Tangan
 * seperti yang dimuat dalam PERJA
 */

@Entity
@Table(name = "register_tamu_ppm_pph")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class RegisterTamuPPHPPM extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -9043424490902526315L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(name = "jenis_pelayanan", nullable = false)
	@Enumerated(EnumType.STRING)
	private JenisPelayanan jenisPelayanan = JenisPelayanan.PPH;

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
	@Enumerated(EnumType.STRING)
	private JenisKelamin jenisKelamin = JenisKelamin.TIDAK_DITENTUKAN;
	
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
	
	@Column(name = "dokumen_yang_disampaikan")
	private String dokumenYangDisampaikan;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;
	
}
