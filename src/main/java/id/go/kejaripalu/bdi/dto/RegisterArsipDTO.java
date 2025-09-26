package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record RegisterArsipDTO(
        String ids,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalPenerimaanArsip,
        @JsonFormat(pattern = "HH:mm", timezone = "GMT+8") Date jamPenerimaanArsip,
        @NotBlank String diterimaDari,
        @NotBlank String nomorSurat,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalSurat,
        @NotBlank String perihal,
        String lampiran,
        @NotBlank String kodePenyimpanan,
        String keterangan,
        String urlFile
) {
}
