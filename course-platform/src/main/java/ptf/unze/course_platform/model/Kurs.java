package ptf.unze.course_platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="kursevi")
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String nivoObuke;
    private int trajanjeDana;
    private int cijenaKursa;

    @ManyToOne
    @JoinColumn(name="instruktor_id")
    @JsonIgnore
    private  Instruktor instruktor;

    @OneToMany(
            mappedBy = "kurs",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Lekcija> lekcije = new ArrayList<>();

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

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public List<Lekcija> getLekcije() {
        return lekcije;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public void setLekcije(List<Lekcija> lekcije) {
        this.lekcije = lekcije;
    }

    public void dodajLekciju(Lekcija lekcija) {
        if(lekcija==null){
            throw new IllegalArgumentException("Lekcija ne smije biti null");
        }
        if(lekcije.contains(lekcija)){
            return;
        }
            }
    public void uloniLekciju(Lekcija lekcija){
        if(lekcije.remove(lekcija)){
            lekcija.setKurs(null);
        }
    }
}
