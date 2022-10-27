package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RegisterArsipRequest {

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalPenerimaanArsip;
	
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jamPenerimaanArsip;
	
	@NotBlank
	private String diterimaDari;
	
	@NotBlank
	private String nomorSurat;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSurat;
	
	@NotBlank
	private String perihal;
	
	private String lampiran;
	
	private String kodePenyimpanan;
	
	private String keterangan;
	
	private String urlFile;
	
}
