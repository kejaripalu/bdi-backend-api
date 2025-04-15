package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.Sektor;
import lombok.Data;

@Data
public class RegisterKegiatanIntelijenRequest implements Serializable {
	
	private static final long serialVersionUID = -8267795277498883279L;

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
