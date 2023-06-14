package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.BidangDirektorat;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.Data;

@Data
public class RegisterOperasiIntelijenResponse {

private String id;
	
	private BidangDirektorat bidangDirektorat;

	private Sektor sektor;
	
	private String nomor;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggal;
	
	private String perihal;
	
	private String namaPetugasPelaksana;
	
	private String hasilPelaksanaanOperasi;
	
	private String keterangan;
	
	private String urlFile;
	
}
