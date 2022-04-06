public class VendingItem {
    private String emoji;
    private String name;
    private Double price;
    private int quantity;

    public VendingItem(int quantity, String emoji, String name, Double price){
        this.quantity = quantity;
        this.emoji = emoji;
        this.name = name;
        this.price = price;
    }

    public int getQuantity(){
        return quantity;
    }
    public String getEmoji(){
        return emoji;
    }
    public String getName(){
        return name;
    }
    //can make this always format the Double like a dollar price (x.xx) in the future 
    public Double getPrice(){
        return price;
    }
}
