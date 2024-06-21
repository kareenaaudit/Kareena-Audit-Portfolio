#Kareena Audit problem set 1 

install.packages("wooldridge")
library("wooldridge")

#I will be using the catholic data from Woolridge. It is a cross-sectional individual data on schooling. 
data("catholic")

summary(catholic)

#QUESTION 3
#research question: 
#Which independent variable effects the dependent variables (reading and math standardized scores) the most?

#independent variable: gender, ethnicity, parents years of education, log of family income, graduated from high school by 1994, attended a catholic HS and if the parent is catholic or not. 
#dependent variable: reading and mathematics standardized score
#control variable: person identifier

#i chose this research question because they are many different independent variables that may affect the scores for each students, but i wanted to see which variable 
#would effect the scores the most. 


#QUESTION 5 
#i think one variable that might benefit from a transformation would be 'parents years of education'
#this is because the relationship between parents education and students reading and math scores are not as clear or linear. 
#one way to capture the relationship better is to square the variable. 

# Transform parents years of education
catholic$motheduc_squared <- catholic$motheduc^2
catholic$fatheduc_squared <- catholic$fatheduc^2

# Create a linear regression model for reading score
reading_model <- lm(read12 ~ female + asian + hispan + black + motheduc + fatheduc + 
                      fatheduc_squared + motheduc_squared + lfaminc + hsgrad + cathhs + parcath,
                    data = catholic)

# Summarize the regression model
summary(reading_model)

# Create a linear regression model for math score
math_model <- lm(math12 ~ female + asian + hispan + black + motheduc + fatheduc + 
                   fatheduc_squared + motheduc_squared + lfaminc + hsgrad + cathhs + parcath,
                 data = catholic)

# Summarize the regression model
summary(math_model)

#QUESTION 6 
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

#QUESTION 7 
# Run multiple regression
multi_model <- lm(cbind(read12, math12) ~ fatheduc + motheduc + lfaminc + female + asian + hispan + black + hsgrad +cathhs + parcath , data = catholic)

# Summarize the regression model
summary(multi_model)

#QUESTION 8 
# Run multiple regression
multi_model <- lm(cbind(read12, math12) ~ fatheduc + motheduc + lfaminc + female + asian + hispan + black + hsgrad +cathhs + parcath + id , data = catholic)

# Summarize the regression model
summary(multi_model)

# in order to find which independent variable has the most effect on read12 and math12 i first looked at the coefficients in the regression model in the reading model and math model. 
# we must look for the variable with a large coefficient and a small p-value to find the variable that is the most impactful on the scores.

# for read12 and math12 regression model, we can see that hsgrad has a large coefficient at 5.13 and 7.08 and a small p-value. therefore, hsgrad is the most significant to read12 and math12.
# this significance shows that individuals who graduated high school by 1994 have a higher reading and math score compared to those who don't. 
# some other variables that may effect read12 and math12 are black, female and ifaminc. 

#Therefore, hsgrad is the most significant independent variable to effect the dependent variables of the reading and math scores. 
