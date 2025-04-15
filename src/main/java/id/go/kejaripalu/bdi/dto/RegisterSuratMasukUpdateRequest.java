package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.JenisSurat;
import lombok.Data;

@Data
public class RegisterSuratMasukUpdateRequest implements Serializable {
	
	private static final long serialVersionUID = 5041775988943518496L;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalPenerimaanSurat;
	
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jamPenerimaanSurat;
	
    private String asal;
	
    private String nomorSurat;
	
    private String perihal;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSurat;
	
	private JenisSurat jenisSurat;
	
	private String isiDisposisi;
	
	private String tindakLanjutDisposisi;
	
	private String keterangan;
	
	private String urlFile;

}
