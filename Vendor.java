import java.util.Scanner;

public class Vendor {  
    public VendingItem items[][];
    
    public Vendor(int width, int height){
        items = new VendingItem[width][height];

        // this.foods = new String[4][5];
        // this.prices = new Double[4][5];
        // foods[0][0] = "🍏"; foods[0][1] = "🍌"; foods[0][2] = "🍇"; foods[0][3] = "🫐"; foods[0][4] = "🌮";
        // foods[1][0] = "🥦"; foods[1][1] = "🥑"; foods[1][2] = "🫒"; foods[1][3] = "🧄"; foods[1][4] = "💩";

        // prices[0][0] = 2.00; prices[0][1] = .75; prices[0][2] = 2.75; prices[0][3] = 2.50; prices[0][4] = 4.00;
        // prices[1][0] = 1.40; prices[1][1] = 2.30; prices[1][2] = .30; prices[1][3] = .45; prices[1][4] = .69;

    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor();
        myVendor.displayVendor();
        System.out.println("\nWelcome to Magic Vendor!");
        myVendor.bearBucksPrompt();
    }

    /**
     * gets vending item at specified coordinates, y-coordinates measured from top-down
     * @return VendingItem object that includes name, emoji, price
     */
    public VendingItem getItemAt(int xCoord, int yCoord){
        return items[xCoord][yCoord];
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
        
        public void printEmojisForLine(int line){
            System.out.println(lineLetters.values()[line] + "\t" + foods[line][0] + "\t" + 
                foods[line][1] + "\t" + foods[line][2] + "\t" + foods[line][3] + "\t" + 
                foods[line][4]);
        }
        
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

}