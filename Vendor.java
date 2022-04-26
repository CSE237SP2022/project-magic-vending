import java.text.DecimalFormat;
import java.util.Scanner;

public class Vendor {  
    private VendingItem items[][];
    private Cart cart;
    private double magicNumber;
    
    public Vendor(int width, int height){
        items = new VendingItem[width][height];
        cart = new Cart();
        magicNumber = generateMagicNumber();
        addItems();
    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor(4, 5);
        myVendor.displayVendor();
        myVendor.printInstructions();
        myVendor.addOrCheckout();
    }

    public void printInstructions(){
        System.out.println("\nWelcome to Magic Vendor!");
        System.out.println("\nYou will be given a magic number. In order to win the game, you must add up items in your cart and aim to get the total as close to the magic number as possible. But be careful, you won't be able to see the cart total until the very end, so choose wisely!");
        System.out.println("\nYour magic number for this round is: "+ formatPrice(magicNumber));
        System.out.println("Achieve a total within $0.20 of the magic number to win your items for free! Good luck!");
       
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

    public VendingItem getItemAt(int x, int y){
        return items[x][y];
    }

    public void addItem(VendingItem item, int x, int y){
        items[x][y] = item;
    }

    public int getNumItemsAt(int x, int y){
        return items[x][y].getQuantity();
    }

    public double getMagicNumber(){
        return this.magicNumber;
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
        
        System.out.println("Otherwise...pay the price.\n");
        int bBucks= bearBucksInput();
        System.out.print(bBucks);
    }

    /** 
     * Creates randomly selected magic number
     * @return magic number between 5-15
    */
    public static double generateMagicNumber(){
        double number = (Math.random()*10)+5;
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
        String subtotal = formatPrice(cart.calculateSubtotal());
        cart.viewCart();
        System.out.println("You've purchased the above items for $" + subtotal);
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