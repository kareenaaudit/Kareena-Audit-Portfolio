install.packages("wooldridge")
library("wooldridge")

#I will be using the catholic data from Woolridge. It is a cross-sectional individual data on schooling. 
data("catholic")

summary(catholic)


#research question: 
#How does parental education level (independent variable) influence the standardized scores in reading and math (dependent variables) for students in Catholic schools, 
#taking into account their gender, ethnicity, and family income (control variables)?

#independent variable:parents years of education 
#dependent variable: reading and mathematics standardized score
#control variable: gender, ethnicity, family income

#i chose this research question because it is interesting to see how parental education level can effect their childrens scores in math and reading. 
#but also shows how other factors can be potential influences as well. 


#regression model
model <- lm(read12 ~ female + lfaminc, data = catholic)
summary(model)

#from these result i can interpret that:
#Holding log family income constant, females tend to have reading standardized scores approximately 1.93 points higher than males on average.
#For each one-unit increase in the log of family income, holding gender constant, the reading standardized score is estimated to increase by approximately 3.12 points on average.
#The model, as a whole, is statistically significant in predicting reading standardized scores.


#I will look at the interactions between gender and family income to see if there is a relationship between family income and standardized scores which
#differs between genders. 
#This is interesting because it allows us to investigate if there is a gender-specific relationship between family income and academic performance. 
#the coefficient will show us how much family income has an effect on standardized scores changes for a on-unit change in gender. If the coefficient is significant
#then it will suggest that the relationship between family income and standardized scores is different for different genders.

# Create the interaction term between female and lfaminc
catholic$female_lfaminc <- catholic$female * catholic$lfaminc 

# Run the regression with the interaction term 
model_with_interaction <- lm(read12 ~ female + lfaminc + female_lfaminc, data = catholic)
summary(model_with_interaction)

#The coefficient for female is -3.6048. This means that, holding log family income and the interaction term constant, females are estimated to have a reading standardized score 
#approximately 3.60 points lower than males on average. However, this result is not statistically significant at the conventional 0.05 significance level (p-value > 0.05).

#The coefficient for lfaminc is 2.8149. This means that for each one-unit increase in the log of family income, holding gender and the interaction term constant, the reading 
#standardized score is estimated to increase by approximately 2.81 points on average.

#The coefficient for female_lfaminc is 0.5339. This suggests that the relationship between log family income and standardized scores is stronger for females compared to males.
#For every one-unit increase in the log of family income, females are estimated to experience an additional increase of approximately 0.53 points in reading standardized scores 
#compared to males therefore, it is statistically significant.


subset_data <- catholic[c("read12", "female", "lfaminc")]
summary(subset_data)


threshold <- 50 
catholic$read12_indicator <- ifelse(catholic$read12 >= threshold, 1, 0)
head(catholic)
#I have made read12 a dummy variable so if read12 is above or equal to 50 (the threshold) then it will be set to 1, if not then 0. 


linear_prob_model <- lm(read12_indicator ~ female + lfaminc + motheduc + fatheduc + hsgrad + cathhs + parcath, data = catholic)
summary(linear_prob_model)


library(sandwich)
library(lmtest)

linear_prob_model_robust <- coeftest(linear_prob_model, vcov = vcovHC(linear_prob_model, type = "HC1"))
summary(linear_prob_model_robust)

#to answer the research question, we can see that parental education does influence students reading and math scores. But also factors like gender, ethnicity, and family income
#can also have a heavy influence on students too. 





