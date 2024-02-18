package hackathon.nttdata.coderpath.cursos.controller.validation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import hackathon.nttdata.coderpath.cursos.codeexception.CustomException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;



@Component
@Slf4j
@RequiredArgsConstructor
public class ObjectValidator {

	private final Validator validator;

	@SneakyThrows
	public <T> T validate(T object) {
		Set<ConstraintViolation<T>> errors = validator.validate(object);
		if (errors.isEmpty())
			return object;
		else {
			String message = errors.stream().map(err -> err.getMessage()).collect(Collectors.joining(", "));
			throw new CustomException(HttpStatus.BAD_REQUEST, message);
		}
	}

}
