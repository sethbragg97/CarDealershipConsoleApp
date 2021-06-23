public class Car {

    private double price;
    private int mileage;
    private int year;
    private String brand;

    public Car(double price, int mileage, int year, String brand) {
        this.price = price;
        this.mileage = mileage;
        this.year = year;
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", mileage=" + mileage +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                '}';
    }
}
