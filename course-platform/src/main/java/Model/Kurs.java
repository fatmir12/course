package Model;

public class Kurs {
    private long id;
    private String naziv;
    private String nivoObuke;
    private int trajanjeDana;
    private int cijenaKursa;

    public Kurs(){}

    public Kurs(long id, String naziv, String nivoObuke, int trajanjeDana,int cijenaKursa) {
        this.id = id;
        this.naziv = naziv;
        this.nivoObuke = nivoObuke;
        this.trajanjeDana = trajanjeDana;
        this.cijenaKursa = cijenaKursa;
    }

    public long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getNivoObuke() {
        return nivoObuke;
    }

    public int getTrajanjeDana() {
        return trajanjeDana;
    }

    public void setTrajanjeDana(int trajanjeDana) {
        this.trajanjeDana = trajanjeDana;
    }

    public void setNivoObuke(String nivoObuke) {
        this.nivoObuke = nivoObuke;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCijenaKursa() {
        return cijenaKursa;
    }
    public void setCijenaKursa(int cijenaKursa) {
        this.cijenaKursa = cijenaKursa;
    }
}
