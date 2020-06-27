package cz.prorobot.webapp.entity;

public class Nastaveni {

    private Long id;
    private String klic;
    private String hodnota;
    private String popis;

    public Nastaveni() {
    }

    public Nastaveni(Long id, String klic, String hodnota, String popis) {
        this.id = id;
        this.klic = klic;
        this.hodnota = hodnota;
        this.popis = popis;
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

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        return "Nastaveni{" +
                "id=" + id +
                ", klic='" + klic + '\'' +
                ", hodnota='" + hodnota + '\'' +
                ", popis='" + popis + '\'' +
                '}';
    }
}
