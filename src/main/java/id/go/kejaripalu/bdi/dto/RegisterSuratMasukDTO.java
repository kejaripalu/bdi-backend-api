package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.JenisSurat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterSuratMasukDTO(
        String ids,
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
        Date tanggalPenerimaanSurat,
        @JsonFormat(pattern="HH:mm", timezone="GMT+8")
        Date jamPenerimaanSurat,
        @NotBlank
        String asal,
        @NotBlank
        String nomorSurat,
        @NotBlank
        String perihal,
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
        Date tanggalSurat,
        @NotNull
        JenisSurat jenisSurat,
        String isiDisposisi,
        String tindakLanjutDisposisi,
        String keterangan,
        String urlFile
) {
}
