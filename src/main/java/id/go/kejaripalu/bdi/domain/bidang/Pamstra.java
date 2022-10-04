package id.go.kejaripalu.bdi.domain.bidang;

/**
 * 
 * @author ucup
 * Bidang Pengamanan Pembangunan Strategis
 * DIR D
 *
 */
public enum Pamstra {
	
    INFRASTRUKTUR_JALAN("Infrastruktur Jalan"),
    INFRASTRUKTUR_PERKERETAAPIAN("Infrastruktur Perkeretaapian"),
    INFRASTRUKTUR_KEBANDARUDARAAN("Infrastruktur Kebandarudaraan"),
    INFRASTRUKTUR_TELEKOMUNIKASI("Infrastruktur Telekomunikasi"),
    INFRASTRUKTUR_KEPELABUHAN("Infrastruktur Kepelabuhan"),
    SMELTER("Smelter"),
    INFRASTRUKTUR_PENGOLAHAN_AIR("Infrastruktur Pengolahan Air"),
    TANGGUL("Tanggul"),
    BENDUNGAN("Bendungan"),
    PERTANIAN("Pertanian"),
    KELAUTAN("Kelautan"),
    KETENAGALISTRIKAN("Ketenagalistrikan"),
    ENERGI_ALTERNATIF("Energi Alternatif"),
    MINYAK_GAS_BUMI("Minyak dan Gas Bumi"),
    IPTEK("Ilmu Pengetahuan dan Teknologi"),
    PERUMAHAN("Perumahan"),
    PARIWISATA("Pariwisata"),
    KAWASAN_INDUSTRI_PRIORITAS_KEK("Kawasan Industri Prioritas / Kawasan Ekonomi Khusus"),
    POS_LINTAS_BATAS_NEGARA_SARANA_PENUNJANG("Pos Lintas Batas Negara dan Sarana Penunjang"),
    SEKTOR_LAINNYA("Sektor Lainnya");
	
	private String description;
	
	private Pamstra(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
