package ptf.unze.course_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptf.unze.course_platform.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseRestController {
    private final CourseService service;

    public CourseRestController(CourseService service){
        this.service=service;
    }

    public record InstruktorDTO(
            Long id,
            String ime,
            String prezime
    ){}
    public record LekcijaDTO(
            Long id,
            String naslov,
            String sadrzaj,
            int trajanjeMinuta
    ){}
    public record KursDTO(
            Long id,
            String naziv,
            String nivoObuke,
            int trajanjeMinuta,
            int cijenaKursa
    ){}

    @GetMapping("/kursevi")
    public List<KursDTO> kursevi(){
        return service.sviKursevi().stream().map(k->new KursDTO(
                k.getId(),
                k.getNaziv(),
                k.getNivoObuke(),
                k.getTrajanjeDana(),
                k.getCijenaKursa()
        )).toList();
    }
    @GetMapping("/lekcija")
    public List<LekcijaDTO> lekcije(){
        return service.sveLekcije().stream().map(l->new LekcijaDTO(
                l.getId(),
                l.getNaslov(),
                l.getSadrzaj(),
                l.getTrajanjeMinuta()
        )).toList();
    }

}
