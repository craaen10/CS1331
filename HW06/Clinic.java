import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Clinic {
    private File patientFile; // private variable File with patient information
    private int day; // define variable int that is day

    private static final int MAX_PETS = 100; // maximum number of pets to expect
    // define array of Pets to store values parsed from Appointments CSV file
    private static Pet[] pets = new Pet[MAX_PETS];
    // Define array of Strings with Patient information, used to check if appointment patient is existing patient
    private static String[] patients = new String[MAX_PETS];


    // Constructor for Clinic, defines variable patientFile using parameter
    public Clinic(File file){
        this.patientFile = file; // Assign file that contains patient info to patientFile
        this.day = 1; // Day initialized to 1

        Scanner fileScan = null; // initialize a Scanner object

        // Scan file of patients and store in array variable to check if patient exists later
        try{
            fileScan = new Scanner(this.patientFile); // constructs fileScan with patientFile
            int lineCount = 0; // initialize int counter variable for number of lines in file

            while (fileScan.hasNextLine()){ // While there is another line that exists in the file being scanned
                String line = fileScan.nextLine(); // Store file line into String variable
                patients[lineCount] = line; // Store string variable into arry of Strings
                lineCount++; // increment lineCount variable
            }
            // TEST
            for (int i = 0; i < patients.length; i++){
                System.out.println("Test: "+patients[i]);
            }
        }
        catch (FileNotFoundException e){ // Exception handler if a file doesn't exist
            System.out.println(e.getMessage()); // Print error message
        }
        finally {
            if (fileScan != null) { // If fileScan isn't null i.e. File exists
                fileScan.close(); // Close Scanner object and subsequently the file
            }
        }

    }

    // Constructor that chains,
    public Clinic(String fileName){
        // chain to first constructor while creating a new file using name of string stored to pass into the constructor
        this(new File(fileName));
    }

    // Method
    public String nextDay(File f) throws FileNotFoundException, InvalidPetException{
        Scanner fileScan = null; // Define null Scanner for file scan
        Scanner petScan = null; // define null scanner for pet scan
        Scanner userInput = null; // define null Scanner object for user input
        int index = 0; // int to count index of Pets arrays
        String updatedPatientInfo = null; // String to store updated patient information that will be returned

        try {
            fileScan = new Scanner(f); // constructs fileScan with fileIn
            userInput = new Scanner(System.in); // constructs userInput to collect user input
            String line = null; // define string to store pet information

            while (fileScan.hasNextLine()) { // While there is another line that exists in the file being scanned
                line = fileScan.nextLine(); // Scan next line into String variable
                petScan = new Scanner(line); // Create new Scanner object with String "line" as input
                petScan.useDelimiter(","); // Use delimiter to parse .csv values at comma
                String petName = petScan.next(); // Store first string value in array of names
                String petType = petScan.next(); // Store next string value in array of pet types
                double dogDroolRate = -1; // declare variable to store drool rate if dog, assign a sentinel value
                int catMiceCaught = -1; // declare int variable to store miceCaught if cat, assign a sentinel value

                // if pet type isn't valid entry
                if (!petType.equals("Cat") && !petType.equals("Dog")) {
                    // throw new exception, calling InvalidPetException, which is extended in method name
                    throw new InvalidPetException();
                }

                if (petType.equals("Dog")){ // if petType is a Dog
                    dogDroolRate = petScan.nextDouble(); // store dog double drool rate
                }
                else if (petType.equals("Cat")){ // else if pet type is a Cat
                    catMiceCaught = petScan.nextInt(); // store cat int miceCaught
                }

                String appointmentTime = petScan.next(); // Store time of appointment as a string

                double petHealth = 0; // declare int to store user input

                // do-while loop to continuously prompt user for an int for pet's health
                do{
                    // Print statement with details to and prompt user for health of pet
                    System.out.println("Consultation for " + petName + " the " + petType + " at "
                            + appointmentTime + ".\nWhat is the health of " + petName + "?\n");

                    // if input provided by user is a double
                    if (userInput.hasNextDouble()) {
                        petHealth = userInput.nextDouble(); // store user input in vairalbe
                        break; // Exit the loop if the input is an integer
                    }
                    else { // if input is not a double
                        userInput.nextLine(); // Consume the invalid input
                        System.out.println("Pleaser enter a double"); // print invalid input
                    }
                } while (true); // continue to reprompt the user until an int is input

                int painLevel = 0; // declare int to store user input for pain level

                // do-while loop to continuously prompt user for an int for pet's health
                do{
                    // Print statement with details to and prompt user for health of pet
                    System.out.println("On a scale of 1 to 10, how much pain is " + petName + " in right now?\n");

                    // if input provided by user is an int
                    if (userInput.hasNextInt()) {
                        painLevel = userInput.nextInt(); // store user input in vairalbe
                        if (painLevel >= 1 && painLevel <= 10) {
                            break; // Exit the loop if the input is an integer
                        }
                    }
                    else { // if input is not an integer
                        userInput.nextLine(); // Consume the invalid input
                        System.out.println("Please enter a number"); // print invalid input
                    }
                } while (true); // continue to reprompt the user until an int is input


                if (petType.equals("Dog")){ // if petType is a Dog
                    // if no value has been assigned to dogDroolRate, than call Dog constructor without DroolReate param
                    if (dogDroolRate == -1){
                        // create dog object with values scanned from file
                        Dog petCreated = new Dog(petName, petHealth, painLevel);
                        pets[index] = petCreated; // assign Dog to pets array
                    }
                    else{ // dogDroolRate has been assigned, call with assigned dogDroolRate
                        // create dog object with values scanned from file
                        Dog petCreated = new Dog(petName, petHealth, painLevel, dogDroolRate);
                        pets[index] = petCreated; // assign Dog to pets array
                    }

                }
                else if (petType.equals("Cat")){ // else if pet type is a Cat
                    // if no value has been assigned to catMiceCaught, call Cat constructor without miceCaught
                    if (catMiceCaught == -1){
                        // create cat object with values scanned from file
                        Cat petCreated = new Cat(petName, petHealth, painLevel);
                        pets[index] = petCreated; // assign Cat to pets array
                    }
                    // else value has been assigned to miceCaught, call contructor with miceCaught
                    else{
                        // create cat object with values scanned from file
                        Cat petCreated = new Cat(petName, petHealth, painLevel, catMiceCaught);
                        pets[index] = petCreated; // assign Cat to pets array
                    }
                }

                pets[index].speak(); // Call speak method

                // call treat method, store time taken that is returned from method call
                int timeTaken = pets[index].treat();
                String exitTime = addTime(appointmentTime, timeTaken); // call addTime method to calculate the exit time

                if (petType.equals("Dog")) {
                    // define string to add to String of patient info
                    String petInfo = petName + "," + petType + "," + dogDroolRate + "," + "Day "
                            + day + "," + appointmentTime + "," + exitTime + "," + petHealth + "," + painLevel + "\n";
                    // either initailize String with petInfo, or concat the string
                    updatedPatientInfo = (updatedPatientInfo != null) ? updatedPatientInfo.concat(petInfo) : petInfo;
                } else if (petType.equals("Cat")) {
                    // define string to add to String of patient info
                    String petInfo = petName + "," + petType + "," + catMiceCaught + "," + "Day "
                            + day + "," + appointmentTime + "," + exitTime + "," + petHealth + "," + painLevel + "\n";
                    // either initailize String with petInfo, or concat the string
                    updatedPatientInfo = (updatedPatientInfo != null) ? updatedPatientInfo.concat(petInfo) : petInfo;
                }
                index++; // increment index value
            }
        }
        catch (FileNotFoundException e){ // Exception handler if a file doesn't exist
            System.out.println(e.getMessage()); // Print error message
        }
        catch (InvalidPetException e) { // Handle the InvalidPetException if caused by incorrect petType
            System.out.println(e.getMessage());
        }
        finally {
            if (fileScan != null) { // If fileScan isn't null i.e. File exists
                fileScan.close(); // Close Scanner object and subsequently the file
            }
        }
        day++; // increment day after scanning file and updating patients information

        return updatedPatientInfo; // return string with updated patients information from scanned file
    }

    // Method
    public String nextDay(String fileName) throws FileNotFoundException {
        try {
            return (nextDay(new File(fileName)));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); // Print error message
            return null; // exit and return null if try attempt fails
        } catch (InvalidPetException e) {
            System.out.println(e.getMessage()); // Handle the InvalidPetException if caused by Method call
            return null; // exit and return null if try attempt fails
        }
    }

    // method to write the strings on updated patient information to file
    public boolean addToFile(String patientInfo){
        PrintWriter filePrint = null; // initialize a PrintWriter object

        // defines output file represented by the patientInfo file defined in constructor
        File fileOut = new File(patientFile.getPath());

        Scanner patientString; // Create a Scanner object to scan input String parameter

        try {
            patientString = new Scanner(patientInfo); // define patientString with patientInfo String
            filePrint = new PrintWriter(fileOut); // Defines print writer object to print to output file

            patientString.useDelimiter(","); // parse string at comma
            String patientNameToCheck = patientString.next(); // Store patients name from input String

            while (patientString.hasNextLine()) { // While string line exists
                String line = patientString.nextLine(); // Store next line of patientInfo into new String

                // Loop through array of Patients
                for (int i = 0; i < patients.length; i++){
                    String existingPatient = ""; // initialize existing patient String to blank
                    if (patients[i] != null){
                        String[] stringToParse = patients[i].split(","); // Store next existing patient string
                        existingPatient = stringToParse[0]; // Store patients name from input String
                    }

                    // Test
                    System.out.println("Name to Check: "+patientNameToCheck);
                    System.out.println("Existing Patient: "+existingPatient);

                    if (patientNameToCheck.equals(existingPatient)){
                        patientString.next(); // consume petType
                        filePrint.println(patientString); // Append the line to the file
                        break;
                    }
                }
                String originalString = patientNameToCheck + line; // concat name to original string
                filePrint.println(originalString); // Append the line to the file
            }

            return true; // returns succesfully printed to file
        }
        catch (Exception e){ // Exception handler if a file doesn't exist
            System.out.println(e.getMessage()); // Print error message
            return false;
        }
        finally {
            if (filePrint != null) { // If filePrint isn't null i.e. File exists
                filePrint.close(); // Close Scanner object and subsequently the file
            }
        }

    }

    // method to calculate additional time
    private String addTime(String timeIn, int treatmentTime){
        int timeInInt = Integer.parseInt(timeIn); // cast String of timeIn to Integer
        int hoursTimeIn = timeInInt / 100; // Extract hours
        int minsTimeIn = timeInInt % 100; // Extract minutes

        int hoursTreatmentTime = treatmentTime / 60; // Extract hours
        int minsTreatmentTime  = treatmentTime % 60; // Extract minutes

        // Perform addition
        hoursTimeIn += hoursTreatmentTime; // add hours values
        minsTimeIn += minsTreatmentTime; // add minutes values

        // cast back final int back to string
        return String.format("%02d%02d", hoursTimeIn, minsTimeIn);
    }
}