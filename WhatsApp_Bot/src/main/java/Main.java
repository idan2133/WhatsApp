import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 700;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\עידן\\Documents\\Downloads\\chromedriver.exe");
        new Main();
    }

    public Main() {//מטודות חובה לפתיחת חלון גרפי
        this.setTitle("Whatsapp Web");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //image
        JLabel background;
        ImageIcon img = new ImageIcon("project.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 400, 700);
        this.add(background);
        background.setVisible(true);
        //font
        Font fn = new Font("Arial", Font.PLAIN, 18);
        //JLabel
        JLabel title = new JLabel("Click down here to open WhatsApp");
        title.setBounds(60, 50, 280, 100);
        this.add(title);
        title.setFont(fn);
        //ClickToWhatsApp
        JButton ClickToWhatsApp = new JButton("Open WhatsApp");
        ClickToWhatsApp.setBounds(90, 120, 220, 50);
        this.add(ClickToWhatsApp);
        ClickToWhatsApp.setFont(fn);
        repaint();
        //JTextField
        JTextField phoneText = new JTextField();
        phoneText.setBounds(80, 120, 250, 40);
        this.add(phoneText);
        phoneText.setFont(fn);
        phoneText.setVisible(false);

        JTextField send = new JTextField();
        send.setBounds(80, 200, 250, 40);
        this.add(send);
        send.setFont(fn);
        send.setVisible(false);
        //text when you login
//        JLabel TextWhenYouLogin = new JLabel("You successfully Logged in");
//        TextWhenYouLogin.setBounds(0, 50, 170, 100);
//        this.add(TextWhenYouLogin);
//        TextWhenYouLogin.setVisible(false);
        // Jlabel Title Text
        JLabel phoneNumber = new JLabel("Enter Your Phone Number");
        phoneNumber.setBounds(80, 80, 250, 50);
        this.add(phoneNumber);

        phoneNumber.setFont(fn);
        phoneNumber.setVisible(false);

        JLabel textMessage = new JLabel("Enter Your Message");
        textMessage.setBounds(80, 160, 250, 50);
        this.add(textMessage);
        textMessage.setFont(fn);
        textMessage.setVisible(false);

        JButton apply = new JButton("Apply");
        apply.setFont(fn);
        apply.setBounds(80, 320, 250, 50);
        this.add(apply);
        apply.setVisible(false);
        repaint();


        String url = "https://web.whatsapp.com/";

        ClickToWhatsApp.addActionListener((event) -> {
            ChromeDriver driver = new ChromeDriver();
            driver.get(url);
            driver.manage().window().maximize();
            while (true) if (driver.getPageSource().contains("menu")) break;
            ClickToWhatsApp.setVisible(false);
            title.setVisible(false);
//            TextWhenYouLogin.setVisible(true);
            driver.manage().window().minimize();
            JOptionPane.showMessageDialog(null, "You successfully logged in.", "Messenger", JOptionPane.PLAIN_MESSAGE);

            phoneNumber.setVisible(true);
            phoneText.setVisible(true);
            send.setVisible(true);
            textMessage.setVisible(true);
            apply.setVisible(true);


            apply.addActionListener((event_2) -> {
                if (phoneText.getText().length() < 10 && send.getText().length() < 1 || phoneText.getText().length() > 10 && send.getText().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please type a correct number & A text that contains at least 1 character ", "⛔ INVALID PHONE NUMBER & TEXT MESSAGE ⛔", JOptionPane.PLAIN_MESSAGE);
                    phoneText.setVisible(true);
                    send.setVisible(true);

                } else if (phoneText.getText().length() < 10 || phoneText.getText().length() > 10) {
                    JOptionPane.showMessageDialog(null, "Please type a correct number", "ERROR PHONE NUMBER", JOptionPane.PLAIN_MESSAGE);
                    phoneText.setVisible(true);
                } else if (send.getText().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please send a text that contains at least 1 character", "ERROR MESSAGE", JOptionPane.PLAIN_MESSAGE);
                    send.setVisible(true);
                } else {
                    //ifs
                    //phone number
                    driver.manage().window().maximize();
                    driver.get("https://web.whatsapp.com/send?phone=972" + phoneText.getText());
                    new Thread(() -> {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    WebElement input = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div.p3_M1 > div > div._13NKt.copyable-text.selectable-text"));
                    input.click();
                    input.sendKeys(send.getText());
                    input.sendKeys(Keys.ENTER);
                    driver.manage().window().minimize();
                }
                ;
            });

        });


    }
}