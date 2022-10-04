package id.go.kejaripalu.bdi.domain.bidang;

/**
 * 
 * @author ucup
 * Bidang Teknologi Informasi, dan Produksi Intelijen
 * DIR E
 *
 */
public enum Tiprodin {
	
	PRODUKSI_INTELIJEN("Produksi Intelijen"),
    PEMANTAUAN("Pemantauan"),
    INTELIJEN_SINYAL("Intelijen Sinyal"),
    INTELIJEN_SIBER("Intelijen Siber"),
    KLANDESTINE("Klandestine"),
    DIGITAL_FORENSIK("Digital Forensik"),
    TRANSMISI_BERITA_SANDI("Transmisi Berita Sandi"),
    KONTRA_PENGINDERAAN("Kontra Penginderaan"),
    AUDIT_PENGUJIAN_SISTEM_KEAMANAN_INFORMASI("Audit dan Pengujian Sistem Keamanan Informasi"),
    PENGAMANAN_SINYAL("Pengamanan Sinyal"),
    PENGEMBANGAN_SDM_SANDI("Pengembangan SDM dan Sandi"),
    PENGEMBANGAN_SDM_INTELIJEN_LAINNYA("Pengembangan SDM Intelijen Lainnya"),
    PENGEMBANGAN_TEKNOLOGI("Pengembangan Teknologi"),
    PENGEMBANGAN_PROSEDUR_APLIKASI("Pengembangan Prosedur dan Aplikasi");
	
	private String description;
	
	private Tiprodin(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
