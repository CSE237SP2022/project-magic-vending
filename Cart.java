import java.util.*;

public class Cart {
    private HashMap<VendingItem, Integer> items;

    public Cart(){
        this.items = new HashMap<VendingItem, Integer>();
    }

    public HashMap<VendingItem, Integer> getItems(){
        return this.items;
    }

    //DOES NOT check for whether item is still in vending machine, so this needs to be done on other end
    public void addItem(VendingItem item){
        HashMap<VendingItem, Integer> cartItems = getItems();
        if(cartItems.containsKey(item)){
            cartItems.replace(item, items.get(item), items.get(item) + 1);
        } else cartItems.put(item, 1);
    }

    //Currently, this method removes the entire quantity of this item from the cart. That can be changed later, if we decide, instead, that it should only remove one of every food, at a time.
    public void removeItem(VendingItem item){
        HashMap<VendingItem, Integer> cartItems = getItems();
        if(cartItems.containsKey(item)) cartItems.remove(item);
    }

    public double calculateSubtotal(){
        double currTotal = 0;
        HashMap<VendingItem, Integer> cartItems = getItems();
        for(VendingItem item : cartItems.keySet()){
            currTotal += item.getPrice() * cartItems.get(item);
        }
        return currTotal;
    }

    public void viewCart(){
        if(getItems().isEmpty()){
            System.out.println("Cart is empty");
            System.out.println();
        }
        else {
            System.out.println("");
            System.out.println("-------------------------------------------");
            viewEmojis(getItems());
            viewPrices(getItems());
            viewQuantities(getItems());
            System.out.println("\n");
        }
    }

    public void viewEmojis(Map<VendingItem, Integer> cartItems){
        System.out.println("\t");
        for(VendingItem item : cartItems.keySet()){
            System.out.print(item.getEmoji() + "\t");
        }
    }

    public void viewPrices(Map<VendingItem, Integer> cartItems){
        System.out.println("\t");
        for(VendingItem item : cartItems.keySet()){
            System.out.print("$" + Vendor.formatPrice(item.getPrice()) + "\t");
        }
    }

    public void viewQuantities(Map<VendingItem, Integer> cartItems){
        System.out.println("\t");
        for(VendingItem item : cartItems.keySet()){
            System.out.print("x" + cartItems.get(item) + "\t");
        }
    }

}
