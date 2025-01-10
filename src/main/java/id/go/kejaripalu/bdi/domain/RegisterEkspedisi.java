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
@Table(name = "register_ekspedisi")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEkspedisi extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3455793502209300165L;

	@Column(name = "nomor_surat", nullable = false)
	private String nomorSurat;

	@Column(name = "tanggal_surat", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
	private Date tanggalSurat;

	@Column(name = "kepada", nullable = false)
	private String kepada;
	
	@Column(name = "perihal", nullable = false)
	private String perihal;
	
	@Column(name = "lampiran")
	private String lampiran;
	
	@Column(name = "tanggal_tanda_terima", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
	private Date tanggalTandaTerima;
	
	@Column(name = "jam_tanda_terima", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jamTandaTerima;

	@Column(name = "nama_dan_paraf", columnDefinition="TEXT")
	private String namaDanParaf;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;
	
	@Column(name = "jenis_surat", nullable = false)
	@Enumerated(EnumType.STRING)
	private JenisSurat jenisSurat = JenisSurat.BIASA;
	
}
