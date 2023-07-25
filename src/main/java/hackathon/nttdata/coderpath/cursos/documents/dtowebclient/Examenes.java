package hackathon.nttdata.coderpath.cursos.documents.dtowebclient;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Examenes {
		
		private String id;
		private String nombre;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createAt;
	
	
}
