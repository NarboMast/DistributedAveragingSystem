
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;

public class MasterMode {
    public static ArrayList<Integer> numbers;
    private static DatagramPacket datagramPacket = null;

    private final byte[] buffer = new byte[256];

    public MasterMode(int number) {
        datagramPacket = new DatagramPacket(buffer, buffer.length);
        numbers = new ArrayList<>();
        HandleValue.handleValue(number);
        receive();
    }

    public void receive(){
        while(true){
            try {
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                UdpServer.datagramSocket.receive(datagramPacket);
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                if(ValidateParameters.parseStringToInt(messageFromClient)){
                    HandleValue.handleValue(Integer.parseInt(messageFromClient));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage() + "Exception in \"UdpServer.receive\" method");
                System.out.println("Breaking receive loop...");
                break;
            }
        }
    }

    public static void send(String message) {
        try {
            InetAddress ip = datagramPacket.getAddress();
            BroadcastAddress broadcastAddress = new BroadcastAddress(ip.getAddress());

            int port = datagramPacket.getPort();
            byte[] messageBytes = message.getBytes();
            datagramPacket = new DatagramPacket(messageBytes, messageBytes.length, InetAddress.getByAddress(broadcastAddress.getBroadcastAddress()), port);
            UdpServer.datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "Exception in \"UdpServer.send\" method");
        }
    }

    public static void closeServer() {
        try {
            if (UdpServer.datagramSocket != null && !UdpServer.datagramSocket.isClosed()) {
                UdpServer.datagramSocket.close();
                System.out.println("Server socket closed.");
            }
        } catch (Exception e) {
            System.out.println("Error closing the server socket: " + e.getMessage());
        }
        System.out.println("Server terminated.");
        System.exit(0);
    }
}
