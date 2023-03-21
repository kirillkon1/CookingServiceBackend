import csv



if __name__ == '__main__':
    with open('data/categories.csv', newline='') as csvfile:
        reader = csv.DictReader(csvfile)


        request = "INSERT INTO receipt_category(name, type) VALUES \n"

        for row in reader:
            request += \
                f"('{row['название'].lower()}', '{row['тип']}'),\n"


        request = request[:-2] + " ON CONFLICT DO NOTHING;"

    file_writer = open("sql-scripts/categories.sql", 'w')
    file_writer.write(request)

