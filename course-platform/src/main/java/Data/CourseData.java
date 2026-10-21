package Data;

import Model.Instruktor;
import Model.Kurs;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Component
public class CourseData {
    private final Map<Long, Instruktor> instrukori= new LinkedHashMap<>();
    private final Map<Long, Kurs> kursevi= new LinkedHashMap<>();
    private long instSeq=1;
    private long kursSeq=1;

    public CourseData() {
        saveInstruktor(new Instruktor(0,"Nevzudin","Buzađija"));
        saveInstruktor(new Instruktor(0,"Edin","Tabak"));

        saveKurs(new Kurs(0,"Engleski","B2",90,200));
        saveKurs(new Kurs(0,"Njemacki","C1",120,300));
        saveKurs(new Kurs(0,"Franckuski","A2",30,150));
        saveKurs(new Kurs(0,"Kineski","C1",180,300));
    }

    public List<Instruktor> nadjiSveInstruktore(){
        return new ArrayList<>(instrukori.values());
    }
    public List<Kurs> nadjiSveKurs(){
        return new ArrayList<>(kursevi.values());
    }

    public Instruktor nadjiInstruktora(Long id){
        return instrukori.get(id);
    }
    public Kurs nadjiKurs(Long id){
        return kursevi.get(id);
    }

    public Instruktor saveInstruktor(Instruktor i){
        if(i.getId()==0) i.setId(++instSeq);
        instrukori.put(i.getId(),i);
        return i;
    }

    public Kurs saveKurs(Kurs k){
        if(k.getId()==0) k.setId(++kursSeq);
        kursevi.put(k.getId(),k);
        return k;
    }

}
