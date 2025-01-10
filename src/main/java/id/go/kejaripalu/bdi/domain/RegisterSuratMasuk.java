package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.BaseEntity;
import id.go.kejaripalu.bdi.domain.util.JenisSurat;
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
public class RegisterSuratMasuk extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 277099488884734027L;

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
