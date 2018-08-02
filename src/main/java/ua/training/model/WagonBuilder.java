package ua.training.model;
/**
 * Concrete builders implement steps defined in the common interface.
 * created by Dibrova Serhii
 */
public class WagonBuilder implements Builder {
    private int id;
    private WagonComfortType type;
    private int numberOfPassengers;
    private int amountOfLuggage;


    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public void setType(WagonComfortType type) {
        this.type = type;
    }

    @Override
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public void setAmountOfLuggage(int amountOfLuggage) {
        this.amountOfLuggage = amountOfLuggage;
    }

    public Wagon getBuild(){
           return new Wagon(id,type,numberOfPassengers,amountOfLuggage);
        }
}
