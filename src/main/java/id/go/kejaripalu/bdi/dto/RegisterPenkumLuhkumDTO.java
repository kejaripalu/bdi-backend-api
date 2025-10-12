package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.JenisKegiatanPenkumLuhkum;
import id.go.kejaripalu.bdi.util.ProgramPenkumLuhkum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterPenkumLuhkumDTO(
        String ids,
        @NotBlank String nomorSuratPerintah,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalSuratPerintah,
        @NotBlank String sasaranKegiatan,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalKegiatan,
        @NotBlank String tempat,
        @NotBlank String materi,
        @NotNull Integer jumlahPeserta,
        String keterangan,
        String urlFoto1,
        String urlFoto2,
        String urlFoto3,
        String urlFoto4,
        @NotNull JenisKegiatanPenkumLuhkum jenisKegiatan,
        @NotNull ProgramPenkumLuhkum program
) {
}
