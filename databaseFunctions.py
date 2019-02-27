import sqlite3
from Students import Students


def SelectAllFunction(cursor):
    cursor.execute("SELECT * FROM Students")
    all_rows = cursor.fetchall()
    print(all_rows)

def SelectMajor(cursor, user_major):
    cursor.execute("SELECT * FROM Students WHERE Major = ?", (user_major,))
    all_rows = cursor.fetchall()
    print(all_rows)

def SelectGPA(cursor, user_gpa):
    cursor.execute("SELECT * FROM Students WHERE GPA = ?", (user_gpa,))
    all_rows = cursor.fetchall()
    print(all_rows)

def SelectAdvisor(cursor, user_advisor):
    cursor.execute("SELECT * FROM Students WHERE FacultyAdvisor = ? ", (user_advisor,))
    all_rows = cursor.fetchall()
    print(all_rows)

def CreateStudent(cursor):
    fname = raw_input("Enter First Name: ")
    lname = raw_input("Enter Last Name: ")
    gpa = raw_input("Enter GPA: ")
    major = raw_input("Enter Major: ")
    advisor = raw_input("Enter Faculty Advisor: ")

    stu = Students(fname, lname, gpa, major, advisor)
    cursor.execute("INSERT INTO Students('FirstName', 'LastName', 'GPA', 'Major', 'FacultyAdvisor')"
     "VALUES (?, ?, ?, ?, ?)", stu.student_info())

def DeleteRecord(cursor, user_delete):
    cursor.execute("DELETE FROM Students WHERE StudentId = " + user_delete)
    all_rows = cursor.fetchall()
    print(all_rows)

def UpdateStudentMajor(cursor, new_major, stud_id):
    cursor.execute("UPDATE Students SET Major = ? WHERE StudentId = ?",  (new_major, stud_id,))
    all_rows = cursor.fetchall()
    print(all_rows)

def UpdateStudentAdvisor(cursor, new_advisor, stud_id):
    cursor.execute("UPDATE Students SET FacultyAdvisor = ? WHERE StudentId = ?", (new_advisor, stud_id))
    all_rows = cursor.fetchall()
    print(all_rows)


