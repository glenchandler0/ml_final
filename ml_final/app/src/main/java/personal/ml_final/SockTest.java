package personal.ml_final;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by gchandler on 3/9/18.
 */

public class SockTest
{
    WirelessParams params;
    SendPackets sp;
    int num_packets;
    DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;

    public SockTest()
    {
        //Maybe initialize wireless params here

        sp = new SendPackets(datagramSocket, datagramPacket);
    }
    public static class WirelessParams
    {

    }

    public int startSendPackets()
    {
        Log.i("About to run!", ":");
        if(!(sp.getStatus() == AsyncTask.Status.RUNNING))
            sp.execute(params);
        //sp.setLoop(true);
        return 0;
    }
    public int stopSendPackets()
    {
        Log.i("About to pause!", ":");
        sp.cancel(true);
        //sp.setLoop(false);
        return 0;
    }
    public int getProgress()
    {
        //Read from progress[i]
        return 0;
    }

    /* Private class for AsyncTask */
    private class SendPackets extends AsyncTask<WirelessParams, Double, Integer> {
        private boolean keepLooping;

        DatagramSocket datagramSocketIn;
        DatagramSocket datagramSocketOut;

        DatagramPacket datagramPacket;
        DatagramPacket rcvPacket;

        Socket socket;
        OutputStream outStream;

        public SendPackets(DatagramSocket ds, DatagramPacket dp)
        {
            try {
                //Something like this
                byte[] dgram_bytes = new String("Android Test!").getBytes();
                byte[] message = new byte[1024];

                datagramSocketIn = new DatagramSocket(15000);
                datagramSocketOut = new DatagramSocket(15000, InetAddress.getByName("192.168.2.172"));

                datagramPacket = new DatagramPacket(dgram_bytes, dgram_bytes.length, InetAddress.getByName("192.168.2.172"), 15000);
                rcvPacket = new DatagramPacket(message, message.length);

//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//
//                socket = new Socket(InetAddress.getByName("192.168.2.172"), 15000);
//                outStream = socket.getOutputStream();
            }catch(Exception e)
            {
                Log.e("There was an error", " initializing udp socket!");
                e.printStackTrace();
                return;
            }
            Log.e("Constructing send ", "packets!");
        }

        protected Integer doInBackground(WirelessParams... params) {

            byte[] outByte = new String("TCP test").getBytes();

            int i;
            num_packets = 100000;
            for(i = 0; i < num_packets; i++)
            {
                //Do packet stuff

                try {
                    Log.e(".", "Waiting!");
                    datagramSocket.receive(rcvPacket);
                    Log.e(".", "RECEIVED PACKET!");
                    datagramSocket.send(rcvPacket);
//                    datagramSocket.receive(datagramPacket);
//                    datagramSocket.send(datagramPacket);
//                    Log.e("received packet", "" + rcvPacket.getData().toString());
//                    outStream.write(outByte);
//                    Thread.sleep(5000,0);
                }catch(Exception e)
                {
                    Log.e("THREAD SLEEP", " CRASHED");
                    e.printStackTrace();
                    return -1;
                }
                if(i % 100 == 0)
                {
                    double temp = i * 1.0 / num_packets*1.0;
                    publishProgress(temp);
                }
            }

            return 0;
        }

        protected void onProgressUpdate(Double... percent) {
            //setProgressPercent(progress[0]);
            Log.e("Task " + 1.0 * percent[0], "% done");
            MainActivity.progressBar.setProgress((int)(percent[0]*100.0));
        }

        protected void onPostExecute(Integer result) {
            //showDialog("Downloaded " + result + " bytes");
        }

        //Setters / getters
        public void setLoop(boolean val)
        {
            keepLooping = val;
        }
    }
}
