## Introduction

Today it is surprising how large companies use learning algorithms to make recommendations to their users and to provide a better experience for them.
But how are these machine learning algorithms performed? In this work we will use different methods that will help us solve some problems, in this way we will be able to understand how some companies or web pages such as netflix or google use the recommendations based on the previous searches of their users. Apache spark will be used in the Scala programming language

# Theoretical framework
In general, Machine Learning algorithms can be classified into two types:

- Supervised: these algorithms are trained with a set of data whose result is already known. That is, the model is trained with data from which the input and output are already known.
- Unsupervised: the model is trained with data that the result is not yet known. That is, the input is known but not the output of the data.

Spark MLlib provides both supervised and unsupervised learning algorithms that offer solutions to the three most used techniques in the world of Machine Learning:
![1](https://i.imgur.com/dBBSpds.png)

In this document we will be seeing only algorithms for classification using the spark mlib library, the algorithms that we will play in this work will be the following:
- Decision tree classifier
- Logistic Regression
- Multilayer Perceptron
- Support Vector Machine
