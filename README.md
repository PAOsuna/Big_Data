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

## Decision Tree Classifier

Decision trees are popular methods for classification and regression tasks of machine learning.
Decision trees are widely used because they are easy to interpret, handle categorical characteristics, extend to the multi-class classification configuration, do not require scale of characteristics and are capable of capturing nonlinearities and characteristic interactions.
A decision tree is a simple representation to classify examples. It is a supervised machine learning where data is continuously divided according to a certain parameter.

A decision tree consists of:
- Nodes: test the value of a certain attribute.
- Borders / branch: corresponds to the result of a test and connects to the next node or sheet.
- Sheet nodes: terminal nodes that predict the result (represent class labels or class distribution).
![Parts-of-a-Decision-Tree](https://i.imgur.com/eUeEGCU.jpg)


*To understand the concept of Decision Tree, consider the previous example. Let's say you want to predict whether a person is fit or not, given their information such as age, eating habits, physical activity, etc. Decision nodes are questions like 'What is the age?', 'Do you exercise?', 'Do you eat a lot of pizzas? And the sheets represent results as 'fit' or 'not fit'*

There are two main types of decision trees:
- Classification trees.
What we have seen before is an example of a classification tree, where the result was a variable like 'fit' or 'not fit'. Here the decision variable is categorical / discrete.
Such a tree is constructed through a process known as binary recursive partition. This is an iterative process of dividing data into partitions and then dividing them further into each of the branches.

- Regression trees.
Decision trees where the objective variable can take continuous values ​​(typically real numbers) are called regression trees. (for example, the price of a house or the length of a patient's stay in a hospital)