import java.util.List;
import java.util.Random;

public class GPNode {
    private static final Random random = new Random();
    private double value;
    private GPNode left;
    private GPNode right;
    private boolean isLeaf;

    public GPNode() {
        this.value = random.nextDouble();
        this.isLeaf = true;
    }

    public GPNode(double value) {
        this.value = value;
        this.isLeaf = true;
    }

    public GPNode(double value, GPNode left, GPNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.isLeaf = false;
    }

    public static GPNode generateRandomTree(int maxDepth) {
        if (maxDepth <= 0 || random.nextDouble() < 0.3) {
            return new GPNode(random.nextDouble());
        } else {
            return new GPNode(random.nextDouble(), generateRandomTree(maxDepth - 1), generateRandomTree(maxDepth - 1));
        }
    }

    public void mutate(int maxDepth) {
        if (random.nextBoolean() || maxDepth <= 0) {
            this.value = random.nextDouble();
        } else {
            if (!this.isLeaf) {
                if (random.nextBoolean() && left != null) {
                    left.mutate(maxDepth - 1);
                } else if (right != null) {
                    right.mutate(maxDepth - 1);
                }
            } else {
                this.left = generateRandomTree(maxDepth - 1);
                this.right = generateRandomTree(maxDepth - 1);
                this.isLeaf = false;
            }
        }
    }

    public GPNode copy() {
        if (this.isLeaf) {
            return new GPNode(this.value);
        } else {
            return new GPNode(this.value, this.left.copy(), this.right.copy());
        }
    }

    public GPNode crossover(GPNode other, double crossoverRate) {
        if (random.nextDouble() < crossoverRate) {
            return other.copy();
        } else {
            if (this.isLeaf) {
                return new GPNode(this.value);
            } else {
                GPNode leftChild = this.left.crossover(other, crossoverRate);
                GPNode rightChild = this.right.crossover(other, crossoverRate);
                return new GPNode(this.value, leftChild, rightChild);
            }
        }
    }

    public double evaluate(double[] instance) {
        if (this.isLeaf) {
            return this.value;
        } else {
            double leftValue = this.left.evaluate(instance);
            double rightValue = this.right.evaluate(instance);
            return this.value + leftValue - rightValue;
        }
    }

    public double evaluateFitness(List<double[]> data) {
        double fitness = 0;
        for (double[] instance : data) {
            double output = this.evaluate(instance);
            double target = instance[instance.length - 1];
            fitness += Math.pow(output - target, 2);
        }
        return fitness / data.size();  // Average fitness
    }

    public int getTreeSize() {
        if (this.isLeaf) {
            return 1;
        } else {
            return 1 + this.left.getTreeSize() + this.right.getTreeSize();
        }
    }

    public GPNode getRandomSubtree() {
        if (random.nextBoolean() || this.isLeaf) {
            return this;
        } else {
            if (random.nextBoolean() && left != null) {
                return left.getRandomSubtree();
            } else if (right != null) {
                return right.getRandomSubtree();
            } else {
                return this;
            }
        }
    }
}
