package hackathon.nttdata.coderpath.cursos.service.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.collect.Maps;

import hackathon.nttdata.coderpath.cursos.config.ApplicationConfiguration;
import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Examenes;
import hackathon.nttdata.coderpath.cursos.repository.CursoRepository;
import hackathon.nttdata.coderpath.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



import java.util.Collections;
import java.util.HashMap;

import static org.springframework.http.MediaType.*;




import static org.springframework.web.reactive.function.BodyInserters.*;


@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

	private final CursoRepository cursoRepository;
	private final ApplicationConfiguration configuration;
	
	@Autowired
	private WebClient client;

	@Override
	public Mono<Cursos> findById(String id) {
		// TODO Auto-generated method stub
		return cursoRepository.findById(id);
	}

	@Override
	public Flux<Cursos> findAlls() {
		// TODO Auto-generated method stub
		return cursoRepository.findAll();
	}

	@Override
	public Mono<Cursos> saves(Cursos document) {
		// TODO Auto-generated method stub
		return cursoRepository.save(document);
	}

	@Override
	public Mono<Void> delete(Cursos document) {
		// TODO Auto-generated method stub
		return cursoRepository.delete(document);
	}

	@Override
	public Map<String, Object> balanceadorTest() {
		// TODO Auto-generated method stub
		Map<String, Object> response = Maps.newHashMap();
		response.put("balanceador", configuration.getBalanceadorTest());
		response.put("cursos_asset", findAlls());
		return response;
		
	}
	// SECCION WEBCLIENT

	@Override
	public Flux<Examenes> findAll() {
		// TODO Auto-generated method stub
		System.out.println("ruta de cursos: " + client.toString());
		return client
				.get()
				.uri("/all")
				.accept(APPLICATION_JSON_UTF8)
				.exchange()
				.flatMapMany(response -> response.bodyToFlux(Examenes.class));
	}
	

	@Override
	public Mono<Examenes> findExamenesById(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id", id);

		return client
				.get()
				.uri("/{id}", params)
				.accept(APPLICATION_JSON_UTF8)
				.retrieve()
				.bodyToMono(Examenes.class);
				//.exchange()
				//.flatMap(response -> response.bodyToMono(Cursos.class));
	}

	@Override
	public Mono<Examenes> save(Examenes document) {
		// TODO Auto-generated method stub
		return client
				.post()
				.uri("/create-examen")
				.accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8)
				.body(fromObject(document))
				//.syncBody(document)
				.retrieve()
				.bodyToMono(Examenes.class);
		
	}

	@Override
	public Mono<Examenes> update(Examenes document, String id) {
		// TODO Auto-generated method stub
		return client
				.put()
				.uri("/update-cursos/{id}", Collections.singletonMap("id", id))
				.accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8)
				//.body(fromObject(document))
				.syncBody(document)
				.retrieve()
				.bodyToMono(Examenes.class);
	}

	@Override
	public Mono<Void> delete(String id) {
		// TODO Auto-generated method stub
		return client
				.delete()
				.uri("/delete-examenes-asset/{id}", Collections.singletonMap("id", id))
				.exchange()
				.then();
	}

	@Override
	public Map<String, Object> rutaWebClientTest() {
		// TODO Auto-generated method stub
		Map<String, Object> response = Maps.newHashMap();
		response.put("balanceador", configuration.getUrlExamenes());
		response.put("personal_asset", findAll());
		return response;
}
	}
