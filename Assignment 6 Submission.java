import java.util.Random;
class assignment6Submission {

    public static void main(String[] args){
        Random rand  = new Random();
        element root = new element(rand.nextInt(101));

        for (int i = 1; i<100; i++){
            root.insertElementAtPosition(new element(rand.nextInt(101)), i);  
        }

        root.print();
        root = root.mergeSort(root);
        System.out.println();
        root.print();
        
    }
}

class element {

    private int value;
    private element next;

element(int value) {this.value = value;}


public element getNextElement() {return this.next;}
public void setNextElement(element element) {this.next = element;}
public element insertElementAtPosition(element newElement, int position) {
    if (position == 0)
    return newRoot(newElement);
    if (position==getListSize())
    return append(newElement);

    element previousElement = getElementAtPosition(position-1);
    newElement.setNextElement(previousElement.getNextElement());
    previousElement.setNextElement(newElement);
    return this;
}

private element newRoot(element newRoot){
    newRoot.setNextElement(this); 
    return newRoot;
}

private int getListSize() {
    int listSize = 1;
    element walker = this;
    while (walker.getNextElement() != null) {
        walker = walker.getNextElement();
        listSize++;
    }
    return listSize;
}

private element append(element element) {
    element lastElement = getElementAtPosition(getListSize()-1);
    lastElement.setNextElement(element);
    return this;
}

private element getElementAtPosition(int position) {
    element walker = this;
    for (int i = 0; i<position; i++){
        walker = walker.getNextElement();
    }
    return walker;
}

public void print() {
    element walker = this;
    System.out.print("["+this.value);
    while (walker.getNextElement() != null) {
        walker = walker.getNextElement();
        System.out.print(", "+walker.value);
    }
    System.out.print("]");
}


private element sortedMerge(element element1, element element2) {

    element result;

    if (element1 == null)
    return element2;
    if(element2 == null)
    return element1;

    if(element1.value <= element2.value) {
        result = element1;
        result.next = sortedMerge(element1.next, element2);
    }
    else {
        result = element2;
        result.next = sortedMerge(element1, element2.next);
    }
    return result;
}

public element mergeSort(element list)
{
    if (list == null || list.next == null)
    return list;

    element middle = getMiddle(list);
    element middleNext = middle.next;

    middle.next = null;

    element left = mergeSort(list);
    element right = mergeSort(middleNext);
    element sortedList = sortedMerge(left,right);

    return sortedList;
    

}

private element getMiddle(element base) 
{
    if (base == null)
    return base;

    element tortoise = base, hare = base; 

    while (hare.next != null && hare.next.next != null) {
        tortoise = tortoise.next;
        hare = hare.next.next;
    }

    return tortoise;
}

}


