import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Escriba el límite de la tarjeta: ");
        float limit = read.nextFloat();
        CreditCard card = new CreditCard(limit);
        int sign = 1;

        while(sign != 0){
            System.out.println("Escriba la descripción de la compra: ");
            String description = read.next();

            System.out.println("Escriba el valor de la compra: ");
            float value = read.nextFloat();

            Purchase purchase = new Purchase(value, description);
            boolean start = card.processPurchase(purchase);

            if(start){
                System.out.println("Compra realizada");
                System.out.println("Escriba 1 si de desea continuar y 0 si desea terminar el proceso");
                sign = read.nextInt();
            } else {
                sign = 0;
            }
        }

        System.out.println("************************");
        System.out.println("COMPRAS REALIZADAS:\n");
        Collections.sort(card.getShoppingList());
        for(Purchase buy: card.getShoppingList()){
            System.out.println( buy.getDescription() + " - " + buy.getValue());
        }
        System.out.println("************************");
        System.out.println("Saldo de la tarjeta: " + card.getBalance());
    }
}
