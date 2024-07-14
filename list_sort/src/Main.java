import java.util.ArrayList;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> listArtist = new ArrayList<>();
        listArtist.add("Pedro");
        listArtist.add("Juan");
        listArtist.add("David");

        Collections.sort(listArtist);
        System.out.println(listArtist);
    }
}