package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.JenisSurat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterSuratKeluarDTO(
        String ids,
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
        Date tanggalSurat,
        @NotBlank
        String nomorSurat,
        @NotBlank
        String kepada,
        @NotBlank
        String perihal,
        @NotNull
        JenisSurat jenisSurat,
        String lampiran,
        String keterangan,
        String urlFile
) {
}
