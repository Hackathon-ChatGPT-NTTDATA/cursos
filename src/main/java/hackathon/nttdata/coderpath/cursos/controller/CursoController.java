package hackathon.nttdata.coderpath.cursos.controller;

import java.util.Date;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import hackathon.nttdata.coderpath.cursos.service.CursoService;
import hackathon.nttdata.coderpath.cursos.service.impl.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
//*@CrossOrigin({"*"})
//*@CrossOrigin({"http://localhost:4200"})
public class CursoController {

	private final CursoService service;

	private final KafkaProducer producer;

	@PostMapping("/producer/{topic}")
	public ResponseEntity<Mono<?>> publishMessage(@PathVariable String topic, @Valid @RequestBody String message) {
		Mono.just(producer).doOnNext(t -> {

			t.publishMessage(topic, message);

		}).onErrorReturn(producer).onErrorResume(e -> Mono.just(producer))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<String> pAsset = Mono.just(message);

		if (pAsset != null) {
			return new ResponseEntity<>(pAsset, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new Cursos()), HttpStatus.I_AM_A_TEAPOT);
	}

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		return ResponseEntity.ok(service.balanceadorTest());
	}

	@GetMapping("/cursos/all")
	public Flux<Cursos> searchAll() {
		Flux<Cursos> per = service.findAlls();
		log.info("CURSOS ASSET registered: " + per);
		return per;
	}

	@GetMapping("/id/{id}")
	public Mono<Cursos> searchById(@PathVariable String id) {
		log.info("Cursos Asset id: " + service.findById(id) + " con codigo: " + id);
		return service.findById(id);
	}

	@PostMapping("/create-cursos")
	public Mono<Cursos> createCursos(@Valid @RequestBody Cursos cursoAsset) {
		log.info("Cursos hackathon NTTTDATA create: " + service.saves(cursoAsset));
		Mono.just(cursoAsset).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(cursoAsset).onErrorResume(e -> Mono.just(cursoAsset))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<Cursos> newPersonalAsset = service.saves(cursoAsset);

		return newPersonalAsset;
	}

	@PutMapping("/update-cursos/{id}")
	public ResponseEntity<Mono<?>> updateCursosAsset(@PathVariable String id, 
			@Valid @RequestBody Cursos cursosAsset) {
		Mono.just(cursosAsset).doOnNext(t -> {
			cursosAsset.setId(id);
			t.setCreateAt(new Date());

		}).onErrorReturn(cursosAsset).onErrorResume(e -> Mono.just(cursosAsset))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<Cursos> pAsset = service.saves(cursosAsset);

		if (pAsset != null) {
			return new ResponseEntity<>(pAsset, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new Cursos()), HttpStatus.I_AM_A_TEAPOT);
	}

	@DeleteMapping("/delete-curso/{id}")
	public ResponseEntity<Mono<Void>> deleteCursosAsset(@PathVariable String id) {
		Cursos cursosAsset = new Cursos();
		cursosAsset.setId(id);
		Mono<Cursos> newPersonalAsset = service.findById(id);
		newPersonalAsset.subscribe();
		Mono<Void> test = service.delete(cursosAsset);
		test.subscribe();
		return ResponseEntity.noContent().build();
	}

	/*
	 * @PutMapping("/editar-con-foto/{id}") public ResponseEntity<?>
	 * editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable
	 * String id,
	 * 
	 * @RequestParam MultipartFile archivo) throws IOException {
	 * 
	 * if (result.hasErrors()) { return this.validar(result); }
	 * 
	 * Mono<Alumno> o = service.findById(id); if (!o.isPresent()) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * Alumno alumnoDb = o.get(); alumnoDb.setNombre(alumno.getNombre());
	 * alumnoDb.setApellido(alumno.getApellido());
	 * alumnoDb.setEmail(alumno.getEmail()); if (!archivo.isEmpty()) {
	 * alumnoDb.setFoto(archivo.getBytes()); }
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.CREATED).body(service.saves(alumnoDb));
	 * 
	 * }
	 */

//	@PostMapping("/crear-con-foto")
	/*
	 * public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult
	 * result,
	 * 
	 * @RequestParam MultipartFile archivo, HttpServletRequest request) throws
	 * IOException { // TODO Auto-generated method stub
	 * 
	 * if (!archivo.isEmpty()) { alumno.setFoto(archivo.getBytes()); }
	 * 
	 * return super.crear(alumno, result); }
	 * 
	 * @PostMapping("/crear-con-fotoruta") public ResponseEntity<?>
	 * crearConFotoRuta(@Valid Alumno alumno, BindingResult result,
	 * 
	 * @RequestParam MultipartFile archivo, HttpServletRequest request) throws
	 * IOException {
	 * 
	 * int totalusuario = service.lastcode() + 1;
	 * 
	 * if (!archivo.isEmpty()) { // usuario.setFoto(archivo.getBytes());
	 * 
	 * String rutax = "/resources/images/usuarios/" + totalusuario;
	 * System.out.println("rutax: " + rutax); String nombreImagen =
	 * Utileria.guardarImagenPlus(archivo, request, rutax);
	 * 
	 * alumno.setRutafoto(nombreImagen);
	 * 
	 * }
	 * 
	 * return super.crear(alumno, result); }
	 * 
	 * 
	 * @PutMapping("/editar-con-fotoruta/{id}") public ResponseEntity<?>
	 * editarConFotoRuta(@Valid Alumno alumno, BindingResult result, @PathVariable
	 * String id,
	 * 
	 * @RequestParam MultipartFile archivo, HttpServletRequest request) throws
	 * IOException {
	 * 
	 * if (result.hasErrors()) { // return this.validar(result); }
	 * 
	 * Mono<Alumno> o = service.findById(id);
	 * 
	 * if (!o.isPresent()) { return ResponseEntity.notFound().build(); }
	 * 
	 * Alumno alumnoDb = o.get();
	 * 
	 * alumnoDb.setNombre(alumno.getNombre());
	 * alumnoDb.setApellido(alumno.getApellido());
	 * alumnoDb.setEmail(alumno.getEmail()); if (!archivo.isEmpty()) {
	 * alumno.setFoto(archivo.getBytes()); String rutax =
	 * "/resources/images/usuarios/" + alumno.getId(); System.out.println("rutax: "
	 * + rutax); String nombreImagen = Utileria.guardarImagenPlus(archivo, request,
	 * rutax); alumno.setRutafoto(nombreImagen);
	 * 
	 * } return
	 * ResponseEntity.status(HttpStatus.CREATED).body(service.saves(alumnoDb)); }
	 * 
	 * 
	 * @GetMapping("/uploads/img/{id}") public ResponseEntity<?>
	 * verFoto(@PathVariable Long id) {
	 * 
	 * Optional<Alumno> o = service.findById(id);
	 * 
	 * if (!o.isPresent() || o.get().getFoto() == null) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * Resource imagen = new ByteArrayResource(o.get().getFoto());
	 * 
	 * return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	 * 
	 * }
	 * 
	 */
}
