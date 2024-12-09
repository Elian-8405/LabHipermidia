import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacoesXML {
    //Contem os metodos de operacoes com no arquivo XML

    private Map<String, List<ElementoInfo>> cache = new HashMap<>();

    public OperacoesXML() {

    }


    public void processXML(Element root, String input) {
        if (root == null || input == null || input.isEmpty()) {
            return;
        }

        NodeList getPages = root.getElementsByTagName("page");

        for (int i = 0; i < getPages.getLength(); i++) {
            Node pageN = getPages.item(i);
            if (pageN.getNodeType() == Element.ELEMENT_NODE) {
                Element element = (Element) pageN;
                String id = element.getElementsByTagName("id").item(0).getTextContent();
                String titulo = element.getElementsByTagName("title").item(0).getTextContent();
                String text = element.getElementsByTagName("text").item(0).getTextContent();
                String[] palavras = text.split("\\s+");

                // Itera sobre as palavras do texto
                for (String palavra : palavras) {
                    palavra = palavra.toLowerCase().replaceAll("[^a-z0-9]", ""); // Normaliza a palavra (minúsculas e remove caracteres especiais)

                    if (palavra.length() > 4 && palavra.equals(input.toLowerCase())) {
                        // Incrementa as ocorrências da palavra no cache
                        List<ElementoInfo> lista = cache.computeIfAbsent(palavra, k -> new ArrayList<>());
                        boolean found = false;
                        for (ElementoInfo info : lista) {
                            if (info.getId().equals(id) && info.getTitulo().equals(titulo)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            lista.add(new ElementoInfo(id, titulo));
                        }

                    }
                }
            }
        }

        // Exibe o resultado
        if (cache.containsKey(input.toLowerCase())) {
            List<ElementoInfo> listaDeInfos = cache.get(input.toLowerCase());
            System.out.println("A palavra '" + input + "' apareceu " + listaDeInfos.size() + " vez(es).");

            for (ElementoInfo info : listaDeInfos) {
                System.out.println("ID: " + info.getId() + ", Título: " + info.getTitulo());
            }
        } else {
            System.out.println("A palavra '" + input + "' não foi encontrada.");
        }
    }
}


















