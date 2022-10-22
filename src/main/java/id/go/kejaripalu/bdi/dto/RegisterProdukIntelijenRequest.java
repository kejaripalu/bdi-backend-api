package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RegisterProdukIntelijenRequest {

    @NotNull
    private JenisProdukIntelijen jenisProdukIntelijen;

    @NotBlank
    private String nomorProduk;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tanggalProduk;

    @NotNull
    private Sektor sektor;

    @NotBlank
    private String perihal;

    private String disposisiPimpinan;

    private String keterangan;

    private String urlFile;

}
