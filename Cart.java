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
        HashMap<VendingItem, Integer> items = getItems();
        if(items.containsKey(item)){
            items.replace(item, items.get(item), items.get(item) + 1);
        } else items.put(item, 1);
    }

    public void removeItem(VendingItem item){
        HashMap<VendingItem, Integer> items = getItems();
        if(items.containsKey(item)) items.remove(item);
    }

}
