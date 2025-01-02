package id.go.kejaripalu.bdi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RegisterTamuPPHPPMResponse {

	private String id;
	
	private String namaPetugasPenerima;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggal;
	
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date jam;
	
	@NotBlank
	private String namaTamu;
	
	private String tempatLahirTamu;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalLahirTamu;
	
	private String alamat;
	
	private String jenisKelamin;
	
	private String nomorHandphone;

	private String email;

	private String pekerjaan;

	private String nomorIdentitas;

	private String namaOrganisasi;

	private String informasiYangDisampaikan;

	private String dokumenYangDisampaikan;

	private String tandaTangan;

	private String keterangan;

	private String urlFile;
	
}
