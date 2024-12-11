public class DAS {
    public static boolean mode;
    public static int port;
    public static int number;

    public static void assignParameters(String[] args){
        try {
            int[] parameters = ValidateParameters.parseParameters(args);
            port = parameters[0];
            number = parameters[1];
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Closing program...");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        assignParameters(args);
        System.out.println(port + " " + number);
        mode = UdpServer.createServer(port);

        if(mode){
            System.out.println("Master mode");
            new MasterMode(number);
        } else {
            new SlaveMode(port, number);
        }
    }
}
