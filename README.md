# README: Portfolio of Projects by Kareena Audit

## Overview

This README provides a summary of three distinct projects demonstrating skills in data analysis, simulation, optimization, and statistical modeling. Each project is detailed with its title, an overview, components or methods used, and instructions for usage. These projects showcase my proficiency in Java, Python, and R to address various problems in infection testing, economic market analysis, and educational data analysis.

## Table of Contents

1. [Infection Testing Simulation](#infection-testing-simulation)
2. [Optimization and Market Analysis](#optimization-and-market-analysis)
3. [Descriptive Statistics and Multiple Regression](#descriptive-statistics-and-multiple-regression)
    - [Descriptive Statistics and Multiple Regression](#descriptive-statistics-and-multiple-regression-project)
    - [Advanced Regression Techniques in Educational Data Analysis](#advanced-regression-techniques-in-educational-data-analysis)

## Infection Testing Simulation

### Project Title: Infection Testing Simulation

### Project Overview

This project simulates an infection testing system using Java. It focuses on managing and tracking the status of individual infection tests. Each test is represented as an instance of the `InfectionTest` class, which encapsulates details about the test, including its unique ID and status (known/unknown, positive/negative).

- **Date:** 22/Nov/2021
- **Author:** Kareena Audit

### Components

1. **InfectionTest Class**
    - **Attributes:**
        - `ID`: A unique identifier for the test.
        - `Status`: Indicates whether the test status is known and, if so, whether it is positive or negative.
    - **Methods:**
        - `getID()`: Returns the test ID.
        - `isKnown()`: Returns `true` if the test status is known, otherwise `false`.
        - `isPositive()`: Returns `true` if the test is positive, `false` if negative; throws an exception if the status is unknown.
        - `setStatus(boolean status)`: Sets the test status to positive (true) or negative (false) and marks it as known.
        - `getDetails()`: Provides a string describing the test status: "unknown", "negative", or "positive".

2. **InfectionTestTester Class**
    - Utilizes JUnit to validate the functionality of the `InfectionTest` class.
    - **Setup**: Initializes test instances with unique IDs.
    - **Tests**:
        - **Initial State**: Verify correct ID and unknown status for new tests.
        - **Status Setting**: Check status updates and known status after setting.
        - **Exception Handling**: Ensure `isPositive` throws an exception when status is unknown.
        - **Details Retrieval**: Validate `getDetails` method returns correct descriptions.

### Usage

- **InfectionTest Class**: 
  - Instantiate with a unique ID.
  - Use `setStatus` to set the test result.
  - Use `isKnown`, `isPositive`, and `getDetails` to query the test status.
- **InfectionTestTester Class**: 
  - Run JUnit tests to ensure the class behaves as expected.

---

## Optimization and Market Analysis

### Project Title: Optimization and Market Analysis

### Project Overview

This Python project explores economic scenarios involving utility maximization and market equilibrium. Using optimization techniques, it analyzes how different parameters affect economic outcomes. The script uses libraries like NumPy, SciPy, Pandas, and Matplotlib to perform and visualize the analyses.

- **Date:** 4/Nov/2022
- **Author:** Kareena Audit

### Components

1. **Optimization Exercises**:
    - **Exercise 0 & 1**: Maximize a Cobb-Douglas utility function under a budget constraint.
    - **Exercise 2**: Modify utility function parameters and observe effects on outcomes.
    - **Exercise 3**: Check market clearance for beef with given supply and demand.
    - **Exercise 4**: Check market clearance for tofu with given supply and demand.
    - **Exercise 5**: Re-evaluate previous exercises with altered parameters to explore different scenarios.

2. **Results Interpretation**:
    - **Optimization**: Output includes the optimized function value, Jacobian, number of evaluations, and status.
    - **Market Clearance**: Functions return values indicating market conditions (surplus or shortage).

### Usage

1. **Dependencies**:
    - Install Python with libraries: NumPy, SciPy, Pandas, Matplotlib.
2. **Running the Script**:
    - Copy the script into a Python environment or editor.
    - Execute the script to see results for optimization and market checks.

### Key Libraries

- **NumPy**: For numerical operations and array handling.
- **SciPy**: For optimization routines.
- **Pandas**: For data manipulation (minimal usage in this script).
- **Matplotlib**: For visualizing demand curves and market data.

---

## Descriptive Statistics and Multiple Regression

### Project Title: Descriptive Statistics and Multiple Regression

### Project Overview

This project investigates how various factors affect standardized reading and math scores using the `catholic` dataset from the Wooldridge package. It involves creating and analyzing multiple regression models to understand the impact of different independent variables on student performance.

- **Date:** 20/Oct/2023
- **Author:** Kareena Audit

### Research Question

Which independent variables most significantly impact standardized reading and math scores?

### Components

1. **Dataset**:
    - **Source**: `catholic` dataset from the Wooldridge package.
    - **Variables**:
        - **Dependent**: Standardized reading (`read12`) and math (`math12`) scores.
        - **Independent**: Gender (`female`), ethnicity (`asian`, `hispan`, `black`), parental education (`motheduc`, `fatheduc`), family income (`lfaminc`), high school graduation status (`hsgrad`), Catholic high school attendance (`cathhs`), and parental religion (`parcath`).

2. **Analysis Steps**:
    - **Regression Model**: Assess the impact of various factors on reading and math scores.
    - **Summary Statistics**: Generate statistics for all variables used in the model.

### Usage

1. **Dataset Selection**:
    - Use the `catholic` dataset from Wooldridge package.
2. **Steps to Follow**:
    - Formulate research questions and hypotheses.
    - Run regression models and interpret the results.
    - Generate summary statistics for understanding variable distributions.
3. **Running the Analysis**:
    - Ensure R and necessary packages are installed.
    - Follow the provided R script to replicate and extend the analysis.

---

## Advanced Regression Techniques in Educational Data Analysis

### Project Title: Advanced Regression Techniques in Educational Data Analysis

### Project Overview

Building on the foundational analysis in the previous project, this project enhances R coding skills for multiple linear regression, focusing on the use of indicator variables, interaction terms, and linear probability models. It explores how these advanced techniques can provide deeper insights into the factors affecting educational outcomes.

- **Date:** 20/Oct/2023
- **Author:** Kareena Audit

### Research Question

Which independent variables and interactions most significantly impact standardized reading and math scores?

### Components

1. **Dataset**:
    - **Source**: `catholic` dataset from the Wooldridge package.
    - **Variables**:
        - **Dependent**: Standardized reading (`read12`) and math (`math12`) scores.
        - **Independent**: Gender (`female`), ethnicity (`asian`, `hispan`, `black`), parental education (`motheduc`, `fatheduc`), family income (`lfaminc`), high school graduation status (`hsgrad`), Catholic high school attendance (`cathhs`), and parental religion (`parcath`).

2. **Analysis Steps**:
    - **Regression Model**: Evaluate the effects of various factors on reading and math scores.
    - **Interaction Terms**: Analyze interactions between variables, such as the effect of Catholic high school attendance and family income.
    - **Summary Statistics**: Provide descriptive statistics for all included variables.
    - **Indicator Variable Transformation**: Convert dependent variables into binary (dummy) variables.
    - **Linear Probability Model**: Implement a regression model with binary dependent variables.
    - **Robust Standard Errors**: Re-estimate models with robust standard errors to correct for heteroskedasticity.

### Usage

1. **Dataset Selection**:
    - Utilize the `catholic` dataset from the Wooldridge package.
2. **Steps to Follow**:
    - Develop and modify research questions.
    - Implement regression models and interpret the coefficients.
    - Include interaction terms to assess combined effects.
    - Convert dependent variables into binary forms for probability modeling.
    - Re-estimate models with robust standard errors to account for heteroskedasticity.
3. **Running the Analysis**:
    - Ensure R and necessary libraries are installed.
    - Execute the R script to perform and extend the analyses, focusing on educational outcomes.

---

## Conclusion

This portfolio demonstrates a diverse range of data analysis capabilities, from Java-based infection simulation to Python optimization and advanced R regression techniques. Each project showcases my ability to handle and analyze complex datasets, providing insights and solutions across different domains.

For further details or any queries, please contact me at kareenaaudit10@gmail.com.

