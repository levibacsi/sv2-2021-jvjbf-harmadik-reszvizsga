package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {
    private String iD;
    private LocalTime rentingTime;

    public Bike(String iD) {
        this.iD = iD;
    }

    public String getiD() {
        return iD;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * 15;
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

        Bike bike = (Bike) o;

        return iD != null ? iD.equals(bike.iD) : bike.iD == null;
    }

    @Override
    public int hashCode() {
        return iD != null ? iD.hashCode() : 0;
    }
}
