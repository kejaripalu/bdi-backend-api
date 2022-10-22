package id.go.kejaripalu.bdi.domain.util;

public enum Sektor {
	
	// A
	PENGAMANAN_PANCASILA("Pengamanan Pancasila", BidangDirektorat.IPOLHANKAM),
    KESATUAN_PERSATUAN_BANGSA("Kesatuan dan Persatuan Bangsa", BidangDirektorat.IPOLHANKAM),
    GERAKAN_SEPARATIS("Gerakan Separatis", BidangDirektorat.IPOLHANKAM),
    PENYELENGGARAAN_PEMERINTAHAN("Penyelenggaraan Pemerintahan", BidangDirektorat.IPOLHANKAM),
    PARPOL("Partai Politik", BidangDirektorat.IPOLHANKAM),
    PEMILU_PILKADA("Pemilihan Umum dan Pemilihan Kepala Daerah", BidangDirektorat.IPOLHANKAM),
    GERAKAN_TERORIS_RADIKAL("Gerakan Teroris dan Radikal", BidangDirektorat.IPOLHANKAM),
    PENGAMANAN_WILAYAH_TERITORIAL("Pengamanan wilayah Teritorial", BidangDirektorat.IPOLHANKAM),
    KEJAHATAN_SIBER("Kejahatan Siber", BidangDirektorat.IPOLHANKAM),
    CEGAH_TANGKAL("Cegah Tangkal", BidangDirektorat.IPOLHANKAM),
    PENGAWASAN_ORANG_ASING("Pengawasan Orang Asing", BidangDirektorat.IPOLHANKAM),
    PENGAMANAN_SUMBER_DAYA_ORGANISASI_KEJAKSAAN("Pengamanan Sumber Daya Organisasi Kejaksaan", BidangDirektorat.IPOLHANKAM),
    PENGAMANAN_PENANGANAN_PERKARA("Pengamanan Penanganan Perkara", BidangDirektorat.IPOLHANKAM),
	
    // B
	PENGAWASAN_BARCET_DALAM_NEGERI("Pengawasan Peredaran Barang Cetakan Dalam Negeri", BidangDirektorat.SOSBUDMAS),
    PENGAWASAN_BARCET_IMPORT("Pengawasan Peredaran Import Barang Cetakan", BidangDirektorat.SOSBUDMAS),
    PENGAWASAN_SISTEM_PEMBUKUAN("Pengawasan Sistem Pembukuan", BidangDirektorat.SOSBUDMAS),
    PENGAWASAN_MEDIA_KOMUNIKASI("Pengawasan Media Komunikasi", BidangDirektorat.SOSBUDMAS),
    PAKEM("Pengawasan Aliran Kepercayaan dan Keagamaan dalam Masyarakat", BidangDirektorat.SOSBUDMAS),
    PENCEGAHAN_PENYALAHGUNAAN_PENODAAN_AGAMA("Pencegahan Penyalahgunaan dan/atau Penodaan Agama", BidangDirektorat.SOSBUDMAS),
    KETAHANAN_BUDAYA("Ketahanan Budaya", BidangDirektorat.SOSBUDMAS),
    PEMBERDAYAAN_MASYARAKAT_DESA("Pemberdayaan Masyarakat Desa", BidangDirektorat.SOSBUDMAS),
    PENGAWASAN_ORMAS_LSM("Pengawasan Organisasi Masyarakat dan Lembaga Swadaya Masyarakat", BidangDirektorat.SOSBUDMAS),
    PENCEGAHAN_KONFLIK_SOSIAL("Pencegahan Konflik Sosial", BidangDirektorat.SOSBUDMAS),
    KETERTIBAN_KETENTRAMAN_UMUM("Ketertiban dan Ketentraman Umum", BidangDirektorat.SOSBUDMAS),
    PEMBINAAN_MASYARAKAT_TAAT_HUKUM("Pembinaan Masyarakat Taat Hukum", BidangDirektorat.SOSBUDMAS),
    
    // C
    LEMBAGA_KEUANGAN("Lembaga Keuangan", BidangDirektorat.EKOKEU),
    KEUANGAN_NEGARA("Keuangan Negara", BidangDirektorat.EKOKEU),
    MONETER("Moneter", BidangDirektorat.EKOKEU),
    PENELUSURAN_ASET("Penelusuran Aset", BidangDirektorat.EKOKEU),
    INVESTASI_PENANAMAN_MODAL("Investasi / Penanaman Modal", BidangDirektorat.EKOKEU),
    PERPAJAKAN("Perpajakan", BidangDirektorat.EKOKEU),
    KEPABEANAN("Kepabeanan", BidangDirektorat.EKOKEU),
    CUKAI("Cukai", BidangDirektorat.EKOKEU),
    PERDAGANGAN("Perdagangan", BidangDirektorat.EKOKEU),
    PERINDUSTRIAN("Perindustrian", BidangDirektorat.EKOKEU),
    KETENAGAKERJAAN("Ketenagakerjaan", BidangDirektorat.EKOKEU),
    PERKEBUNAN("Perkebunan", BidangDirektorat.EKOKEU),
    KEHUTANAN("Kehutanan", BidangDirektorat.EKOKEU),
    LINGKUNGAN_HIDUP("Lingkungan Hidup", BidangDirektorat.EKOKEU),
    PERIKANAN("Perikanan", BidangDirektorat.EKOKEU),
    AGRARIA_TATARUANG("Agraria / Tataruang", BidangDirektorat.EKOKEU),
    
    // D
    INFRASTRUKTUR_JALAN("Infrastruktur Jalan", BidangDirektorat.PAMSTRA),
    INFRASTRUKTUR_PERKERETAAPIAN("Infrastruktur Perkeretaapian", BidangDirektorat.PAMSTRA),
    INFRASTRUKTUR_KEBANDARUDARAAN("Infrastruktur Kebandarudaraan", BidangDirektorat.PAMSTRA),
    INFRASTRUKTUR_TELEKOMUNIKASI("Infrastruktur Telekomunikasi", BidangDirektorat.PAMSTRA),
    INFRASTRUKTUR_KEPELABUHAN("Infrastruktur Kepelabuhan", BidangDirektorat.PAMSTRA),
    SMELTER("Smelter", BidangDirektorat.PAMSTRA),
    INFRASTRUKTUR_PENGOLAHAN_AIR("Infrastruktur Pengolahan Air", BidangDirektorat.PAMSTRA),
    TANGGUL("Tanggul", BidangDirektorat.PAMSTRA),
    BENDUNGAN("Bendungan", BidangDirektorat.PAMSTRA),
    PERTANIAN("Pertanian", BidangDirektorat.PAMSTRA),
    KELAUTAN("Kelautan", BidangDirektorat.PAMSTRA),
    KETENAGALISTRIKAN("Ketenagalistrikan", BidangDirektorat.PAMSTRA),
    ENERGI_ALTERNATIF("Energi Alternatif", BidangDirektorat.PAMSTRA),
    MINYAK_GAS_BUMI("Minyak dan Gas Bumi", BidangDirektorat.PAMSTRA),
    IPTEK("Ilmu Pengetahuan dan Teknologi", BidangDirektorat.PAMSTRA),
    PERUMAHAN("Perumahan", BidangDirektorat.PAMSTRA),
    PARIWISATA("Pariwisata", BidangDirektorat.PAMSTRA),
    KAWASAN_INDUSTRI_PRIORITAS_KEK("Kawasan Industri Prioritas / Kawasan Ekonomi Khusus", BidangDirektorat.PAMSTRA),
    POS_LINTAS_BATAS_NEGARA_SARANA_PENUNJANG("Pos Lintas Batas Negara dan Sarana Penunjang", BidangDirektorat.PAMSTRA),
    SEKTOR_LAINNYA("Sektor Lainnya", BidangDirektorat.PAMSTRA),
    
    // E
    PRODUKSI_INTELIJEN("Produksi Intelijen", BidangDirektorat.TIPRODIN),
    PEMANTAUAN("Pemantauan", BidangDirektorat.TIPRODIN),
    INTELIJEN_SINYAL("Intelijen Sinyal", BidangDirektorat.TIPRODIN),
    INTELIJEN_SIBER("Intelijen Siber", BidangDirektorat.TIPRODIN),
    KLANDESTINE("Klandestine", BidangDirektorat.TIPRODIN),
    DIGITAL_FORENSIK("Digital Forensik", BidangDirektorat.TIPRODIN),
    TRANSMISI_BERITA_SANDI("Transmisi Berita Sandi", BidangDirektorat.TIPRODIN),
    KONTRA_PENGINDERAAN("Kontra Penginderaan", BidangDirektorat.TIPRODIN),
    AUDIT_PENGUJIAN_SISTEM_KEAMANAN_INFORMASI("Audit dan Pengujian Sistem Keamanan Informasi", BidangDirektorat.TIPRODIN),
    PENGAMANAN_SINYAL("Pengamanan Sinyal", BidangDirektorat.TIPRODIN),
    PENGEMBANGAN_SDM_SANDI("Pengembangan SDM dan Sandi", BidangDirektorat.TIPRODIN),
    PENGEMBANGAN_SDM_INTELIJEN_LAINNYA("Pengembangan SDM Intelijen Lainnya", BidangDirektorat.TIPRODIN),
    PENGEMBANGAN_TEKNOLOGI("Pengembangan Teknologi", BidangDirektorat.TIPRODIN),
    PENGEMBANGAN_PROSEDUR_APLIKASI("Pengembangan Prosedur dan Aplikasi", BidangDirektorat.TIPRODIN);
	
	private String description;
	private BidangDirektorat bidangDirektorat;
	
	private Sektor(String description, BidangDirektorat bidangDirektorat) {
		this.description = description;
		this.bidangDirektorat = bidangDirektorat;
	}

	public String getDescription() {
		return description;
	}

	public BidangDirektorat getBidangDirektorat() {
		return bidangDirektorat;
	}

}
