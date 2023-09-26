package ar.unlam.materia;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestAula {
	private Aula aula;

	@Before
	public void setUp() {
		// Configura el contexto de prueba
		aula = new Aula("Departamento 1", 30);
	}

	@Test
	public void testGetId() {
		// Verifica que el método getId() devuelva el ID esperado
		assertEquals(Integer.valueOf(501), Aula.getId());
	}

	@Test
	public void testGetDepartamento() {
		// Verifica que el método getDepartamento() devuelva el departamento esperado
		assertEquals("Departamento 1", aula.getDepartamento());
	}

	@Test
	public void testSetDepartamento() {
		// Configura un nuevo departamento y verifica que se haya establecido
		// correctamente
		aula.setDepartamento("Departamento 2");
		assertEquals("Departamento 2", aula.getDepartamento());
	}

	@Test
	public void testGetCantidad() {
		// Verifica que el método getCantidad() devuelva la cantidad esperada
		assertEquals(Integer.valueOf(30), aula.getCantidad());
	}

	@Test
	public void testSetCantidad() {
		// Configura una nueva cantidad y verifica que se haya establecido correctamente
		aula.setCantidad(40);
		assertEquals(Integer.valueOf(40), aula.getCantidad());
	}

	@Test
	public void testGetEstadoInicial() {
		// Verifica que el método getEstado() devuelva el estado inicial esperado
		// (false)
		assertFalse(aula.getEstado());
	}

	@Test
	public void testSetEstado() {
		// Configura un nuevo estado (true) y verifica que se haya establecido
		// correctamente
		aula.setEstado(true);
		assertTrue(aula.getEstado());
	}

	@Test
	public void testBuscarAulaVacia() {
		// Crea un arreglo de aulas con una aula vacía y otra ocupada
		Aula[] aulas = new Aula[2];
		aulas[0] = new Aula("Departamento 3", 50);
		aulas[1] = aula;
		
		aulas[0].setEstado(true);
		// Llama a buscarAulaVacia y verifica que devuelva la aula vacía
		Aula aulaVacia = aula.buscarAulaVacia(aulas);
		assertFalse(aula.getEstado()); // El estado de esta aula no debería cambiar
		assertTrue(aulas[0].getEstado());
	}
}
