import java.util.Scanner;
public class Vendor {
 
    public static void main(String[] args){
        
         
       
    }
    public static void MagicRange(){
        int magicNumber= (int)Math.random();
        int rangeHigh = magicNumber+2;
        int rangeLow= magicNumber-2;
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