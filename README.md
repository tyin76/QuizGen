# My Personal Project

## Phase 0

Questions answered about the Personal Project below:
- What will the application do?
- Who will use it?
- Why is this project of interest to you?


The application that I will create for my personal project is a **quiz generator**. 
My application will be available to **anyone**. Anyone interested in studying, refining their knowledge,
or other quiz-related purposes will be able to use my application. 
The user will be able to add questions and the corresponding answers into the application. The user can also change, 
delete, save, load, view the questions and answers in the set, and quit the application from the main menu. 
The application will keep track of the user's score during the quiz and will present the user's final score 
on the end screen when the user finishes. If the user wants to quit the application and would like to use it at a 
later time, the application will save the user's current data and reload the program
with the saved data when the quiz is relaunched at a later time.

I am interested in making a quiz generator because I feel that I study better when I am presented with study problems/questions
in a quiz format. Rather than just reciting and trying to remember facts from my notes, I noticed that I learn better 
when I am actively involved
in a test-like format and create questions out of my notes. Many of my friends also share my preference of study habits and 
instead of creating a personal project that won't be touched again after this course, I believe that making a quiz generator
will be helpful to me in the future and hopefully many others that decide to use it. 


## User Stories

- As a user, I want to be able to add questions and answers to my set collection
- As a user, I want to be able to view the current questions and answers in my quiz set
- As a user, I want to be able to select a question or answer in my quiz set and change what it says
- As a user, I want to be able to delete a question from my quiz set
- As a user, I want to be able to load my question set from file
- As a user, I want to be able to save my question set to file

## Instructions For Grader
- **You can generate the first required action related to adding Xs to a Y** by going to the QuizGen tab located on the left hand side
- Fill in the three text fields that make up a new question
- Press the button labelled "Add" to add the question to the set
- 
- **You can generate the second required action related to adding Xs to a Y** by pressing the button labelled "Delete Question"
- Make sure there is at least one question in the set, otherwise a message will appear telling you to do so
- Select the index of the question you would like to delete
- 
- **You can generate a third action related to adding Xs to a Y** by pressing the button labelled "Change Question"
- Make sure there is at least one question in the set, otherwise a message will appear telling you to do so
- Select the index of the question that you would like to change and create a new question with the pop-up windows that appear
- 
- **You can generate a fourth action related to adding Xs to a Y** by pressing the button labelled "View Set"
- The set of questions will appear for viewing
- 
- **You can locate my visual component** by pressing the button labelled "Take Quiz"
- Make sure there is at least one question in the set, otherwise a message will appear telling you to do so
- Select the index of the question you would like to try
- When a question is answered correctly a green checkmark labelled "Correct!" will appear
- When a question is answered incorrectly a red X labelled "Incorrect!" will appear
- 
- **You can save the state of my application** by going to the QuizGen tab located on the left hand side
- Press the button labelled "Save"
- 
- **You can reload the state of my application** by going to the QuizGen tab located on the left hand side
- Press the button labelled "Load"
- Press the button labelled "View Set" to see the loaded in set

## Phase 4: Task 2
- Wed Aug 09 17:21:40 PDT 2023: A question was added to the Set!
- Wed Aug 09 17:21:49 PDT 2023: A question was added to the Set!
- Wed Aug 09 17:21:58 PDT 2023: A question was deleted!
- Wed Aug 09 17:22:10 PDT 2023: A question was changed!

## Phase 4: Task 3
If I had more time to work on my project, I would create more tab classes with each tab class having a single 
responsibility. My current program has only 3 tab classes, with the abstract Tab class not providing any implementations. The vast 
majority of the implementations is located in the QuizGenTab class that has partial or complete implementations for adding, viewing, changing, deleting, 
quizzing, saving, and loading. Obviously, the QuizGenTab class has associations with my model that provides implementations, 
but the QuizGenTab class has more than one responsibility which is poor design practice, and should be split into multiple
different classes. 

To refactor, I would split the QuizGenTab class into separate Add, View, Delete, Change, TakeQuiz, Save/Load classes.
Each class would have a single responsibility as per their name, resulting in greater organization and readability. Also, 
making changes to my application would be much easier, due to the greater organization and separation of responsibility.
Each class would only have access to fields and objects that they require, reducing the possibility of introducing a bug.

Overall, by creating classes that are each delegated one primary responsibility for the program, greater readability,
organization, debugging, and cleaner code is provided as a result.
















