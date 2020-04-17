import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class ClientWindow {
    private JFrame frame;
    private Client client;
    private JTextField textFieldGlobal;
    private static final int PORT_ID = 5555;
    private static JTextArea textArea = new JTextArea();

    public ClientWindow() {
        initialization();
        String name = JOptionPane.showInputDialog("Enter name");
        client = new Client(name, "localHost", PORT_ID);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    ClientWindow window = new ClientWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialization() {
        frame = new JFrame();
        frame.setTitle("Chat registration");
        frame.setBounds(100,100,450,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0,0));

        JTextArea textArea= new JTextArea();
        frame.getContentPane().add(textArea, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));

        textFieldGlobal = new JTextField();
        panel.add(textFieldGlobal);
        textFieldGlobal.setColumns(50);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args) {
                if (!textFieldGlobal.getText().equals("")) {
                    client.send(textFieldGlobal.getText());
                    textFieldGlobal.setText("");
                }
            }
        });
        panel.add(btnSend);

        frame.setLocationRelativeTo(null);
    }

    public static void printToConsole(String message) {
        textArea.setText(textArea.getText() + message + "\n");
    }

    private static String decrypt(String str) {
        /*AtomicReference<String> value = new AtomicReference<>();
        Single.just(str)
            .flatMap(v -> )*/
        return null;
    }
}
