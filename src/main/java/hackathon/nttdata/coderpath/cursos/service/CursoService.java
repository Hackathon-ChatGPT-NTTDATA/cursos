package hackathon.nttdata.coderpath.cursos.service;

import java.util.Map;


import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Alumnos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Examenes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CursoService {

	
	Mono<Cursos> findCursoById(String id);

	Flux<Cursos> findAllCursos();

	Mono<Cursos> saveCurso(Cursos document);

	Mono<Void> deleteCurso(Cursos document);

	Map<String, Object> balanceadorTest();
	/*
	 * seccion WEBCLIENT EXAMENES
	 */

	Flux<Examenes> findAllExamenes();

	Mono<Examenes> findExamenesById(String id);

	Mono<Examenes> saveExamenes(Examenes document);

	Mono<Examenes> updateExamenes(Examenes document, String id);

	Mono<Void> deleteExamenes(String id);
	
	Map<String, Object> rutaWebClientTest();
	
	//con Examen y Curso
		Mono<Cursos> saveCursoExamenes(Cursos document, String examenId);
	
	/*
	 * seccion WEBCLIENT Alumnos
	 */

	Flux<Alumnos> findAllAlumnos();

	Mono<Alumnos> findAlumnosById(String id);

	Mono<Alumnos> saveAlumnos(Cursos document);

	Mono<Alumnos> updateAlumnos(Cursos document, String id);

	Mono<Void> deleteAlumnos(String id);
	
	
	//con Alumno y Curso
	Mono<Cursos> saveCursoAlumnos(Cursos document, String alumnoId);


}
