package id.go.kejaripalu.bdi.domain;

public enum JenisProdukIntelijen {

    LAPINHAR("Laporan Informasi Harian"),
    LAPINSUS("Laporan Informasi Khusus"),
    LAPSUS("Laporan Intelijen Khusus"),
    LAPHASTUG("Laporan Hasil Pelaksanaan Tugas"),
    LAPOPSIN("Laporan Hasil Operasi Intelijen"),
    LAPAT("Laporan Atensi"),
//    LAHIN("Telaahan Intelijen"),
    KIRKA("Perkiraan Keadaan Intelijen"),
    TROOP_INFO("Troop-Info");

    private String description;

    JenisProdukIntelijen(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
