package hackathon.nttdata.coderpath.cursos.documents;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Alumnos;
import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Examenes;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cursos")
public class Cursos {

	@Id
	private String id;

	private String nombre;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;


	private List<Alumnos> alumnos;
	
	private List<Examenes> examenes;

}
