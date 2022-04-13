import java.util.Scanner;

public class Vendor {  
    private String[][] foods;
    private Double[][] prices;
    
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
     * initializes 2D array of foods and their prices
     */
    public Vendor(){
        this.foods = new String[4][5];
        this.prices = new Double[4][5];
        foods[0][0] = "🍏"; foods[0][1] = "🍌"; foods[0][2] = "🍇"; foods[0][3] = "🫐"; foods[0][4] = "🌮";
        foods[1][0] = "🥦"; foods[1][1] = "🥑"; foods[1][2] = "🫒"; foods[1][3] = "🧄"; foods[1][4] = "💩";

        prices[0][0] = 2.00; prices[0][1] = .75; prices[0][2] = 2.75; prices[0][3] = 2.50; prices[0][4] = 4.00;
        prices[1][0] = 1.40; prices[1][1] = 2.30; prices[1][2] = .30; prices[1][3] = .45; prices[1][4] = .69;

    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor();
        myVendor.displayVendor();
        System.out.println("\nWelcome to Magic Vendor!");
        myVendor.bearBucksPrompt();
    }

    /** 
     * Prints a visual representation of vending machine products & prices to stdout.
    */
    public void displayVendor(){
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println("-------------------------------------------");
        for(int i = 0; i<2; i++){
            printEmojisForLine(i);
            printPricesForLine(i);
        }
     
    }
    enum lineLetters{A, B, C, D}
    /**
     * Helper function for displayVendor
     * @param line row of vending machine to print food emojis for
     */
    public void printEmojisForLine(int line){
        System.out.println(lineLetters.values()[line] + "\t" + foods[line][0] + "\t" + 
            foods[line][1] + "\t" + foods[line][2] + "\t" + foods[line][3] + "\t" + 
            foods[line][4]);
    }
    /**
     * Helper function for displayVendor
     * @param line row of vending machine to print prices for
     */
    public void printPricesForLine(int line){
        System.out.println("$\t" + prices[line][0] + "\t" + prices[line][1] + "\t" + prices[line][2] + "\t" +
        prices[line][3] + "\t" + prices[line][4]);
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