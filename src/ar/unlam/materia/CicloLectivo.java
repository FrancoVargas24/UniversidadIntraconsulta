package ar.unlam.materia;

public class CicloLectivo {
	
	private String tipo;
	private String turno;
	private static Integer id = 5000;
	
	public CicloLectivo(String tipo, String turno) {
		this.tipo=tipo.toUpperCase();
		this.turno = turno.toUpperCase();
		id++;
	}
	
	
	
	

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}



