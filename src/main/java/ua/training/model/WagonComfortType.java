package ua.training.model;

/**
 * created by Dibrova Serhii
 */
public enum WagonComfortType {


    COMMON (81),
    COUCHETTE (54),
    COMPARTMENT (36),
    BUSINESS (18);

    private final int maxPassenger;

    WagonComfortType(int maxPassenger){
        this.maxPassenger=maxPassenger;
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }

    @Override
    public String toString() {
        return  name();
    }
}
