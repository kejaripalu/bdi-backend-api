package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.JenisSurat;
import lombok.Data;

@Data
public class RegisterSuratKeluarUpdateRequest {

	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalSurat;
    	
    @NotBlank
    private String nomorSurat;
	
    @NotBlank
    private String kepada;

    @NotBlank
	private String perihal;
	
    @NotNull
	private JenisSurat jenisSurat;
    
    private String lampiran;
	
	private String keterangan;
	
	private String urlFile;
		
}
