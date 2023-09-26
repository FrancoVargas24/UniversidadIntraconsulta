package ar.unlam.materia;

public class Universidad {

	private String nombreUniversidad;
	private Alumno[] alumnos;
	private Materia[] materias;
	private Profesor[] profesores;
	private Comision[] comisiones;
	private Aula[] aulas;
	private static final Integer CANTIDADMAXIMADEALUMNOS = 100;
	private static final Integer CANTIDADMAXIMADEMATERIAS = 20;
	private static final Integer CANTIDADMAXIMADEPROFESORES = 10;
	private static final Integer CANTIDADMAXIMADEAULAS = 20;
	private static final Integer CANTIDADMAXIMADECOMISIONES = 30;

	private static Integer contadorAlumnos = 0;
	private static Integer contadorProfesores = 0;
	private static Integer contadorMaterias = 0;
	private static Integer contadorAulas = 0;
	private static Integer contadorComisiones = 0;

	public Universidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
		alumnos = new Alumno[CANTIDADMAXIMADEALUMNOS];
		materias = new Materia[CANTIDADMAXIMADEMATERIAS];
		profesores = new Profesor[CANTIDADMAXIMADEPROFESORES];
		aulas = new Aula[CANTIDADMAXIMADEAULAS];
		comisiones = new Comision[CANTIDADMAXIMADECOMISIONES];
	}

	public void registrarComision(Comision comision) {
		for (int i = 0; i < comisiones.length; i++) {
			if (comisiones[i] == null && verficarCodigoComision(comision)) {
				comisiones[i] = comision;
				break;
			}
		}
	}

	public void registrarAula(Aula aula) {
		for (int i = 0; i < aulas.length; i++) {
			if (aulas[i] == null && verficarAulaExistente(aula)) {
				aulas[i] = aula;
				break;
			}
		}
	}

	public Comision[] getComisiones() {
		return comisiones;
	}

	public void setComisiones(Comision[] comisiones) {
		this.comisiones = comisiones;
	}

	public boolean verficarAulaExistente(Aula aula) {
		for (int i = 0; i < aulas.length; i++) {
			if (aulas[i] != null) {
				if (aulas[i].getId() != aula.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	public void asignarNota(Integer codigoMateria, Integer dni, Integer nota) {
		Nota notaAuxiliar = new Nota();
		notaAuxiliar.asignarValor(nota);
		if (buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getPrimerParcial() == null) {
			buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).setPrimerParcial(notaAuxiliar);
		}
		if (buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getSegundoParcial() == null) {
			buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).setSegundoParcial(notaAuxiliar);
			buscarAlumnoPorDni(dni).buscarMateria(codigoMateria)
					.setFinalAnual(buscarAlumnoPorDni(dni).promediarFinal(
							buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getSegundoParcial(),
							buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getPrimerParcial()));
		}
	}

	public boolean puedeRecuperar(Integer dni, Integer codigoMateria) {

		if (buscarAlumnoPorDni(dni).puedeRecuperar(buscarAlumnoPorDni(dni).buscarMateria(codigoMateria))) {
			return true;
		}
		return false;
	}

	public void asignarNotaRecuperatorio(Integer codigoMateria, Integer dni, Integer nota, Integer parcial) {
		Nota notaAuxiliar = new Nota();
		notaAuxiliar.asignarValor(nota);
		if (parcial == 1) {
			if (buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getRecuperatorio() == null) {
				buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).setRecuperatorio(notaAuxiliar);
				buscarAlumnoPorDni(dni).buscarMateria(codigoMateria)
						.setFinalAnual(buscarAlumnoPorDni(dni).promediarFinal(
								buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getSegundoParcial(),
								buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getRecuperatorio()));
			}
			if (parcial == 2) {
				if (buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getRecuperatorio() == null
						&& buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getSegundoParcial().getValor() < 7) {
					buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).setRecuperatorio(notaAuxiliar);
					buscarAlumnoPorDni(dni).buscarMateria(codigoMateria)
							.setFinalAnual(buscarAlumnoPorDni(dni).promediarFinal(
									buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getPrimerParcial(),
									buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getRecuperatorio()));
				}
			}
		}

		if (buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getFinalAnual() == null) {
			buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).setFinalAnual(notaAuxiliar);
		}
	}

	public boolean asignarNotaFinal(Integer codigoMateria, Integer dni, Integer nota) {
		Nota notaAuxiliar = new Nota();
		notaAuxiliar.asignarValor(nota);
		if (buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getPrimerParcial().getValor() >= 4
				&& buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).getSegundoParcial().getValor() >= 4) {
			buscarAlumnoPorDni(dni).buscarMateria(codigoMateria).setFinalAnual(notaAuxiliar);
			return true;
		}
		return false;
	}

	public void inscribirAUnAlumnoAUnaComision(Alumno alumno, Comision comision) {
		comision.agregarAlumno(alumno);

	}

	public void asignarProfesoresAUnaComision(Profesor profesor, Comision comision) {
		comision.agregarProfesor(profesor);
	}

	public Materia[] buscarMateriasAprobadasConDni(Integer dni, Universidad universidad) {
		return universidad.buscarAlumnoPorDni(dni).getMateriasAprobadas();
	}

	public Materia[] listaDeMateriasConDniPorCursar(Integer dni, Universidad universidad) {
		return universidad.buscarAlumnoPorDni(dni).getMaterias();
	}

	public void agregarProfesorPorDni(Integer idComision, Integer dni) {
		buscarComisionPorId(idComision).agregarProfesor(buscarProfesorPorDni(dni));
	}

	public Comision buscarComisionPorId(Integer idComision) {
		Comision comisionAuxiliar = null;
		for (int i = 0; i < comisiones.length; i++) {
			if (comisiones[i].getId() == idComision) {
				comisionAuxiliar = comisiones[i];
			}
		}
		return comisionAuxiliar;
	}

	public Materia AsignarMateriaCorrelativa(Materia materia, Materia materiaCorrelativa) {
		materia.asignarCorrelativa(materiaCorrelativa);
		return materia;
	}

	public Materia EliminarMateriaCorrelativa(Materia materia, Materia materiaCorrelativa) {
		materia.EliminarCorrelativa(materiaCorrelativa);
		return materia;
	}

	public void inscribirAUnAlumnoALaUniversidad(Alumno alumno) {
		for (int i = 0; i < alumnos.length; i++) {
			if (alumnos[i] == null) {
				if (!verificarDni(alumno.getDni())) {
					alumnos[i] = alumno;
					contadorAlumnos++;
					break;
				}
			}
		}
	}

	public boolean verificarDni(Integer dni) {
		for (int i = 0; i < contadorAlumnos; i++) {
			if (alumnos[i] != null) {
				if (alumnos[i].getDni() == dni) {
					return true;
				}
			}

		}
		return false;
	}

	public void inscribirAUnProfesorALaUniversidad(Profesor profesor) {
		for (int i = 0; i < profesores.length; i++) {
			if (profesores[i] == null) {
				if (!verificarSiYaExiste(profesor.getDni())) {
					profesores[i] = profesor;
					contadorProfesores++;
					break;
				}

			}
		}
	}

	public boolean verificarSiYaExiste(Integer dni) {
		for (int i = 0; i < profesores.length; i++) {
			if (profesores[i] != null) {
				if (profesores[i].getDni() == dni) {
					return true;
				}
			}

		}
		return false;
	}

	public Alumno buscarAlumnoPorDni(Integer dni) {
		for (int i = 0; i < contadorAlumnos; i++) {
			if (alumnos[i].getDni() == dni) {
				return alumnos[i];
			}

		}
		return null;
	}

	public Materia buscarMateriaPorCodigo(Integer codigoMateria) {
		for (int i = 0; i < contadorMaterias; i++) {
			if (materias[i].getCodigoMateria() == codigoMateria) {
				return materias[i];
			}
		}
		return null;
	}

	public Profesor buscarProfesorPorDni(Integer dni) {
		for (int i = 0; i < profesores.length; i++) {
			if (profesores[i] != null) {
				if (profesores[i].getDni() == dni) {
					return profesores[i]; // Devolver el profesor encontrado
				}
			}
		}
		return null; // El profesor no fue encontrado
	}

	public void registrarMateria(Materia materia) {
		for (int i = 0; i < materias.length; i++) {
			if (materias[i] == null && verficarCodigoMateria(materia)) {
				materias[i] = materia;
				contadorMaterias++;
				break;
			}

		}
	}

	public boolean verficarCodigoMateria(Materia materia) {
		for (int i = 0; i < materias.length; i++) {
			if (materias[i] != null) {
				if (materias[i].getCodigoMateria() != materia.getCodigoMateria()) {
					return true;
				}
			}
		}
		return false;
	}

	public Aula[] getAulas() {
		return aulas;
	}

	public void setAulas(Aula[] aulas) {
		this.aulas = aulas;
	}

	public boolean verficarCodigoComision(Comision comision) {
		for (int i = 0; i < comisiones.length; i++) {
			if (comisiones[i] != null) {
				if (comisiones[i].getId() != comision.getId()) {
					return true;
				}
			}
		}
		return false;
	}
	// Otros métodos de la clase Universidad

	public String getNombreUniversidad() {
		return nombreUniversidad;
	}

	public void setNombreUniversidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
	}

	public Materia[] getMaterias() {
		return materias;
	}

	public void setMaterias(Materia[] materias) {
		this.materias = materias;
	}

	public Profesor[] getProfesores() {
		return profesores;
	}

	public void setProfesores(Profesor[] profesores) {
		this.profesores = profesores;
	}

	// Otros métodos de la clase Universidad
}
