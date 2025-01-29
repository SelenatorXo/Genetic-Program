import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<double[]> trainingData = loadData("mushroom_data/mushroom_train.csv");
        List<double[]> testData = loadData("mushroom_data/mushroom_test.csv");

        GeneticProgramming gp = new GeneticProgramming(trainingData, testData);
        gp.evolve();
        GPNode bestIndividual = gp.getBestIndividual();
        gp.printMetrics(bestIndividual);
    }

    public static List<double[]> loadData(String filePath) {
        List<double[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            if ((line = br.readLine()) != null) {
                if (line.startsWith("cap-diameter")) {
                    line = br.readLine(); 
                }
            }
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] instance = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    instance[i] = Double.parseDouble(values[i]);
                }
                data.add(instance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing line: " + e.getMessage());
        }
        return data;
    }
}
