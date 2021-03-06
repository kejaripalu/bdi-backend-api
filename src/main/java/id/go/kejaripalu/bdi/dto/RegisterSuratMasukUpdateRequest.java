package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.JenisSurat;
import lombok.Data;

@Data
public class RegisterSuratMasukUpdateRequest {
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date waktuPenerimaanSurat;
	
    private String asal;
	
    private String nomorSurat;
	
    private String perihal;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSurat;
	
	private JenisSurat jenisSurat;
	
	private String isiDisposisi;
	
	private String tindakLanjutDisposisi;
	
	private String keterangan;

}
