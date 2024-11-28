import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacoesXML {
    //Contem os metodos de operacoes com no arquivo XML

    public OperacoesXML(){

    }

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


    public void search(String entrada, Element e){
        if(e == null){
            return;

        }
        double last_relevance = 0.0;
        double cur_relevance = 0.0;
        NodeList childs = e.getElementsByTagName("page");
        for (int i = 0; i < childs.getLength(); i++){
            Node cur = childs.item(i);
            if(cur.getNodeType() == Element.ELEMENT_NODE){
                Element elm = (Element)cur;
                String s = e.getElementsByTagName("text").item(0).getTextContent().toLowerCase();
                if(s.contains(entrada.toLowerCase())){
                    cur_relevance = calcRelevance(entrada.toLowerCase(), elm);
                    System.out.println("-----------");
                    System.out.println("Id: " + e.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Titulo: " + e.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Relevancia: " + cur_relevance);
                    System.out.println("-----------");

                }



            }
        }
    }



    public void printElements(Element root){
        if(root == null){
            return;

        }

        NodeList childs = root.getElementsByTagName("page");

        for(int i = 0; i < childs.getLength(); i++){
            Node n = childs.item(i);
            System.out.println("------------");
            System.out.println("Elemento Atual :" + n.getNodeName());
            System.out.println("------------");
            if(n.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element)n;
                System.out.println("Id: " + e.getElementsByTagName("id").item(0).getTextContent());
                System.out.println("Titulo: " + e.getElementsByTagName("title").item(0).getTextContent());
            }

        }

    }

    private double calcRelevance(String input, Element e){
        String titulo = e.getElementsByTagName("title").item(0).getTextContent().toLowerCase();

        String txt = e.getElementsByTagName("text").item(0).getTextContent().toLowerCase();
        double relevancia = (double) countOcurrence(input, txt) / txt.length();
        if(input.equalsIgnoreCase(titulo)){
            relevancia += 0.2;

        }

        return relevancia;
    }



    private int countOcurrence(String input, String txt){
        int ocurrence = 0;
        if(txt == null){
            return 0;

        }
        Pattern p = Pattern.compile(input.toLowerCase(), Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(txt);
        while (m.find()){
            ocurrence++;

        }

        return ocurrence;
    }





}
