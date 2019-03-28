import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagementSystem extends JFrame implements ActionListener {
	private String ROOTPIN = "123456";
	public Scooter[] scooterlist = new Scooter[15];//15 scooters
	public Docking_Station[] stationlist = new Docking_Station[3];//3 stations
	private JTextField userIDField;
	private JLabel screen;
	private JTextField PINField;
	private JButton buttonW;
	private JButton buttonE;
	private JButton eMADDChangeButton;
	private JButton nameChangeButton;
	private JButton payButton;
	private JButton addressChangeButton;
	private JPanel userLeftSidebar;
	private int GUIState;
	//private User currentUser;
	
	public ManagementSystem(String str, int stationseq[],int slotseq[]) {
		super(str);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		userLeftSidebar = new JPanel();
		userLeftSidebar.setLayout(new  GridLayout(4, 1) );
		userIDField = new JTextField("Input your ID here");
		PINField = new JTextField("Input your PIN here");
		buttonW = new JButton("click to login");
		buttonE = new JButton("EXIT");
		eMADDChangeButton = new JButton("change email address");
		nameChangeButton = new JButton("change name address");
		payButton = new JButton("pay my fine");
		addressChangeButton = new JButton("change address");
		userLeftSidebar.add(nameChangeButton);
		userLeftSidebar.add(addressChangeButton);
		userLeftSidebar.add(eMADDChangeButton);
		userLeftSidebar.add(payButton);
		buttonW.addActionListener(this);
		buttonE.addActionListener(this);
		eMADDChangeButton.addActionListener(this);
		nameChangeButton.addActionListener(this);
		payButton.addActionListener(this);
		buttonW.addActionListener(this);
		buttonE.addActionListener(this);
		this.getContentPane().add(BorderLayout.NORTH, userIDField);
		this.getContentPane().add(BorderLayout.CENTER, PINField);
		this.getContentPane().add(BorderLayout.SOUTH, buttonW);
		/*createStation();
		createScooter(stationseq,slotseq);
		this.setSize(400, 200);
		this.setVisible(true);*/
	}//seq is the station of separate scooter

	public ManagementSystem() {
		super("Management System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		userLeftSidebar = new JPanel();
		userLeftSidebar.setLayout(new GridLayout(4, 1));
		userIDField = new JTextField("Input your ID here");
		PINField = new JTextField("Input your PIN here");
		buttonW = new JButton("click to login");
		buttonE = new JButton("EXIT");
		eMADDChangeButton = new JButton("change email address");
		nameChangeButton = new JButton("change name address");
		payButton = new JButton("pay my fine");
		addressChangeButton = new JButton("change address");
		userLeftSidebar.add(nameChangeButton);
		userLeftSidebar.add(addressChangeButton);
		userLeftSidebar.add(eMADDChangeButton);
		userLeftSidebar.add(payButton);
		buttonW.addActionListener(this);
		buttonE.addActionListener(this);
		eMADDChangeButton.addActionListener(this);
		nameChangeButton.addActionListener(this);
		payButton.addActionListener(this);
		addressChangeButton.addActionListener(this);
		this.getContentPane().add(BorderLayout.NORTH, userIDField);
		this.getContentPane().add(BorderLayout.CENTER, PINField);
		this.getContentPane().add(BorderLayout.SOUTH, buttonW);
		createStation();
		int[] stationseq = {1,0,2,2,0,1,0,2,1,0,1,2,1,2,0};//range 0 to 2
		int[] slotseq =    {3,2,4,3,4,7,5,6,0,3,4,7,1,1,1};//range 0 to 7
		createScooter(stationseq,slotseq);//default constructor
		this.setSize(400, 200);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonW && GUIState == 0 && true
				/*check(userIDField.getText(), PINField.getText()*/){
			buttonW.setText("Pay my fine");
			userIDField.setText("Input the amount of fine you want to pay");
			GUIState = 1;
			this.getContentPane().removeAll();
			this.getContentPane().setLayout(new BorderLayout());
			screen = new JLabel("<html>Hello: " + "UserQMID<br>" + "FirstName: " + "UserFirstName<br>" + 
					"LastName: " + "UserLastName<br>" + "E-mail: " + "UserEMAILADD<br>" + "Usage: " + "0" );
			this.getContentPane().add(BorderLayout.CENTER, screen);
			this.getContentPane().add(BorderLayout.NORTH, userIDField);
			this.getContentPane().add(BorderLayout.WEST, userLeftSidebar);
			this.getContentPane().add(BorderLayout.EAST, buttonE);
		}
		else if (e.getSource() == payButton && GUIState == 1 && true){
			int i;
			try {
				i = Integer.parseInt((userIDField.getText()));
			}catch(Exception Ex){
				userIDField.setText("your input isn't a number");
				return;
			}
			screen.setText(screen.getText() + "<br>pay+" + i);
			userIDField.setText("Successed!");
		}
		else if (e.getSource() == payButton && GUIState == 1 && true){
			int i;
			try {
				i = Integer.parseInt((userIDField.getText()));
			}catch(Exception Ex){
				userIDField.setText("your input isn't a number");
				return;
			}
			screen.setText(screen.getText() + "<br>pay+" + i);
			userIDField.setText("Successed!");
		}
		else if (e.getSource() == buttonE){
			userIDField.setText("Input your ID here");
			PINField.setText("Input your PIN here");
			buttonW.setText("click to login");
			GUIState = 0;
			this.getContentPane().removeAll();
			this.getContentPane().setLayout(new BorderLayout());
			this.getContentPane().add(BorderLayout.NORTH, userIDField);
			this.getContentPane().add(BorderLayout.CENTER, PINField);
			this.getContentPane().add(BorderLayout.SOUTH, buttonW);
		}
	}
	
	

			
	public void createScooter(int[] stationseq,int[] slotseq) {
		for(int i=0;i<15;i++) {
			Scooter sc = new Scooter(i);
			sc.setStationNum(stationseq[i]);
			sc.setSlotNum(slotseq[i]);
			scooterlist[i] = sc;
			stationlist[stationseq[i]].slotlist[slotseq[i]].setScooterNum(i);
			stationlist[stationseq[i]].slotlist[slotseq[i]].setState(true);
		}
	}
	public void createStation() {
		Docking_Station station1 = new Docking_Station("library",0,this);
		Docking_Station station2 = new Docking_Station("lab",1,this);
		Docking_Station station3 = new Docking_Station("shop",2,this);
		stationlist[0] = station1;
		stationlist[1] = station2;
		stationlist[2] = station3;
	}		
}


