import java.util.Scanner;

public class Vendor {  
    public VendingItem items[][];
    
    public Vendor(int width, int height){
        items = new VendingItem[width][height];
        addItems();
    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor(4, 5);
        myVendor.displayVendor();
        System.out.println("\nWelcome to Magic Vendor!");
        // myVendor.bearBucksPrompt();
    }

    public void addItems(){
        items[0][0] = new VendingItem(3, "🥑", "avocado", 2.50);
        items[0][1] = new VendingItem(3, "🌮", "taco", 3.70);
        items[0][2] = new VendingItem(3, "🍇", "grapes", 2.50);
        items[0][3] = new VendingItem(2, "🍏", "apple", 1.20);
        items[0][4] = new VendingItem(4, "🥨", "pretzel", 3.10);
    }

    /**
     * @return VendingItem at specified coordinates, y-coordinates measured from top-down
     */
    public VendingItem getItemAt(int xCoord, int yCoord){
        return items[xCoord][yCoord];
    }

    public int getNumItemsAt(int x, int y){
        return items[x][y].getQuantity();
    }

    /**
     * @return previous item at spot, if it existed
     */
    public VendingItem addItem(VendingItem newItem, int x, int y){
        VendingItem prevItem = items[x][y];
        items[x][y] = newItem;
        return prevItem;
    }

    /** 
     * Prints a visual representation of vending machine products & prices
    */
    public void displayVendor(){
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println("-------------------------------------------");
        for(int i = 0; i<1; i++){
            printLine(i);
        }
    }
        enum lineLetters{A, B, C, D, E}
        
        public void printLine(int line){
            System.out.print(lineLetters.values()[line]);
            for(int y = 0; y < items[line].length; y++){
                System.out.print("\t" + items[line][y].getEmoji());
            }
            System.out.println();
            for(int y = 0; y < items[line].length; y++){
                System.out.print("\t" + items[line][y].getPrice());
            }
        }

    /**
     * Prompts user to input their bear bucks
     */
    public void bearBucksPrompt(){
        int number = magicNumber();
        int rangeHigh = number+2;
        int rangeLow= number-2;
        System.out.println("Magic number: $" + number + "✨");
        System.out.println("Achieve a total between " + rangeLow + "-" + rangeHigh + " to win your items for free!");
        System.out.println("Otherwise...pay the price.\n");
        int bBucks= bearBucksInput();
        System.out.print(bBucks);
    }

    /** 
     * Creates randomly selected magic number
     * @return magic number between 5-15
    */
    public static int magicNumber(){
        int number = (int)(Math.random()*10)+5;
        if(number<0){
            number = Math.abs(number);
        }
        else if(number<=0 && number<=2){
            number=3;
        }
        return number;
        
    }
    
    /** 
     * Prints message prompting for bear bucks input
     * @return number of bearbucks input
    */
    public static int bearBucksInput(){
        Scanner input = new Scanner(System.in);
        try{
        System.out.println("Enter amount of Bear Bucks");
        int bearBucks = input.nextInt();
        return bearBucks;
        }
        finally{
            input.close();
        }
    }

}