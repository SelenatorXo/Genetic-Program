import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneticProgramming {
    private List<GPNode> population;
    private List<double[]> trainingData;
    private List<double[]> testData;
    private Random random;
    private GPNode bestIndividual;
    private static final int MAX_TREE_DEPTH = 4;
    private static final int POPULATION_SIZE = 100;
    private static final int TOURNAMENT_SIZE = 5;
    private static final int NUM_GENERATIONS = 50;
    private static final double CROSSOVER_RATE = 0.7;
    private static final double MUTATION_RATE = 0.1;

    public GeneticProgramming(List<double[]> trainingData, List<double[]> testData) {
        this.trainingData = trainingData;
        this.testData = testData;
        this.random = new Random(42); 
        this.population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            this.population.add(GPNode.generateRandomTree(MAX_TREE_DEPTH));
        }
    }

    public void evolve() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
            List<GPNode> newPopulation = new ArrayList<>();
            List<Future<GPNode>> futures = new ArrayList<>();
            for (int i = 0; i < POPULATION_SIZE; i++) {
                futures.add(executor.submit(() -> {
                    GPNode parent1 = tournamentSelection();
                    GPNode parent2 = tournamentSelection();
                    GPNode offspring = parent1.crossover(parent2, CROSSOVER_RATE);
                    if (random.nextDouble() < MUTATION_RATE) {
                        offspring.mutate(MAX_TREE_DEPTH);
                    }
                    return offspring;
                }));
            }
            try {
                for (Future<GPNode> future : futures) {
                    newPopulation.add(future.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.population = newPopulation;
            GPNode best = getBestIndividual();
            System.out.println("Generation " + generation + " Accuracy: " + (1 - best.evaluateFitness(trainingData) / trainingData.size()));
        }
        executor.shutdown();
    }

    public GPNode tournamentSelection() {
        GPNode best = null;
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            GPNode individual = population.get(random.nextInt(population.size()));
            if (best == null || individual.evaluateFitness(trainingData) < best.evaluateFitness(trainingData)) {
                best = individual;
            }
        }
        return best;
    }

    public GPNode getBestIndividual() {
        GPNode best = null;
        for (GPNode individual : population) {
            if (best == null || individual.evaluateFitness(trainingData) < best.evaluateFitness(trainingData)) {
                best = individual;
            }
        }
        this.bestIndividual = best;
        return best;
    }

public void printMetrics(GPNode individual) {
    double TP = 0, TN = 0, FP = 0, FN = 0;
    for (double[] instance : testData) {
        double actual = instance[instance.length - 1]; 
        double predicted = individual.evaluate(instance);
        predicted = predicted > 0.5 ? 1 : 0; 
        if (actual == 1) {
            if (predicted == 1) TP++;
            else FN++;
        } else {
            if (predicted == 1) FP++;
            else TN++;
        }
    }
    double accuracy = (TP + TN) / testData.size();
    double specificity = TN / (TN + FP);
    double sensitivity = TP / (TP + FN);
    double precision = TP / (TP + FP);
    double fMeasure = 2 * (precision * sensitivity) / (precision + sensitivity);

    System.out.println("Test Accuracy: " + accuracy*100 + "%");
    System.out.println("Test Specificity: " + specificity);
    System.out.println("Test Sensitivity: " + sensitivity);
    System.out.println("Test F-measure: " + fMeasure);
}
}