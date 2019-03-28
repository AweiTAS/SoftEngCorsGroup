import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
public class Docking_Station {

	private int num;//range from 0 to 2
	private String name;
	public Slot[] slotlist = new Slot[8];
	public ManagementSystem sys;
	private int[] stateSeq = new int[8];
	private JButton[] buttonlist = new JButton[8];
	int triggernum=0;
	int triggerstate=0;
	//instance variables
	
	public Docking_Station(String name,int num,ManagementSystem sys) {
		this.name = name;
		this.num = num;
		this.sys = sys;
		createSlotList();
	}
	//constructor
	
	
	
	public int getNum() {
		return num;
	}
	public String getName() {
		return name;
	}
	public void createSlotList() {
		for(int i=0;i<8;i++) {
			Slot slot = new Slot(i);
			slotlist[i] = slot;
		}
	}
	
	
	
	public Scooter release(int slotNum) {
		sys.scooterlist[slotlist[slotNum].getScooterNum()].setState(false);
		sys.scooterlist[slotlist[slotNum].getScooterNum()].setStationNum(-1);
		sys.scooterlist[slotlist[slotNum].getScooterNum()].setSlotNum(-1);//no station or no slot
		slotlist[slotNum].setFlashState(true);
		slotlist[slotNum].setState(false);
		return sys.scooterlist[slotlist[slotNum].getScooterNum()];
	}
	public void lock(Scooter sc, int slotNum) {
		sc.setSlotNum(slotNum);
		sc.setStationNum(num);
		sc.setState(true);
		slotlist[slotNum].setState(true);
		slotlist[slotNum].setFlashState(true);
		slotlist[slotNum].setScooterNum(sc.getNumber());
	}
	public int[] getSlotState() {
		for(int i=0;i<8;i++) {
			if(slotlist[i].getState()) {
				stateSeq[i] = 1;//has scooter
			}
		}
		return stateSeq;
	}
	
	
	//part Gui
	public void basicGui(JFrame jf,int mode){
		int[] seq = getSlotState();
		long initialTime = System.currentTimeMillis();
		jf.setVisible(true);
		jf.setSize(1600, 600);
		jf.setLayout(new FlowLayout());//layout need  be improved
		JButton takeButton = new JButton("Please take the scooter");
		takeButton.addActionListener(new PickTakeListener());
		JButton Counter = new JButton("counter");
		for(int i=0;i<8;i++) {
			JButton pickButton = new JButton();
			if(mode==0) {
				pickButton.addActionListener(new PickListener(i,Counter));
			}else {
				pickButton.addActionListener(new LockListener());
			}
			
			if(seq[i]==1) {
				pickButton.setBackground(Color.GREEN);//has scooter green
				pickButton.setText("scooter"+(slotlist[i].getScooterNum()));
			}else {
				pickButton.setBackground(Color.RED);
				pickButton.setText("no scooter");
			}
			buttonlist[i] = pickButton;
			jf.getContentPane().add(pickButton);
			
	}
		jf.getContentPane().add(takeButton);
		jf.getContentPane().add(Counter);
		JTextField comment = new JTextField();
		comment.setText("GReen-has scooter,Red-no Scooter,White-flashes(unlocked,waits for taking)");
		
		
	}//mode = 0 pick_mode mode = 1 lock_mode
	
	public class PickTakeListener implements ActionListener{
		boolean iftake;
		int slotNum;
		public void actionPerformed(ActionEvent e) {
			slotNum = triggernum;
			if(slotNum!=-1) {
				buttonlist[slotNum].setBackground(Color.RED);
				buttonlist[slotNum].setText("no Scooter");
				release(slotNum);
				triggernum = -1;
				triggerstate = 0;
			}
		}
	}
	
	
		
	public void pickUpGui() {
		
		JFrame PickFrame = new JFrame("Scan your id to get a scooter");
		basicGui(PickFrame,0);
	}
	public void LockGui() {
	}
	
	
	private class PickRemindTask extends TimerTask{
		int buttonNum;
		long initialTime;
		JButton counter;
		public PickRemindTask(int num,long time,JButton counter) {
			buttonNum = num;
			initialTime = time;
			this.counter = counter;
			
		}
		public void run() {
			long currentTime = System.currentTimeMillis();
			int  time = (int) (currentTime - initialTime)/1000;
			counter.setText(""+time);
			if(time>=10&&triggerstate==1) {
				buttonlist[buttonNum].setBackground(Color.GREEN);
				triggerstate=0;
				triggernum = -1;
			}
		}
	}
		
	
	
	public class PickListener implements ActionListener{
		int slotNum1;
		long initialTime;
		JButton counter;
		public PickListener(int num,JButton counter) {
			slotNum1 = num;
			this.counter = counter;
			
		}
		public void counter() {
			Timer timer = new Timer();
			timer.schedule(new PickRemindTask(slotNum1,initialTime,counter),0, 1000);
		}
		public void actionPerformed(ActionEvent e) {
			if(slotlist[slotNum1].getState()) {
				initialTime =  System.currentTimeMillis();
				buttonlist[slotNum1].setBackground(Color.WHITE);
				triggernum=slotNum1;
				triggerstate=1;
				counter();
				
			}
		}
	}//Listener when pick a scooter
	public class LockListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}//Listener when lock a scooter
	public void go() {
		pickUpGui();
	}
	
}
