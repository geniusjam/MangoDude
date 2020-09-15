package cf.jammy.mangodude;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame{
    private JPanel contentPane;

    private JTextField usernameFieldLogIn;
    private JPasswordField passwordFieldLogIn;
    private JButton buttonLogin;

    private JTextField usernameFieldSignUp;
    private JPasswordField passwordFieldSignUp;
    private JPasswordField confirmPassFieldSignUp;
    private JButton buttonSignUp;


    public LoginFrame(){
        setBounds(450, 200, 1000, 700);
        setTitle("MangoDude Login");
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBounds(10, 10, 500, 500);

        JLabel login_label = new JLabel("Log In");
        login_label.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        login_label.setBounds(150, 15, 500, 100);
        contentPane.add(login_label);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        usernameLabel.setBounds(50, 165, 195, 50);
        contentPane.add(usernameLabel);

        usernameFieldLogIn = new JTextField();
        usernameFieldLogIn.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        usernameFieldLogIn.setBounds(240, 165, 200, 50);
        contentPane.add(usernameFieldLogIn);
        usernameFieldLogIn.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        passwordLabel.setBounds(50, 350, 195, 50);
        contentPane.add(passwordLabel);

        passwordFieldLogIn = new JPasswordField();
        passwordFieldLogIn.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        passwordFieldLogIn.setBounds(240, 350, 200, 50);
        contentPane.add(passwordFieldLogIn);

        buttonLogin = new JButton("Login");
        buttonLogin.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        buttonLogin.setBounds(145, 500, 160, 70);
        contentPane.add(buttonLogin);

        // SIGN UP STUFF

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        signUpLabel.setBounds(650, 15, 500, 100);
        contentPane.add(signUpLabel);

        JLabel usernameLabel2 = new JLabel("Username:");
        usernameLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        usernameLabel2.setBounds(550, 165, 195, 50);
        contentPane.add(usernameLabel2);

        usernameFieldSignUp = new JTextField();
        usernameFieldSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        usernameFieldSignUp.setBounds(740, 165, 200, 50);
        contentPane.add(usernameFieldSignUp);
        usernameFieldSignUp.setColumns(10);

        JLabel password_label2 = new JLabel("Password:");
        password_label2.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        password_label2.setBounds(550, 265, 195, 50);
        contentPane.add(password_label2);

        passwordFieldSignUp = new JPasswordField();
        passwordFieldSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        passwordFieldSignUp.setBounds(740, 265, 200, 50);
        contentPane.add(passwordFieldSignUp);

        JLabel passwordLabel3 = new JLabel("Password:");
        passwordLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        passwordLabel3.setBounds(550, 350, 195, 50);
        contentPane.add(passwordLabel3);

        confirmPassFieldSignUp = new JPasswordField();
        confirmPassFieldSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        confirmPassFieldSignUp.setBounds(740, 350, 200, 50);
        contentPane.add(confirmPassFieldSignUp);

        buttonSignUp = new JButton("Sign Up");
        buttonSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        buttonSignUp.setBounds(645, 500, 160, 70);
        contentPane.add(buttonSignUp);

        setVisible(true);

        buttonLogin.addActionListener((event) -> {
            String u = usernameFieldLogIn.getText();
            String pw = passwordFieldLogIn.getText();

            if(pw.length() < 4 || pw.length() > 32) {
                JOptionPane.showMessageDialog(this, "Passwords cannot be shorter than 4 or longer than 32 characters.");
                return;
            }

            if(!u.matches("^[a-zA-Z0-9_]{2,32}$")) {
                JOptionPane.showMessageDialog(this, "Usernames must only contain alphanumeric letters and number and cannot contain less than 2 or more than 32 characters.");
                return;
            }

            Main.prefs.put("username", u);
            Main.prefs.put("password", pw);
            setVisible(false);
            Main.connect(u, pw);
        });

        buttonSignUp.addActionListener((event) -> {
            String u = usernameFieldSignUp.getText();
            String pw = passwordFieldSignUp.getText();
            String pwAgain = confirmPassFieldSignUp.getText();

            if(!pw.equals(pwAgain)) {
                JOptionPane.showMessageDialog(this, "The passwords you've entered don't match!");
                return;
            }

            if(pw.length() < 4 || pw.length() > 32) {
                JOptionPane.showMessageDialog(this, "Passwords cannot be shorter than 4 or longer than 32 characters.");
                return;
            }

            if(!u.matches("^[a-zA-Z0-9_]{2,32}$")) {
                JOptionPane.showMessageDialog(this, "Usernames must only contain alphanumeric letters and number and cannot contain less than 2 or more than 32 characters.");
                return;
            }

            new Thread((() -> {
                try {
                    buttonSignUp.setEnabled(false);
                    buttonLogin.setEnabled(false);
                    String resp = ConnectionHandler.signUp(u, pw);
                    if(resp.equals("OK")) {
                        Main.prefs.put("username", u);
                        Main.prefs.put("password", pw);
                        JOptionPane.showMessageDialog(this, "Successful! Logging in...");
                        setVisible(false);
                        Main.connect(u, pw);
                    } else {
                        JOptionPane.showMessageDialog(this, resp);
                    }
                    buttonSignUp.setEnabled(true);
                    buttonLogin.setEnabled(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    e.printStackTrace();
                }
            })).start();
        });
    }
}