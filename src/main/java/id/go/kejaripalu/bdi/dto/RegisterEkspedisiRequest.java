package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.JenisSurat;
import lombok.Data;

@Data
public class RegisterEkspedisiRequest implements Serializable {
	
	private static final long serialVersionUID = -4801421775111817618L;

	@NotBlank
	private String nomorSurat;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalSurat;
    
    @NotBlank
    private String kepada;
    
    @NotBlank
    private String perihal;
    
    private String lampiran;
    
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalTandaTerima;
    
    @JsonFormat(pattern="HH:mm", timezone="GMT+8")
	private Date jamTandaTerima;
	
	private String namaDanParaf;
	
	private String keterangan;
	
	private String urlFile;
	
	@NotNull
	private JenisSurat jenisSurat;
			
}
