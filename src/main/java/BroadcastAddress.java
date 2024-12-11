public class BroadcastAddress {
    private final byte[] mask;
    byte[] broadcastAdd;

    public BroadcastAddress(byte[] ip){
        mask = new byte[]{(byte) 255, (byte) 255, (byte) 255, 0};
        broadcastAdd = new byte[4];
        setBroadcastAddress(ip);
    }

    private void setBroadcastAddress(byte[] ip){
        for(int i = 0; i < ip.length; i++){
            broadcastAdd[i] = (byte)(ip[i] | ~mask[i]);
        }
    }

    public byte[] getBroadcastAddress(){
        return broadcastAdd;
    }
}
