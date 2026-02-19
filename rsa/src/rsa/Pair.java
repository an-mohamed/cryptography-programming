package rsa;

public class Pair<T, U>{
		  private T first;
		  private U second;
		  
		  public Pair(T first,U second){
			   this.first= first;
			   this.second= second;
		  }
		  
		  // Getters
		  public T getKey() {
			   return this.first; 
		  }
		  public U getValue() {
			   return this.second; 
		  }
		  
		  
		  @Override
		  public String toString() {
			  return "("+first+" , "+second+")";
		  }
}
