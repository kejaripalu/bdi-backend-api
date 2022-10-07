package id.go.kejaripalu.bdi.domain.bidang;

public enum BidangDirektorat {
	
	IPOLHANKAM("Ideologi, Politik, Pertahanan dan Keamanan"),
	SOSBUDMAS("Sosial, Budaya, dan Kemasyarakatan"),
	EKOKEU("Ekonomi dan Keuangan"),
	PAMSTRA("Pengamanan Pembangunan Strategis"),
	TIPRODIN("Teknologi Informasi, dan Produksi Intelijen");
	
	private String description;
	
	private BidangDirektorat(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
