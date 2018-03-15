#For adding Distance, and Access category(0,1,2,3,4,5,6,7)
s = ",\"5.0\""#,"\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\""
with open('out2.csv', 'w') as out_file:
    with open('out1.csv', 'r') as in_file:
        for line in in_file:
            out_file.write(line.rstrip('\n') + s + '\n')
