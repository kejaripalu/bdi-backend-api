package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.JenisSurat;
import lombok.Data;

@Data
public class RegisterSuratMasukCreateRequest implements Serializable {

  	private static final long serialVersionUID = 982039392766435583L;

	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalPenerimaanSurat;
    
    @JsonFormat(pattern="HH:mm", timezone="GMT+8")
	private Date jamPenerimaanSurat;
	
    @NotBlank
	private String asal;
	
    @NotBlank
	private String nomorSurat;
	
    @NotBlank
	private String perihal;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalSurat;
	
	@NotNull
	private JenisSurat jenisSurat;
	
	private String isiDisposisi;
	
	private String tindakLanjutDisposisi;
	
	private String keterangan;
	
	private String urlFile;
	
}
