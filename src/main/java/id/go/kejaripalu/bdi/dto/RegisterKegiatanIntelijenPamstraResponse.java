package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.HasilPamstra;
import id.go.kejaripalu.bdi.util.Sektor;
import lombok.Data;

@Data
public class RegisterKegiatanIntelijenPamstraResponse implements Serializable {

	private static final long serialVersionUID = -3895039557714683393L;

	private String ids;
	
	private Sektor sektor;
	
	private String namaKegiatan;
	
	private String sumberDana;
	
	private String instansi;
	
	private BigDecimal paguAnggaran;
	
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
