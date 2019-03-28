
public class Slot {

	private int num;//range from 0 to 7
	private int scooterNum;
	private boolean state;//true has scooter
	private boolean flash_state;//if the slot flashes
	
	
	
	public Slot(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean getState() {
		return this.state;
	}
	public void setFlashState(boolean state) {
		flash_state = state;
	}
	public boolean getFlahsState() {
		return flash_state;
	}
	public void setScooterNum(int num) {
		scooterNum = num;
	}
	public int getScooterNum() {
		return scooterNum;
	}
}
