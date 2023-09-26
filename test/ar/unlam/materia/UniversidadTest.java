package ar.unlam.materia;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class UniversidadTest {
	private Universidad universidad;
	private Alumno alumno;
	private Materia materia;
	private Comision comision;
	private Profesor profesor;
	private Aula aula;
	private CicloLectivo ciclolectivo;
	private Nota nota;

	@Before
	public void setUp() { 
		universidad = new Universidad("Universidad XYZ");
		alumno = new Alumno(123456, "Perez", "Juan", "01/01/1990");
		materia = new Materia("Matematicas", 1);
		comision = new Comision(materia, new CicloLectivo("primerCuatrimestre", "Mañana"), new Aula("Aula 101", 30));
		profesor = new Profesor(987654, "Gonzalez", "Maria", "15/03/1985");
		aula = new Aula("Aula 102", 2);
		ciclolectivo = new CicloLectivo("Segundocuatrimestre", "Mañana");
	}
	
	@Test
	public void sePuedeInscribirAUnaComision() {
		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);
		
		assertTrue(comision.sePuedeInscribir(LocalDate.now()));
	}
	
	@Test
	public void queNoPuedeInscribirAUnaComision() {
		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		assertFalse(comision.sePuedeInscribir(LocalDate.of(2025, 12, 31)));		
	}
	
	@Test
	public void crearUniversidad() {
		assertEquals("Universidad XYZ", universidad.getNombreUniversidad());

	}

	@Test
	public void inscribirAUnAlumnoALaUniversidad() {
		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		assertTrue(universidad.verificarDni(alumno.getDni()));
	}

	@Test
	public void noInscribirADosAlumnosIguales() {
		Alumno alumno2 = new Alumno(123456, "Gomez", "Maria", "15/03/1995");

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);

		universidad.inscribirAUnAlumnoALaUniversidad(alumno2);

		assertTrue(universidad.verificarDni(alumno.getDni()));

		assertFalse(universidad.verificarDni(123456));
	}

	@Test
	public void inscribirAUnProfesor() {

		universidad.inscribirAUnProfesorALaUniversidad(profesor);

		assertTrue(universidad.verificarSiYaExiste(profesor.getDni()));

	}

	@Test
	public void noInscribirADosProfesoresIguales() {
		Profesor profesor2 = new Profesor(987654, "Gonzalez", "Jorge", "15/03/1985");

		universidad.inscribirAUnProfesorALaUniversidad(profesor);

		universidad.inscribirAUnProfesorALaUniversidad(profesor2);

		assertTrue(universidad.verificarSiYaExiste(profesor.getDni()));

		assertFalse(universidad.verificarSiYaExiste(987654));

	}

	@Test
	public void inscribirUnaMateria() {
		universidad.registrarMateria(materia);

		assertNotNull(materia);
	}

	@Test
	public void inscribirDosMateriasIguales() {
		Materia materiaCopia = new Materia("Matematicas", 1);

		universidad.registrarMateria(materia);

		assertFalse(universidad.verficarCodigoMateria(materiaCopia));
	}

	@Test
	public void inscribirUnAula() {
		universidad.registrarAula(aula);

		Aula aulaRegistrada = universidad.getAulas()[0];

		assertEquals(aula.getId(), aulaRegistrada.getId());
	}

	@Test
	public void inscribirComision() {

		universidad.registrarComision(comision);

		Comision comisionRegistrada = universidad.getComisiones()[0];

		assertEquals(comision.getId(), comisionRegistrada.getId());

	}

	@Test
	public void incribirAUnAlumnoAUnaComision() {

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		universidad.inscribirAUnAlumnoAUnaComision(alumno, comision);

		Alumno[] alumnosComision = comision.getAlumno();
		assertTrue(Arrays.asList(alumnosComision).contains(alumno));

	}

	@Test
	public void agregarCorrelatividad() {
		Materia materiaCorrelativa = new Materia("Matematicas General", 15);

		materia.asignarCorrelativa(materiaCorrelativa);

		assertTrue(materia.tieneCorrelativa(materiaCorrelativa));

	}

	@Test
	public void incribirAUnAlumnoAUnaComisionQueTieneCorrelativa() {

		Materia materiaCorrelativa = new Materia("Matematicas General", 15);

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		materia.asignarCorrelativa(materiaCorrelativa);

		Nota nota1 = new Nota();

		nota1.asignarValor(9);
		materiaCorrelativa.setFinalAnual(nota1);

		alumno.setMateriasAprobadas(materiaCorrelativa);

		universidad.inscribirAUnAlumnoAUnaComision(alumno, comision);

		comision.agregarAlumno(alumno);

		Alumno[] alumnosComision = comision.getAlumno();
		assertFalse(Arrays.asList(alumnosComision).contains(alumno));

	}

	@Test
	public void noSePuedeInscribirElAlumnoSiExcedeLaCantidadDeAlumnosPermitidosEnElAula() {
		
		Comision comision1 = new Comision(materia, new CicloLectivo("primerCuatrimestre", "Mañana"), new Aula("Aula 102", 2));

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarComision(comision1);

		Alumno alumno1 = new Alumno(112356, "Vargas", "Pedro", "01/01/1990");
		Alumno alumno2 = new Alumno(12356, "Franchella", "Yoyita", "01/01/1990");

		universidad.inscribirAUnAlumnoAUnaComision(alumno, comision1);
		universidad.inscribirAUnAlumnoAUnaComision(alumno1, comision1);
		universidad.inscribirAUnAlumnoAUnaComision(alumno2, comision1);

		Alumno alumnosComision = comision.getAlumno()[2];
		assertNull(alumnosComision);

	}
	

	//No se puede inscribir a una materia que haya aprobado previamente
	@Test
	public void noSePuedeInscribirAUnaMateriaQueHayaAprobadoPreviamente() {

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		Nota nota1 = new Nota();
		nota1.asignarValor(9);
		materia.setFinalAnual(nota1);

		alumno.setMateriasAprobadas(materia);
				
		 // Intenta inscribir al alumno en una materia aprobada
        universidad.inscribirAUnAlumnoAUnaComision(alumno, comision);

        // Verifica que la inscripción no haya sido exitosa
		Alumno alumnosComision = comision.getAlumno()[0];
	    assertFalse(Arrays.asList(alumnosComision).contains(alumno));
	}
	

	
	// verificar que exista la comisión y el docente
	@Test
	public void incribirAUnProfesorEnUnaComision() {

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.inscribirAUnProfesorALaUniversidad(profesor);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		universidad.inscribirAUnAlumnoAUnaComision(alumno, comision);
		
		comision.agregarProfesor(profesor);

		assertNotNull(comision.getProfesor());
	}
	//Cada 20 alumnos se debe agregar un docente ejemplo de 1 a 20 alumnos solo se puede asignar un docente, de 21 a 40 2 docentes
	@Test
	public void asignarDocentesSegunCantidadDeAlumnos() {
	    Profesor profesor2 = new Profesor(93254, "Gonzalez", "Felipe", "15/03/1985");

	    // Configura el contexto inicial
	    universidad.inscribirAUnProfesorALaUniversidad(profesor);
	    universidad.inscribirAUnProfesorALaUniversidad(profesor2);
	    
	    universidad.inscribirAUnAlumnoALaUniversidad(alumno);
	    
	    universidad.registrarMateria(materia);
	    universidad.registrarAula(aula);
	    

	    for (int i = 0; i < 21; i++) {
	        Alumno alumno1 = new Alumno(i, "Apellido" + i, "Nombre" + i, "01/01/2000");
	        universidad.inscribirAUnAlumnoAUnaComision(alumno1, comision);
	    }
	    
		comision.agregarProfesor(profesor);
		comision.agregarProfesor(profesor2);

	    
	    //Verifica que se haya asignado un segundo docente;
	    Profesor profesorUno = comision.getProfesor()[0];
	    Profesor profesorDos = comision.getProfesor()[1];
	    assertEquals(profesor.getDni(), profesorUno.getDni());
	    assertEquals(profesor2.getDni(), profesorDos.getDni());
	    
	   // assertEquals(0, comision.getProfesor().length);


	    
	}

	//no puede rendir 2 recuperatorios, es solo 1.
	@Test
	public void quePuedaRecuperarUnParcial() {

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.inscribirAUnProfesorALaUniversidad(profesor);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		universidad.inscribirAUnAlumnoAUnaComision(alumno, comision);
		
		Nota nota1 = new Nota();
		nota1.asignarValor(9);
		materia.setPrimerParcial(nota1);
		
		Nota nota2 = new Nota();
		nota2.asignarValor(2);
		materia.setSegundoParcial(nota2);
		
		alumno.setMaterias(materia);
		
		assertTrue(universidad.puedeRecuperar(alumno.getDni(), materia.getCodigoMateria()));
		
		
	}
	
	//para cargar la nota final, debe tener aprobadas las parciales
	@Test
	public void asignarNotaFinalDeParcialesAprobados() {

		universidad.inscribirAUnAlumnoALaUniversidad(alumno);
		universidad.registrarMateria(materia);
		universidad.registrarAula(aula);
		universidad.registrarComision(comision);

		universidad.inscribirAUnAlumnoAUnaComision(alumno, comision);
		
		Nota nota1 = new Nota();
		nota1.asignarValor(4);
		materia.setPrimerParcial(nota1);
		
		Nota nota2 = new Nota();
		nota2.asignarValor(5);
		materia.setSegundoParcial(nota2);
		
		Nota nota3 = new Nota();
		nota3.asignarValor(9);
		materia.setSegundoParcial(nota3);
		
		alumno.setMaterias(materia);
		
		assertTrue(universidad.asignarNotaFinal(materia.getCodigoMateria(), alumno.getDni(), nota3.getValor()));
		
		
	}


}
