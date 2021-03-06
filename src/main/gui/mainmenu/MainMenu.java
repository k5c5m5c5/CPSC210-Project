package gui.mainmenu;

import gui.PlayButtonSound;
import model.Closet;
import model.StyleBoard;
import persistence.Json;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// GUI containing text fields for account information and buttons for account actions in the Main Menu
public class MainMenu extends JFrame {
    public static final int MENU_FRAME_HEIGHT = 340;
    public static final int MENU_FRAME_WIDTH = 670;
    public static final int MENU_BUTTON_INITIAL_X = 35;
    public static final int MENU_BUTTON_INCREMENT_X = 160;
    public static final int MENU_BUTTON_Y = 210;
    public static final int MENU_BUTTON_WIDTH = 120;
    public static final int MENU_BUTTON_HEIGHT = 60;
    public static final String MAIN_MENU_FONT_STYLE = "Comic Sans MS";

    public static JFrame menuFrame;
    public static JTextField usernameInfo;
    public static JTextField passwordInfo;

    public static JButton loginButton;
    public static JButton registerButton;
    public static JButton deleteButton;
    public static JButton quitButton;

    public static Closet myCloset = new Closet();
    public static StyleBoard myStyleBoard = new StyleBoard();

    private ListenForMenuButton listenLogin = new ListenForMenuButton();
    private ListenForMenuButton listenRegister = new ListenForMenuButton();
    private ListenForMenuButton listenDelete = new ListenForMenuButton();
    private ListenForMenuButton listenQuit = new ListenForMenuButton();


    public static void main(String[] args) {
        new MainMenu();
    }

    public MainMenu() {

        try {
            Json.userList = Json.parseUserInfo("User");
        } catch (IOException e) {
            System.out.println("Could not find user information");
            e.printStackTrace();
        }

        makeFrameSettings();
        JPanel menuPanel = makeMenuPanel();

        makeWelcomeLabel(menuPanel);

        makeInputLabel(menuPanel, "Username:", 35, -50);
        makeInputLabel(menuPanel, "Password:", 42, 0);

        usernameInfo = makeUserTextField(menuPanel);
        passwordInfo = makePassTextField(menuPanel);


        loginButton = makeMenuButton(menuPanel, "Login", MENU_BUTTON_INITIAL_X, MENU_BUTTON_HEIGHT);
        registerButton = makeMenuButton(menuPanel, "Register", MENU_BUTTON_INITIAL_X + MENU_BUTTON_INCREMENT_X,
                MENU_BUTTON_HEIGHT);
        deleteButton = makeMenuButton(menuPanel, "Delete",
                MENU_BUTTON_INITIAL_X + 2 * MENU_BUTTON_INCREMENT_X, MENU_BUTTON_HEIGHT);
        quitButton = makeMenuButton(menuPanel, "Quit",
                MENU_BUTTON_INITIAL_X + 3 * MENU_BUTTON_INCREMENT_X, 60);

        addActionListenerToMenuButtons();
        addShutDownSound();


        menuFrame.setVisible(true);
    }

    private void addShutDownSound() {
        menuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PlayButtonSound.playSound("shutdown.wav");
                menuFrame.dispose();
            }
        });
    }


    private void addActionListenerToMenuButtons() {
        loginButton.addActionListener(listenLogin);
        registerButton.addActionListener(listenRegister);
        deleteButton.addActionListener(listenDelete);
        quitButton.addActionListener(listenQuit);
    }

    private void makeWelcomeLabel(JPanel menuPanel) {
        JLabel welcomeLabel = new JLabel("Welcome to your Digital Closet!");
        welcomeLabel.setFont(new Font(MAIN_MENU_FONT_STYLE, Font.PLAIN, 30));
        welcomeLabel.setBounds(MENU_BUTTON_WIDTH, 0, 600, 100);
        menuPanel.add(welcomeLabel);
    }

    private JButton makeMenuButton(JPanel menuPanel, String login, int menuButtonInitialX, int menuButtonHeight) {
        JButton menuButton = new JButton(login);
        menuButton.setBounds(menuButtonInitialX, MENU_BUTTON_Y, MENU_BUTTON_WIDTH, menuButtonHeight);
        menuButton.setFont(new Font(MAIN_MENU_FONT_STYLE, Font.PLAIN, 20));
        menuPanel.add(menuButton);
        return menuButton;
    }

    private JTextField makePassTextField(JPanel thePanel) {
        JTextField passTextField = new JPasswordField("", 15);
        passTextField.setBounds(194, 135, 300, 35);
        passTextField.setFont(new Font(MAIN_MENU_FONT_STYLE, Font.PLAIN, 20));
        thePanel.add(passTextField);

        return passTextField;
    }

    private JTextField makeUserTextField(JPanel thePanel) {
        JTextField userTextField = new JTextField("", 15);
        userTextField.setBounds(194, 85, 300, 35);
        userTextField.setFont(new Font("Comic San MS", Font.PLAIN, 20));
        thePanel.add(userTextField);
        return userTextField;
    }

    private void makeInputLabel(JPanel thePanel, String s, int x, int y) {
        JLabel userLabel = new JLabel();
        userLabel.setText(s);
        userLabel.setBounds(x, y, 300, 300);
        userLabel.setFont(new Font(MAIN_MENU_FONT_STYLE, Font.BOLD, 30));
        thePanel.add(userLabel);
    }

    private JPanel makeMenuPanel() {
        JPanel thePanel = new JPanel();
        thePanel.setLayout(null);
        menuFrame.add(thePanel);
        return thePanel;
    }

    private void makeFrameSettings() {
        menuFrame = new JFrame();
        menuFrame.setSize(MENU_FRAME_WIDTH, MENU_FRAME_HEIGHT);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int frameXPos = (dim.width / 2) - (menuFrame.getWidth() / 2);
        int frameYPos = (dim.height / 2) - (menuFrame.getHeight() / 2);

        menuFrame.setLocation(frameXPos, frameYPos);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setTitle("Main Menu");
    }

    public JTextField getUsernameInfo() {
        return usernameInfo;
    }

    public JTextField getPasswordInfo() {
        return passwordInfo;
    }
}

