public class Vendor {
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

    public void displayVendor(){
        System.out.println("________________________________");
        System.out.println("|" + foods[0][0] + "| |" + foods[0][1] + "| |" + foods[0][2] + "| |" + foods[0][3] + "|");
        System.out.println("1.20 3.60 2.00");
    }

}