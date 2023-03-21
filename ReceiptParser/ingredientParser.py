import csv

if __name__ == '__main__':
    with open('data/ingredients.csv', newline='') as csvfile:
        reader = csv.DictReader(csvfile)

        ingredients = []
        metrics = []

        repeats = []
        for row in reader:

            if not (row['Метрика'] in metrics):  # O(n^2)
                metrics.append(row['Метрика'].lower())
            if not (row['Название'] in ingredients):  # O(n^2)
                ingredients.append(row['Название'].lower())
            else:
                repeats.append(row['Название'])   #duplicates

        print("Повторы: ")

        for ingredientParser in repeats:
            print(" " + ingredientParser)

    # print(ingredients)
    # print(metrics)

    ingredients.sort()

    sql_request_ingredient = "INSERT INTO ingredients(name) VALUES \n"
    sql_request_metrics = "INSERT INTO metrics(name) VALUES \n"

    for ingredient in ingredients:
        sql_request_ingredient += f"(\'{ingredient}\'),\n"

    sql_request_ingredient = \
        sql_request_ingredient[:len(sql_request_ingredient)]

    for metric in metrics:
        sql_request_metrics += f"(\'{metric}\'),\n"

    sql_request_ingredient = \
        sql_request_ingredient[:len(sql_request_ingredient) - 2] + " ON CONFLICT DO NOTHING;"
    sql_request_metrics = \
        sql_request_metrics[:len(sql_request_metrics) - 2] + " ON CONFLICT DO NOTHING;"

    file_writer = open("sql-scripts/ingredients.sql", 'w')
    file_writer.write(sql_request_ingredient)

    file_writer = open("sql-scripts/metrics.sql", 'w')
    file_writer.write(sql_request_metrics)
