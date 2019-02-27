import sqlite3
from Students import Students
import databaseFunctions

program_start = 0

conn = sqlite3.connect('Students.db')
c = conn.cursor()

print ("Welcome to the Student Portal")
print ("Please type in your option, then press the enter key")
print ("1: Display all the Students in the Database. ")
print ("2: Select a Student Based on either GPA, Major, or Advisor.")
print ("3: Add a Student to the Database.")
print ("4: Delete a Student to the Database.")
print ("5: Update a Students Major or the Students Advisor.")
print ("6: Exit.")

while (program_start == 0):
    print ("Please Enter an Input")
    user_input_menu = raw_input()

    if (user_input_menu == '1'):
        print ("Here is the Student Table")
        databaseFunctions.SelectAllFunction(c)

    elif(user_input_menu == '2'):
        print ("To search for GPA, enter 1")
        print ("To search for Major, enter 2")
        print ("To search for Advisor, enter 3")
        search_input = 0
        while (search_input == 0):
            howFind = raw_input("What parameter would you like to search for: ")

            if (howFind == '1'):
                user_gpa = raw_input("What is the GPA you are searching: ")
                print (databaseFunctions.SelectGPA(c, user_gpa))
                search_input = 1

            elif (howFind == '2'):
                user_major = raw_input("What is the Major you are searching: ")
                print (databaseFunctions.SelectMajor(c, user_major))
                search_input = 1

            elif(howFind == '3'):
                user_advisor = raw_input("Who is the Advisor you are searching: ")
                databaseFunctions.SelectAdvisor(c, user_advisor)
                search_input = 1

            else:
                print("Your input was incorrect, please try again.")
                search_input = 0

    elif (user_input_menu == '3'):
        databaseFunctions.CreateStudent(c)

    elif (user_input_menu == '4'):
        print("Please enter the Student's ID number to delete their record")
        user_delete = raw_input("Enter Here: ")
        databaseFunctions.DeleteRecord(c, user_delete)

    elif (user_input_menu == '5'):
        print("Please enter the ID number Student you want to update")
        user_ID = raw_input("Enter Here: ")
        new_major = raw_input("What is the new major? Enter Here: ")
        new_advisor = raw_input("Who is the new advisor? Enter Here: ")
        databaseFunctions.UpdateStudentMajor(c, new_major, user_ID)
        databaseFunctions.UpdateStudentAdvisor(c, new_advisor, user_ID)

    elif (user_input_menu == '6'):
        print ("Thank you for using the Database.")
        print ("Goodbye")
        program_start = 1

    else:
        print ("Your input was incorrect. Please try again.")
        program_start = 0


    conn.commit() #comits the change and sends the record


