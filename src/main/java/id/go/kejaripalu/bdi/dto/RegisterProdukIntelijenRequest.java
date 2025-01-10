package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.domain.util.Sektor;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Data
public class RegisterProdukIntelijenRequest implements Serializable {

    private static final long serialVersionUID = 6227614813321255106L;

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
