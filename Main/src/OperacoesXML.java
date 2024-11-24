import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    





}
