package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {
    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<Rentable, User> registry = new TreeMap<>(Comparator.comparing(Rentable::getRentingTime));

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Map<Rentable, User> getActualRenting() {
        return new HashMap<>(registry);
    }

    public void addRentable(Rentable rentable){
        rentables.add(rentable);
    }

    public void registerUser (User user){
        if (users.contains(user)){
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }

    public void rent(User user, Rentable rentable, LocalTime time){
        if (time.isBefore(LocalTime.of(3,0)) && !registry.containsKey(rentable) &&
                user.getBalance() > rentable.calculateSumPrice(180)){
            registry.put(rentable, user);
        } else {
            throw new IllegalStateException ("Renting is impossible");
        }
    }

    public void closeRent(Rentable rentable, int minutes){
        if (registry.containsKey(rentable)){
            User user = registry.remove(rentable);
            user.minusBalance(rentable.calculateSumPrice(minutes));
            rentable.closeRent();
        }
    }
}
