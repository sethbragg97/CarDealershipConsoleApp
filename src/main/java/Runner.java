import java.text.SimpleDateFormat;
import java.util.*;

public class Runner {

    private static final Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static Date date = new Date();
    private static List<Car> listOfAvailableCars = new ArrayList<>();
    private static List<Car> confirmedBookingList = new ArrayList<>();

    public static void main(String[] args) {
        Car car1 = new Car(2000,10000,2003,"Ford Mustang");
        Car car2 = new Car(3000,3000,2009,"Vauxhall Vectra");
        Car car3 = new Car(2500,4000,2012,"BMW 3 Series");
        Car car4 = new Car(5000,2000,2020,"Ford Focus");
        listOfAvailableCars.add(car1);
        listOfAvailableCars.add(car2);
        listOfAvailableCars.add(car3);
        listOfAvailableCars.add(car4);


        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();

        System.out.println("Please enter your surname");
        String lastName = scanner.nextLine();

        System.out.println("Please enter your email");
        String email = scanner.nextLine();

        String bookAnother;
        do {
            System.out.println("Enter car brand");
            String brandSearch = scanner.nextLine();
            List<Car> searchResults = carSearch(brandSearch, listOfAvailableCars);

            System.out.println("Did you want to filter your search results?(Y/N)");
            String decisionOnFilter = scanner.nextLine();

            if(decisionOnFilter.equalsIgnoreCase("y")){
                System.out.println("Enter field to sort by, select from: Brand, Year, Mileage or Price");
                String filter = scanner.nextLine();
                List<Car> filteredSearchResults = advancedOptions(filter,searchResults);
                filteredSearchResults.forEach((car) -> System.out.println(car.getBrand()));
            } else {
                searchResults.forEach((car) -> System.out.println(car.getBrand()));
            }

            while(true) {
                Car selectedCar = selectCar(searchResults);
                System.out.println(selectedCar.getBrand() + " " + selectedCar.getMileage() + "m £" + selectedCar.getPrice() + " " + selectedCar.getYear());
                System.out.println("Would you like to book the selected car?(Y/N)");
                String confirmBooking = scanner.nextLine();
                if(confirmBooking.equalsIgnoreCase("y")) {
                    bookCar(selectedCar);
                    break;
                }
            }

            System.out.println("Do you want to book another car?(Y/N)");
            bookAnother = scanner.nextLine();
        }
        while (bookAnother.equalsIgnoreCase("y"));
    }

    public static List<Car> carSearch(String brandSearch, List<Car> listOfAvailableCars){
        List<Car> matchingCars = new ArrayList<>();
        for(Car car: listOfAvailableCars){
            if(car.getBrand().contains(brandSearch)){
                matchingCars.add(car);
            }
        }

        if(!matchingCars.isEmpty()){
            return matchingCars;
        } else{
            System.out.println("There are no matches, showing all available cars");
            return listOfAvailableCars;
        }

    }

    public static Car selectCar(List<Car> searchResults){
        while(true){
            System.out.println("Please select a car:");
            String carName = scanner.nextLine();
            Car selectedCar = getCarByName(carName, searchResults);
            if(selectedCar != null) {
                return selectedCar;
            } else {
                System.out.println("This is not a match");
            }
        }
    }

    public static void bookCar(Car selectedCar) {
        System.out.println(selectedCar.getBrand() + " has been booked at " + sdf.format(date));
        listOfAvailableCars.remove(selectedCar);
        confirmedBookingList.add(selectedCar);
    }

    public static Car getCarByName(String carName,List<Car> searchResults){
        for(Car car: searchResults){
            if(car.getBrand().equalsIgnoreCase(carName)) {
                return car;
            }
        }
        return null;
    }

    public static List<Car> advancedOptions(String filter, List<Car> searchResults){

        if(filter.equalsIgnoreCase("Brand")){
            searchResults.sort((car1, car2) -> car1.getBrand().compareTo(car2.getBrand()));
        } else if (filter.equalsIgnoreCase("Price")){
            searchResults.sort((car1, car2) -> Double.compare(car1.getPrice(), car2.getPrice()));
        } else if(filter.equalsIgnoreCase("Mileage")){
            searchResults.sort((car1, car2) -> Integer.compare(car1.getMileage(), car2.getMileage()));
        } else if (filter.equalsIgnoreCase("Year")){
            searchResults.sort((car1, car2) -> Integer.compare(car2.getYear(), car1.getYear()));
        }

        return searchResults;
    }
}
