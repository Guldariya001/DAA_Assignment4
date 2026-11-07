# DAA Assignment 4 — Dynamic Programming and String Algorithms

## Project Description

This project was created for Assignment 4 in the Design and Analysis of Algorithms course.

The main goal is to compare different algorithms for calculating Edit Distance and searching for patterns in text.

The project uses Java, Maven, and JUnit 5.

## Implemented Algorithms

### 1. Edit Distance

I implemented three versions:

- **Recursive:** Calculates Edit Distance using recursion without memorization.
- **Table:** Uses a dynamic programming table to store intermediate results.
- **TwoRows:** Uses only two rows to reduce memory usage.

### 2. String Search

I implemented two algorithms:

- **Naive Search:** Checks the pattern at every possible position.
- **KMP Search:** Uses the LPS array to avoid unnecessary comparisons.

Both algorithms return all matching positions, including overlapping matches.

## Technologies

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA

## Project Structure

```text
DAA_Assignment4/
├── src/
│   ├── main/java/
│   │   ├── EditDistance.java
│   │   ├── StringSearch.java
│   │   ├── Main.java
│   │   └── Experiment.java
│   └── test/java/
│       ├── EditDistanceTest.java
│       └── StringSearchTest.java
├── results/
│   ├── results.csv
│   └── plots/
│       ├── edit_operations.png
│       ├── edit_time.png
│       └── search_operations.png
├── pom.xml
├── README.md
└── REPORT.md
```

## How to Run

### 1. Build the project

Open the project in IntelliJ IDEA and reload Maven dependencies.

Run the following command in the terminal:

```bash
mvn clean package
```

### 2. Run the tests

```bash
mvn test
```

The project includes seven JUnit 5 test methods covering reference values, edge cases, and random inputs.

### 3. Run the experiment

In IntelliJ IDEA, open `Experiment.java` and run its `main()` method.

The experiment will generate:

```text
results/results.csv
```

The experiment uses a fixed random seed of 42 and five runs for each input configuration.

### 4. View the plots

Open the PNG files inside:

```text
results/plots/
```

The three plots compare operation counts and execution time.

## Experiment Details

Edit Distance input sizes: 2, 4, 6, 8, 10.

String Search input sizes: 1000, 4000, 8000, 16000, 32000.

Input types include random strings, completely different strings, and worst-case repeated-character text.

The experiment records execution time in milliseconds and algorithm operation counts.

## Results

The recursive Edit Distance algorithm required many more operations than the dynamic programming versions.

Table and TwoRows produced the same results, but TwoRows required less extra memory.

For the worst-case string search input, KMP performed fewer counted operations than Naive.

Detailed results and explanations are available in `REPORT.md`.

## AI Usage Acknowledgement

I used ChatGPT as an assistant to help understand the assignment requirements, organize the Java code, prepare tests, and structure the report. I ran the algorithms and experiments in IntelliJ IDEA and used the measured results for the analysis.

## Author

Student: Urazaliyeva Guldariya  
Group: SE-2521

## GitHub Repository

https://github.com/Guldariya001/DAA_Assignment4