package memoryManagement;

public class Process {
	String name;
	int size;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Process(String name, int size) {
		super();
		this.name = name;
		this.size = size;
	}
}
