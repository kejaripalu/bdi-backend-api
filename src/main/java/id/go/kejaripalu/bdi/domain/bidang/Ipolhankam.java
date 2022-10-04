package id.go.kejaripalu.bdi.domain.bidang;

/**
 * 
 * @author ucup
 * Bidang Ideologi, Politik, Pertahanan dan Keamanan
 * DIR A	
 *
 */
public enum Ipolhankam {
	
	PENGAMANAN_PANCASILA("Pengamanan Pancasila"),
    KESATUAN_PERSATUAN_BANGSA("Kesatuan dan Persatuan Bangsa"),
    GERAKAN_SEPARATIS("Gerakan Separatis"),
    PENYELENGGARAAN_PEMERINTAHAN("Penyelenggaraan Pemerintahan"),
    PARPOL("Partai Politik"),
    PEMILU_PILKADA("Pemilihan Umum dan Pemilihan Kepala Daerah"),
    GERAKAN_TERORIS_RADIKAL("Gerakan Teroris dan Radikal"),
    PENGAMANAN_WILAYAH_TERITORIAL("Pengamanan wilayah Teritorial"),
    KEJAHATAN_SIBER("Kejahatan Siber"),
    CEGAH_TANGKAL("Cegah Tangkal"),
    PENGAWASAN_ORANG_ASING("Pengawasan Orang Asing"),
    PENGAMANAN_SUMBER_DAYA_ORGANISASI_KEJAKSAAN("Pengamanan Sumber Daya Organisasi Kejaksaan"),
    PENGAMANAN_PENANGANAN_PERKARA("Pengamanan Penanganan Perkara");
	
	private String description;
	
	private Ipolhankam(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
