
class Students:

    def __init__(self, first_name, last_name, gpa, major, faculty_advisor):
        self.first_name = first_name
        self.last_name = last_name
        self.gpa = gpa
        self.major = major
        self.faculty_advisor = faculty_advisor


    def get_first_name(self):
        return self.first_name

    def get_last_name(self):
        return self.last_name

    def get_gpa(self):
        return self.gpa

    def get_major(self):
        return self.major

    def get_faculty_advisor(self):
        return self.faculty_advisor

    def student_info(self):
        return (self.get_first_name(), self.get_last_name(),self.get_gpa(), self.get_major(), self.get_faculty_advisor())
