package Controller;

import Data.CourseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class InstruktorController {
    private final CourseData data;

    public InstruktorController(CourseData data) {
        this.data = data;
    }

    @GetMapping("/")
    public String home(){return"redirect:/instuktori";}

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

}
