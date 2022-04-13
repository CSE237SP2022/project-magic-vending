import java.util.Scanner;

public class Vendor {  
    public VendingItem items[][];
    // public Cart cart;
    
    public Vendor(int width, int height){
        items = new VendingItem[width][height];
        // cart = new Cart();
        addItems();
    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor(4, 5);
        myVendor.displayVendor();
        System.out.println("\nWelcome to Magic Vendor!");
        myVendor.buyItem("a4");
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

        items[2][0] = new VendingItem(2, "🧀", "Cheese", 2.60);
        items[2][1] = new VendingItem(1, "🍔", "Burger", 4.20);
        items[2][2] = new VendingItem(1, "🍗", "Turkey leg", 3.35);
        items[2][3] = new VendingItem(3, "🍕", "Pizza", 2.45);
        items[2][4] = new VendingItem(1, "🥞", "Flapjacks", 1.05);
        items[3][0] = new VendingItem(1, "🥐", "Croissant", 1.10);
        items[3][1] = new VendingItem(1, "🥪", "Sandwich", 5.20);
        items[3][2] = new VendingItem(1, "🧆", "Falafel", 2.85);
        items[3][3] = new VendingItem(3, "🍬", "Candy", 0.65);
        items[3][4] = new VendingItem(1, "🍩", "Donut", 1.15);
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
     * parses user input and calls Cart.addItem()
     * @param input user-input string in format of "a5", "b2", etc.
     * @return false if user input invalid
     */
    public boolean buyItem(String input){
        String filteredInput = input.toUpperCase();
        System.out.println(filteredInput);
        if(!filteredInput.matches("[ABCD][12345]")){
            return false;
        }
        char rowAsChar = filteredInput.charAt(0);
        int row = (int) rowAsChar - 65;
        System.out.println("row: " + row); 
        int col = filteredInput.charAt(1) - 49;
        System.out.println("col: " + col);
        VendingItem boughtItem = getItemAt(row, col);
        // cart.addItem(boughtItem);
        return true;
    }

    /** 
     * Prints a visual representation of vending machine products & prices
    */
    public void displayVendor(){
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println("-------------------------------------------");
        for(int i = 0; i<items.length; i++){
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

}