package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.SektorPeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DataPetaDTO(
        String ids,
        @NotNull BidangDirektorat bidangDirektorat,
        @NotNull SektorPeta sektor,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggal,
        @NotBlank String lokasi,
        @NotNull Double latitude,
        @NotNull Double longitude,
        @NotBlank String siapa,
        @NotBlank String apa,
        @NotBlank String mengapa,
        @NotBlank String bagaimana,
        String keterangan
) {
}
