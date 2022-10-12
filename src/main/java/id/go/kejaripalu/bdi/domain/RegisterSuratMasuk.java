package id.go.kejaripalu.bdi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_surat_masuk")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSuratMasuk extends BaseUUIDEntity {
	
	@Column(name = "tanggal_penerimaan_surat", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
	private Date tanggalPenerimaanSurat;
	
	@Column(name = "jam_penerimaan_surat", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jamPenerimaanSurat;
	 
	@Column(name = "asal", nullable = false)
	private String asal;
	
	@Column(name = "nomor_surat", nullable = false, unique = true)
	private String nomorSurat;
	
	@Column(name = "perihal", nullable = false)
	private String perihal;
	
	@Column(name = "tanggal_surat", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
	private Date tanggalSurat;
	
	@Column(name = "jenis_surat", nullable = false)
	@Enumerated(EnumType.STRING)
	private JenisSurat jenisSurat = JenisSurat.BIASA;
	
	@Column(name = "isi_disposisi")
	private String isiDisposisi;
	
	@Column(name = "tindak_lanjut_disposisi")
	private String tindakLanjutDisposisi;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;

}
