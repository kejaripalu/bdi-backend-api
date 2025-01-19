package id.go.kejaripalu.bdi.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.JenisKelamin;
import id.go.kejaripalu.bdi.domain.util.JenisPelayanan;
import lombok.Data;

@Data
public class RegisterTamuPPHPPMResponse implements Serializable {

	private static final long serialVersionUID = -818464337279197667L;

	private String ids;
	
	private JenisPelayanan jenisPelayanan;
	
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
	
	private JenisKelamin jenisKelamin;
	
	private String nomorHandphone;

	private String email;

	private String pekerjaan;

	private String nomorIdentitas;

	private String namaOrganisasi;

	private String informasiYangDisampaikan;

	private String dokumenYangDisampaikan;

	private String keterangan;

	private String urlFile;
	
}
