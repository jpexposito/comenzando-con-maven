package es.formacion.cip.ejemplo3;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FileWorkTest {

    public static final String TEST_STRING = "Testing sobre ficheros\r\n";

    FileWork fileWork = null;
    String path = "D:\\workspace-fuse63\\acceso-ficheros-test\\src\\test\\resources\\";
    String nameFile = "datos.xml";

    @Before
    public void init() {
        if (fileWork == null)
            fileWork = new FileWork();
    }


    @Test
    public void testLoadFile() {

        String file = null;

        try {
           file =  fileWork.readFile(nameFile);
            Assert.assertNotNull(file, "Se ha obtenido un fichero nulo");
            System.out.println("Contenido de fichero:" +file);
        }catch (Exception e) {
                Assert.fail("Se ha producido un error en la lectura del fichero");
        }

    }

    @Test
    public void testWriteFile() {

        nameFile = "datos1.xml";
        try {
            fileWork.writeFile(nameFile, TEST_STRING, true);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testLoadFileXMLDOM() {

        nameFile = "staff.xml";
        try {
            fileWork.loadXMLDOM(nameFile);
        }catch (Exception e) {
            Assert.fail("Se ha producido un error en la lectura del fichero");
        }
    }

    @Test
    public void testLoadFileXMLSAX() {

        nameFile = "staff.xml";
        try {
            fileWork.loadXMLSAX(nameFile);
        }catch (Exception e) {
            Assert.fail("Se ha producido un error en la lectura del fichero");
        }
    }
}
