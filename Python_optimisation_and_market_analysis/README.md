PROJECT TITLE: 
Optimisation and Market Analysis

PROJECT OVERVIEW:  
This Python script utilises optimisation techniques and market analysis to explore economic scenarios involving utility maximisation and market clearance checks for different goods. The script primarily makes use of NumPy for numerical operations, SciPy's optimisation module (scipy.optimize) for maximising utility functions under constraints, Pandas for data handling (though not extensively used in this version), and Matplotlib for visualisations.

DATE:
4/Nov/2022

AUTHORS:
Kareena Audit 

OVERVIEW: 

The script is organised into several exercises, each focusing on different economic scenarios:

Exercise 0 & 1: Optimisation with a Cobb-Douglas utility function under a budget constraint. The utility function parameters (al) and market conditions (p for prices, cs for supply) are defined, followed by an optimisation to maximise utility under the given constraints.

Exercise 2: Similar to Exercise 0 & 1, but with a different utility function parameter (al1). This exercise demonstrates how changing utility function parameters affects optimisation outcomes.

Exercise 3: Checks market clearance for beef based on given quantities (qb) and a total beef amount (B). The function markb(B) determines if there's a surplus or shortage in the market.

Exercise 4: Similar to Exercise 3 but for tofu, checking market clearance based on given quantities (qt) and a total tofu amount (T). The function markt(T) assesses market equilibrium for tofu.

Exercise 5: Repeats Exercises 0-4 with various parameter changes (al, p, cs) to explore different economic scenarios. Each subsection redefines utility functions, prices, and constraints, then performs optimisation and market clearance checks.

RESULTS INTERPRETATION:

Optimisation Results: Each optimisation (opt.minimize) prints a summary including the objective function value (fun), Jacobian (jac), number of function evaluations (nfev), number of iterations (nit), and status (message). A successful optimisation (success: True) indicates that the utility function was maximised under the given constraints.

Market Clearance Results: Functions markb(B) and markt(T) return a numerical value indicating the market situation: positive values denote excess demand, negative values denote excess supply, and zero denotes market equilibrium.

USAGE:

To run the script:
Ensure Python is installed on your system along with necessary libraries (numpy, scipy, pandas, matplotlib).
Copy the script into a Python environment or script editor.
Execute the script. It will print the results of each optimisation and market clearance check to the console.
Dependencies
NumPy: For numerical operations and array manipulations.
SciPy: Specifically scipy.optimize for optimisation routines.
Pandas: Used for data handling, but not extensively in this script.
Matplotlib: For plotting demand curves and visualising market data.