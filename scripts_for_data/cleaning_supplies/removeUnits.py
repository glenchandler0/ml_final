with open('out3.csv', 'w') as out_file:
    with open('out2.csv', 'r') as in_file:
        for line in in_file:
            raw = line.rstrip('\n')
            new = raw.replace("\"", "")
            new = new.replace(" MHz", "")
            new = new.replace("MHz", "")
            new = new.replace(" dBm", "")
            new = new.replace("dBm", "")
            out_file.write(new + '\n')
