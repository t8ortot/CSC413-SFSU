# Lexer

A lexer program that tokenizes a fake coding language. The purpose of this project was to further the understanding of compilers and lexical analysis, as well as learn how to read and debug nearly completed code.

Learned how lexers operate and how to debug someone elses code.

Code provided:
- 99% of the source code was provided, to complete the assignment we only needed to add one line of code after finding where to insert it.

How to Run (assuming in Lexer directory):
- javac lexer/*.java lexer/setup/TokenSetup.java
- java lexer/Lexer (insert name of .x file here ex: "factorial.x")

How to Use:
- Specify the .x file you would like the lexer to tokenize as a parameter, as shown above. There are 6 different .x files to choose from in the Lexer directory.
- The program will output the tokenized format of the input code by stating the line, the left and right boundaries of each token, as well as the line number it is on. Then it will output the given input for comparison.