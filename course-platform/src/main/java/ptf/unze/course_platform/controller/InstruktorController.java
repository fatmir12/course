package ptf.unze.course_platform.controller;

import ptf.unze.course_platform.data.CourseData;
import ptf.unze.course_platform.model.Instruktor;
import ptf.unze.course_platform.model.Kurs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
@Controller
public class InstruktorController {
    private final CourseData data;

    public InstruktorController(CourseData data) {
        this.data = data;
    }

    @GetMapping("/")
    public String home(){return"redirect:/instruktori";}

    @GetMapping("/instruktori")
    public String instruktori(Model model){
        model.addAttribute("instruktori",data.nadjiSveInstruktore());
        return "instruktori";
    }

    @GetMapping("/kursevi")
    public String kursevi(Model model){
        model.addAttribute("kursevi",data.nadjiSveKurs());
        return "kursevi";
    }

    @GetMapping("/akcija")
    public String akcija(Model model, @RequestParam(value = "status", required = false) String status){
        model.addAttribute("instruktori",data.nadjiSveInstruktore());
        model.addAttribute("kursevi",data.nadjiSveKurs());
        model.addAttribute("status",status);
        return "akcija";
    }

    @PostMapping("/akcija")
    public String dodijeliKurs(@RequestParam("instruktorId") Long instruktorId,
                               @RequestParam("kursId") Long kursId){
        Instruktor instruktor = data.nadjiInstruktora(instruktorId);
        Kurs kurs = data.nadjiKurs(kursId);

        if(instruktor == null || kurs == null){
            return "redirect:/akcija?status=invalid";
        }
        if(instruktor.getKursevi().stream().anyMatch(postojeci -> postojeci.getId() == kurs.getId())){
            return "redirect:/akcija?status=duplicate";
        }
        if(!instruktor.mozeDodatiKurs()){
            return "redirect:/akcija?status=limit";
        }

        instruktor.dodajKurs(kurs);
        return "redirect:/akcija?status=success";
    }

}
