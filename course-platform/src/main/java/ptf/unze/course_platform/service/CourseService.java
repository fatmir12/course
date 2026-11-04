package ptf.unze.course_platform.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptf.unze.course_platform.model.Instruktor;
import ptf.unze.course_platform.model.Kurs;
import ptf.unze.course_platform.model.Lekcija;
import ptf.unze.course_platform.repository.InstruktorRepository;
import ptf.unze.course_platform.repository.KursRepository;
import ptf.unze.course_platform.repository.LekcijaRepository;

import java.util.List;

@Service
public class CourseService {
    private final InstruktorRepository instruktorRepository;
    private final KursRepository kursRepository;
    private final LekcijaRepository lekcijaRepository;

    public CourseService(InstruktorRepository instruktorRepository, KursRepository kursRepository, LekcijaRepository lekcijaRepository) {
        this.instruktorRepository = instruktorRepository;
        this.kursRepository = kursRepository;
        this.lekcijaRepository = lekcijaRepository;
    }

    @Transactional(readOnly = true)
    public List<Kurs> sviKursevi() {
        return kursRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Lekcija> sveLekcije() {
        return lekcijaRepository.findAll();
    }
    @Transactional
    public String dodijeliKurs(Long instruktorId,Long kursId){
        Instruktor instruktor = instruktorRepository.findById(instruktorId).orElse(null);
        Kurs kurs=kursRepository.findById(kursId).orElse(null);
        if(instruktor==null || kurs==null){
            return "invalid";
        }

        if(kurs.getInstruktor()!=null){
            if(kurs.getInstruktor().getId() != (instruktorId)){
                return "Već je dodijelejn";
            }
            return "Dodijeljen";
        }
        instruktor.dodajKurs(kurs);
        kursRepository.save(kurs);
        return "Dodijeljen";
    }
    @Transactional
    public Lekcija dodajLekciju(String naslov, String sadrzaj, int trajanjeDana,Long kursId){
        Kurs kurs=kursRepository.findById(kursId).orElse(null);
        if(kurs==null){
            return null;
        }
        Lekcija lekcija=new Lekcija();
        lekcija.setNaslov(naslov);
        lekcija.setSadrzaj(sadrzaj);
        lekcija.setTrajanjeMinuta(trajanjeDana);
        kurs.dodajLekciju(lekcija);
        lekcijaRepository.save(lekcija);
        return lekcija;
    }
}
