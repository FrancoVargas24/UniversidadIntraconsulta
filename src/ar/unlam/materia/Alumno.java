package ar.unlam.materia;

import java.time.LocalDate;
import java.util.Iterator;

public class Alumno {

	private String nombre;
	private String apellido;
	private Integer dni;
	private static Integer id = 1000;
	private LocalDate fechaIngreso;
	private String fechaNacimiento;
	private Materia[] materiasAprobadas;
	private Materia[] materias;
	private static final Integer MAXIMAMATERIASPORALUMNO = 500;


	public Alumno(Integer dni, String apellido, String nombre, String fechaNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = LocalDate.now();
		this.materiasAprobadas = new Materia[MAXIMAMATERIASPORALUMNO];
		this.materias = new Materia[MAXIMAMATERIASPORALUMNO];
		id++;
	}
	

	public boolean buscarMateriaCorrelativa(Materia materia) {
	    for (int i = 0; i < materias.length; i++) {
	        if (materias[i] != null && materias[i].tieneCorrelativa(materia)) {
	            return true;
	        }
	    }
	    return false;
	}

	
	
	
	public Materia[] getMaterias() {
		return materias;
	}
	
	public void setMaterias(Materia materia) {
		for (int i = 0; i < materias.length; i++) {
			if (materias[i] == null) {
				materias[i] = materia;
				break;
			}
		}
	}
	
	public String toStringAprobadas() {
		return "Alumno nombre" + nombre + ", apellido" + apellido + ", dni" + dni + "\n" + materiasAprobadas.toString();
	}

	public String toStringAnotadas() {
		return "Alumno nombre" + nombre + ", apellido" + apellido + ", dni" + dni + "\n" + materias.toString();
	}
	
	@Override
	public String toString() {
		return "Alumno nombre" + nombre + ", apellido" + apellido + ", dni" + dni;
	}
	
	public Materia[] getMateriasAprobadas() {
		return materiasAprobadas;
	}
	
	public void setMateriasAprobadas(Materia materia) {
		for (int i = 0; i < materiasAprobadas.length; i++) {
			if (materiasAprobadas[i] == null) {
				materiasAprobadas[i] = materia;
				break;
			}
		}
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Integer getid() {
		return this.id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Materia buscarMateria(Integer codigoMateria) {
		Materia materiaAuxiliar = null;
		for (int i = 0; i < materias.length; i++) {
			if (materias[i].getCodigoMateria() == codigoMateria) {
				materiaAuxiliar = materias[i];
				return materiaAuxiliar;
			}
		}
		return materiaAuxiliar;
	}

	public Nota promediarFinal(Nota nota, Nota nota2){
		Nota notaFinal = new Nota();
		
		if(nota.getValor() >= 4 && nota2.getValor() >= 4) {
			notaFinal.asignarValor((nota.getValor()+nota2.getValor())/2);
			return notaFinal;
		}
		return notaFinal;
	}

	public boolean puedeRecuperar(Materia materia){	
		if(materia.getPrimerParcial().getValor()<4 && materia.getSegundoParcial().getValor() >= 4) {
			return true;
		}if(materia.getSegundoParcial().getValor()<4 && materia.getPrimerParcial().getValor() >= 4) {
			return true;		
		}
		if(materia.getSegundoParcial().getValor()>=4 && materia.getPrimerParcial().getValor() >= 4) {
			return true;		
		}
		return false;
	}

	
}
