


class Assignment2 {
    public static void main (String[] args) {


        

        System.out.println("This program will manage student information.");
        System.out.println("We begin with a list of students.");
        StudentList list1 = new StudentList(new Student("John", 1, 3.0));        
        list1.insert(new Student("James", 2, 2.7),1); 
        list1.insert(new Student("Caitlyn",6,3.5),2);
        list1.insert(new Student("Ashe", 8, 2.9),0);
        list1.insert(new Student("Elizabeth", 80, 3.3), 0);
        list1.print();
        System.out.println("We now remove some of the students from the list.");
        list1.removal(3);
        list1.removal(2);
        list1.removal(0);
        list1.print();
        System.out.println("We now add another student to the list.");
        list1.insert(new Student("Ryan", 28, 2.8), 0);
        list1.print();
        System.out.println("We now adjust some information in the list.");
        list1.update(new Student("Ben", 35, 3.1), 0);
        list1.update(new Student("Olivia", 41, 3.3), 1);
        list1.print();
        System.out.println("We now retrieve a particular student's information.");
        list1.retrieve(1);
        System.out.println("We now view what index value is associated with each student in the list.");
        list1.positionInformation();




    }
}

class Student {

    private String name;
    private String studentid;
    private String gpa;

public Student(String Name, int studentID, double GPA) {
    name = Name;
    studentid = String.valueOf(studentID);
    gpa = String.valueOf(GPA);
}

public String getStudentInformation() {
    return "Student's name is " + name + " with the ID number " + studentid + " and a GPA of " + gpa;
}


}

class StudentList {

    private Student[] list;

    public StudentList(Student firstStudent) {
        this.list = new Student[1]; 
        this.list[0] = firstStudent; 
    }

    public void print() {
        for (Student student: this.list) {
            System.out.println(student.getStudentInformation());
        }
    }

    public void insert(Student student, int element) {
        if (this.list.length == 0 && element == 0 ) {
            this.list = new Student [1];
            this.list[0] = student;
            return;
        }

        if (element < 0 || element > this.list.length) {
            System.out.println("Sorry, this sort of entry is invalid."); 
            return;
        }
        
        Student[] newList = new Student[this.list.length + 1];
        
        for (int i = 0; i < this.list.length + 1; i++) {

            if (i < element) { 
                newList[i] = this.list[i];  
            }
            else if (i > element) { 
                newList[i] = this.list[i-1];
            }
            else {
                newList[i] = student;
            }
             
            }
            
            this.list = newList;  

        }

        public void removal(int element) {
            if (element > this.list.length || element < 0 ) {
                System.out.println("There is nothing to remove.");
            }
            
            Student[] newList = new Student[this.list.length -1 ]; 

            for (int i = 0; i < this.list.length - 1; i++) { 
                if (i < element) {
                    newList[i] = list[i];
                } 
                else  if (i == element) {
                    newList[i] = list[i+1]; 
                }
                else {
                    newList[i] = list[i+1]; 
                }
                }

                this.list = newList; 
            }

            public void update(Student student, int element) {
                
                if (element < 0 || element > this.list.length) {
                    System.out.println("Sorry, there is no information at this position.");
                }
                    else {
                        this.list[element] = student;
                 }
                }

            public void retrieve(int element) {
                if (element < 0 || element > this.list.length) {
                    System.out.println("There is no information to retrieve at this location.");
                }
                else {
                
                System.out.println(this.list[element].getStudentInformation());
                }
            }

            public void positionInformation() {
                
                for (int i = 0; i<this.list.length; i++) {
                    System.out.println(this.list[i].getStudentInformation() + ". This student's information is associated with the index value " + i);
                }
                }
            }

            
            
        


    



