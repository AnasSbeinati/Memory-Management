package memoryManagement;

import java.util.Comparator;

public class Compare implements Comparator<Partition>{

	@Override
	public int compare(Partition p1, Partition p2) {
		if(p1.getSize()>p2.getSize())
			return 1;
		if(p1.getSize()<p2.getSize())
			return -1;
		return 0;
	}
	

}
