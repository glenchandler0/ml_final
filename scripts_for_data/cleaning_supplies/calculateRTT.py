import decimal
with open('out4.csv', 'w') as out_file:
    with open('out3.csv', 'r') as in_file:
        i = 0
        prev_epoch = 0.0
        for line in in_file:
            if i == 0:
                epoch_string = line[:20]
                prev_epoch = decimal.Decimal(epoch_string)
                i = i + 1
                continue

            epoch_string = line[:20]
            rest_string = line[20:]

            curr_time = decimal.Decimal(epoch_string)
            time_diff = curr_time - prev_epoch
            prev_epoch = curr_time

            rtt_string = str(time_diff) + rest_string
            # print(rtt_string + "\n")
            # print(line)
            #
            # print(time_diff)
            # print(prev_epoch)
            # print(epoch_string)
            #
            # print(rest_string)

            out_file.write(rtt_string)
