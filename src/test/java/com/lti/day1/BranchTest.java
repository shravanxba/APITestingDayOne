package com.lti.day1;

import org.testng.annotations.Test;

public class BranchTest {
  @Test
  public void branch() {
	  System.out.println("Hello new branch!");
  }
  @Test
  public void branchTwo() {
	  System.out.println("Hello new branch! A new method has been added.");
  }
  @Test
  public void branchThree() {
	  System.out.println("Hello new branch!This will remain in staging branch");
  }
}
