package ar.unlam.materia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestNota {

    private Nota nota;

    @Before
    public void setUp() {
        nota = new Nota();
    }

    @Test
    public void asignarNotaValida() {
        // Asignamos una nota válida (5)
        nota.asignarValor(5);
        
        // Verificamos que la nota se haya asignado correctamente
        assertEquals(Integer.valueOf(5), nota.getValor());
    }

    @Test
    public void asignarNotaMenorQue1() {
        // Intentamos asignar una nota menor que 1 (-1)
        nota.asignarValor(-1);
        
        // Verificamos que la nota siga siendo 0
        assertEquals(Integer.valueOf(0), nota.getValor());
    }

    @Test
    public void asignarNotaMayorQue10() {
        // Intentamos asignar una nota mayor que 10 (15)
        nota.asignarValor(15);
        
        // Verificamos que la nota siga siendo 0
        assertEquals(Integer.valueOf(0), nota.getValor());
    }
}
