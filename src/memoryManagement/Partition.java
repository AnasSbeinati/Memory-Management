package memoryManagement;

public class Partition {
	int physicalAddress;
	int num;
	int size;
	boolean state=false;
	Process process;
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public Partition(int physicalAddress, int num, int size, boolean state,
			Process process) {
		super();
		this.physicalAddress = physicalAddress;
		this.num = num;
		this.size = size;
		this.state = state;
		this.process = process;
	}
	public int getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(int physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
