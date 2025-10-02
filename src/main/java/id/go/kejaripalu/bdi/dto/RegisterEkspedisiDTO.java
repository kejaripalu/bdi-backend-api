package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.JenisSurat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterEkspedisiDTO(
        String ids,
        @NotBlank String nomorSurat,
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8") Date tanggalSurat,
        @NotBlank String kepada,
        @NotBlank String perihal,
        String lampiran,
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8") Date tanggalTandaTerima,
        @JsonFormat(pattern="HH:mm", timezone="GMT+8") Date jamTandaTerima,
        String namaDanParaf,
        String keterangan,
        String urlFile,
        @NotNull JenisSurat jenisSurat
) {
}
