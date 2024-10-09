//import java.awt.*;
//import java.sql.*;
////public class Main {
//    public static void main(String[] args) {
//        int n = 5;
//        int a=1;
//        for (int i =1; i <= n; i++) {
//            for (int j = 1; j<=n-i; j++) {
//                System.out.print(j);
//              //  a=a+1;
//            }
//           // a=a+1;
//            System.out.println();
//        }
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

class UserRegistrationGUI extends JFrame implements ActionListener {
    private JTextField nameField, passwordField, dobField, addressField;
    private JTextArea contactListArea;
    private JButton registerButton, addContactButton, deleteContactButton, updateContactButton, saveContactButton;
    private Map<String, String> users;
    private Connection connection;

    public UserRegistrationGUI() {
        super("User Registration");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2));

        users = new HashMap<>();
        connectToDatabase();

        JPanel registrationPanel = new JPanel(new GridLayout(0, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel dobLabel = new JLabel("DOB:");
        dobField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        registrationPanel.add(nameLabel);
        registrationPanel.add(nameField);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(dobLabel);
        registrationPanel.add(dobField);
        registrationPanel.add(addressLabel);
        registrationPanel.add(addressField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        buttonPanel.add(registerButton);

        add(registrationPanel);
        add(buttonPanel);

        JPanel contactPanel = new JPanel(new BorderLayout());
        JLabel contactLabel = new JLabel("pop");
        contactListArea = new JTextArea();
        contactListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contactListArea);
        JPanel contactButtonPanel = new JPanel(new GridLayout(0, 1));
        addContactButton = new JButton("Add");
        addContactButton.addActionListener(this);
        deleteContactButton = new JButton("Delete");
        deleteContactButton.addActionListener(this);
        updateContactButton = new JButton("Update");
        updateContactButton.addActionListener(this);
        saveContactButton = new JButton("Save");
        saveContactButton.addActionListener(this);

        contactButtonPanel.add(addContactButton);
        contactButtonPanel.add(deleteContactButton);
        contactButtonPanel.add(updateContactButton);
        contactButtonPanel.add(saveContactButton);

        contactPanel.add(contactLabel, BorderLayout.NORTH);
        contactPanel.add(scrollPane, BorderLayout.CENTER);
        contactPanel.add(contactButtonPanel, BorderLayout.EAST);

        add(contactPanel);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:contact");
            System.out.println("Connect");
            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users (name TEXT PRIMARY KEY, password TEXT, dob TEXT, address TEXT)";
            connection.createStatement().execute(createUserTableQuery);
        } catch (SQLException e) {
            System.out.println("khtm tata bye byee" + e.getMessage());
        }
    }

    public void registerUser(String name, String password, String dob, String address) {
        try {
            String insertUserQuery = "INSERT INTO users (name, password, dob, address) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, dob);
            preparedStatement.setString(4, address);
            preparedStatement.executeUpdate();
            System.out.println("register");
            System.out.println("Name: " + name);
            System.out.println("DoB: " + dob);
            System.out.println("Address: " + address);
        } catch (SQLException e) {
            System.out.println("khtm tata bye bye: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String name = nameField.getText();
            String password = passwordField.getText();
            String dob = dobField.getText();
            String address = addressField.getText();
            registerUser(name, password, dob, address);
        } else if (e.getSource() == addContactButton) {
        } else if (e.getSource() == deleteContactButton) {
        } else if (e.getSource() == updateContactButton) {
        } else if (e.getSource() == saveContactButton) {
        }
    }

    public static void main(String[] args) {
        UserRegistrationGUI userRegistrationGUI = new UserRegistrationGUI();
        userRegistrationGUI.setVisible(true);
    }
}