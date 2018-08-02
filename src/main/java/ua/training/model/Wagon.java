    package ua.training.model;
    /**
     * Wagon is a product class.
     * created by Dibrova Serhii
     */
    public class Wagon {


        private  int id;
        private  WagonComfortType type;
        private  int numberOfPassengers;
        private  int amountOfLuggage;
        private int  maxPassengers;

        public Wagon(int id){
            this.id=id;
        }
        public Wagon(int id, WagonComfortType type, int numberOfPassengers, int amountOfLuggage) {
            this.id = id;
            this.type = type;
            this.numberOfPassengers = numberOfPassengers;
            this.amountOfLuggage = amountOfLuggage;
        }
        public int getId() {
            return id;
        }
        public WagonComfortType getType() {
            return type;
        }

        public int getNumberOfPassengers() {
            return numberOfPassengers;
        }

        public int getAmountOfLuggage() {
            return amountOfLuggage;
        }
        public int getMaxPassengers() {
            return type.getMaxPassenger();
        }



        @Override
        public String toString() {

            return type.name()+ " " + numberOfPassengers+ "  " + amountOfLuggage;
        }
    }
