package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RegisterTelaahanIntelijenResponse {
	
	private String id;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggal;
	
	private String nomor;

	private String pembuat;
	
	private String perihal;
	
	private String lampiran;
	
	private String tindakLanjut;
	
	private String keterangan;
	
	private String urlFile;
	
}
