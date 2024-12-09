import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacoesXML {
    //Contem os metodos de operacoes com no arquivo XML

    private Map<String, Map<Double, List<Element>>> cacheDeBusca;
    private Set<String> palavrasValidas;

    public OperacoesXML(){
        this.cacheDeBusca = new HashMap<>();
        this.palavrasValidas = new HashSet<>();
    }




    public void processarXML(Element root){
        if(root == null){
            return;
        }

        NodeList pages = root.getElementsByTagName("page");

        for(int i = 0; i < pages.getLength(); i++) {
            Node pageNode = pages.item(i);
            if(pageNode.getNodeType() == Element.ELEMENT_NODE){
                Element elementoDaPagina = (Element) pageNode;
                //Pega o titulo da tag <tilte>
                String titulo = elementoDaPagina.getElementsByTagName("title").item(0).getTextContent();
                //Pega o texto da tag <text>
                String textContent = elementoDaPagina.getElementsByTagName("text").item(0).getTextContent();

                String[] palavras = textContent.split("\\s+");

                for (String palavra : palavras) {
                    if(palavra.length() > 4){
                        //Adiciona somente as palavras validadas no processamento
                        palavrasValidas.add(palavra.toLowerCase());

                        int ocorrencias = countOcurrence(palavra, textContent);
                        double relevancia = (double)ocorrencias/ textContent.length();
                        if(titulo.toLowerCase().contains(palavra.toLowerCase())){
                            relevancia += 1.0;
                        }
                        cacheDeBusca.computeIfAbsent(palavra.toLowerCase(), k -> new TreeMap<>(Comparator.reverseOrder()))
                                    .computeIfAbsent(relevancia, k-> new ArrayList<>())
                                    .add(elementoDaPagina);


                    }

                }
            }

        }

    }


   public void search(String input){
        if(input.isEmpty()){
            return;
        }
        if(!palavrasValidas.contains(input)){
            System.out.println("Palavra Invalida");
            return;

        }
        if(cacheDeBusca.containsKey(input)){
            Map<Double, List<Element>> result = cacheDeBusca.get(input);
            System.out.println("Resultados para: " + input);
            int first5 = 0;
            for(Map.Entry<Double, List<Element>> entry : result.entrySet()){
                double relevance = entry.getKey();

                for(Element page : entry.getValue()){
                    String titulo = page.getElementsByTagName("title").item(0).getTextContent();
                    String id = page.getElementsByTagName("id").item(0).getTextContent();
                    System.out.println("Relevancia: "+ relevance);
                    System.out.println("ID: "+ id);
                    System.out.println("Titulo: "+ titulo);

                    first5++;
                    if(first5 >= 5){
                        return;

                    }
                }


            }

        }else{
            System.out.println("Nenhum resuldao Encontrado!");


        }
   }





    private int countOcurrence(String input, String s){
        if(!s.isEmpty()){
            int ocurrence = 0;
            //Faz o split do texto recebido
            String[] palavrasDoTexto = s.split("\\s+");
            //Para cada palavra do meu texto com split
            for (String ocorrence : palavrasDoTexto) {
                if(ocorrence.equalsIgnoreCase(input)){
                    ocurrence++;

                }

            }

            return ocurrence;
        }
        return 0;

    }








}
