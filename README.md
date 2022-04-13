# Magic Vendor
by Charles Berghausen, Annabel Rice, Katherine Kim, Jackson Tidland for CSE237

The magic vending machine randomly selects a magic price range in which the customer will get their items for free. However, your shopping time is limited, and you will only be blessed with free snacks if you finish your selection in time and reach an after-tax total within the magic range.


Built in Java and used via the command line. Usage instructions will be written below as we develop functionality.
* What user stories were completed this iteration?
    * Cart class that holds items, adds items, removes items, and displays its contents
    * addOrCheckout() loop that repeatedly prompts user for an input and prints out options
    * buyItem() which filters user input and adds an item to the cart
    * VendingItem class that represents vending items, including emoji, name, price, and quantity
    * redesign of Vendor class to hold an array of VendingItems rather than an array of emojis and prices
    * formatPrice() which formats a Double into $x.xx format
    * redesign of displayVendor() which makes it adaptable and uses VendingItem's instance variables
* What user stories do you intend to complete next iteration?
    * Features like the magic number, applying a tax, and a time limit in order to make it a game.
* Is there anything that you implemented but doesn't currently work?
     * No

### Usage

1. clone this repo
2. run $ ./PlayVendor.sh


