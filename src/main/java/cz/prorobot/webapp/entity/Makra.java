package cz.prorobot.webapp.entity;

public class Makra {
    private Long id;
    private String nazev;
    private String hodnota;
    private String popis;

    public Makra() {
    }

    public Makra(Long id, String nazev, String hodnota, String popis) {
        this.id = id;
        this.nazev = nazev;
        this.hodnota = hodnota;
        this.popis = popis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
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
        return "Makra{" +
                "id=" + id +
                ", nazev='" + nazev + '\'' +
                ", hodnota='" + hodnota + '\'' +
                ", popis='" + popis + '\'' +
                '}';
    }
}
