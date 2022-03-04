

class Assignment3 {

public static void main (String[] args) {



StateStack StateStack1 = new StateStack();
StateStack1.stackLength();
StateStack1.push(new State("Delaware", 1787, 973764));
StateStack1.push(new State("Pennsylvania", 1787, 12800000));
StateStack1.push(new State("New Jersey", 1787, 8882000));
StateStack1.push(new State("Georgia", 1788, 10620000)); 
StateStack1.stackLength();
System.out.println("The stack defaults to a size of 3 but increased to 4 to allow for the addition of Georgia.");
StateStack1.showAll();
System.out.println("Testing the pop method");
StateStack1.pop();
StateStack1.showAll();
StateStack1.stackLength();
System.out.println("The pop resulted in the removal of Georgia which previously held the top position and the downsizing in the length of the stack.");
StateStack1.push(new State("Connecticut",1788,3565000));
StateStack1.stackLength();
StateStack1.push(new State("Massachusetts", 1788, 6893000));
StateStack1.stackLength();
System.out.println("The size of the stack increased to four again because we added Connecticut. It then increased to five since we added Massachusetts. These additions can be seen below.");
StateStack1.showAll();
System.out.println("We are now going to use the peek method to see what is at the top of the stack.");
StateStack1.peek();
System.out.println("Now we will use pop to remove this entry and then use peek again to see what takes its place.");
StateStack1.pop();
StateStack1.peek();
System.out.println("We can now see that Connecticut is the new top in the stack and the entire stack can be seen below.");
StateStack1.showAll();
System.out.println("Everything below is working with a new stack that was created with a specified size of 5 which was then exceeded and the size automatically adjusted to accommodate the new entries.");
StateStack StateStack2 = new StateStack(5);
StateStack2.stackLength();
StateStack2.push(new State("aaa",111,321));
StateStack2.push(new State("bbb",112,322));
StateStack2.push(new State("ccc",113,323));
StateStack2.push(new State("ddd",114,324));
StateStack2.push(new State("eee",115,325));
StateStack2.push(new State("fff",116,326));
StateStack2.push(new State("ggg",117,327));
StateStack2.push(new State("hhh",118,326));
StateStack2.stackLength();
StateStack2.showAll();
StateStack2.pop();
System.out.println("We have now used the pop method on this new stack.");
StateStack2.stackLength();
System.out.println("The size of the stack decreased to seven since the eighth slot was no longer needed.");
StateStack2.showAll();


}



}

class State {

private String name;
private String admission;
private String population;

public State(String Name, int  Admission, int Population) {
    name = Name;
    admission = String.valueOf(Admission);
    population = String.valueOf(Population);
}

public String getStateInfo() {
    return "State: " + name + " Population: " + population + " Admitted in: " + admission;
}

}


class StateStack {

private State[] data; 
private int top;
private int size;
public StateStack()
{
    top = -1;
    size = 3;
    data = new State[3];
}
public StateStack(int n)
{
    top= -1;
    size = n;
    data = new State[n];
}


public void push (State newState) {

   State[] newDataExceedingSize =  new State[this.data.length + 1]; 
   State[] newDataSmallerThanSize = new State[this.data.length];
    top=top+1;

   for (int i = 0; i < top + 1; i++){

    if (i<top) { 
    newDataExceedingSize[i] = data[i];
    newDataSmallerThanSize[i] = data[i];
    }
    else if (i==top & i<size) {
        newDataSmallerThanSize[top] = newState;
        data = newDataSmallerThanSize;
    }
    else if (i==top & i>= size) {
        newDataExceedingSize[top] = newState;
        data = newDataExceedingSize;
      

    }      
   }


}


public State pop() {
    State[] newData = new State[this.data.length-1]; 
    for (int i = 0; i <  top; i++){ 
        newData[i] = data[i];
    }
    int topLocation;
    top = top - 1;
    topLocation=top;
    data = newData;
    size = size - 1;
    return data[topLocation];
    
}

public void stackLength() {
     System.out.println("The length of the stack is: " + String.valueOf(this.data.length));
}


public void showAll() 
{ 
    for (int i = top; i >=0 ; i--)
    System.out.println(data[i].getStateInfo()); 

}


public void peek()
{
    int i = top;
    System.out.println(data[i].getStateInfo());
}
}

    
    




