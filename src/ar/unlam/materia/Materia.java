package ar.unlam.materia;

public class Materia {

	private String nombre;
	private Integer codigoMateria;
	private Materia correlativa;
	private Nota primerParcial;
	private Nota segundoParcial;
	private Nota finalAnual;
	private Nota recuperatorio;

	public Materia(String nombre, Integer codigoMateria) {
		this.nombre = nombre;
		this.codigoMateria = codigoMateria;
		this.correlativa = null;
		this.primerParcial= null;
		this.segundoParcial= null;
		this.finalAnual= null;
		this.recuperatorio= null;
		
	}

	@Override
	public String toString() {
		String completo = "Materia" + nombre + ", codigo materia" + codigoMateria + ", correlativas=" + correlativa;
		
		if (segundoParcial !=null || finalAnual != null) {
			completo += ", primerParcial=" + primerParcial + ", segundoParcial=" + segundoParcial + ", final"
					+ finalAnual + ", recuperatorio" + recuperatorio;
		}
		return completo;
		
	}

	public Materia getCorrelativa() {
		return correlativa;
	}

	public void setCorrelativa(Materia correlativas) {
		this.correlativa = correlativas;
	}

	public Nota getPrimerParcial() {
		return primerParcial;
	}

	public void setPrimerParcial(Nota primerParcial) {
		this.primerParcial = primerParcial;
	}

	public Nota getSegundoParcial() {
		return segundoParcial;
	}

	public void setSegundoParcial(Nota segundoParcial) {
		this.segundoParcial = segundoParcial;
	}

	public Nota getFinalAnual() {
		return finalAnual;
	}

	public void setFinalAnual(Nota finalAnual) {
		this.finalAnual = finalAnual;
	}

	public Nota getRecuperatorio() {
		return recuperatorio;
	}

	public void setRecuperatorio(Nota recuperatorio) {
		this.recuperatorio = recuperatorio;
	}

	public Integer asignarCorrelativa(Materia materia) {
		correlativa = materia;
		return codigoMateria;
	}
/*
	public Integer getCorrelativa() {
		return correlativa;
	}
*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigoMateria() {
		return codigoMateria;
	}

	public void setCodigoMateria(Integer codigoMateria) {
		this.codigoMateria = codigoMateria;
	}

	public void EliminarCorrelativa(Materia materiaCorrelativa) {
		Materia nula = null;
		if (getCorrelativa() == materiaCorrelativa) {
			if (getCorrelativa() != null) {
				asignarCorrelativa(nula);
			}
		}
	}

/*
	public boolean tieneCorrelativaa(Materia materiaCorrelativa) {
	    // Verifica si esta materia tiene la materiaCorrelativa como correlativa
	    return this.correlativa != null && this.correlativa.equals(materiaCorrelativa);
	}
	*/
	public boolean tieneCorrelativa(Materia materiaCorrelativa) {
		if (getCorrelativa() == null ||getCorrelativa().getCodigoMateria() == materiaCorrelativa.getCodigoMateria()) {
			return true;
		}
		return false;

	}

	
}
