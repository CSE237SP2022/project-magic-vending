import java.util.*;

public class Cart {
    private HashMap<VendingItem, Integer> items;

    public Cart(){
        this.items = new HashMap<VendingItem, Integer>();
    }

    public HashMap<VendingItem, Integer> getItems(){
        return this.items;
    }

    public void addItem(VendingItem item){
        HashMap<VendingItem, Integer> cartItems = getItems();
        if(cartItems.containsKey(item)){
            cartItems.replace(item, items.get(item), items.get(item) + 1);
        } else cartItems.put(item, 1);
    }

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

}
