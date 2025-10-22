package id.go.kejaripalu.bdi.util;

public enum BidangDirektorat {
	
	IPOLHANKAM("Ideologi, Politik, Pertahanan dan Keamanan"),
	SOSBUDMAS("Sosial, Budaya, dan Kemasyarakatan"),
	EKOKEU("Ekonomi dan Keuangan"),
	PAMSTRA("Pengamanan Pembangunan Strategis"),
	TIPRODIN("Teknologi Informasi, dan Produksi Intelijen");
	
	private final String description;
	
	BidangDirektorat(String description) {
		this.description = description;
	}

}
