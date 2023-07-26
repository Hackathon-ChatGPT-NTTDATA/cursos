package hackathon.nttdata.coderpath.cursos.service;

import java.util.Map;

import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Examenes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CursoService {

	
	Mono<Cursos> findById(String id);

	Flux<Cursos> findAlls();

	Mono<Cursos> saves(Cursos document);

	Mono<Void> delete(Cursos document);

	Map<String, Object> balanceadorTest();
	/*
	 * seccion WEBCLIENT
	 */

	Flux<Examenes> findAll();

	Mono<Examenes> findExamenesById(String id);

	Mono<Examenes> save(Examenes document);

	Mono<Examenes> update(Examenes document, String id);

	Mono<Void> delete(String id);
	Map<String, Object> rutaWebClientTest();


}
