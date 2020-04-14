import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main  {
    public static void main (String[] args) {
       /*
        a) Ekstensja - tworzenie ekstensji, znajduje się w clasie Car
        b) Ekstensja – trwałość Car save extents
        c) Atrybuty złożone - Klasa car, skladajca sie z wielu atrybutow
        d) Atrybuty opcjonalne - Klasa car , opcje -> pole jest opcjonalne
        e) Atrybuty powtarzalne - Klasa car przechowujaca mozliwa liste modyfikacji danego auta;
        f) Atrybuty klasowe - Klasa car maksymalna emisja spalin, dla samochodu, w zwiazku z prawem UE
        g) Atrybuty pochodne - Klasa car wiekAuta, atrybut pochodny z roku produkcji
        h) Metody klasowe - metoda createCar klasa Main
        i) Przesłonięcie - metoda toString w klasie Car
        j) Przeciążenie - klasa car metoda porownaj
        */
       List<Car> carList = readFile("C:\\Users\\Lagowsky\\Desktop\\MAS\\cars.txt");
       for (Car c: carList) {
            System.out.println(c);
            c.showWiek();
       }
       carList.get(0).addToListaModyfikacji("wydech - akrapovic");
       System.out.println(carList.get(0));
       System.out.println(Car.showMaxEmisjaSpalin() + " CO [g/km]");
       carList.get(0).showWiek();
       carList.get(0).porownaj(3.0);
       carList.get(0).porownaj(3.0,1998);

       Car.saveExtent(Car.getExtent(),"C:\\Users\\Lagowsky\\Desktop\\MAS\\cars1.txt");
       Car.showExtent();

    }
     private static List<Car> readFile(String fileName){
            List<Car> cars = new ArrayList<>();
            try(BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)){
                br.readLine(); //dzieki tej lini nie mamy pierwszej lini z pliku
                String line = br.readLine();
                while (line != null){
                    String[] atributes = line.split(",");
                    Car car = createCar(atributes);
                    cars.add(car);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    return cars;
    }
    private static Car createCar(String[] tab){
        if(tab.length == 9){
            String marka = tab[0];
            String model = tab[1];
            String nadowozie = tab[2];
            String silnik = tab[3];
            double pojemnosc = Double.parseDouble(tab[4]);
            int iloscDrzwi = Integer.parseInt(tab[5]);
            int iloscWlascieli = Integer.parseInt(tab[6]);
            String krajPochodzenia = tab[7];
            int rokProdukcji = Integer.parseInt(tab[8]);
            List<String> opcje = null;
            return new Car(marka,model,nadowozie,silnik,pojemnosc,iloscDrzwi,iloscWlascieli,krajPochodzenia,rokProdukcji,opcje);
        }else{
            String marka = tab[0];
            String model = tab[1];
            String nadowozie = tab[2];
            String silnik = tab[3];
            double pojemnosc = Double.parseDouble(tab[4]);
            int iloscDrzwi = Integer.parseInt(tab[5]);
            int iloscWlascieli = Integer.parseInt(tab[6]);
            String krajPochodzenia = tab[7];
            int rokProdukcji = Integer.parseInt(tab[8]);
            String[] opcjeDodatkowe = tab[9].split(";");
            List<String> opcje = new ArrayList<>();
            for(int i =0; i<opcjeDodatkowe.length; i++){
                opcje.add(opcjeDodatkowe[i]);
            }
            return new Car(marka,model,nadowozie,silnik,pojemnosc,iloscDrzwi,iloscWlascieli,krajPochodzenia,rokProdukcji,opcje);
        }
    }
}
