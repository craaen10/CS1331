public class Dog extends Pet{
    private double droolRate; // Instance variable for droolRate
    public static final double DEFAULT_droolRate = 5.0; // Default droolRate is 5.0

    // Less specific constructor method for Dog
    public Dog(String name, double health, int painLevel){
        // call more specific constructor, use default droolRate as input
        this(name, health, painLevel, DEFAULT_droolRate);
    }

    // More specific constructor method for Dog
    public Dog(String name, double health, int painLevel, double droolRate){
        super(name, health, painLevel); // Call the constructor of the Pet class with "super" keyword
        // If droolRate is less than or equal to zero, set drool rate to 0.5 else set to droolRate
        this.droolRate = (droolRate <= 0.0) ? 0.5 : droolRate;
    }



    // Getter Method for droolRate
    public double getDroolRate() {
        return droolRate;
    }

    // method treat that Returns the time taken (in minutes) to treat the pet. Round values up.
    public int treat(){
        int timeTaken; // defines variable to store time taken that will be returned

        // if droolRate is less than 3.5, the minutes for treatment is (painLevel*2)/health
        if (this.droolRate < 3.5){
            // cast painLevel to double to peform calculation. use Math.ceil to round, then cast to int
            timeTaken = ((int) Math.ceil((this.getPainLevel() * 2) / this.getHealth()));
        }
        // if droolRate is in between 3.5 and 7.5 inclusive, the minutes for treatment is painLevel/health
        else if (3.5 <= this.droolRate && this.droolRate <= 7.5){
            timeTaken = ((int) Math.ceil((this.getPainLevel() / this.getHealth())));
        }
        // if droolRate is greater than 7.5, the minutes for treatment is painLevel/(health*2)
        else {
            timeTaken = ((int) Math.ceil( this.getPainLevel() / (this.getHealth() * 2)));
        }
        heal(); // call super class of heal
        return timeTaken; // return time taken to treat
    }

    public void speak(){
        super.speak(); // Call Pet speak method
        int curPainLevel = this.getPainLevel(); // Get pain level
        // Prints “bark” number of times of the painLevel
        String sound = (curPainLevel > 5) ? "BARK " : "bark ";
        String output = "";
        for (int i = 0; i < curPainLevel; i++){
            output = output + sound;
        }
        System.out.println(output.trim());
        return;
    }
    // Compares One Dog to another
    public boolean equals(Object o){
        boolean nameCheck = false; // initailize Boolean value for name check
        // Object o is an instance of Dog
        if (o instanceof Dog) {
            // Cast Object o to Dog
            Dog pet = (Dog) o;
            // Use the equals() method in Pet as part to evaluate if names are equal
            nameCheck = super.equals(o);
            // if nameCheck equals True
            if (nameCheck == true){
                // check additional condition of droolRate being the same
                return (this.droolRate == pet.droolRate);
            }
        }
        return false;
    }
}