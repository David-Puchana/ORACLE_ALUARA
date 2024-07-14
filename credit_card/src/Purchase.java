public class Purchase  implements Comparable<Purchase> {
    private float value;
    private String description;

    public Purchase(float value, String description) {
        this.value = value;
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "Valor de la compra: " + value + "\n" +
                "Descripción: " + description;
    }

    @Override
    public int compareTo(Purchase other) {
        Float value_1 = this.value;
        Float value_2 = other.getValue();
        return value_1.compareTo(value_2);
    }
}
