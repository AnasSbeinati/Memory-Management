package memoryManagement;

import java.util.ArrayList;

public class Simulator {
	int memorySize;
	int freeSpace;
	ArrayList<Partition>partitions;
	ArrayList<Partition>freePartitions;
	public Simulator(int memorySize) {
		super();
		this.memorySize = memorySize;
		this.freeSpace = memorySize;
		partitions=new ArrayList<>();
		freePartitions=new ArrayList<>();
		Partition partition=new Partition(0,1000,memorySize,false,null);
    	partitions.add(partition);
    	freePartitions.add(partition);
	}
	public int getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}
	public int getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}
	public ArrayList<Partition> getPartitions() {
		return partitions;
	}
	public void setPartitions(ArrayList<Partition> partitions) {
		this.partitions = partitions;
	}
	public int firstFit(Process process) {
		 return firstFit(process,freePartitions);
    }
	private int firstFit(Process process,ArrayList<Partition>freePartitions1) {
		for (Partition partition : freePartitions1) {
			if(partition.getSize()>=process.getSize()) {
				if(partition.getNum()==1000) {
					Partition temp=new Partition(partition.getPhysicalAddress(), partition.getNum(), process.getSize(), true, process);
					partition.setNum(partition.getNum()+1);
					partition.setPhysicalAddress(partition.getPhysicalAddress()+process.getSize());
					partition.setSize(partition.getSize()-process.getSize());
					partitions.add(partitions.size()-1,temp);
					temp.setState(true);
					//freePartitions.add(partitions.size()-1,partition);
					return partition.getNum()-1;
				}
				else {
					if(partition.getSize()==process.getSize()) {
						partition.setProcess(process);
						partition.setState(true);
						freePartitions.remove(partition);
					}
					else {
						Partition temp=new Partition(partition.getPhysicalAddress(), partition.getNum(), process.getSize(), true, process);
						partition.setPhysicalAddress(partition.getPhysicalAddress()+process.getSize());
						partition.setSize(partition.getSize()-process.getSize());
						partitions.add(partitions.indexOf(partition),temp);
						temp.setState(true);
						for (int i = partitions.indexOf(partition); i < partitions.size(); i++) {
							partitions.get(i).setNum(partitions.get(i).getNum()+1);
						}
					}
					return  partition.getNum()-1;
				}
			}
		}
    	return -1;
	}
    public int bestFit(Process process) {
    	ArrayList<Partition>bestFitArray=new ArrayList<>(freePartitions);
    	bestFitArray.sort(new Compare());
    	return firstFit(process, bestFitArray);
    }
    public ArrayList<Partition> getFreePartitions() {
		return freePartitions;
	}
	public void setFreePartitions(ArrayList<Partition> freePartitions) {
		this.freePartitions = freePartitions;
	}
	public int worstFit(Process process) {
		ArrayList<Partition>worstFitArray=new ArrayList<>(freePartitions);
    	worstFitArray.sort(new CompareRevers());
    	return firstFit(process, worstFitArray);
    }
    public boolean deAllocate(String name) {
    	int s=-1;
    	for (Partition partition : partitions) {
    		if(partition.getProcess()!=null)
			 if(partition.getProcess().getName().equals(name)) {
				if(partition.isState()) {
					s=partition.getPhysicalAddress();
					break;
				}
				else
					return false;
			}
		}
    	if(s==-1)
    		return false;
    	for (int i = 0; i < partitions.size(); i++) {
			Partition partition=partitions.get(i);
			if(partition.isState())
				if(partition.getPhysicalAddress()==s) {
					partition.setState(false);
					if(i>freePartitions.size()) {
						freePartitions.add(partition);
					}
					else {
						freePartitions.add(i, partition);
					}
					return true;
				}
		}
    	return false;
    }
    public boolean deFragmantition() {
    	int com=0;
    	for (int i=0;i<partitions.size();i++) {
    		Partition partition=partitions.get(i);
			if(!partition.isState()) {
				com+=partition.getSize();
				partitions.remove(partition);
				if(partitions.isEmpty())
					break;
			}
		}
    	if(com!=0) {
    		Partition partition=new Partition(0,1000,com,false,null);
        	partitions.add(partition);
        	freePartitions.clear();
        	freePartitions.add(partition);
    	}
    	else {
    		System.out.println("Does not need defragmantition");
    		return false;
    	}
    	com=0;
    	for (Partition partition : partitions) {
			partition.setNum(com);
			com++;
		}
    	return true;
    }
}
