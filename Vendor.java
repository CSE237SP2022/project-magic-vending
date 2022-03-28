import java.util.Scanner;
public class Vendor {
 
    public static void main(String[] args){
        int number = magicNumber();
        int rangeHigh = number+2;
        int rangeLow= number-2;
        System.out.print(rangeLow + "-" + rangeHigh);
        int bBucks= BearBucksInput();
        System.out.print(bBucks);
      
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