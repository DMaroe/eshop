### Hi, This is Dylan
## Welcome to my reflections for Advanced Programming Course Tutorials
Dylan Dahran Pribadi - 2106720872

# LAB01 - Reflection 1

Having not ever to touch the java programming language, experiencing 
it for the first time seems so new and unique to me.

I've tried to implement the code as clean as possible, but mistakes are inevitable.
My code is ran successfully, I might not implement the best practice but it works.

As I am concerned, so far through my lens I couldn't find mistakes that
causes my program to malfunction. Thus, I am very interested to hear words
from the teaching assistant team regarding my work. Thanks

# LAB01 - Reflection 2

1. TDD is a very new concept to me, I feel dumb at first but once I got the hang of it making tests is really fun.
- The number of unit tests to be written for a class varies, as far as I know there is no specific fixed number for the total of tests. But, usually developers implement test cases for normal cases, edge cases, and error conditions.
- There are a couple of analysis to say that our tests is "enough", such as test coverage analysis, boundary testing, test automation, etc.
- As far as I know, code coverage measures the proportion of code executed by tests, so percentages near 100% means that most code paths are exercised by tests. However, achieving 100% code coverage doesn't guarantee the absence of bugs or errors, since it is possible to have code paths that are unreachable or untestable.

2. Making a new functional test similar to the existing one may increase code cleanliness, but if not approached carefully there might be additional bugs happening.
- As of code quality, it is highly possible for it to decrease
- There are many potential clean code issues, I'll be naming a few such as Code Duplication (if the new test repeats setup procdeures, may cause maintenance overhead and inconsistencies), Violation of DRY Principle (stands for Don't Repeat Yourself, where we should avoid repetition), Low Cohesion (occurs when unrelated functionalities are combined within a single module or class), and many more issues...
- To improve the code I suggest us 2 major points, which are :
a. Code Reuse : do extraction of common setup procedures and instance variables into shared method or base classes (to avoid Code Duplication and DRY)
b. Focused Test Suites : separarate unrelated functionalities into distinct test suites (to prevent Low Choesion)

Thank you so much for your time and consideration, I wish you good health