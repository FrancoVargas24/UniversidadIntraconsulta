package ar.unlam.materia;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestAlumno {

    private Alumno alumno;
    private Materia materia1;
    private Materia materia2;
    private Materia materiaCorrelativa;

    @Before
    public void setUp() {
        alumno = new Alumno(123456, "Perez", "Juan", "01/01/1990");
        materia1 = new Materia("Matematicas", 1);
        materia2 = new Materia("Fisica", 2);
        materiaCorrelativa = new Materia("Quimica", 3);
    }

    @Test
    public void buscarMateriaCorrelativa() {
        // Agregamos materias al alumno
        alumno.setMaterias(materia1);
        alumno.setMaterias(materia2);

        // Asignamos una correlativa a materia1
        materia1.setCorrelativa(materiaCorrelativa);

        // Verificamos que el alumno pueda buscar la materia correlativa
        assertTrue(alumno.buscarMateriaCorrelativa(materiaCorrelativa));
    }

    @Test
    public void promediarFinal() {
        // Creamos notas para los parciales
        Nota nota1 = new Nota();
        nota1.asignarValor(6);
        Nota nota2 = new Nota();
        nota2.asignarValor(7);

        // Calculamos el promedio final
        Nota notaFinal = alumno.promediarFinal(nota1, nota2);

        // Verificamos que el promedio final sea el correcto
        assertEquals(Integer.valueOf(6), notaFinal.getValor());
    }

    @Test
    public void puedeRecuperar() {
        // Asignamos notas a materia1
        materia1.setPrimerParcial(new Nota());
        materia1.setSegundoParcial(new Nota());

        // Caso 1: Primer parcial < 4 y segundo parcial >= 4
        materia1.getPrimerParcial().asignarValor(3);
        materia1.getSegundoParcial().asignarValor(6);
        assertTrue(alumno.puedeRecuperar(materia1));

        // Caso 2: Segundo parcial < 4 y primer parcial >= 4
        materia1.getPrimerParcial().asignarValor(7);
        materia1.getSegundoParcial().asignarValor(2);
        assertTrue(alumno.puedeRecuperar(materia1));

        // Caso 3: Ambos parciales >= 4
        materia1.getPrimerParcial().asignarValor(6);
        materia1.getSegundoParcial().asignarValor(5);
        assertTrue(alumno.puedeRecuperar(materia1));

        // Caso 4: Ningún parcial >= 4
        materia1.getPrimerParcial().asignarValor(3);
        materia1.getSegundoParcial().asignarValor(2);
        assertFalse(alumno.puedeRecuperar(materia1));
    }
}

