import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Vendor { 

    private State state;
    private VendingItem items[][];
    private Cart cart;

    public Vendor(int width, int height){
        items = new VendingItem[width][height];
        cart = new Cart();
        addItems();
        state = pickState();
    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor(4, 5);
        myVendor.displayVendor();
        System.out.println("\nWelcome to Magic Vendor!");
        System.out.println("\nWe are currently situated in " + myVendor.getState());
        myVendor.addOrCheckout();
    }

    public enum State {
        AL(4), AK(0), AZ(5.6), AR(6.5), CA(7.25), CO(2.9), CT(6.35), DE(0), FL(6), GA(4), HI(4), ID(6), IL(6.25), IN(7), IA(6),
        KS(6.5), KY(5), LA(4.45), ME(5.5), MD(6), MA(6.25), MI(6), MN(6.88), MS(7), MO(4.23), MT(0), NE(5.5), NV(6.85),
        NH(0), NJ(6.63), NM(6.13), NY(4), NC(4.75), ND(5), OH(5.75), OK(4.5), OR(0), PA(6), RI(7), SC(6), SD(4.5), TN(7),
        TX(6.25), UT(5.95), VT(6), VA(5.3), WA(6.5), WV(6), WI(5), WY(4);
        private double taxRate;

        private State(double taxRate){
            this.taxRate = taxRate;
        }

        private double getTaxRate(){
            return this.taxRate;
        }
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

    public State pickState(){
        double randVal = Math.random();
        return State.values()[((int) (randVal * 100)) / 2 ];
    }

    public State getState(){
        return state;
    }

    public VendingItem getItemAt(int x, int y){
        return items[x][y];
    }

    public void addItem(VendingItem item, int x, int y){
        items[x][y] = item;
    }

    public int getNumItemsAt(int x, int y){
        return items[x][y].getQuantity();
    }

    public void displayVendor(){
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println("-------------------------------------------");
        
        for(int i = 0; i<items.length; i++){
            printLine(i);
        }
        System.out.println();
    }
 
    enum lineLetters{A, B, C, D, E};
    
    public void printLine(int line){
        System.out.print(lineLetters.values()[line]);
        for(int y = 0; y < items[line].length; y++){
            System.out.print("\t" + items[line][y].getEmoji());
        }
        System.out.println();
        for(int y = 0; y < items[line].length; y++){
            System.out.print("\t" + formatPrice(items[line][y].getPrice()));
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

    public void checkout(){
        String total = formatPrice(cart.calculateSubtotal() * ((100.0 + getState().getTaxRate())/100.0));
        cart.viewCart();
        System.out.println("You've purchased the above items for $" + total);
        System.out.println("Thanks for using magic vendor!\n");
    }

    public static String formatPrice(Double price){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

    /**
     * repeatedly prompts user for input, exits while loop when user checks out
     */
    public void addOrCheckout(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Options: enter 'add d3' to add item d3 to cart (or any item), 'cart' to view cart, 'show' to display the vending machine. 'checkout' to buy items in cart & quit");
            String response = input.nextLine().toLowerCase();
            //checks to see if add command is correct format
            if(response.matches("add\\s.*")){
                handleAdd(response);
            }
            else if(response.equals("cart")){
                cart.viewCart();
            }
            else if(response.equals("checkout")){
                checkout();
                break;
            }
            else if(response.equals("show")){
                displayVendor();
            }
            else{
                System.out.println("Invalid Input");
            }
        }
        input.close();
    }

    /**
     * 
     * @param response passed item in form of a5, b2, etc.
     */
    public void handleAdd(String response){
        String itemToBuy = " ";
        if(response.split("\\s").length > 1){
            itemToBuy = response.split("\\s")[1];
        }
        if(buyItem(itemToBuy)){
            System.out.println("Added item " + itemToBuy + " to cart.");
        }
        else {
            System.out.println("Valid items are a1 - d5. For example, enter 'add c4' to add c4 to cart");
        }
    }

    /**
     * filters user input and calls Cart.addItem()
     * @param input user-input string in format of "a5", "b2", etc.
     * @return false if user input invalid
     */
    public boolean buyItem(String input){
        String filteredInput = input.toUpperCase();
        if(!filteredInput.matches("[ABCD][12345]")){
            return false;
        }
        char rowAsChar = filteredInput.charAt(0);
        int row = (int) rowAsChar - 65;
        int col = filteredInput.charAt(1) - 49;
        VendingItem boughtItem = getItemAt(row, col);
        cart.addItem(boughtItem);
        return true;
    }


}