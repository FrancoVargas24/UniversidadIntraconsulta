package ar.unlam.materia;

public class Aula {

	private static Integer id = 500;
	private String departamento;
	private Integer cantidad;
	private boolean estado;
	
	
	public Aula(String departamento, Integer cantidad) {
		this.cantidad = cantidad;
		id++;
		this.departamento = departamento;
		this.estado = false;
		
	}


	public Aula buscarAulaVacia(Aula[] aulas) {
		Aula aulaAuxiliar = null;
		for (int i = 0; i < aulas.length; i++) {
			if (aulas[i]!= null && aulas[i].getEstado() == false) {
				aulas[i] = aulaAuxiliar;
				return aulaAuxiliar;
			}
		}
		return aulaAuxiliar;
	}
	
	
	
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public static Integer getId() {
		return id;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
