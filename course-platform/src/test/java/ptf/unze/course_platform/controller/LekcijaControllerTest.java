package ptf.unze.course_platform.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ptf.unze.course_platform.model.Lekcija;
import ptf.unze.course_platform.service.CourseService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LekcijaController.class)
class LekcijaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService service;

    @Test
    void lekcijePageRendersLessons() throws Exception {
        when(service.sveLekcije()).thenReturn(List.of(
                new Lekcija(1L, "HTML osnove", "Uvod u HTML", 45, null)));

        mockMvc.perform(get("/lekcija"))
                .andExpect(status().isOk())
                .andExpect(view().name("lekcije"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("HTML osnove")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Uvod u HTML")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("45")));
    }

    @Test
    void lekcijePageRendersWhenThereAreNoLessons() throws Exception {
        when(service.sveLekcije()).thenReturn(List.of());

        mockMvc.perform(get("/lekcija"))
                .andExpect(status().isOk())
                .andExpect(view().name("lekcije"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Trenutno nema lekcija.")));
    }
}