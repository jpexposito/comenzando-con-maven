package es.formacion.cip.ejemplo3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileWork {

    private static final Logger log = Logger.getLogger(FileWork.class.getCanonicalName());
    ClassLoader classLoader = getClass().getClassLoader();
    public String readFile(String fileName) throws IOException {
        String content = null;
        File file = null;
        FileReader reader = null;
        try {
            //Get file from resources folder

            file = getFile(fileName);
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(reader !=null){
                reader.close();
            }
        }
        return content;
    }

    public void writeFile(String fileName, String content, Boolean add) throws IOException {
        File file = null;
        file = getFile(fileName);
        FileWriter writer = new FileWriter(file, add);
        System.out.println("File: " + file.getCanonicalPath());
        writer.write(content);
        writer.close();
    }

    public void loadXMLDOM(String path) throws IOException, SAXException, ParserConfigurationException {

        Document doc = getDocumentDOM(path);

        System.out.println("Elemento Padre :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("staff");

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            System.out.println("\nElemento Actual :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                System.out.println("Identificador de empleado : " + eElement.getAttribute("id"));
                System.out.println("1 Nombre : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("2 Nombre : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Apodo : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("Salario : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

            }
        }
    }

    private Document getDocumentDOM(String fileName) throws ParserConfigurationException, SAXException, IOException {
        File file = null;
        file = getFile(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();
        return doc;
    }


    public void addNodeDOM (String path, String firstname, String lastname,  String nickname , String salary) throws IOException, SAXException, ParserConfigurationException {

        Document doc = getDocumentDOM(path);

        Node _firstname =doc.createElement("firstname");
        Node _firstnametxt =doc.createTextNode(firstname);
        _firstname.appendChild(_firstnametxt );

        Node _lastname =doc.createElement("lastname");
        Node _lastnamtxt =doc.createTextNode(lastname);
        _lastname.appendChild(_lastnamtxt);

        Node _nickname =doc.createTextNode("nickname");
        Node _nicknametxt =doc.createTextNode(nickname);
        _nickname.appendChild(_nicknametxt);


        Node _salary =doc.createElement("salary");
        Node _salarytxt =doc.createTextNode(salary);
        _salary.appendChild(_salarytxt);

        Node staff =doc.createElement("staff");

        staff.appendChild(_firstname);
        staff.appendChild(_lastname);
        staff.appendChild(_nickname);
        staff.appendChild(_salary);

        Node raiz=doc.getChildNodes().item(0);
        raiz.appendChild(staff);

    }

    public void loadXMLSAX(String fileName) throws IOException, SAXException, ParserConfigurationException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {

            boolean bfname = false;
            boolean blname = false;
            boolean bnname = false;
            boolean bsalary = false;

            public void startElement(String uri, String localName,String qName,
                                     Attributes attributes) throws SAXException {

                System.out.println("Primer Elemento :" + qName);

                if (qName.equalsIgnoreCase("FIRSTNAME")) {
                    bfname = true;
                }

                if (qName.equalsIgnoreCase("LASTNAME")) {
                    blname = true;
                }

                if (qName.equalsIgnoreCase("NICKNAME")) {
                    bnname = true;
                }

                if (qName.equalsIgnoreCase("SALARY")) {
                    bsalary = true;
                }

            }

            public void endElement(String uri, String localName,
                                   String qName) throws SAXException {

                System.out.println("Elemento Final :" + qName);

            }

            public void characters(char ch[], int start, int length) throws SAXException {

                if (bfname) {
                    System.out.println("1 Nombre: " + new String(ch, start, length));
                    bfname = false;
                }

                if (blname) {
                    System.out.println("2 Nombre : " + new String(ch, start, length));
                    blname = false;
                }

                if (bnname) {
                    System.out.println("Apodo : " + new String(ch, start, length));
                    bnname = false;
                }

                if (bsalary) {
                    System.out.println("Salario : " + new String(ch, start, length));
                    bsalary = false;
                }

            }

        };
        File file = null;
        file = getFile(fileName);
        saxParser.parse(file, handler);

    }

    File getFile (String fileName) {
        File file = null;
        file = new File(classLoader.getResource(fileName).getFile());
        return file;

    }

}
