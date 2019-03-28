
public class Scooter {
	private int number;
	private int station_num;
	private int slot_num;
	private boolean state;
	
	
	
	public Scooter(int number) {
		this.number = number;//range from 0 to 15
	}
	public int getNumber() {
		return number;
	}
	public void setStationNum(int num) {
		station_num = num;//range from 0 to 2
	}
	public int getStationNum() {
		return station_num;
	}
	public void setSlotNum(int num) {
		slot_num = num;//range from 0 to 7
	}
	public int getSlotNum() {
		return slot_num;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean getState() {
		return this.state;
	}
	
}
