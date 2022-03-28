import java.util.Scanner;
public class Vendor {
    public static void main(String[] args){
        int number = magicNumber();
        int rangeHigh = number+2;
        int rangeLow= number-2;
        System.out.print(rangeLow + "-" + rangeHigh);
        int bBucks= BearBucksInput();
        System.out.print(bBucks);
   
    private String[][] foods;
    private Double[][] prices;
    
    public Vendor(){
        this.foods = new String[4][5];
        this.prices = new Double[4][5];
        foods[0][0] = "🍏"; foods[0][1] = "🍌"; foods[0][2] = "🍇"; foods[0][3] = "🫐";
        foods[1][0] = "🥦"; foods[1][1] = "🥑"; foods[1][2] = "🫒"; foods[1][3] = "🧄";
    }

    public static void main(String[] args){
        Vendor myVendor = new Vendor();
        myVendor.displayVendor();
    }

    /*
    Prints a visual representation of vending machine products & prices to stdout.
    */
    public void displayVendor(){
        System.out.println("________________________________");
        System.out.println("|" + foods[0][0] + "| |" + foods[0][1] + "| |" + foods[0][2] + "| |" + foods[0][3] + "|");
        System.out.println("1.20 3.60 2.00 3.20 4.50");
     
    }
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
    

    public static int BearBucksInput(){
        Scanner input = new Scanner(System.in);
        try{
        System.out.print("Enter amount of Bear Bucks");
        int bearBucks = input.nextInt();
        return bearBucks;
        }
        finally{
            input.close();
        }
        
        
    }

}