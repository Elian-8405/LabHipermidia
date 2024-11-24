import java.io.*;

public class RemoveInvalid {
    public static void main(String[] args) throws IOException {
        String inputFile = "C:\\Users\\elian\\OneDrive\\Área de Trabalho\\motorDeBusca\\Main\\src\\documents\\verbetesWikipedia_cleaned.xml";
        String outputFile = "verbetesWikipedia_cleaned1.xml";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Remove caracteres inválidos
                String cleaned = line.replaceAll("[\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F]", "");
                writer.write(cleaned);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Arquivo corrigido salvo em: " + outputFile);
    }
}
