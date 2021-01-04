package Entity;

public class Kendaraan {
    private String no_plat;
    private String tipe_kendaraan;
    private int id_customer;
    private String merk;
    private String jns_kendaraan;

    @Override
    public String toString() {
        return "Kendaraan{" +
                "no_plat=" + no_plat +
                ", tipe_kendaraan='" + tipe_kendaraan + '\'' +
                ", id_customer=" + id_customer +
                ", merk='" + merk + '\'' +
                ", jns_kendaraan='" + jns_kendaraan + '\'' +
                '}';
    }

    public String getNo_plat() {
        return no_plat;
    }

    public void setNo_plat(String no_plat) {
        this.no_plat = no_plat;
    }

    public String getTipe_kendaraan() {
        return tipe_kendaraan;
    }

    public void setTipe_kendaraan(String tipe_kendaraan) {
        this.tipe_kendaraan = tipe_kendaraan;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJns_kendaraan() {
        return jns_kendaraan;
    }

    public void setJns_kendaraan(String jns_kendaraan) {
        this.jns_kendaraan = jns_kendaraan;
    }
}
