import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class Car {
    //Marka,Model,Nazdwozie,Silnik,Pojemnosc,Drzwi,Ilosc wlascieli,Kraj Pochodzenia,Opcje
    private String marka;
    private String model;
    private String nadowozie;
    private String silnik;
    private double pojemnosc;
    private int iloscDrzwi;
    private int iloscWlascieli;
    private String krajPochodzenia;
    private int rokProdukcji;
    private int wiekAuta; //atrybut pochodny, ktory przechowuje wiek auta
    private LocalDate localDate;
    private List<String> opcje; //atrybut opcjonalny przechowuje opcje dodatkowe auta, ale nie musi, pole moze przyjac null
    private List<String> listaModyfikacji; //atrybut powtarzalny przechowuje liste, z mozliwymi modyfikacjami danego auta
    private static double maxEmisjaSpalin = 1.0;
    private static List<Car> extent = new ArrayList<>(); //lista zawierajce ekstensje klasy Car, gdy tworzymy obiekt za kazdym razem dodajemy go do listy

    public Car(String marka, String model, String nadowozie, String silnik, double pojemnosc, int iloscDrzwi, int iloscWlascieli, String krajPochodzenia, int rokProdukcji, List<String> opcje) {
        this.marka = marka;
        this.model = model;
        this.nadowozie = nadowozie;
        this.silnik = silnik;
        this.pojemnosc = pojemnosc;
        this.iloscDrzwi = iloscDrzwi;
        this.iloscWlascieli = iloscWlascieli;
        this.krajPochodzenia = krajPochodzenia;
        this.rokProdukcji = rokProdukcji;
        this.opcje = opcje;
        addCar(this);
        showWiek();
    }
    public void addToListaModyfikacji(String modyfikacja) {
        ArrayList<String> listModyfikacji = new ArrayList<>();
        listModyfikacji.add(modyfikacja);
        this.listaModyfikacji = listModyfikacji;

    }
    public List<String> getListaModyfikacji()
    {
        return this.listaModyfikacji;
    }

    //atrybut opcjonalny -> moze przyjmowac warotsc null
    public List<String> getOpcje() {
        if (this.opcje == null) {
            return null;
        } else {
            return this.opcje;
        }

    }
    public void showWiek(){
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        this.wiekAuta = year - rokProdukcji;
        System.out.println("Samochod ma " + wiekAuta + " lat");

    }
    public void porownaj(double pojemnosc){
        if(this.pojemnosc > pojemnosc){
            System.out.println("Dany samochod ma więcej niz " +pojemnosc + " silnik,bo jego pojemnosc wynosi" +this.pojemnosc);
        }else{
            System.out.println("Dany samochod ma mniej niz " +pojemnosc + " silnik,bo jego pojemnosc wynosi" +this.pojemnosc);
        }
    }
    //przeciazenie metody porownaj
    public void porownaj(double pojemnosc, int rokProdukcji){
        if(this.pojemnosc > pojemnosc){
            System.out.println("Dany samochod ma więcej niz " +pojemnosc + " pojemnosci silnika,bo jego pojemnosc wynosi " +this.pojemnosc);
        }else{
            System.out.println("Dany samochod ma mniej niz " +pojemnosc + " pojemnosci silnika,bo jego pojemnosc wynosi " +this.pojemnosc);
        }
        if(this.rokProdukcji > rokProdukcji){
            System.out.println("Dany samochod jest mlodszy i zostal wyprodukowany w " +this.rokProdukcji);
        }else{
            System.out.println("Dany samochod jest starszy i zostal wyprodukowany w " +this.rokProdukcji);
        }
    }
    public static double showMaxEmisjaSpalin(){

        return maxEmisjaSpalin;
    }
    //dodawanie samochodu do ekstensi
    private static void addCar(Car car) {
        extent.add(car);
    }

    private static void removeCar(Car car) {
        extent.remove(car);
    }

    public static List<Car> getExtent(){
        return extent;
    }
    public static void saveExtent(List<Car> car, String pathToFile){
        try(PrintWriter printWriter = new PrintWriter(new File(pathToFile))){
            StringBuilder sb = new StringBuilder();

                sb.append(car);
                printWriter.append(sb);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void showExtent() {
        System.out.println("Extent of the class " + Car.class.getName());
        for (Car car : extent) {
            System.out.println(car);
        }
    }
    //przesloniecie metody toString
    @Override
    public String toString() {
        return "Car{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", nadowozie='" + nadowozie + '\'' +
                ", silnik='" + silnik + '\'' +
                ", pojemnosc=" + pojemnosc +
                ", iloscDrzwi=" + iloscDrzwi +
                ", iloscWlascieli=" + iloscWlascieli +
                ", krajPochodzenia='" + krajPochodzenia + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", opcje=" + opcje +
                ", listaModyfikacji=" + listaModyfikacji +
                '}';
    }
}
