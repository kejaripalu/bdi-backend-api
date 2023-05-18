package id.go.kejaripalu.bdi.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.HasilPamstra;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.Data;

@Data
public class RegisterKegiatanIntelijenPamstraResponse {

	private String id;
	
	private Sektor sektor;
	
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
