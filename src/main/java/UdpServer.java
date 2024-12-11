
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    public static DatagramSocket datagramSocket = null;

    public static boolean createServer(int port) {
        try{
            System.out.println("Trying create a server at port: " + port);
            datagramSocket = new DatagramSocket(port);
            System.out.println("Server started at port " + port);
            return true;
        }catch (SocketException e) {
            System.out.println("Server could not start at port " + port);
            return false;
        }
    }
}
