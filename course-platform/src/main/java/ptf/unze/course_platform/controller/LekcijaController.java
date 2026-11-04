package ptf.unze.course_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ptf.unze.course_platform.service.CourseService;

@Controller
public class LekcijaController {
    private final CourseService service;
    public LekcijaController(CourseService service){
        this.service=service;
    }
    @GetMapping("/lekcija")
    public String lekcije(Model model){
        model.addAttribute("lekcije",service.sveLekcije());
        return "lekcije";
    }
    @GetMapping("/dodaj-lekciju")
    public String forma(Model model){
        model.addAttribute("kursevi",service.sviKursevi());
        return "dodaj-lekciju";
    }

    @PostMapping("/dodaj-lekciju")
    public String dodajLekciju(
            @RequestParam String naslov,
            @RequestParam String sadrzaj,
            @RequestParam int trajanjeDana,
            @RequestParam Long kursId
    ){
        service.dodajLekciju(naslov,sadrzaj,trajanjeDana,kursId);
        return "redirect:/lekcija";
    }
}
