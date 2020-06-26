package cz.prorobot.webapp.entity;

public class Uzivatel {
    private Long id;
    private String prijmeni;
    private String jmeno;
    private int role;
    private String heslo_hash;

    public Uzivatel() {
    }

    public Uzivatel(Long id, String prijmeni, String jmeno, int role, String heslo_hash) {
        this.id = id;
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;
        this.role = role;
        this.heslo_hash = heslo_hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getHeslo_hash() {
        return heslo_hash;
    }

    public void setHeslo_hash(String heslo_hash) {
        this.heslo_hash = heslo_hash;
    }

    @Override
    public String toString() {
        return "Uzivatel{" +
                "id=" + id +
                ", prijmeni='" + prijmeni + '\'' +
                ", jmenono='" + jmeno + '\'' +
                ", role=" + role +
                ", heslo_hash='" + heslo_hash + '\'' +
                '}';
    }
}
