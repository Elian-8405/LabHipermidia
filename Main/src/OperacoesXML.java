import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacoesXML {
    //Contem os metodos de operacoes com no arquivo XML

    public OperacoesXML(){

    }
    /*
    class Resultado {
        private final String id;
        private final String titulo;
        private final double relevancia;

        public Resultado(String id, String titulo, double relevancia) {
            this.id = id;
            this.titulo = titulo;
            this.relevancia = relevancia;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Resultado resultado)) return false;
            return Double.compare(getRelevancia(), resultado.getRelevancia()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getRelevancia());
        }

        public String getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public double getRelevancia() {
            return relevancia;
        }
    }
    */


    //Conta os Elementos da Raiz pelo nome

    public int countElementByName(Element root, String tagName){
        int total = 0;
        if(root != null){
            NodeList childs = root.getChildNodes();
            Node curNode = null;
            for(int i = 0; i < childs.getLength(); i++){
                curNode = childs.item(i);
                if(curNode.getNodeName().equalsIgnoreCase(tagName)){
                    total++;
                }
            }
        }else{
            return 0;
        }
        return total;
    }

    public void readContentByTag(Element root, String tagName){
        try{
            NodeList selectedTag = root.getElementsByTagName(tagName.toLowerCase());
            Node curNode = null;
            Element element = null;
            for(int i = 0; i < selectedTag.getLength(); i++){
                curNode = selectedTag.item(i);
                element = (Element)curNode;
                System.out.println(element.getTextContent());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


   public void search(Element root, String input){
        if(root == null){
            return;
        }

        NodeList pages = root.getElementsByTagName("page");


        for(int i = 0; i < pages.getLength(); i++) {
            Node pageNode = pages.item(i);
            if (pageNode.getNodeType() == Element.ELEMENT_NODE){
                Element pageElement = (Element )pageNode;

                String titulo = pageElement.getElementsByTagName("title").item(0).getTextContent();
                String textContent = pageElement.getElementsByTagName("text").item(0).getTextContent();

                int ocorrencia = countOcurrence(input, textContent);

                if(ocorrencia > 0){
                    double relevance = (double) ocorrencia/textContent.length();
                    String [] palavrasTitulo = titulo.split("\\s+");
                    for (String palavraDoTitulo : palavrasTitulo) {
                        if(input.equalsIgnoreCase(palavraDoTitulo)){
                            relevance += 1.0;
                            break;

                        }

                    }

                    String id = pageElement.getElementsByTagName("id").item(0).getTextContent();
                    System.out.println("Titulo: "+ titulo + "\nID: " + id + "\nRelevancia: " + relevance);

                }



            }


        }
   }













    public int countOcurrence(String input, String s){
        if(!s.isEmpty()){
            int ocurrence = 0;
            String[] palavras = s.split("\\s+");
            for (String palavra : palavras) {
                if (input.equalsIgnoreCase(palavra)){
                    ocurrence++;
                }
            }
            return ocurrence;
        }
        return 0;

    }








}
