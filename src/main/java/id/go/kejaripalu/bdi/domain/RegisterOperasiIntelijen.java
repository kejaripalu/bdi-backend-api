package id.go.kejaripalu.bdi.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
@Table(name = "register_operasi_intelijen")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOperasiIntelijen extends BaseUUIDEntity {
	
	@Column(name = "bidang_direktorat", nullable = false)
	@Enumerated(EnumType.STRING)
	private BidangDirektorat bidangDirektorat;
	
	@Column(name = "sektor", nullable = false)
	@Enumerated(EnumType.STRING)
	private Sektor sektor;
	
	@Column(name = "nomor", nullable = false, unique = true)
	private String nomor;
	
	@Column(name = "tanggal", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggal;
	
	@Column(name = "perihal", nullable = false)
	private String perihal;
	
	@Column(name = "nama_petugas_pelaksana", columnDefinition="TEXT", nullable = false)
	private String namaPetugasPelaksana;
	
	@Column(name = "hasil_pelaksanaan_operasi", columnDefinition="TEXT")
	private String hasilPelaksanaanOperasi;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;

}
