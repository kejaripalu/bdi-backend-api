package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.go.kejaripalu.bdi.domain.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.domain.bidang.Sektor;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterProdukIntelijenResponse {

    private String id;

    private JenisProdukIntelijen jenisProdukIntelijen;

    private String nomorProduk;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tanggalProduk;

    private Sektor sektor;

    private String perihal;

    private String disposisiPimpinan;

    private String keterangan;

    private String urlFile;

}
