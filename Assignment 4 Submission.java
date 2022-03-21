import java.util.Scanner;
class Assignment4 {
    public static void main (String[] args) {

Student root = new Student("Steve", 11111, 3.0);
root.setNextStudent(new Student("Ashe",11112,3.2));

root = root.insertStudentAtPosition(new Student("Caitlyn",11115,3.1), 1);
root = root.insertStudentAtPosition(new Student("Ashley",24680,3.3), 0); 
root = root.insertStudentAtPosition(new Student("James",36900,3.2), 4);
root = root.insertStudentAtPosition(new Student("Luke", 45670, 3.3), 5);


root.print();
System.out.println(" ");
root.fetch(2);
System.out.println(" ");
root.update(2);
System.out.println(" ");
root.print();
System.out.println(" ");
root.delete(0);
root.delete(5);
System.out.println(" ");
root.print();
root.delete(1);
System.out.println(" ");
root.print();
System.out.println(" ");
root = root.insertStudentAtPosition(new Student("Oliver",13579,3.0), 0); 
root.print();
    }
}

class Student {

    private String name;
    private int idNumber;
    private double GPA;
    private Student next;


Student(String name, int idNumber, double GPA) {this.name = name; this.idNumber = idNumber; this.GPA = GPA;}

public Student getNextStudent() {return this.next;} 


public void setNextStudent(Student student) {this.next = student;}

public Student insertStudentAtPosition(Student newStudent, int position) {

if(position==0)
return newRoot(newStudent); 
if(position==getListSize())
return append(newStudent);

Student previousStudent = getStudentAtPosition(position-1);
newStudent.setNextStudent(previousStudent.getNextStudent());
previousStudent.setNextStudent(newStudent);
return this;

}

private Student newRoot(Student newRoot) {
newRoot.setNextStudent(this);
    return newRoot;
}

private int getListSize() {
    int listSize = 1;
    Student walker = this;
    while (walker.getNextStudent() != null) {
        walker = walker.getNextStudent();
        listSize++;
    }
    return listSize;
}

private Student append(Student student) {
    Student lastStudent = getStudentAtPosition(getListSize()-1); 
    lastStudent.setNextStudent(student);
    return this;
}

private Student getStudentAtPosition(int position) {
    Student walker = this;
    for (int i = 0; i<position; i++) {
        walker = walker.getNextStudent();
    }
    return walker;
}

public void print() {
Student walker = this;
System.out.println(this.name + " " + this.idNumber + " " + this.GPA);
while (walker.getNextStudent() != null) {
    walker = walker.getNextStudent();
    System.out.println(walker.name + " " + walker.idNumber + " " + walker.GPA); 
}


}

public void fetch(int position) {
 Student walker = this;
 for (int i = 0; i<position; i++) {
    walker=walker.getNextStudent();
 }
 System.out.println(walker.name);
}


public Student update(int position) {
    Scanner inputScanner = new Scanner(System.in);
    Student walker = this;
    for (int i = 0; i<position; i++){
        walker = walker.getNextStudent();
    }
    System.out.println("Please enter new name: ");
    walker.name = inputScanner.nextLine();
    System.out.println("Please enter new GPA: ");
    walker.GPA = inputScanner.nextDouble();
    System.out.println("Please enter new idNumber");
    walker.idNumber = inputScanner.nextInt();
    return walker;
}


public Student delete(int position) {

if (position == 0) {
Student temp = new Student(this.getNextStudent().name, this.getNextStudent().idNumber,this.getNextStudent().GPA);
name = temp.name;
idNumber=temp.idNumber;
GPA = temp.GPA;
next = getNextStudent().getNextStudent();
}
if (position == getListSize()) {
    Student temp;
    temp = getStudentAtPosition(getListSize()-2);
    temp.next = null;
}

else {
Student walker = this;
for (int i=0; i<position;i++) { 
    walker = walker.getNextStudent();
}

getStudentAtPosition(position-1).next = walker.getNextStudent();

}
return this;


}



}





