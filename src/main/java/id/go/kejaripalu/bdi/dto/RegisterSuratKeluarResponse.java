package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.JenisSurat;
import lombok.Data;

@Data
public class RegisterSuratKeluarResponse implements Serializable {
	
	private static final long serialVersionUID = 3072044264706177401L;

	private String ids;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSurat;
	
	private String nomorSurat;
	
    private String kepada;
	
    private String perihal;
	
    private String lampiran;
	
	private String keterangan;

	private JenisSurat jenisSurat;
	
	private String urlFile;
	
}
