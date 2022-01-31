package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable{
    private String iD;
    private LocalTime rentingTime;
    private int pricePerMinute;

    public Car(String iD, int pricePerMinute) {
        this.iD = iD;
        this.pricePerMinute = pricePerMinute;
    }

    public String getiD() {
        return iD;
    }

    public int getPricePerMinute() {
        return pricePerMinute;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * pricePerMinute;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;

    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (pricePerMinute != car.pricePerMinute) return false;
        return iD != null ? iD.equals(car.iD) : car.iD == null;
    }

    @Override
    public int hashCode() {
        int result = iD != null ? iD.hashCode() : 0;
        result = 31 * result + pricePerMinute;
        return result;
    }
}
