package ptf.unze.course_platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "lekcije")
public class Lekcija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naslov;

    @Column(length = 255)
    private String sadrzaj;
    private int trajanjeMinuta;

    @ManyToOne
    @JoinColumn(name = "kurs_id")
    @JsonIgnore
    private Kurs kurs;
    public Lekcija(){}

    public Lekcija(Long id, String naslov, String sadrzaj, int trajanjeMinuta, Kurs kurs) {
        this.id = id;
        this.naslov = naslov;
        this.sadrzaj = sadrzaj;
        this.trajanjeMinuta = trajanjeMinuta;
        this.kurs = kurs;
    }

    public Long getId() {
        return id;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public int getTrajanjeMinuta() {
        return trajanjeMinuta;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public void setTrajanjeMinuta(int trajanjeMinuta) {
        this.trajanjeMinuta = trajanjeMinuta;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }
}
