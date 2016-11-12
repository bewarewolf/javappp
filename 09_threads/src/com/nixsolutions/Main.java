package com.nixsolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  public static void task1() {
   
    int[][] matrix1 = new int[][] {
      {1, 2, 3, 4},
      {3, 4, 5, 6},
      {5, 6, 7, 8},
      {7, 8, 9 , 0},
      {3, 4, 5, 6}
    };
    int[][] matrix2 = new int[][] {
      {2, 3, 3, 4, 5, 6},
      {3, 4, 5, 6, 7, 8},
      {5, 6, 7, 8, 9, 0},
      {7, 8, 9 , 0, 1, 2}
    };
    
    List<Multiplier> list = new ArrayList<>();
    
    for (int i = 0; i < matrix1.length; i++) {
      for (int j = 0; j < matrix2[0].length; j++) {
	list.add(new Multiplier(matrix1, matrix2, i, j));
      }
    }
    
    ExecutorService executor = Executors.newFixedThreadPool(3);
        
    int[][] resMatrix = new int[matrix1.length][matrix2[0].length];
    
    try {
      List<Future<Integer>> resList  = executor.invokeAll(list);
      StringBuilder builder = new StringBuilder ();
      
      for (int i = 0; i < matrix1.length; i++) {
        for (int j = 0; j < matrix2[0].length; j++) {
          resMatrix[i][j] = resList.get(i * matrix2[0].length + j).get();
       
          builder.append(String.format(" %3d ", resMatrix[i][j]));
          
        }
        builder.append("\r\n");
        
      }
      
      System.out.println(builder.toString());
      
      executor.shutdown();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void task2() {
    new Thread(new Factory()).start();
    new Thread(new Consumer("cons1")).start();
    new Thread(new Consumer("cons2")).start();
  }
  
  public static void main(String[] args) {
    
    System.out.println("Task 1:");
    task1();
    System.out.println();
    
    System.out.println("Task 2:");
    task2();
  }
}
