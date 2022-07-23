package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.JenisSurat;
import lombok.Data;

@Data
public class SuratMasukCreateRequest {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+8")
	private Date waktuPenerimaanSurat;
	
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
	
}
