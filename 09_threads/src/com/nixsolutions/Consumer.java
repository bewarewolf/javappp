package com.nixsolutions;

public class Consumer implements Runnable {

  int comp = 0;
  String name;
  
  public Consumer(String n) {
    name = n;
  }
  
  @Override
  public void run() {
    while (comp != 10) {
      if (Store.count() >= 2) {
        comp += Store.get(2);
        System.out.println(name + " got 2 comp");
      }
      
      try {
	Thread.sleep(7000);
      } catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
      }
    }
    
    System.out.println("Consumer finished. " + comp);
  }
}
