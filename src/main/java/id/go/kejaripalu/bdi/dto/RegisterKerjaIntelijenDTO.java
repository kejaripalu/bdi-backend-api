package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.Sektor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterKerjaIntelijenDTO(
        String ids,
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8") Date tanggalWaktuDiterima,
        @JsonFormat(pattern="HH:mm", timezone="GMT+8") Date jamWaktuDiterima,
        @NotBlank String sumberBapul,
        @NotBlank String nilaiDataInformasi,
        @NotBlank String uraianPeristiwaMasalah,
        String catatan,
        String disposisiTindakan,
        String tindakLanjut,
        String keterangan,
        String urlFile,
        @NotNull BidangDirektorat bidangDirektorat,
        @NotNull Sektor sektor
) {
}
