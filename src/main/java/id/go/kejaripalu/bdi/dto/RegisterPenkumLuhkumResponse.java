package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.JenisKegiatanPenkumLuhkum;
import id.go.kejaripalu.bdi.domain.util.ProgramPenkumLuhkum;
import lombok.Data;

@Data
public class RegisterPenkumLuhkumResponse implements Serializable {
	
	private static final long serialVersionUID = -1799592340732448197L;
	
	private String ids;

	private String nomorSuratPerintah;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSuratPerintah;
	
	private String sasaranKegiatan;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalKegiatan;
		
	private String tempat;
	
	private String materi;
	
	private Integer jumlahPeserta;
	
	private String keterangan;
	
	private String urlFoto1;
	
	private String urlFoto2;
	
	private String urlFoto3;
	
	private String urlFoto4;
	
	private JenisKegiatanPenkumLuhkum jenisKegiatan;
	
	private ProgramPenkumLuhkum program;
	
}
