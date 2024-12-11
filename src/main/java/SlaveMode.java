
import java.io.IOException;
import java.net.*;

public class SlaveMode {
    private DatagramSocket datagramSocket;
    private byte[] buffer;

    public SlaveMode(int originalPort, int number){
        InetAddress address = getAddress();
        if(address == null){
            closeServer();
        }
        createServerInSlaveMode();
        sendFromSlaveMode(String.valueOf(number), address, originalPort);
        closeServer();
    }

    private InetAddress getAddress(){
        try {
            BroadcastAddress broadcastAddress = new BroadcastAddress(InetAddress.getLocalHost().getAddress());
            return InetAddress.getByAddress(broadcastAddress.getBroadcastAddress());
        } catch (IOException e){
            System.out.println("Error getting master host address");
        }
        return null;
    }

    private void createServerInSlaveMode(){
        try {
            System.out.println("Creating server in slave mode");
            datagramSocket = new DatagramSocket();
            System.out.println("Server created in slave mode");
        } catch (SocketException e) {
            System.out.println("Error creating server in slave mode" + e.getMessage());
            System.exit(1);
        }
    }

    private void sendFromSlaveMode(String message, InetAddress originalAddress , int originalPort){
        try {
            buffer = message.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, originalAddress, originalPort);
            datagramSocket.send(datagramPacket);
            System.out.println("Message sent from slave mode");
        } catch (IOException e) {
            System.out.println("Error sending message in slave mode " + e.getMessage());
        }
    }

    private void closeServer(){
        try{
            if(datagramSocket != null && !datagramSocket.isClosed()){
                datagramSocket.close();
                System.out.println("Server in slavic mode is closed");
            }
        } catch (Exception e){
            System.out.println("Error closing server in slavic mode " + e.getMessage());
        }
        System.out.println("Server in slavic mode is closed");
        System.exit(0);
    }
}
