package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RegisterArsipResponse implements Serializable {
	
	private static final long serialVersionUID = 9196266749399869259L;

	private String id;
	
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
