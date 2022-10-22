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

import id.go.kejaripalu.bdi.domain.util.BaseUUIDEntity;
import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;
import id.go.kejaripalu.bdi.domain.util.Sektor;
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
public class RegisterKerjaIntelijen extends BaseUUIDEntity {
	
	@Column(name = "tanggal_waktu_diterima", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
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
