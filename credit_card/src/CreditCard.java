import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreditCard{
    private float limit;
    private float balance;
    private List<Purchase> shoppingList;

    public CreditCard(float limit) {
        this.limit = limit;
        this.balance = limit;
        this.shoppingList = new ArrayList<>();
    }

    public List<Purchase> getShoppingList() {
        return shoppingList;
    }

    public float getBalance() {
        return balance;
    }

    public float getLimit() {
        return limit;
    }

    public boolean processPurchase(Purchase purchase){
        if(purchase.getValue() <= this.balance){
            this.balance -= purchase.getValue();
            this.shoppingList.add(purchase);
            return true;
        }
        return false;
    }
}
