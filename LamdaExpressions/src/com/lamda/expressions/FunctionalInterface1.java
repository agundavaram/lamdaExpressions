package com.lamda.expressions;

// We need to use the @FunctionalInterface to mark the interface as functional
@FunctionalInterface
public interface FunctionalInterface1 {
 
    public  void doSomeWork();
    
   /* public static void m1(){
    	System.out.println("This is a static method");
    }
    
    public default void m2(){
    	System.out.println("This is a default method");
    } */
    
    // cannot have another non-abstract method, below will give a compilation error
   /* public void doSomeMoreWork();*/
}