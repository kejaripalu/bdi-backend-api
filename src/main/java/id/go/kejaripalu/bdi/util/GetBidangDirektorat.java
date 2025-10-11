package id.go.kejaripalu.bdi.util;

public class GetBidangDirektorat {

    public static BidangDirektorat get(String stringBidangDirektorat) {
        return switch (stringBidangDirektorat) {
            case "IPOLHANKAM" -> BidangDirektorat.IPOLHANKAM;
            case "SOSBUDMAS" -> BidangDirektorat.SOSBUDMAS;
            case "EKOKEU" -> BidangDirektorat.EKOKEU;
            case "PAMSTRA" -> BidangDirektorat.PAMSTRA;
            case "TIPRODIN" -> BidangDirektorat.TIPRODIN;
            default -> null;
        };
    }
}
