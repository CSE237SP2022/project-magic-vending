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
