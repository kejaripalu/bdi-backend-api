package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.util.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.util.Sektor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterProdukIntelijenDTO(
        String ids,
        @NotNull JenisProdukIntelijen jenisProdukIntelijen,
        @NotBlank String nomorProduk,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date tanggalProduk,
        @NotNull Sektor sektor,
        @NotBlank String perihal,
        String disposisiPimpinan,
        String keterangan,
        String urlFile
) {
}
