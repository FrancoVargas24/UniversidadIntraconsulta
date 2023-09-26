package ar.unlam.materia;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestMateria {

    private Materia materia;
    private Materia correlativa;

    @Before
    public void setUp() {
        materia = new Materia("Matematicas", 1);
        correlativa = new Materia("Fisica", 2);
    }

    @Test
    public void asignarYObtenerCorrelativa() {
        // Asignamos una materia correlativa
        materia.setCorrelativa(correlativa);
        
        // Verificamos que se haya asignado correctamente
        assertEquals(correlativa, materia.getCorrelativa());
    }

    @Test
    public void eliminarCorrelativa() {
        // Asignamos una materia correlativa
        materia.setCorrelativa(correlativa);

        // Eliminamos la correlativa
        materia.EliminarCorrelativa(correlativa);

        // Verificamos que la correlativa se haya eliminado correctamente
        assertNull(materia.getCorrelativa());
    }

    @Test
    public void verificarCorrelativa() {
        // Asignamos una materia correlativa
        materia.setCorrelativa(correlativa);

        // Verificamos que la materia tenga la correlativa asignada
        assertTrue(materia.tieneCorrelativa(correlativa));

        // Verificamos que la materia no tenga otra materia como correlativa
        assertFalse(materia.tieneCorrelativa(new Materia("Quimica", 3)));
    }
}
