package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.Sektor;
import lombok.Data;

@Data
public class RegisterOperasiIntelijenResponse implements Serializable {

	private static final long serialVersionUID = 9000865980135503287L;

	private String ids;
	
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
