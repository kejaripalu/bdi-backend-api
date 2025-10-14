package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record RegisterTelaahanIntelijenDTO(
        String ids,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggal,
        @NotBlank String nomor,
        @NotBlank String pembuat,
        @NotBlank String perihal,
        String lampiran,
        String tindakLanjut,
        String keterangan,
        String urlFile
) {
}
