package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.HasilPamstra;
import id.go.kejaripalu.bdi.util.Sektor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record RegisterKegiatanIntelijenPamstraDTO(
        String ids,
        @NotNull Sektor sektor,
        @NotBlank String namaKegiatan,
        @NotBlank String sumberDana,
        @NotBlank String instansi,
        @NotNull BigDecimal paguAnggaran,
        @NotBlank String nomorSuratPermohonan,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalSuratPermohonan,
        String tempatPemaparan,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalPemaparan,
        String telaahanIntelijen,
        Boolean tindakLanjut,
        String tindakLanjutKeterangan,
        String nomorSprintWalpam,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalSprintWalpam,
        String namaPetugasPelaksana,
        BigDecimal nilaiKontrak,
        HasilPamstra hasilPelaksanaan,
        String hasilPelaksanaanKeterangan,
        String nomorKertasKerja,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalKertasKerja,
        String keterangan,
        String urlFile
) {
}
