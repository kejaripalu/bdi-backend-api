package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.JenisKegiatanPenkumLuhkum;
import id.go.kejaripalu.bdi.util.ProgramPenkumLuhkum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterPenkumLuhkumRequest implements Serializable {
	
	private static final long serialVersionUID = -5294072992331513008L;
	
	@NotBlank
	private String nomorSuratPerintah;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalSuratPerintah;
	
	@NotBlank
	private String sasaranKegiatan;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalKegiatan;
		
	@NotBlank
	private String tempat;
	
	@NotBlank
	private String materi;
	
	@NotNull
	private Integer jumlahPeserta;
	
	private String keterangan;
	
	private String urlFoto1;
	
	private String urlFoto2;
	
	private String urlFoto3;
	
	private String urlFoto4;
	
	@NotNull
	private JenisKegiatanPenkumLuhkum jenisKegiatan;
	
	@NotNull
	private ProgramPenkumLuhkum program;

}
