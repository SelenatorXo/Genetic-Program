# Genetic Programming for Classification

This repository contains a Java implementation of a Genetic Programming (GP) algorithm for solving binary classification problems. The GP algorithm evolves a population of tree-based models to predict the target variable in a dataset.

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Dataset](#dataset)
5. [Code Structure](#code-structure)
6. [How to Run](#how-to-run)
7. [Results](#results)
8. [Contributing](#contributing)
9. [License](#license)

---

## Overview

Genetic Programming (GP) is an evolutionary algorithm that evolves computer programs (represented as trees) to solve a given problem. In this implementation, GP is used to evolve mathematical expressions (trees) that classify data into one of two classes (binary classification).

The algorithm works as follows:
1. **Initialization**: A population of random trees is generated.
2. **Evaluation**: Each tree is evaluated on the training data using a fitness function (mean squared error).
3. **Selection**: The best-performing trees are selected for reproduction.
4. **Crossover**: Selected trees are combined to create offspring.
5. **Mutation**: Random changes are introduced to the offspring.
6. **Evolution**: The process repeats for a fixed number of generations.

---

## Features

- **Tree-based Representation**: Each individual in the population is represented as a binary tree, where leaf nodes are constants and internal nodes are arithmetic operations.
- **Parallel Evolution**: The evolution process is parallelized using Java's `ExecutorService` for improved performance.
- **Customizable Parameters**: Key parameters such as population size, mutation rate, and crossover rate can be adjusted.
- **Metrics Calculation**: The program calculates accuracy, specificity, sensitivity, and F-measure for the test set.

---

## Requirements

To run this project, you need:
- **Java Development Kit (JDK) 8 or higher**
- **Maven** (for dependency management and building the project)
- A dataset in CSV format (see [Dataset](#dataset) for details)

---

## Dataset

The program expects a dataset in CSV format with the following characteristics:
- The last column should contain the target variable (binary: 0 or 1).
- All other columns should contain numeric features.
- The first row can be a header row (e.g., column names), but it will be skipped during data loading.

Example dataset format:
```
feature1,feature2,feature3,target
0.1,0.5,0.7,1
0.2,0.3,0.8,0
0.4,0.6,0.9,1
```

---

## Code Structure

The project consists of the following Java files:

1. **`GeneticProgramming.java`**:
   - Contains the main GP algorithm, including population initialization, evolution, and selection.
   - Implements parallel execution for faster evolution.

2. **`GPNode.java`**:
   - Represents a node in the GP tree.
   - Includes methods for tree generation, evaluation, mutation, and crossover.

3. **`Main.java`**:
   - The entry point of the program.
   - Loads the dataset, initializes the GP algorithm, and prints the results.

4. **`README.md`**:
   - This file, providing an overview of the project and instructions for running the code.

---

## How to Run

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-username/genetic-programming-classification.git
cd genetic-programming-classification
```

### Step 2: Build the Project
If you have Maven installed, run:
```bash
mvn clean package
```
This will compile the code and create a JAR file in the `target` directory.

### Step 3: Prepare the Dataset
Place your training and test datasets in the `data` directory (or any directory of your choice). Ensure the datasets are in CSV format.

### Step 4: Run the Program
Run the program using the following command:
```bash
java -cp target/genetic-programming-classification-1.0.jar Main data/mushroom_train.csv data/mushroom_test.csv
```
Replace `data/mushroom_train.csv` and `data/mushroom_test.csv` with the paths to your training and test datasets.

---

## Results

After running the program, you will see the following outputs:
1. **Training Accuracy**: The accuracy of the best individual on the training set for each generation.
2. **Test Metrics**: The accuracy, specificity, sensitivity, and F-measure of the best individual on the test set.

Example output:
```
Generation 0 Accuracy: 0.9998860509707512
Generation 1 Accuracy: 0.9998860509707512
...
Test Accuracy: 58.80398671096345%
Test Specificity: 0.0
Test Sensitivity: 1.0
Test F-measure: 0.7405857740585774
```

---

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Submit a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgments

- Inspired by the field of Genetic Programming and evolutionary algorithms.
- Built using Java and Maven.

---

