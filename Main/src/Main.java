import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        String caminho = "C:\\Users\\elian\\OneDrive\\Área de Trabalho\\motorDeBusca\\Main\\src\\documents\\verbetesWikipedia.xml";
        Document doc = lerXML(caminho);
        OperacoesXML op = new OperacoesXML();
        Element root = doc.getDocumentElement();
        String entrada = "Computer";


        op.search(root, entrada);











    }





    //op.printElements(root);




        //System.out.println(op.countElementByName(root, "page"));
        //op.readContentByTag(root, "ID");




        //System.out.println("Raiz elemento: " + root.getNodeName());




    public static Document lerXML(String caminhoDoArquivo) throws Exception {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        Document document = builder.parse(new File(caminhoDoArquivo));


        document.getDocumentElement().normalize();

        return document;
    }
}