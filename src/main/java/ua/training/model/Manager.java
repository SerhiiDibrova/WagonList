package ua.training.model;

import java.util.Random;

/**
 * Manager defines the order of building steps. It works with a builder object
 * through common Builder interface. Therefore it may not know what product is
 * being built.
 * created by Dibrova Serhii
 */
public class Manager {


    public void constructCommonWagon(Builder builder , int id , WagonComfortType type , int numberOfPassenger , int amountOfLuggage){
        builder.setId(id);
        builder.setType(WagonComfortType.COMMON);
        builder.setNumberOfPassengers(numberOfPassenger);
        builder.setAmountOfLuggage(amountOfLuggage);
    }

    public void constructCouchetteWagon(Builder builder , int id , WagonComfortType type , int numberOfPassenger , int amountOfLuggage){
        builder.setId(id);
        builder.setType(WagonComfortType.COUCHETTE);
        builder.setNumberOfPassengers(numberOfPassenger);
        builder.setAmountOfLuggage(amountOfLuggage);
    }

    public void constructCompartmentWagon(Builder builder , int id , WagonComfortType type , int numberOfPassenger , int amountOfLuggage){
        builder.setId(id);
        builder.setType(WagonComfortType.COMPARTMENT);
        builder.setNumberOfPassengers(numberOfPassenger);
        builder.setAmountOfLuggage(amountOfLuggage);
    }
    public void constructBusinessWagon(Builder builder , int id , WagonComfortType type, int numberOfPassenger, int amountOfLuggage){
        builder.setId(id);
        builder.setType(WagonComfortType.BUSINESS);
        builder.setNumberOfPassengers(numberOfPassenger);
        builder.setAmountOfLuggage(amountOfLuggage);
    }
    /**
     *
     * Construct Random Wagon
     */

    public void constructRandomCommonWagon(Builder builder , int id){
        builder.setId(id);
        builder.setType(WagonComfortType.COMMON);
        builder.setNumberOfPassengers(getRandomNumber(WagonComfortType.COMMON.getMaxPassenger()));
        builder.setAmountOfLuggage(getRandomNumber(WagonComfortType.COMMON.getMaxPassenger()));
    }

    public void constructRandomCouchetteWagon(Builder builder , int id){
        builder.setId(id);
        builder.setType(WagonComfortType.COUCHETTE);
        builder.setNumberOfPassengers(getRandomNumber(WagonComfortType.COUCHETTE.getMaxPassenger()));
        builder.setAmountOfLuggage(getRandomNumber(WagonComfortType.COUCHETTE.getMaxPassenger()));
    }

    public void constructRandomCompartmentWagon(Builder builder ,int id){
        builder.setId(id);
        builder.setType(WagonComfortType.COMPARTMENT);
        builder.setNumberOfPassengers(getRandomNumber(WagonComfortType.COMPARTMENT.getMaxPassenger()));
        builder.setAmountOfLuggage(getRandomNumber(WagonComfortType.COMPARTMENT.getMaxPassenger()));
    }
    public void constructRandomBusinessWagon(Builder builder, int id){
        builder.setId(id);
        builder.setType(WagonComfortType.BUSINESS);
        builder.setNumberOfPassengers(getRandomNumber(WagonComfortType.BUSINESS.getMaxPassenger()));
        builder.setAmountOfLuggage(getRandomNumber(WagonComfortType.BUSINESS.getMaxPassenger()));
    }

    private int getRandomNumber(int max) {
        return new Random().nextInt(max-1 ) + 1;
    }
}
