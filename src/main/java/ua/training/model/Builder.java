package ua.training.model;

/**
 * Builder interface defines all possible ways to configure a product.
 * created by Dibrova Serhii
 */
public interface Builder {
    void setId(int id);

    void setType(WagonComfortType type);

    void setNumberOfPassengers(int numberOfPassengers);

    void setAmountOfLuggage(int amountOfLuggage);
}
