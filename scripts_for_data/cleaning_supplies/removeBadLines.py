with open('out5.csv', 'w') as out_file:
    with open('out4.csv', 'r') as in_file:
        for line in in_file:
            raw = line.strip('\n')
            if(raw.find(",,") > 0):
                continue
            else:
                out_file.write(raw + '\n')
