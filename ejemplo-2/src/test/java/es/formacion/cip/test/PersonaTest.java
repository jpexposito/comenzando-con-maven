package es.formacion.cip.test;

import es.formacion.cip.ejemplo2.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jpexposito on 21/2/17.
 */
public class PersonaTest {

    Persona persona = null;


    @Before
    public void init() {
        if (persona == null) {
            persona = new Persona();
        }
    }

    @Test
    public void testearCarmen() {

        persona.setNombre("Carmen");
        persona.setApellido1("Apellido1");
        persona.setApellido2("Apellido2");
        persona.setDni("00000000T");

        Assert.assertNotNull("El objeto persona no debe de ser nulo", persona);
        Assert.assertTrue("No se ha obtenido el texto experado", persona.toString().contains("Carmen"));

    }


}
