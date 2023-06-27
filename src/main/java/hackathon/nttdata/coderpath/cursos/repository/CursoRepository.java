package hackathon.nttdata.coderpath.cursos.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import hackathon.nttdata.coderpath.cursos.documents.Cursos;

@Repository
public interface CursoRepository extends ReactiveMongoRepository<Cursos, String> {

}
