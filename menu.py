from databaseFunctions import databaseFunctions
from main import main_baby

db = databaseFunctions()
baby = main_baby()

program_start = 0

print ("Welcome to the Student Portal")
print ("Please type in your option, then press the enter key")
print ("1: Display all the Students in the Database. ")
print ("2: Select a Student Based on either GPA, Major, or Advisor.")
print ("2: Add a Student to the Database.")
print ("3: Delete a Student to the Database.")
print ("4: Update a Students Major or the Students Advisor.")
print ("5: Exit.")

user_input_menu = input()

while (program_start == 0):
    if (user_input_menu == '5'):
        print ("Thank you for using the Database.")
        print ("Goodbye")
        program_start = 1

    elif (user_input_menu == 1):
        print ("Here is the Student Table")
        db.SelectAllFunction(baby.c)

    else:
        print ("Your input was incorrect. Please try again.")
        program_start = 0
