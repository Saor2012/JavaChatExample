import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private String name;
    private static boolean isRunnig;

    public Client(String name, String address, int port) {
        try {
            this.address = InetAddress.getByName(address);
            this.port = port;
            this.name = name;

            socket = new DatagramSocket();
            isRunnig = true;
            listener();
            send("\\con:" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //send("\\con:jacob");
    }

    public void send (String message) {
        try {
            if (!message.startsWith("\\")) {
                message = name + ": " + message;
            }

            message += "\\n";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.send(packet);
            System.out.println("Send message to" + address.getHostAddress() + ":" + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listener() {
        Thread listenerThread = new Thread("Chat listener") {
            public void run() {
                try {
                    while (isRunnig) {
                        byte[] data = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        socket.receive(packet);

                        String message = new String(data);
                        message = message.substring(0, message.indexOf("\\n"));

                        if (!isCommand(message, packet)) {
                            ClientWindow.printToConsole(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        listenerThread.run();
    }

    public static boolean isCommand(String message, DatagramPacket packet) {
        if (message.startsWith("\\con:")) {

            return true;
        }

        return false;
    }
}
