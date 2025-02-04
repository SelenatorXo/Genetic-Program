# 🧬 Genetic Programming for Classification

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![VS Code](https://img.shields.io/badge/Editor-VS%20Code-blue)](https://code.visualstudio.com/)

This repository contains a **Java implementation** of a **Genetic Programming (GP)** algorithm for solving binary classification problems. The GP algorithm evolves a population of tree-based models to predict the target variable in a dataset.

---

## 🌟 Features

- **🌳 Tree-based Representation**: Each individual in the population is represented as a binary tree, where leaf nodes are constants and internal nodes are arithmetic operations.
- **⚡ Parallel Evolution**: The evolution process is parallelized using Java's `ExecutorService` for improved performance.
- **🎛️ Customizable Parameters**: Adjust key parameters such as population size, mutation rate, and crossover rate.
- **📊 Metrics Calculation**: The program calculates accuracy, specificity, sensitivity, and F-measure for the test set.

---

## 🛠️ Requirements

To run this project, you need:
- **Java Development Kit (JDK) 17 or higher**
- **Visual Studio Code** (or any Java IDE)
- A dataset in CSV format (see [Dataset](#dataset) for details)

---

## 📂 Dataset

The program expects a dataset in CSV format with the following characteristics:
- The **last column** should contain the target variable (binary: `0` or `1`).
- All other columns should contain numeric features.
- The first row can be a header row (e.g., column names), but it will be skipped during data loading.

### Example Dataset Format
```csv
feature1,feature2,feature3,target
0.1,0.5,0.7,1
0.2,0.3,0.8,0
0.4,0.6,0.9,1
```

---

## 🗂️ Code Structure

The project consists of the following Java files:

| File                  | Description                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| `GeneticProgramming.java` | Contains the main GP algorithm, including population initialization, evolution, and selection. |
| `GPNode.java`         | Represents a node in the GP tree. Includes methods for tree generation, evaluation, mutation, and crossover. |
| `Main.java`           | The entry point of the program. Loads the dataset, initializes the GP algorithm, and prints the results. |
| `README.md`           | This file, providing an overview of the project and instructions for running the code. |

---

## 🚀 How to Run

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-username/genetic-programming-classification.git
cd genetic-programming-classification
```

### Step 2: Open the Project in VS Code
1. Open **Visual Studio Code**.
2. Open the folder containing the cloned repository (`genetic-programming-classification`).

### Step 3: Prepare the Dataset
Place your training and test datasets in the `data` directory (or any directory of your choice). Ensure the datasets are in CSV format.

### Step 4: Run the Program
1. Open the `Main.java` file in VS Code.
2. Modify the file paths in the `Main.java` file to point to your training and test datasets:
   ```java
   List<double[]> trainingData = loadData("data/mushroom_train.csv");
   List<double[]> testData = loadData("data/mushroom_test.csv");
   ```
3. Run the program by clicking the **Run** button in VS Code or by using the terminal:
   ```bash
   javac Main.java
   java Main
   ```

---

## 📊 Results

After running the program, you will see the following outputs:
1. **Training Accuracy**: The accuracy of the best individual on the training set for each generation.
2. **Test Metrics**: The accuracy, specificity, sensitivity, and F-measure of the best individual on the test set.

### Example Output
```plaintext
Generation 0 Accuracy: 0.9998860509707512
Generation 1 Accuracy: 0.9998860509707512
...
Test Accuracy: 58.80398671096345%
Test Specificity: 0.0
Test Sensitivity: 1.0
Test F-measure: 0.7405857740585774
```

---

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Submit a pull request.

---

## 📜 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- Inspired by the field of **Genetic Programming** and evolutionary algorithms.
- Built using **Java** and **Visual Studio Code**.

---

## 🎉 Enjoy!

Feel free to explore, experiment, and contribute to this project. If you have any questions or suggestions, don't hesitate to open an issue or reach out!

---
