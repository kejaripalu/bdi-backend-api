package id.go.kejaripalu.bdi.domain.bidang;

/**
 * 
 * @author ucup
 * Bidang Sosial, Budaya, dan Kemasyarakatan
 * DIR B
 *
 */
public enum Sosbudmas {
	
    PENGAWASAN_BARCET_DALAM_NEGERI("Pengawasan Peredaran Barang Cetakan Dalam Negeri"),
    PENGAWASAN_BARCET_IMPORT("Pengawasan Peredaran Import Barang Cetakan"),
    PENGAWASAN_SISTEM_PEMBUKUAN("Pengawasan Sistem Pembukuan"),
    PENGAWASAN_MEDIA_KOMUNIKASI("Pengawasan Media Komunikasi"),
    PAKEM("Pengawasan Aliran Kepercayaan dan Keagamaan dalam Masyarakat"),
    PENCEGAHAN_PENYALAHGUNAAN_PENODAAN_AGAMA("Pencegahan Penyalahgunaan dan/atau Penodaan Agama"),
    KETAHANAN_PANGAN("Ketahanan Budaya"),
    PEMBERDAYAAN_MASYARAKAT_DESA("Pemberdayaan Masyarakat Desa"),
    PENGAWASAN_ORMAS_LSM("Pengawasan Organisasi Masyarakat dan Lembaga Swadaya Masyarakat"),
    PENCEGAHAN_KONFLIK_SOSIAL("Pencegahan Konflik Sosial"),
    KETERTIBAN_KETENTRAMAN_UMUM("Ketertiban dan Ketentraman Umum"),
    PEMBINAAN_MASYARAKAT_TAAT_HUKUM("Pembinaan Masyarakat Taat Hukum");
	
	private String description;
	
	private Sosbudmas(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
