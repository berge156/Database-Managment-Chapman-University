from faker import Faker
import csv

faker = Faker()


def writeToCSV():
    with open(fileName, 'w') as csvfile:
        fieldnames = ['first_name', 'last_name', 'city', 'state', 'email', 'credit_card_number']

        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

        writer.writeheader()
        for i in range(int(numTuples)):
            writer.writerow(
                {
                    'first_name': faker.first_name(),
                    'last_name': faker.last_name(),
                    'city': faker.city(),
                    'state': faker.state(),
                    'email': faker.email(),
                    'credit_card_number': faker.credit_card_number()
                }
            )

if __name__ == '__main__':
    fileName = raw_input('Enter the name of the file you wish to create: ')
    fileName = fileName + '.csv'
    numTuples = raw_input('Enter the number of tuples you would like: ')
    writeToCSV()
