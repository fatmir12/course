package ptf.unze.course_platform.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="instruktor")
public class Instruktor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String prezime;

    @OneToMany(
            mappedBy = "instruktor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Kurs> kursevi=new ArrayList<>();

    public Instruktor(){}

    public Instruktor(long id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public List<Kurs> getKursevi() {
        return kursevi;
    }

    public void setKursevi(List<Kurs> kursevi) {
        this.kursevi.clear();

        if (kursevi != null){
            for(Kurs k:new ArrayList<>(kursevi)){
                dodajKurs(k);
            }
        }
    }


    public boolean mozeDodatiKurs(){return kursevi.size()<5;}

    public void dodajKurs(Kurs k){
        if(mozeDodatiKurs()){
            kursevi.add(k);
        }
    }
}
