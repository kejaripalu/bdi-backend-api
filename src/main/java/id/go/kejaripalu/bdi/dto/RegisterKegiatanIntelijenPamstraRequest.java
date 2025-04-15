package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.HasilPamstra;
import id.go.kejaripalu.bdi.util.Sektor;
import lombok.Data;

@Data
public class RegisterKegiatanIntelijenPamstraRequest implements Serializable {

	private static final long serialVersionUID = -6941288805307530011L;

	@NotNull
	private Sektor sektor;
	
	@NotBlank
	private String namaKegiatan;
	
	@NotBlank
	private String sumberDana;
	
	@NotBlank
	private String instansi;	
	
	@NotNull
	private BigDecimal paguAnggaran;

	@NotBlank
	private String nomorSuratPermohonan;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSuratPermohonan;
	
	private String tempatPemaparan;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalPemaparan;

	private String telaahanIntelijen;

	private Boolean tindakLanjut;

	private String tindakLanjutKeterangan;

	private String nomorSprintWalpam;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSprintWalpam;
	
	private String namaPetugasPelaksana;

	private BigDecimal nilaiKontrak;

	private HasilPamstra hasilPelaksanaan;

	private String hasilPelaksanaanKeterangan;

	private String nomorKertasKerja;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalKertasKerja;
	
	private String keterangan;

	private String urlFile;
	
}
