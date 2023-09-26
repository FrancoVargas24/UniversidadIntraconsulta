package ar.unlam.materia;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestProfesor {
    private Profesor profesor;

    @Before
    public void setUp() {
        // Configura el contexto de prueba
        profesor = new Profesor(123456, "Gómez", "María", "01/01/1980");
    }

    @Test
    public void testGetId() {
        // Verifica que el método getid() devuelva el ID esperado
        assertEquals(Integer.valueOf(5004), profesor.getid());
    }

    @Test
    public void testGetNombre() {
        // Verifica que el método getNombre() devuelva el nombre esperado
        assertEquals("María", profesor.getNombre());
    }

    @Test
    public void testSetNombre() {
        // Configura un nuevo nombre y verifica que se haya establecido correctamente
        profesor.setNombre("Juan");
        assertEquals("Juan", profesor.getNombre());
    }

    @Test
    public void testGetApellido() {
        // Verifica que el método getApellido() devuelva el apellido esperado
        assertEquals("Gómez", profesor.getApellido());
    }

    @Test
    public void testSetApellido() {
        // Configura un nuevo apellido y verifica que se haya establecido correctamente
        profesor.setApellido("Pérez");
        assertEquals("Pérez", profesor.getApellido());
    }

    @Test
    public void testGetDni() {
        // Verifica que el método getDni() devuelva el DNI esperado
      assertEquals(Integer.valueOf(123456), profesor.getDni());
       
    }


    @Test
    public void testGetFechaNacimiento() {
        // Verifica que el método getFecha_nacimiento() devuelva la fecha de nacimiento esperada
        assertEquals("01/01/1980", profesor.getFecha_nacimiento());
    }

    @Test
    public void testSetFechaNacimiento() {
        // Configura una nueva fecha de nacimiento y verifica que se haya establecido correctamente
        profesor.setFecha_nacimiento("15/03/1975");
        assertEquals("15/03/1975", profesor.getFecha_nacimiento());
    }
}

