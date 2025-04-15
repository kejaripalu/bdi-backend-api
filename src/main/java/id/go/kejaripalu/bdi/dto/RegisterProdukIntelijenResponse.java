package id.go.kejaripalu.bdi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.util.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.util.Sektor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RegisterProdukIntelijenResponse implements Serializable {

    private static final long serialVersionUID = 5466222738041315962L;

	private String ids;

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
