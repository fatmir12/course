package ptf.unze.course_platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ptf.unze.course_platform.data.CourseData;
import ptf.unze.course_platform.model.Instruktor;
import ptf.unze.course_platform.model.Kurs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoursePlatformApplicationTests {

	@Autowired
	private CourseData data;

	@Value("${local.server.port}")
	private int port;

	private HttpResponse<String> get(String path) throws Exception {
		try (HttpClient client = HttpClient.newHttpClient()) {
			return client.send(HttpRequest.newBuilder(
					URI.create("http://localhost:" + port + path)).GET().build(),
					HttpResponse.BodyHandlers.ofString());
		}
	}

	private HttpResponse<String> postAssignment(long instruktorId, long kursId) throws Exception {
		try (HttpClient client = HttpClient.newHttpClient()) {
			String body = "instruktorId=" + instruktorId + "&kursId=" + kursId;
			return client.send(HttpRequest.newBuilder(
					URI.create("http://localhost:" + port + "/akcija"))
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build(),
					HttpResponse.BodyHandlers.ofString());
		}
	}

	@Test
	void homeRedirectsToInstruktori() throws Exception {
		HttpResponse<String> response = get("/");
		assertEquals(302, response.statusCode());
		assertTrue(response.headers().firstValue("Location").orElseThrow().endsWith("/instruktori"));
	}

	@Test
	void instruktoriPageRenders() throws Exception {
		HttpResponse<String> response = get("/instruktori");
		assertEquals(200, response.statusCode());
		assertTrue(response.body().contains("Nevzudin"));
	}

	@Test
	void kurseviPageRenders() throws Exception {
		HttpResponse<String> response = get("/kursevi");
		assertEquals(200, response.statusCode());
		assertTrue(response.body().contains("Engleski"));
	}

	@Test
	void akcijaPageRendersInstruktoreAndKurseve() throws Exception {
		HttpResponse<String> response = get("/akcija");
		assertEquals(200, response.statusCode());
		assertTrue(response.body().contains("Nevzudin"));
		assertTrue(response.body().contains("Engleski"));
		assertTrue(response.body().contains("instruktorId"));
		assertTrue(response.body().contains("kursId"));
	}

	@Test
	void akcijaDodajeKursIspisujeDuplikat() throws Exception {
		Instruktor instruktor = data.nadjiSveInstruktore().get(0);
		Kurs kurs = data.nadjiSveKurs().get(0);

		HttpResponse<String> response = postAssignment(instruktor.getId(), kurs.getId());
		assertEquals(302, response.statusCode());
		assertTrue(response.headers().firstValue("Location").orElseThrow().endsWith("status=success"));

		response = postAssignment(instruktor.getId(), kurs.getId());
		assertEquals(302, response.statusCode());
		assertTrue(response.headers().firstValue("Location").orElseThrow().endsWith("status=duplicate"));
		assertTrue(get("/instruktori").body().contains(kurs.getNaziv()));
	}

	@Test
	void akcijaOdbijaNepostojeceIdentifikatore() throws Exception {
		HttpResponse<String> response = postAssignment(999999, 999999);
		assertEquals(302, response.statusCode());
		assertTrue(response.headers().firstValue("Location").orElseThrow().endsWith("status=invalid"));
	}

	@Test
	void akcijaOdbijaKursPrekoOgranicenja() throws Exception {
		Instruktor instruktor = data.nadjiSveInstruktore().get(1);
		List<Kurs> kursevi = data.nadjiSveKurs();
		while (kursevi.size() < 6) {
			int broj = kursevi.size() + 1;
			data.saveKurs(new Kurs(0, "Test kurs " + broj, "A1", broj, broj));
			kursevi = data.nadjiSveKurs();
		}

		for (int i = 0; i < 5; i++) {
			HttpResponse<String> response = postAssignment(instruktor.getId(), kursevi.get(i).getId());
			assertEquals(302, response.statusCode());
			assertTrue(response.headers().firstValue("Location").orElseThrow().endsWith("status=success"));
		}

		HttpResponse<String> response = postAssignment(instruktor.getId(), kursevi.get(5).getId());
		assertEquals(302, response.statusCode());
		assertTrue(response.headers().firstValue("Location").orElseThrow().endsWith("status=limit"));
	}

}
