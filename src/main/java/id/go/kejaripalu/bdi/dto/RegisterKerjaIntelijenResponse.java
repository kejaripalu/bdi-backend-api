package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.bidang.BidangDirektorat;
import id.go.kejaripalu.bdi.domain.bidang.Sektor;
import lombok.Data;

@Data
public class RegisterKerjaIntelijenResponse {

	private String id;
	
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date tanggalWaktuDiterima;
    
    @JsonFormat(pattern="HH:mm", timezone="GMT+8")
	private Date jamWaktuDiterima;
	
	private String sumberBapul;
	
	private String nilaiDataInformasi;
	
	private String uraianPeristiwaMasalah;
	
	private String catatan;
	
	private String disposisiTindakan;
	
	private String tindakLanjut;
	
	private String keterangan;
	
	private String urlFile;
	
	private BidangDirektorat bidangDirektorat;
	
	private Sektor sektor;
			
}
