package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.Sektor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_kerja_intelijen")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class RegisterKerjaIntelijen extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 4113420502461797562L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "tanggal_waktu_diterima", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalWaktuDiterima;
	
	@Column(name = "jam_waktu_diterima", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jamWaktuDiterima;

	@Column(name = "sumber_bapul", nullable = false)
	private String sumberBapul;

	@Column(name = "nilai_data_informasi", length = 2, nullable = false)
	private String nilaiDataInformasi;
	
	@Column(name = "uraian_peristiwa_masalah", nullable = false, columnDefinition="TEXT")
	private String uraianPeristiwaMasalah;
	
	@Column(name = "catatan")
	private String catatan;
	
	@Column(name = "disposisi_tindakan")
	private String disposisiTindakan;
	
	@Column(name = "tindak_lanjut")
	private String tindakLanjut;

	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;
	
	@Column(name = "bidang_direktorat", nullable = false)
	@Enumerated(EnumType.STRING)
	private BidangDirektorat bidangDirektorat;
	
	@Column(name = "sektor", nullable = false)
	@Enumerated(EnumType.STRING)
	private Sektor sektor;
	
}
