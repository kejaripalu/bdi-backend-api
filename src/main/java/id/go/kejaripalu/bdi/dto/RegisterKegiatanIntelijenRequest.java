package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.Data;

@Data
public class RegisterKegiatanIntelijenRequest {
	
	@NotNull
	private BidangDirektorat bidangDirektorat;

	@NotNull
	private Sektor sektor;
	
	@NotBlank
	private String nomor;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggal;
	
	@NotBlank
	private String perihal;
	
	@NotBlank
	private String namaPetugasPelaksana;
	
	private String hasilPelaksanaanKegiatan;
	
	private String keterangan;
	
	private String urlFile;
	
}
