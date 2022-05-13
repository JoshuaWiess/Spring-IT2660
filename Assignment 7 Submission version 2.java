import java.util.Arrays;
import java.util.Scanner;


class Assignment7SubmissionVersion2 {

public static void main(String[] args){

Scanner inputScanner = new Scanner(System.in);
student testing5 = new student(new student[]{new student("Bill", 12345, 4.0), new student("Ashley",12346,4.0), new student("Bob",12347,3.8), new student("John",12348,3.7), new student("Ashe", 12349,3.6), new student("Sam",12350,3.5), new student("Jake",12351, 3.4)});
System.out.println("If asked to enter multiple pieces of information, type one piece at a time and hit enter before typing the next.");
System.out.println("Please also note that the idNumber is an integer and the student's GPA is a double.");
System.out.println("The current student list is as follows: ");
testing5.printInorder();
System.out.println();

System.out.println("Enter: 1 to insert a new student's information,\n2 to fetch and output a student's information,\n3 to delete a student's information,\n4 to update a student's information,\n5 to output all student information in descending order, and\n6 to exit the program.");
int selectedFunction = inputScanner.nextInt();
if (selectedFunction == 1) {
    System.out.println("Please input the student's name, then idNumber, and lastly the student's GPA.");
    testing5.insert(testing5, new student(inputScanner.next(), inputScanner.nextInt(), inputScanner.nextDouble()));
}
else if (selectedFunction == 2) {
    System.out.println("Please enter the name of the student whose information you would like to retrieve.");
    testing5.getStudentInformation(testing5.fetch(testing5,inputScanner.next()));
}
else if (selectedFunction == 3) {
    System.out.println("Please enter the name of the student whose information you would like to delete.");
    testing5.delete(testing5, inputScanner.next());
    testing5.printInorder();
    System.out.println();
}
else if (selectedFunction == 4) {
    System.out.println("Please enter the name of the student whose information you would like to update.");
    String selectedStudent = inputScanner.next();
    System.out.println("Now please enter the updated idNumber and then the updated GPA after pressing enter");
    testing5.update(testing5, selectedStudent, new student(selectedStudent, inputScanner.nextInt(), inputScanner.nextDouble()));
    System.out.println("The student's updated information is as follows: ");
    testing5.getStudentInformation(testing5.fetch(testing5, selectedStudent));
}
else if (selectedFunction == 5) {
    System.out.println("All of the students' information will now be printed.");
    testing5.printAllInOrder();
    System.out.println();
}
else if (selectedFunction == 6){
System.out.println("The program will now exit.");
System.exit(0);
}
else 
System.out.println("Sorry but there is no function associated with the number that was entered.");

//The following were various test cases.
/*
testing5.getStudentInformation(testing5.fetch(testing5,"Sam"));
testing5.getStudentInformation(testing5.fetch(testing5,"Ashley"));
testing5.insert(testing5,new student("Mason", 12352,3.3));
testing5.getStudentInformation(testing5.fetch(testing5,"Mason"));
testing5.printInorder();
testing5.delete(testing5, "Bill");
System.out.println();
testing5.printInorder();
testing5.update(testing5, "Sam", new student("Chris",12355,3.2));
System.out.println();
testing5.printInorder();
*/

System.out.println("-------------------------------------------");
System.out.println("The new list is as follows: ");
testing5.printAllInOrder();

}
}

class student {

private String name;
private int idNumber;
private double GPA;
private student[] array;
private student base = this;
private student leftChild;
private student rightChild;

public student (String name, int idNumber, double GPA) {
    this.name = name;
    this.idNumber = idNumber;
    this.GPA = GPA;
}


public student(student[] Students) {
    int length = Students.length;
    this.sort(Students, 0, Students.length-1);

    switch (length) {
        case 1: 
        this.name = Students[0].name;
        this.GPA = Students[0].GPA;
        this.idNumber = Students[0].idNumber;
        return;
        case 2:
        student alphabeticallyFirst;
        student alphabeticallySecond;
        if (Students[0].name.compareToIgnoreCase(Students[1].name) < 0) {
            alphabeticallyFirst = Students[0];
            alphabeticallySecond = Students[1];
            this.name = alphabeticallyFirst.name;
            this.GPA = alphabeticallyFirst.GPA;
            this.idNumber = alphabeticallyFirst.idNumber;
            this.leftChild = alphabeticallySecond;
        }
        else if(Students[0].name.compareToIgnoreCase(Students[1].name) > 0){
            alphabeticallyFirst = Students[1];
            alphabeticallySecond = Students[0];
            this.name = alphabeticallyFirst.name;
            this.GPA = alphabeticallyFirst.GPA;
            this.idNumber = alphabeticallyFirst.idNumber;
            this.leftChild = alphabeticallySecond;
        }
        else 
        return;
        default:
        int median = length/2;
        student[] leftArray = Arrays.copyOfRange(Students,0,median);
        student[] rightArray = Arrays.copyOfRange(Students, median+1,Students.length);
        if (leftArray.length > 0 ) this.leftChild = new student(leftArray);
        this.name = Students[median].name;
        this.GPA = Students[median].GPA;
        this.idNumber=Students[median].idNumber;
        if(rightArray.length>0) this.rightChild = new student(rightArray);
    }    


}


public void printInorder(student Student) {
    if (Student == null)
    return;

    printInorder(Student.leftChild);
    System.out.print(Student.name + " ");

    printInorder(Student.rightChild);
}
public void printInorder() {printInorder(this);}

public void merge (student array[], int left, int middle, int right){
    
  student temp[] = new student[right-left+1];
  int i = left;
  int j = middle+1;
  int k = 0;


    while(i<=middle&&j<=right) {
        if(array[i].name.compareToIgnoreCase(array[j].name) < 0) {
            temp[k] = array[i];
            i++; 
            k++;
        }
        else {
            temp[k] = array[j];
            j++; 
            k++;
        }
    }

    while(i<=middle) {
        temp[k] = array[i];
        i++;
        k++;
    }
    while (j<=right) {
        temp[k]=array[j];
        j++;
        k++;
    }

    for(i = left;i<=right;i++)
    array[i] = temp[i-left];


} 

public void sort(student array[], int left, int right)
{
    if (left<right){ 
    int middle = (left + right) /2;
    sort(array,left,middle);
    sort (array,middle+1,right);
    merge(array,left,middle,right);
    }
    
    setArray(array);
    
}

public void setArray(student[] newArray){
    this.array = newArray;
}
public student[] getArray() {
    return this.array;
}
public String getName() {
    return this.name;
}

public void getStudentInformation(student Student) {
    System.out.println("The student's name is " + Student.name + " and the student's ID is " + Student.idNumber + ". This student has a GPA of " + Student.GPA + ".");
}

public student fetch(student base, String key) {
    if (base==null||base.name.equalsIgnoreCase(key))  
    return base;
    if (base.name.compareToIgnoreCase(key) < 1) 
    return fetch(base.rightChild,key);
    return fetch(base.leftChild,key);
} 

public student insert(student base, student newStudent) {
if (base == null){
return newStudent;
}

if (newStudent.name.compareToIgnoreCase(base.name) < 0)
base.leftChild = insert(base.leftChild,newStudent);
else
    base.rightChild = insert(base.rightChild,newStudent);
return base; 
}


public student delete(student base, String key) {
    if (base==null)
    return base;
    if(key.compareToIgnoreCase(base.name) < 0 )
    base.leftChild = delete(base.leftChild,key);
    else if (key.compareToIgnoreCase(base.name) > 0) 
    base.rightChild = delete(base.rightChild,key);
    else {
        if (base.leftChild == null)
        return base.rightChild;
        else if (base.rightChild == null)
        return base.leftChild;        
        dataTransfer(base, lexicographicallySmaller(base.rightChild)); 
        base.rightChild = delete(base.rightChild,base.name);
                
    }
    return base;
}

public void dataTransfer(student originalStudent, student selectedStudent) {
originalStudent.name = selectedStudent.name;
originalStudent.idNumber=selectedStudent.idNumber;
originalStudent.GPA = selectedStudent.GPA;
}


public student lexicographicallySmaller (student base)
{
    while (base.leftChild != null)
    {
        base = base.leftChild;
    }
    return base;
}

public student update(student base, String key, student updatedStudent){
 base = delete(base,key);
 base = insert(base,updatedStudent); 
 return base;
}

public void printAllInOrder(student Student){
    if (Student == null)
    return;

    printAllInOrder(Student.leftChild);
    System.out.print(Student.name + " " + Student.idNumber + " " + Student.GPA + " ");

    printAllInOrder(Student.rightChild);
}

public void printAllInOrder() {printAllInOrder(this);}

}