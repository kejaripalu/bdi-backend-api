package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.JenisKelamin;
import id.go.kejaripalu.bdi.util.JenisPelayanan;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record RegisterTamuPPHPPMDTO(
        String ids,
        JenisPelayanan jenisPelayanan,
        @NotBlank String namaPetugasPenerima,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggal,
        @JsonFormat(pattern = "HH:mm", timezone = "GMT+8") Date jam,
        @NotBlank String namaTamu,
        String tempatLahirTamu,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalLahirTamu,
        String alamat,
        JenisKelamin jenisKelamin,
        String nomorHandphone,
        String email,
        String pekerjaan,
        String nomorIdentitas,
        String namaOrganisasi,
        @NotBlank String informasiYangDisampaikan,
        String dokumenYangDisampaikan,
        String keterangan,
        String urlFile
) {
}
