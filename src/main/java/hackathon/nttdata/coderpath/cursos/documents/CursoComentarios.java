package hackathon.nttdata.coderpath.cursos.documents;



public class CursoComentarios {

	private Cursos cursos;
	private Comentarios comentarios;

	public CursoComentarios(Cursos cursos, Comentarios comentarios) {

		this.cursos = cursos;
		this.comentarios = comentarios;

	}
	@Override
	public String toString() {
		return "CursoComentarios[cursos=" + cursos + ", comentarios=" + comentarios + "]";
	}
}
