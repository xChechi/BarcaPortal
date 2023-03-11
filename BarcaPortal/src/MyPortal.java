import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.TextField;


public class MyPortal extends JFrame {

	/** https://github.com/xChechi/BarcaPortal.git
	 *  git@github.com:xChechi/BarcaPortal.git 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField usernameField, emailField;
	private JPasswordField passwordField, repeatPasswordField;
	private JLabel usernameLabel, emailLabel, passwordLabel, repeatPasswordLabel, rightTitle;
	private Button button;
	Dashboard dashboard;
	Image image, image2;
	
	public static void main(String[] args) {
		//Видях, че е добра практика и май така се прави да се облича с този EventQueue, защото така изчаква всичко да зареди 
		//при по-големи проекти и тогава рендерира нашия фрейм. 234
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					MyPortal frame = new MyPortal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
		
	public MyPortal() {
		
		setResizable(false);
		this.setTitle("FC Barcelona Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("images/barcaLogo.png");
        
		try {
			image = ImageIO.read(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.setIconImage(image);
		//ImageIcon logo = new ImageIcon("barcaLogo.png");
		//this.setIconImage(logo.getImage());
		
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBounds(0, 0, 420, 550);
		contentPane.add(leftPanel);
		
		//ClassLoader classLoader2 = getClass().getClassLoader();
        InputStream inputStream2 = classLoader.getResourceAsStream("images/logo.png");
        
		try {
			image2 = ImageIO.read(inputStream2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ImageIcon icon = new ImageIcon(image2);
        Image mainLogo = icon.getImage();
        
        
        //Това дето го открихме вчера за фитването на снимки в даден прозорец. В случая вместо frame.getWidht направо съм сложил размерите му.      
        JLabel labelLogo = new JLabel(new ImageIcon(mainLogo.getScaledInstance(400, 550, Image.SCALE_SMOOTH)));
        leftPanel.add(labelLogo);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(430, 0, 344, 550);
		rightPanel.setBackground(Color.WHITE);
		contentPane.add(rightPanel);
		
		button = new Button("SIGN IN");
		button.setFont(new Font("Gotham Book", Font.BOLD, 14));
		button.setForeground(new Color(255, 250, 250));
		button.setBackground(new Color(162, 49, 93));
		button.setBounds(22, 434, 300, 34);
		rightPanel.setLayout(null);
		rightPanel.add(button);
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String email = emailField.getText();
				String name = usernameField.getText();
                String password = new String(passwordField.getPassword()); //с JPassword и getPassword() правиш да е с **** и после да вземеш инфото
                String confirmPassword = new String(repeatPasswordField.getPassword());
				
				if (isValidEmail(email) && password.equals(confirmPassword) && e.getSource() == button) {
					
					//Това е последователноста - 1-во създаваш новия прозорец, после затваряш текущия с dispose() и накрая правиш видим новия		
					dashboard = new Dashboard();
					dispose();
					dashboard.setVisible(true);
				}
				
				
				
				
				//Валидация дали имейла е в правилен формат. Функцията за проверка е отделена за по пригледно
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(MyPortal.this,
                            "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
				
				
                
                //Валидация дали данните в двете полета за парола и потвърди парола съвпадат
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(MyPortal.this,
                            "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                //Информативен поп-ъп, че е прибрало инфото от формата
                if(isValidEmail(email) && password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(MyPortal.this,
                        "Your name is " + name + " with email: " + email + " and your hidden password is " + password,
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE);
				}
			}
				
		});
		
		
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setFont(new Font("Gotham Narrow Thin", Font.PLAIN, 20));
		repeatPasswordField.setBounds(22, 364, 300, 34);
		rightPanel.add(repeatPasswordField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Gotham Narrow Thin", Font.PLAIN, 20));
		passwordField.setBounds(22, 294, 300, 34);
		rightPanel.add(passwordField);
		
		emailField = new TextField();
		emailField.setFont(new Font("Gotham Narrow Thin", Font.PLAIN, 20));
		emailField.setBounds(22, 224, 300, 34);
		rightPanel.add(emailField);
		
		usernameField = new TextField();
		usernameField.setFont(new Font("Gotham Narrow Thin", Font.PLAIN, 20));
		usernameField.setBounds(22, 154, 300, 34);
		rightPanel.add(usernameField);
		
		repeatPasswordLabel = new JLabel("REPEAT PASSWORD");
		repeatPasswordLabel.setFont(new Font("Gotham Book", Font.BOLD, 12));
		repeatPasswordLabel.setBounds(22, 346, 153, 15);
		rightPanel.add(repeatPasswordLabel);
		
		passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Gotham Book", Font.BOLD, 12));
		passwordLabel.setBounds(22, 273, 153, 15);
		rightPanel.add(passwordLabel);
		
		emailLabel = new JLabel("EMAIL");
		emailLabel.setFont(new Font("Gotham Book", Font.BOLD, 12));
		emailLabel.setBounds(22, 203, 153, 15);
		rightPanel.add(emailLabel);
		
		usernameLabel = new JLabel("USERNAME");
		usernameLabel.setFont(new Font("Gotham Book", Font.BOLD, 12));
		usernameLabel.setBounds(22, 137, 153, 15);
		rightPanel.add(usernameLabel);
		
		rightTitle = new JLabel("FC Barcelona Portal");
		rightTitle.setHorizontalAlignment(SwingConstants.CENTER);
		rightTitle.setFont(new Font("Gotham Book", Font.PLAIN, 22));
		rightTitle.setBounds(22, 69, 300, 27);
		rightPanel.add(rightTitle);
		
		
	}
		//Функцията за валидация на имейл с използване на Regular Expression (RegEx)
		private boolean isValidEmail(String email) {
        
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            	"[a-zA-Z0-9_+&*-]+)*@" +
                            	"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            	"A-Z]{2,7}$";

        return email.matches(emailRegex);
		}
}
