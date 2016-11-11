package com.nixsolutions;

import java.util.concurrent.Callable;

public class Multiplier implements Callable<Integer> {

  private int[][] matrix1;
  private int[][] matrix2;

  private int index1;
  private int index2;

  public Multiplier(int[][] matrix1, int[][] matrix2, int index1, int index2) {
    super();
    this.matrix1 = matrix1;
    this.matrix2 = matrix2;
    this.index1 = index1;
    this.index2 = index2;
  }

  @Override
  public Integer call() throws Exception {
    Integer res = 0;
    
    for (int i = 0; i < matrix1[index1].length; i++) {
      res += matrix1[index1][i] * matrix2[i][index2];
    }
    
    return res;
  }

}
