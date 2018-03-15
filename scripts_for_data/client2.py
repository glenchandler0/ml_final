import socket
import time

myFile  = open("rtt.txt", "w")

UDP_IP = "192.168.3.225"
UDP_PORT = 15000

print "UDP target IP:", UDP_IP
print "UDP target port:", UDP_PORT

HYPER_PARAMS = "1.00, "

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # UDP
#sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
for i in range(0,5000):
    MESSAGE = str(i);
    sock.sendto(MESSAGE, (UDP_IP, UDP_PORT))
    startTime = time.time() * 1000

    print("Waiting..." + str(i) + "\n")
    tmp, address = sock.recvfrom(8)
    #print(address)
    endTime = time.time() * 1000

    res_str = MESSAGE + "," + str(endTime - startTime) + "\n"
    myFile.write(res_str)

    time.sleep(0.010);
print("done!")
myFile.close()

#See if you can make phone the server,
#Then isolate wireshark to just pakets leaving the phone
#Read the data of the packet to line up IDs
#with output coming from this file
#Read both files into jupyter seperately
