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
import id.go.kejaripalu.bdi.domain.util.JenisSurat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_surat_keluar")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSuratKeluar extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -3392350147752277694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "tanggal_surat", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSurat;
	
	@Column(name = "nomor_surat", nullable = false, unique = true)
	private String nomorSurat;
	
	@Column(name = "kepada", nullable = false)
	private String kepada;
	
	@Column(name = "perihal", nullable = false)
	private String perihal;
	
	@Column(name = "lampiran")
	private String lampiran;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "jenis_surat", nullable = false)
	@Enumerated(EnumType.STRING)
	private JenisSurat jenisSurat = JenisSurat.BIASA;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;

}
