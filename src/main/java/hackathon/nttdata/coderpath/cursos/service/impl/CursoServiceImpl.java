package hackathon.nttdata.coderpath.cursos.service.impl;

import com.google.common.collect.Maps;

import hackathon.nttdata.coderpath.cursos.config.ApplicationConfiguration;
import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Alumnos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Examenes;
import hackathon.nttdata.coderpath.cursos.repository.CursoRepository;
import hackathon.nttdata.coderpath.cursos.service.CursoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.*;
import org.springframework.stereotype.Service;

import static org.springframework.web.reactive.function.BodyInserters.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

	private final CursoRepository cursoRepository;

	private final ApplicationConfiguration configuration;

	@Autowired
	private WebClient client;

	final String urlServerAlumno = "http://localhost:8090/api/alumnowebflux";
	private WebClient webClientalumnos = WebClient.create(urlServerAlumno);

	private Mono<Examenes> getExamenesById(String id) {
		Mono<Examenes> examen =

				client.get().uri("/id/{id}", id).retrieve().bodyToMono(Examenes.class);
		return examen;

	}

	public Mono<ServerResponse> getOneExamen(ServerRequest request, String idx) {

		String id = request.pathVariable(idx);

		return this.findExamenesById(id).flatMap(c -> ServerResponse.ok().contentType(APPLICATION_JSON_UTF8).syncBody(c)
				.switchIfEmpty(ServerResponse.notFound().build()));
	}

	private Mono<Alumnos> getAlumnosById(String id) {
		Mono<Alumnos> alumno =

				webClientalumnos.get().uri("/id/{id}", id).retrieve().bodyToMono(Alumnos.class);
		return alumno;

	}

	public Mono<ServerResponse> getOneAlumno(ServerRequest request, String idx) {

		String id = request.pathVariable(idx);

		return this.findAlumnosById(id).flatMap(c -> ServerResponse.ok().contentType(APPLICATION_JSON_UTF8).syncBody(c)
				.switchIfEmpty(ServerResponse.notFound().build()));
	}

	@Override
	public Mono<Cursos> findCursoById(String id) {
		// TODO Auto-generated method stub
		return cursoRepository.findById(id);
	}

	@Override
	public Flux<Cursos> findAllCursos() {
		// TODO Auto-generated method stub
		return cursoRepository.findAll();
	}

	@Override
	public Mono<Cursos> saveCurso(Cursos document) {
		// TODO Auto-generated method stub
		return cursoRepository.save(document);
	}

	@Override
	public Mono<Void> deleteCurso(Cursos document) {
		// TODO Auto-generated method stub
		return cursoRepository.delete(document);
	}

	@Override
	public Map<String, Object> balanceadorTest() {
		// TODO Auto-generated method stub
		Map<String, Object> response = Maps.newHashMap();
		response.put("balanceador", configuration.getBalanceadorTest());
		response.put("cursos_asset", findAllCursos());
		return response;

	}
	// SECCION WEBCLIENT

	@Override
	public Flux<Examenes> findAllExamenes() {
		// TODO Auto-generated method stub
		System.out.println("ruta de Examenes: " + client.toString());
		return client.get().uri("/all").accept(APPLICATION_JSON_UTF8).exchange()
				.flatMapMany(response -> response.bodyToFlux(Examenes.class));
	}

	@Override
	public Mono<Examenes> findExamenesById(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id", id);

		return client.get().uri("/id/{id}", params).accept(APPLICATION_JSON_UTF8).retrieve().bodyToMono(Examenes.class);
		// .exchange()
		// .flatMap(response -> response.bodyToMono(Cursos.class));
	}

	@Override
	public Mono<Void> deleteExamenes(String id) {
		// TODO Auto-generated method stub
		return client.delete().uri("/delete-examen/{id}", Collections.singletonMap("id", id)).exchange().then();
	}

	@Override
	public Map<String, Object> rutaWebClientTest() {
		// TODO Auto-generated method stub
		Map<String, Object> response = Maps.newHashMap();
		response.put("balanceador", configuration.getUrlExamenes());
		response.put("cursos ", findAllCursos());
		return response;
	}

	@Override
	public Mono<Examenes> saveExamenes(Examenes document) {
		// TODO Auto-generated method stub
		return client.post().uri("/create-examen").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8)
				.body(fromObject(document))
				// .syncBody(document)
				.retrieve().bodyToMono(Examenes.class);
	}

	@Override
	public Mono<Examenes> updateExamenes(Examenes document, String id) {
		// TODO Auto-generated method stub
		return client.put().uri("/update-examen/{id}", Collections.singletonMap("id", id)).accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8)
				// .body(fromObject(document))
				.syncBody(document).retrieve().bodyToMono(Examenes.class);
	}

	@Override
	public Mono<Cursos> saveCursoExamenes(Cursos document, String examenId) {
		List<String> examenesIds = Arrays.asList(examenId);

		Flux<Examenes> examenes = Flux.fromIterable(examenesIds)

				.flatMap(x -> {
					System.out.println("Cursos seleccionados:  " + getExamenesById(examenId));
					return getExamenesById(examenId);
				});

		List<Examenes> examenex = new ArrayList<>();

		examenes.collectList().subscribe(examenex::addAll);
		document.setExamenes(examenex);
		// TODO Auto-generated method stub
		return cursoRepository.save(document);
	}

	@Override
	public Flux<Alumnos> findAllAlumnos() {
		// TODO Auto-generated method stub
		System.out.println("ruta de Alumnos: " + webClientalumnos.toString());
		return webClientalumnos.get().uri("/all").accept(APPLICATION_JSON_UTF8).exchange()
				.flatMapMany(response -> response.bodyToFlux(Alumnos.class));
	}

	@Override
	public Mono<Alumnos> findAlumnosById(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id", id);

		return webClientalumnos.get().uri("/id/{id}", params).accept(APPLICATION_JSON_UTF8).retrieve()
				.bodyToMono(Alumnos.class);
		// .exchange()
		// .flatMap(response -> response.bodyToMono(Cursos.class));
	}

	@Override
	public Mono<Alumnos> saveAlumnos(Cursos document) {
		// TODO Auto-generated method stub
		return webClientalumnos.post().uri("/create-alumno").accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8).body(fromObject(document))
				// .syncBody(document)
				.retrieve().bodyToMono(Alumnos.class);
	}

	@Override
	public Mono<Alumnos> updateAlumnos(Cursos document, String id) {
		// TODO Auto-generated method stub
		return webClientalumnos.put().uri("/update-alumno/{id}", Collections.singletonMap("id", id))
				.accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8)
				// .body(fromObject(document))
				.syncBody(document).retrieve().bodyToMono(Alumnos.class);
	}

	@Override
	public Mono<Void> deleteAlumnos(String id) {
		// TODO Auto-generated method stub
				return webClientalumnos.delete().uri("/delete-alumno/{id}", Collections.singletonMap("id", id)).exchange().then();
	}

	@Override
	public Mono<Cursos> saveCursoAlumnos(Cursos document, String alumnoId) {
		List<String> alumnosIds = Arrays.asList(alumnoId);

		Flux<Alumnos> alumnos = Flux.fromIterable(alumnosIds)

				.flatMap(x -> {
					System.out.println("Alumnos seleccionados:  " + getAlumnosById(alumnoId));
					return getAlumnosById(alumnoId);
				});

		List<Alumnos> alumnox = new ArrayList<>();

		alumnos.collectList().subscribe(alumnox::addAll);
		document.setAlumnos(alumnox);
		// TODO Auto-generated method stub
		return cursoRepository.save(document);
	}
}
