package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.bidang.BidangDirektorat;
import id.go.kejaripalu.bdi.domain.bidang.Sektor;
import lombok.Data;

@Data
public class RegisterKerjaIntelijenRequest {

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalWaktuDiterima;
    
    @JsonFormat(pattern="HH:mm", timezone="GMT+8")
	private Date jamWaktuDiterima;
	
    @NotBlank
	private String sumberBapul;
	
    @NotBlank
	private String nilaiDataInformasi;
	
    @NotBlank
	private String uraianPeristiwaMasalah;
	
	private String catatan;
	
	private String disposisiTindakan;
	
	private String tindakLanjut;
	
	private String keterangan;
	
	private String urlFile;
	
	@NotNull
	private BidangDirektorat bidangDirektorat;
	
	@NotNull
	private Sektor sektor;
			
}
