package com.nixsolutions;

public class Factory implements Runnable {

  int comp = 0;
  
  @Override
  public void run() {
    while (comp != 21) {
      Store.put(3);
      comp += 3;
      System.out.println("Provided 3 comp");
      try {
	Thread.sleep(5000);
      } catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
      }
    }
    
    System.out.println("Factory finished. " + comp);
  }
}
