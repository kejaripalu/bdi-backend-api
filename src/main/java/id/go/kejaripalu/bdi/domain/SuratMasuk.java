package id.go.kejaripalu.bdi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "surat_masuk")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SuratMasuk extends BaseUUIDEntity {
	
	@Column(name = "waktu_penerimaan_surat", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuPenerimaanSurat;
	
	@Column(name = "asal", nullable = false)
	private String asal;
	
	@Column(name = "nomor_surat", nullable = false, unique = true)
	private String nomorSurat;
	
	@Column(name = "perihal", nullable = false)
	private String perihal;
	
	@Column(name = "tanggal_surat", nullable = false)
	@Temporal(TemporalType.DATE)
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

}
