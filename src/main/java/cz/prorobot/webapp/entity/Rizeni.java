package cz.prorobot.webapp.entity;

public class Rizeni {
    private Long id;
    private String klic;
    private String hodnota;

    public Rizeni() {
    }

    public Rizeni(Long id, String klic, String hodnota) {
        this.id = id;
        this.klic = klic;
        this.hodnota = hodnota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlic() {
        return klic;
    }

    public void setKlic(String klic) {
        this.klic = klic;
    }

    public String getHodnota() {
        return hodnota;
    }

    public void setHodnota(String hodnota) {
        this.hodnota = hodnota;
    }

    @Override
    public String toString() {
        return "Rizeni{" +
                "id=" + id +
                ", klic='" + klic + '\'' +
                ", hodnota='" + hodnota + '\'' +
                '}';
    }
}
