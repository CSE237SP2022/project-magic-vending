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
        addOrCheckout();
        // myVendor.bearBucksPrompt();
    }

    public void addItems(){
        items[0][0] = new VendingItem(3, "🥑", "avocado", 2.50);
        items[0][1] = new VendingItem(3, "🌮", "taco", 3.70);
        items[0][2] = new VendingItem(3, "🍇", "grapes", 2.50);
        items[0][3] = new VendingItem(2, "🍏", "green apple", 1.20);
        items[0][4] = new VendingItem(4, "🥨", "pretzel", 3.10);
        items[1][0] = new VendingItem(1, "🍎", "red apple", 1.10);
        items[1][1] = new VendingItem(1, "🍑", "peach", 1.90);
        items[1][2] = new VendingItem(1, "🍒", "cherries", 3.35);
        items[1][3] = new VendingItem(3, "🍆", "eggplant", 2.65);
        items[1][4] = new VendingItem(1, "🥖", "baguette", 2.95);
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
        for(int i = 0; i<2; i++){
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
            System.out.println();
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

    public static void addOrCheckout(){
        System.out.println("Would you like to continue adding items or checkout? Enter 'add' or 'checkout' or 'q' to quit");
        Scanner input = new Scanner(System.in);
        while(true){
            
            String response = input.nextLine();
            if(response.equals("add")){
                //add to cart function
                break;
            }
            else if(response.equals("checkout")){
                //checkout function
                break;
            }
            else if(response.equals("q")){
                break;
            }
            else{
                System.out.println("Invalid Input");
            }
            
        }
        input.close();
        

    }

}