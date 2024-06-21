install.packages("wooldridge")
library("wooldridge")

#I will be using the catholic data from Woolridge. It is a cross-sectional individual data on schooling. 
data("catholic")

summary(catholic)

#research question: 
#Which independent variable effects the dependent variables (reading and math standardized scores) the most?

#independent variable: gender, ethnicity, parents years of education, log of family income, graduated from high school by 1994, attended a catholic HS and if the parent is catholic or not. 
#dependent variable: reading and mathematics standardized score
#control variable: person identifier

# The goal is to identify which variable has the largest effect on the scores. 


#i think one variable that might benefit from a transformation would be 'parents years of education'
#this is because the relationship between parents education and students reading and math scores are not as clear or linear. 
#one way to capture the relationship better is to square the variable. 

# Transform parents' years of education by squaring them 
catholic$motheduc_squared <- catholic$motheduc^2
catholic$fatheduc_squared <- catholic$fatheduc^2

# Create a linear regression model for reading score
reading_model <- lm(read12 ~ female + asian + hispan + black + motheduc + fatheduc + 
                      fatheduc_squared + motheduc_squared + lfaminc + hsgrad + cathhs + parcath,
                    data = catholic)

# Summarise the regression model
summary(reading_model)

# Create a linear regression model for math score
math_model <- lm(math12 ~ female + asian + hispan + black + motheduc + fatheduc + 
                   fatheduc_squared + motheduc_squared + lfaminc + hsgrad + cathhs + parcath,
                 data = catholic)

# Summarize the regression model
summary(math_model)

# Original variables
original_vars <- c("female", "asian", "hispan", "black", "motheduc", "fatheduc", 
                   "lfaminc", "hsgrad", "cathhs", "parcath")

# Squared variables
squared_vars <- c("fatheduc_squared", "motheduc_squared")

# Combine both sets of variables
all_vars <- c(original_vars, squared_vars)

# Get summary statistics for each variable
summary_stats <- lapply(all_vars, function(var) {
  summary(catholic[[var]])
})

print(summary_stats)


# Run multiple regression for both reading and math scores
multi_model <- lm(cbind(read12, math12) ~ fatheduc + motheduc + lfaminc + female + asian + hispan + black + hsgrad +cathhs + parcath , data = catholic)

# Summarize the regression model
summary(multi_model)


# Run multiple regression
multi_model <- lm(cbind(read12, math12) ~ fatheduc + motheduc + lfaminc + female + asian + hispan + black + hsgrad +cathhs + parcath + id , data = catholic)

# Summarize the regression model
summary(multi_model)

# Interpretation:
# To find the most significant variable affecting the reading and math scores, we examine the coefficients and p-values.
# The variable 'hsgrad' (high school graduation status by 1994) has large coefficients for both reading (5.13) and math (7.08) scores,
# and it has a small p-value, indicating high significance.
# This suggests that students who graduated high school by 1994 tend to have higher reading and math scores.
# Other significant variables include 'black', 'female', and 'lfaminc'.
