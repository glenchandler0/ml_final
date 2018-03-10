package personal.ml_final;

import android.os.AsyncTask;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

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

        //Initialize socket here
        try {
            //Something like this
            byte[] dgram_bytes = new String("Android Test!").getBytes();
            byte[] ipAddr = new byte[] { 127, 21, 97, 6 };
//            byte[] address = new String("172.21.97.139").getBytes();
            datagramSocket = new DatagramSocket(15000, InetAddress.getByAddress(ipAddr));
            datagramPacket = new DatagramPacket(dgram_bytes, dgram_bytes.length);
        }catch(Exception e)
        {
            Log.e("There was an error", " initializing udp socket!");
            e.printStackTrace();
            return;
        }

        sp = new SendPackets();
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

        public SendPackets()
        {
            ;
        }

        protected Integer doInBackground(WirelessParams... params) {

            int i;
            num_packets = 10000;
            for(i = 0; i < num_packets; i++)
            {
                //Do packet stuff

                try {
                    Thread.sleep(10,0);
                }catch(Exception e)
                {
                    Log.e("THREAD SLEEP", " CRASHED");
                }
                if(i % 100 == 0)
                {
                    try
                    {
                        datagramSocket.send(datagramPacket);
                    }catch(Exception e)
                    {
                        Log.e("There was an error ", "sending packet!");
                        e.printStackTrace();
                    }
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
