/*
 * Giancarlo Sirio
 * 
 */

public class Set  {
    
    private int SIZE = 20;  // length of the array
    
    private int[] set;            // array reference to the set
    private int next;             // index to next available slot in array
    
    //no args constructor
    public Set() {
        this.set = new int[SIZE];
        this.next = 0;
    }
    
    // constructor that takes in an array of integers, returns set object with corresponding elements
    public Set(int[] A) {
    	this.set = new int[SIZE];
    	this.next = 0;	
        for(int i = 0; i < A.length; i++) {
        	this.insert(A[i]);
        }
    }
    //Cloneing Method, creates a new instance of a set with a copy of the same elements
    public Set clone() {
        Set cloned = new Set();
        for(int i = 0; i < this.next; i++) {
        	cloned.insert(this.set[i]);
        }
        return cloned;
    }
    
    // This method reallocates the array S to twice as big and copies all the elements over.
    // It is called only by insert.
    
    private void resize() {
        int[] temp = new int[SIZE * 2];
        for(int i = 0; i <set.length; ++i) {
            temp[i] = set[i];
        }
        SIZE = SIZE * 2;
        set = temp;
    }
    //The toString method for the class, prints out string representation of set    
    public  String toString()  {
    	String s = "";
        for(int i =0; i < this.next; i++) {
        	if(this.next == 1 || i == this.size()-1) {
        		s+= this.set[i];
        	}
        	else {
        		s += this.set[i] + ",";
        	}
        	
        }
        return "[" +s+ "]";   // just to get it to compile; replace null with something appropriate    
    } 
     
    //returns the number of elements in the set
    public int size() {
    	return this.next;
        
    }
    //checks if the set is empty or not, relies on next field
    public  boolean isEmpty() {
    	boolean empty = false;
        if(this.next == 0) {
        	empty = true;
        }
        return empty;
    }
    //Method that returns True if the element is in the set False otherwise  
    public boolean member(int k) {
        boolean isInSet = false;
        
        for(int i =0; i < this.next; i++) {
        	if(this.set[i] == k) {
        		isInSet = true;
        		break;
        	}
        	
        }
        return isInSet;   // just to get it to compile; replace false with something appropriate   
    }    
   //method inputs a set T, returns true if set is a subset of the Set T 
    public  boolean subset(Set T) {
        boolean isSubset = true;
        for(int i = 0; i < this.next; i++) {
        	if(T.member(this.set[i]) == false) {
        		isSubset = false;
        		break;
        	}
        }
        return isSubset;
    }
    //Method inputs a set T, returns true if the set is equal to the set T
    public  boolean equal(Set T) {
        boolean isEqual = false;
        if(this.subset(T)== true && this.size() == T.size()) {
        	isEqual = true;
        }
        return isEqual;
    }
    //Insert Method, inputs a int k, adds k into the set
    //Insert relies on member method for the logic to work
    public void insert(int k) {
    	int canAdd = SIZE - this.next;		//canAdd will determine if a resize is necessary or not
        if(this.member(k) == false && canAdd > 0) {
        	this.set[next] = k;
        	this.next += 1;				
        	
        }
        else if(this.member(k) == false && canAdd <= 0) {
        	this.resize();
        	this.set[next] = k;
        	this.next += 1;
        	
        }
        
        
    }
    //delete inputs an int k, if k is in the set, k will be deleted and values are shifted in set
    public void delete(int k) {
    	int index = 0;
        if(this.member(k) == true) {
        	
        	for(int i =0; i < this.next; i++) {
        		if(this.set[i] == k) {
        			index = i;
        		}
        	}
        	
        	for(int i = index; i < this.next;i++) {
        		
        		this.set[i] = this.set[i+1];
        		
        	}
        	this.next -= 1;
        }
        
       
    }
    //inputs a set T, and returns a new set with the Union of both sets
    public Set union(Set T) {
    	Set unionSet = this.clone();
    	for(int i=0; i< T.size();i++) {
    		unionSet.insert(T.set[i]);
    	}
    	return unionSet;
        
    }
    //inputs a set T, and returns a new set with the intersection of both sets
    public Set intersection(Set T) {
           Set andSet = new Set();
           for(int i = 0; i < this.size(); i++) {
        	   if(T.member(this.set[i]) == true) {
        		   andSet.insert(this.set[i]);
        	   }
        	   
           }
           return andSet;
    }
    //inputs a set T, and returns a new set with the difference of both sets
    public Set setdifference(Set T) {
        Set diffSet = new Set();
        for(int i = 0; i < this.size(); i++) {
     	   if(T.member(this.set[i]) == false) {
     		   diffSet.insert(this.set[i]);
     	   }
     	   
        }
        return diffSet;
    }
    
      
}
