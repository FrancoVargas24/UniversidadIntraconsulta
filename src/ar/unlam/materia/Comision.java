package ar.unlam.materia;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;

public class Comision {

	private Alumno[] alumnos;
	private Materia materia;
	private Nota nota;
	private Profesor[] profesores;
	private static Integer id = 510;
	private CicloLectivo lectivo;
	private Aula aula;
	private static final Integer CANTIDADMAXIMADEALUMNOS = 40;
	private static final Integer CANTIDADMAXIMADEPROFESORES = 20;
	private static final LocalDate FECHA_LIMITE_INSCRIPCION = LocalDate.of(2023, 12, 31);// se puede inscribir hasta fin
																							// de mes

	public Comision(Materia materia, CicloLectivo lectivo, Aula aula) {
		this.materia = materia;
		id++; 
		this.nota = new Nota();
		this.lectivo = lectivo;
		this.alumnos = new Alumno[CANTIDADMAXIMADEALUMNOS];
		this.profesores = new Profesor[CANTIDADMAXIMADEPROFESORES];
		
	}

	public boolean sePuedeInscribir(LocalDate fechaInscripcion) {
        LocalDate fechaActual = LocalDate.now();
        if (fechaInscripcion.isBefore(FECHA_LIMITE_INSCRIPCION) || fechaInscripcion.isEqual(FECHA_LIMITE_INSCRIPCION)) {
          
            return true;
        } 
         return false;
	}

	public void agregarAlumno(Alumno alumno) {
		for (int i = 0; i < alumnos.length; i++) {
			if (alumno.getMateriasAprobadas()[i] != materia) {
				if (materia.getCorrelativa() == null || alumno.buscarMateriaCorrelativa(materia)) {
					for (int j = 0; j < alumnos.length; j++) {
						if (alumnos[i] == null) {
							alumnos[i] = alumno;
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Comision alumnos" + Arrays.toString(alumnos) + ", materia" + materia + ", nota" + nota + ", profesores"
				+ Arrays.toString(profesores) + ", aula" + aula;
	}

	public void agregarProfesor(Profesor profesor) {
		for (int i = 0; i < asignacionProfesores(); i++) {
			if (profesores[i] == null) {
				profesores[i] = profesor;
				break;
			}
		}
	}

	public Alumno[] getAlumno() {
		return alumnos;
	}

	public void setAlumno(Alumno[] alumno) {
		this.alumnos = alumno;
	}

	public Profesor[] getProfesor() {
		return profesores;
	}

	public void setProfesor(Profesor[] profesor) {
		this.profesores = profesor;
	}

	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		Comision.id = id;
	}

	public CicloLectivo getLectivo() {
		return lectivo;
	}

	public void setLectivo(CicloLectivo lectivo) {
		this.lectivo = lectivo;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Integer asignacionProfesores() {
		Integer contador = 0;
		for (int i = 0; i < alumnos.length; i++) {
			if (alumnos[i] != null) {
				contador++;
			}
		}
		contador = contador / 20;
		return contador;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public void calificar(Integer valor) {

		nota.asignarValor(valor);

	}

}
