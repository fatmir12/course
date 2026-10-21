package Model;

import java.util.ArrayList;
import java.util.List;

public class Instruktor {
    private long id;
    private String ime;
    private String prezime;

    List<Kurs> kursevi=new ArrayList<>();

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
        this.kursevi = kursevi;
    }

    public boolean mozeDodatiKurs(){return kursevi.size()<5;}

    public void dodajKurs(Kurs k){
        if(mozeDodatiKurs()){
            kursevi.add(k);
        }
    }
}
