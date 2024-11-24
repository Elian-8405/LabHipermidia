import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanInvalisXmlChars {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\elian\\OneDrive\\Área de Trabalho\\motorDeBusca\\Main\\src\\documents\\verbetesWikipedia.xml";
        String outputFile = "verbetesWikipedia_cleaned.xml";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Remove caracteres inválidos para XML
                String cleanedLine = line.replaceAll("[\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F-\\x9F]", "");
                writer.write(cleanedLine + System.lineSeparator());
            }

            System.out.println("Arquivo processado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
