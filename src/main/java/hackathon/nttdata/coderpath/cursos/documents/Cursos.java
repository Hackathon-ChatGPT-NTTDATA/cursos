package hackathon.nttdata.coderpath.cursos.documents;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;



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



}
