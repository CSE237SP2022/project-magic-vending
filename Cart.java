import java.util.*;

public class Cart {
    private HashMap<VendingItem, Integer> items;
    private State state;

    public Cart(){
        this.items = new HashMap<VendingItem, Integer>();
        state = pickState();
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

        double getTaxRate(){
            return this.taxRate;
        }
    } 

    public State pickState(){
        double randVal = Math.random();
        return State.values()[((int) (randVal * 100)) / 2 ];
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
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

    //Returns false if the item was not already in cart
    public boolean removeItem(VendingItem item){
        HashMap<VendingItem, Integer> cartItems = getItems();
        if(cartItems.containsKey(item)) {
            if(cartItems.get(item) == 1) {
                cartItems.remove(item);
            }
            else{
                cartItems.replace(item, items.get(item) - 1);
            }
            return true;
        }
        else {
            return false;
        }
    }

    public double calculateSubtotal(){
        double currTotal = 0;
        HashMap<VendingItem, Integer> cartItems = getItems();
        for(VendingItem item : cartItems.keySet()){
            currTotal += item.getPrice() * cartItems.get(item);
        }
        return currTotal;
    }

    public void viewCart(boolean atCheckout){
        if(getItems().isEmpty()){
            System.out.println("Cart is empty");
            System.out.println();
        }
        else {
            System.out.println("");
            System.out.println("-------------------------------------------");
            viewNames(getItems());
            viewEmojis(getItems());
            viewPrices(getItems());
            viewQuantities(getItems());
            if(!atCheckout){
                System.out.println("You can enter 'remove <item name>' to remove that item from your cart (e.g. 'remove banana' to remove one banana from cart) \n");
            }
        }
    }

    public void viewEmojis(Map<VendingItem, Integer> cartItems){
        for(VendingItem item : cartItems.keySet()){
            System.out.print("\t" + item.getEmoji() );
        }
        System.out.println();
    }

    public void viewPrices(Map<VendingItem, Integer> cartItems){
        for(VendingItem item : cartItems.keySet()){
            System.out.print("\t$" + Vendor.formatPrice(item.getPrice()));
        }
        System.out.println();
    }

    public void viewQuantities(Map<VendingItem, Integer> cartItems){
        for(VendingItem item : cartItems.keySet()){
            System.out.print("\tx" + cartItems.get(item));
        }
        System.out.println();
    }

    public void viewNames(Map<VendingItem, Integer> cartItems){
        for(VendingItem item : cartItems.keySet()){
            System.out.print("\t" + item.getName());
        }
        System.out.println();
    }

}
