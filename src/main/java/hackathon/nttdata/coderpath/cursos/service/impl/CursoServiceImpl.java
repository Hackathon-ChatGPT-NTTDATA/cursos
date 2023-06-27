package hackathon.nttdata.coderpath.cursos.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import hackathon.nttdata.coderpath.cursos.config.ApplicationConfiguration;
import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import hackathon.nttdata.coderpath.cursos.repository.CursoRepository;
import hackathon.nttdata.coderpath.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

	private final CursoRepository cursoRepository;
	private final ApplicationConfiguration configuration;

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

}
