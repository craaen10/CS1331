public class Cat extends Pet {
    private int miceCaught; // Instance variable for miceCaught
    private static final int DEFAULT_miceCaught = 0; // Default miceCaught value


    // less specific Cat constructor
    public Cat(String name, double health, int painLevel){
        this(name, health, painLevel, DEFAULT_miceCaught); // call more specific constructor with default miceCaught
    }

    // More specific Cat constructor
    public Cat(String name, double health, int painLevel, int miceCaught){
        super(name, health, painLevel); // call parent constructor for name, health, and painlevel
        // If miceCaught passed in is less than 0, set miceCaught to 0 else set to miceCaught
        this.miceCaught = (miceCaught < 0) ? 0 : miceCaught;
    }

    // Getter method for mice caught
    public int getMiceCaught(){
        return miceCaught;
    }

    // treat method definition
    public int treat(){
        int timeTaken; // defines variable to store time taken that will be returned


        // if number of miceCaught is less than 4
        if (this.miceCaught < 4){
            // minutes for treatment is equal to (painLevel *2)/health
            timeTaken = ((int) Math.ceil(( this.getPainLevel() * 2) / this.getHealth()));
        }
        // if miceCaught is in between 4 and 7 inclusive the
        else if (this.miceCaught >= 4 && this.miceCaught <= 7){
            // minutes for treatment equals painLevel/health
            timeTaken = ((int) Math.ceil( this.getPainLevel() / this.getHealth()));
        }
        // if miceCaught is greater than 7
        else if (this.miceCaught > 7){
            // minutes for treatment equals painLevel/(health*2)
            timeTaken = ((int) Math.ceil( this.getPainLevel() / (this.getHealth() * 2)));
        }
        else{ // If nothing evaluates to true, return 0
            return 0;
        }
        heal(); // call super class of heal
        return timeTaken; // Return calculated time taken to treat
    }

    // Speak method definition
    public void speak(){
        super.speak(); // Calls parent speak method
        int curPainLevel = this.getPainLevel(); // Get pain level
        // Prints “bark” number of times of the painLevel
        String sound = (curPainLevel > 5) ? "MEOW " : "meow ";
        String output = "";
        for (int i = 0; i < curPainLevel; i++){
            output = output + sound;
        }
        System.out.println(output.trim());
        return;
    }

    // equals method to compare if object instances are the same
    public boolean equals(Object o){
        boolean nameCheck = false; // initailize Boolean value for name check
        // Object o is an instance of Dog
        if (o instanceof Cat) {
            // Cast Object o to Dog
            Cat pet = (Cat) o;
            // Use the equals() method in Pet as part to evaluate if
            nameCheck = super.equals(o);
            // if nameCheck equals True
            if (nameCheck == true){
                // check additional condition of miceCaught being the same
                return (this.miceCaught == pet.miceCaught);
            }
        }
        return false;
    }
}