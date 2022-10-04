package id.go.kejaripalu.bdi.domain.bidang;

/**
 * 
 * @author ucup
 * Bidang Ekonomi dan Keuangan
 * DIR C
 *
 */
public enum Ekokeu {
	
    LEMBAGA_KEUANGAN("Lembaga Keuangan"),
    KEUANGAN_NEGARA("Keuangan Negara"),
    MONETER("Moneter"),
    PENELUSURAN_ASET("Penelusuran Aset"),
    INVESTASI_PENANAMAN_MODAL("Investasi / Penanaman Modal"),
    PERPAJAKAN("Perpajakan"),
    KEPABEANAN("Kepabeanan"),
    CUKAI("Cukai"),
    PERDAGANGAN("Perdagangan"),
    PERINDUSTRIAN("Perindustrian"),
    KETENAGAKERJAAN("Ketenagakerjaan"),
    PERKEBUNAN("Perkebunan"),
    KEHUTANAN("Kehutanan"),
    LINGKUNGAN_HIDUP("Lingkungan Hidup"),
    PERIKANAN("Perikanan"),
    AGRARIA_TATARUANG("Agraria / Tataruang");
	
	private String description;
	
	private Ekokeu(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
