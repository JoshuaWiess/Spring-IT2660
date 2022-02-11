import java.util.Scanner;


class Assignment1 {
    public static void main (String[] args) {
        
        

        Scanner inputScanner = new Scanner(System.in);
        String userResponse;

        System.out.println("Hello! As part of this program, you will be asked to give a name of an item you wish to sell and the price that you want to give that item. When prompted, please provide this information.");
        Listing[] item = new Listing[100];
    
            for (int i=0; i<100; i++) {
                
                System.out.println("Please provide the name of your item then click enter and provide the cost of the item then hit enter again.");
                item[i] = new Listing(inputScanner.next(),inputScanner.nextDouble());
                System.out.println("Is the following name and cost correct: " + item[i].getName() + " $" + item[i].getCost() + ". If it is not, then please type no.");
                userResponse = inputScanner.next();
                
                do {
                    if (userResponse.equals("no")) {
                        System.out.println("Please retype the name of your item and the cost of your item.");
                        item[i].setName(inputScanner.next());
                        item[i].setCost(inputScanner.nextDouble());
                        System.out.println("Is this new entry correct: " + item[i].getName() + " $" + item[i].getCost() + ". If it is not, then please type no.");
                        userResponse = inputScanner.next();

                    }     
                } while (userResponse.equals("no"));    
                
                System.out.println("Excellent! Do you wish to list another item? Please type no if you are done.");
                userResponse = inputScanner.next();
                if(userResponse.equals("no")) {
                    i=100;
                }
            }
            
            System.out.println("Here are all the listings that you created:");
            for (int z=0; z<100; z++) {
                System.out.println(item[z].getName() + " " + item[z].getCost());

            }
            System.out.println("Thank you for using the program!");
        
       


    }
}

class Listing {
    private String name;
    private double cost;

    public Listing() {
        name = "   ";
        cost = 0;
    }

    public Listing(String _name, double _cost) {
        name = _name;
        cost = _cost;

    }

    public void setName(String nameInput) {
        name = nameInput;
    }

    public void setCost(double costInput) {
        cost = costInput;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }


}