package memoryManagement;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int c=0;
		System.out.println("Enter the Memmory Size");
		int memmorySize=new Scanner(System.in).nextInt();
		Simulator simulator=new Simulator(memmorySize);
		do {
			System.out.println("Choose 1- for add process 2-for deAllocate process 3-for defragmantition 4-for EXIT ");
			c=new Scanner(System.in).nextInt();
			if(c==1) {
				System.out.println("Choose 1-First Fit 2-Best Fit 3-Worst Fit ");
				int b=new Scanner(System.in).nextInt();
				System.out.println("Enter the name of the process and size");
				Process process=new Process(new Scanner(System.in).next(),new Scanner(System.in).nextInt());
				switch (b) {
				case 1:
					System.out.println(simulator.firstFit(process)>=0?"The process has been added successfully":"The process has not been added");
					break;
				case 2:
					System.out.println(simulator.bestFit(process)>=0?"The process has been added successfully":"The process has not been added");
					break;
				case 3:
					System.out.println(simulator.worstFit(process)>=0?"The process has been added successfully":"The process has not been added");
				default:
					System.out.println("Wrong Chose");
					break;
				}
			}
			if(c==2) {
				System.out.println("Enter the name of the process");
				System.out.println(simulator.deAllocate(new Scanner(System.in).next())?"The process has been deallocated successfully":"The process has not been deallocated");
			}
			if(c==3) {
				System.out.println(simulator.deFragmantition()?"Disk has been defragmantited":"");
				for (Partition partition : simulator.getPartitions()) {
					if((partition.getProcess()!=null)) {
					    System.out.print("*Partition* "+partition.getProcess().getName()+" *the state is* : ");
					    System.out.print(partition.isState()?"Occupied":"free");
					    System.out.println(" *the size is* :"+ partition.getSize());
					}
				}
				 System.out.println("*Partition* blank *the size is* "+simulator.partitions.get(simulator.partitions.size()-1).getSize());
			}
		} while (c!=4);
		for (Partition partition : simulator.getPartitions()) {
			if((partition.getProcess()!=null)) {
			    System.out.print("*Partition* "+partition.getProcess().getName()+" *the state is* : ");
			    System.out.print(partition.isState()?"Occupied":"free");
			    System.out.println(" *the size is* :"+ partition.getSize());
			}
		}
		 System.out.print("*Partition* blank *the size is* "+simulator.partitions.get(simulator.partitions.size()-1).getSize());
		/*for (Partition partition : simulator.getFreePartitions()) {
			if(partition.getProcess()!=null)
			    System.out.println(partition.getProcess().getName());
		}*/
	}

}
