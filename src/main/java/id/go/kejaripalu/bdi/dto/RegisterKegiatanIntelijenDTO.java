package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.Sektor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterKegiatanIntelijenDTO(
        String ids,
        @NotNull BidangDirektorat bidangDirektorat,
        @NotNull Sektor sektor,
        @NotBlank String nomor,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggal,
        @NotBlank String perihal,
        @NotBlank String namaPetugasPelaksana,
        String hasilPelaksanaanKegiatan,
        String keterangan,
        String urlFile
) {
}
