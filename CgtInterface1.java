
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class CgtInterface1 {
    private ArrayList<User> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // Program main function
    public static void main(String[] args) {
        CgtInterface1 calc = new CgtInterface1();
        calc.run();
    }

    public void run() {
        int choosedOption;

        // Show all options
        System.out.println("\nOptions: ");
        System.out.println("[1] > Add user");
        System.out.println("[2] > Delete user");
        System.out.println("[3] > Display user");
        System.out.println("[4] > Display all users");
        System.out.println("[5] > Add investment");
        System.out.println("[6] > Display investment");
        System.out.println("[7] > Delete investment");
        System.out.println("[8] > Save in a file");
        System.out.println("[9] > Exit");

        // Choose an option (eg, Enter option number: 2)
        System.out.print("\nEnter option's serial number: ");
        choosedOption = scanner.nextInt();

        // Actions
        switch (choosedOption) {
            case 1:
                // Add user to user list
                mAddUser();

                run();
                break;
            case 2:
                // Delete user from user list
                mDeleteUser();
                break;
            case 3:
                // Display single user
                mDisplayUser();
                break;
            case 4:
                // Display all users
                mDisplayAllUsers();
                break;
            case 5:
                // Add investment of a user (maximum 2 investment can add)
                mAddInvestment();
                break;
            case 6:
                // Display an investment of a user
                mDisplayInvestment();
                break;
            case 7:
                // Delete investment from investment list of a user
                mDeleteInvestment();
                break;
            case 8:
                // All user's info and investments are saved in a txt file
                mSaveToFile();
                break;
            case 9:
                // Exit from program
                mExit();
                break;

            default:
                // When input a number which is the out of range 1 to 9
                System.out.println("Sorry! Invalid option's number.");
                run();
                break;
        }
    }

    private void mExit() {
        // Terminate the program with an exit status of 0 (success)
        System.exit(0);
    }

    private void mSaveToFile() {

        // Writing to a file
        String fileName = "user_informations.txt";
        PrintWriter printWriter;

        try {
            // initialize output stream to write data into file
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));

            // Check either users exist or not
            if (users.size() > 0) {
                User tempUser = new User();

                // Apply bubble sort algorithm to sort user's names
                mBubbleSortAlgo(tempUser);

                // Display all users information
                for (User currentUser : users) {
                    ArrayList<Investment> investments = new ArrayList<>();
                    String columnWidth = "%-25s %-18s\n";
                    String bulletPoint = "\u2022 ";
                    String spaces = "      ";

                    // Print all information of a user in each iteration
                    printWriter.println("User " + (users.indexOf(currentUser) + 1) + ":");
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Name", ": " + currentUser.getName());
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Residency status",
                            ": " + currentUser.getRecidencyStatus());
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Annual salary",
                            ": " + currentUser.getAnnualSalary());
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Buying price",
                            ": " + currentUser.getBuyingPrice());
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Selling price",
                            ": " + currentUser.getSellingPrice());
                    printWriter.printf(columnWidth, spaces + bulletPoint + "CGT",
                            ": " + String.format("%.2f", currentUser.getCgtDetailsMap().get("CGTtax")));
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Actual profit",
                            ": " + String.format("%.2f", currentUser.getCgtDetailsMap().get("actualCGTProfit")));
                    printWriter.printf(columnWidth, spaces + bulletPoint + "Remaining amount",
                            ": " + String.format("%.2f",
                                    currentUser.getCgtDetailsMap().get("remainingActualCGTProfit")));

                    // Check this user has either any investment or not
                    investments = currentUser.getInvestments();
                    if (investments.size() > 0) {

                        // print all investment info 
                        printWriter.printf(columnWidth, spaces + bulletPoint + "Num of investment",
                                ": " + investments.size());
                        for (Investment item : investments) {
                            printWriter.println(
                                    "\n" + spaces + bulletPoint + "Investment " + (investments.indexOf(item) + 1)
                                            + ":");
                            item.mShowCryptoTableFormat2(printWriter);
                        }
                    } else {

                        // No investment availabe for this user
                        printWriter.printf(columnWidth, spaces + bulletPoint + "Num of investment",
                                ": " + "No investment");
                    }
                }

            } else {

                // No user found in program
                printWriter.println("\nNo users.");
            }

            // Terminate ouptut stream
            printWriter.close();

            // Again start program
            run();

        } catch (FileNotFoundException e) {

            // If there occurs any error in file saving
            System.out.println("Error Opeing File " + fileName);
            System.exit(0);
        }
    }

    private void mBubbleSortAlgo(User tempUser) {

        // Bubble Sort Algorithm
        for (int i = users.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++)
                if (users.get(j).getName().compareTo(users.get(j + 1).getName()) > 0) {
                    tempUser = users.get(j);
                    users.set(j, users.get(j + 1));
                    users.set(j + 1, tempUser);
                }
        }
    }

    private void mDisplayInvestment() {
        String inputName;
        int inputInvestmentNum;
        Map<String, Object> checkedUserResults = new HashMap<>();
        User currentUser = new User();

        // Get user name for displaying his investments
        System.out.print("\nEnter the user name: ");
        scanner.skip("\n");
        inputName = scanner.nextLine().trim();

        // Check user either exist or not
        checkedUserResults = mCheckUser(inputName);

        if (checkedUserResults.get("isMatch").equals(true)) {
            ArrayList<Investment> investments = new ArrayList<>();

            // Initialized the current user object
            currentUser = (User) checkedUserResults.get("currentUser");

            // Get all investments
            investments = currentUser.getInvestments();

            // Show investment option
            System.out.println("\nAll investements: ");
            System.out.println("[1] > Investment 1");
            System.out.println("[2] > Investment 2");

            // Get investment number
            System.out.print("\nEnter the option's serial number: ");
            inputInvestmentNum = scanner.nextInt();

            // Check either investment exists or not
            if (investments.size() >= inputInvestmentNum) {
                // Show all investments
                investments.get(inputInvestmentNum - 1).mShowCryptoTable();
            } else {
                System.out.println("\nSorry! The investment does not exist.");
            }
        } else {
            System.out.println("\nSorry! The user does not exist.");
        }

        run();

    }

    private void mDeleteInvestment() {
        String inputName;
        int inputInvestmentNum;
        Map<String, Object> checkedUserResults = new HashMap<>();
        User currentUser = new User();

        // Get user name for deleting his investments
        System.out.print("\nEnter the user name: ");
        scanner.skip("\n");
        inputName = scanner.nextLine().trim();

        // Check user either user exist or not
        checkedUserResults = mCheckUser(inputName);

        if (checkedUserResults.get("isMatch").equals(true)) {
            ArrayList<Investment> investments = new ArrayList<>();

            // Initialized the user for add investments
            currentUser = (User) checkedUserResults.get("currentUser");
            investments = currentUser.getInvestments();

            // Show investment option
            System.out.println("\nAll investements: ");
            System.out.println("[1] > Investment 1");
            System.out.println("[2] > Investment 2");
            System.out.print("\nEnter the option's serial number: ");
            inputInvestmentNum = scanner.nextInt();

            // Check either investment exists or not
            if (investments.size() >= inputInvestmentNum) {
                investments.remove(inputInvestmentNum - 1);
                currentUser.setInvestments(investments);
                System.out.println("\nSuccess! The investment is deleted.");
            } else {
                System.out.println("\nSorry! The investment does not exist.");
            }
        } else {
            System.out.println("\nSorry! The user does not exist.");
        }

        run();
    }

    private void mDisplayAllUsers() {

        // Check either users exist or not
        if (users.size() > 0) {

            // Initialize a temporary user object
            User tempUser = new User();

            // Apply bubble sort algorithm to sort user's names
            mBubbleSortAlgo(tempUser);

            // Display all users information
            for (User currentUser : users) {
                ArrayList<Investment> investments = new ArrayList<>();
                String columnWidth = "%-25s %-18s\n";
                String bulletPoint = "\u2022 ";
                String spaces = "      ";

                // Print all information of a user in each iteration
                System.out.println(); // print a new line
                System.out.println("User " + (users.indexOf(currentUser) + 1) + ":");
                System.out.printf(columnWidth, spaces + bulletPoint + "Name", ": " + currentUser.getName());
                System.out.printf(columnWidth, spaces + bulletPoint + "Residency status",
                        ": " + currentUser.getRecidencyStatus());
                System.out.printf(columnWidth, spaces + bulletPoint + "Annual Salary",
                        ": " + currentUser.getAnnualSalary());
                System.out.printf(columnWidth, spaces + bulletPoint + "Actual Profit",
                        ": " + String.format("%.2f", currentUser.getCgtDetailsMap().get("actualCGTProfit")));

                // Get investments of current user
                investments = currentUser.getInvestments();

                // Check either investment exist or not
                if (investments.size() > 0) {

                    // print the number of investment
                    System.out.printf(columnWidth, spaces + bulletPoint + "Num of investment",
                            ": " + investments.size());
                } else {

                    // Current user has no investment
                    System.out.printf(columnWidth, spaces + bulletPoint + "Num of investment", ": " + "No investment");
                }
            }

        } else {

            // No user exists
            System.out.println("\nSorry! No users");
        }

        run();
    }

    private void mAddInvestment() {
        String inputName;
        int inputCoinSelection;
        double inputYear1InvestmentAmount;
        double inputYear2InvestmentAmount;
        double inputYear3InvestmentAmount;
        Map<String, Object> checkedUserResults = new HashMap<>();
        User currentUser = new User();

        // Input the user name for adding investments
        System.out.print("\nEnter the user name: ");
        scanner.skip("\n");
        inputName = scanner.nextLine().trim();

        // Check user either user exist or not
        checkedUserResults = mCheckUser(inputName);

        if (checkedUserResults.get("isMatch").equals(true)) {
            // Initialized the user for add investments
            currentUser = (User) checkedUserResults.get("currentUser");

            // Check the user's previously added investments
            if (currentUser.getInvestments().size() < 2) {

                double remainingActualCGTProfit;
                Map<String, Object> cgtDetailsMap = new HashMap<>();

                if (currentUser.getInvestments().size() == 0) {

                    // Calculate Capital Gain Tax for current user
                    currentUser.mCalculateCGT();
                }

                // Get all calcualted Details
                cgtDetailsMap = currentUser.getCgtDetailsMap();
                remainingActualCGTProfit = (Double) cgtDetailsMap.get("remainingActualCGTProfit");

                double remainingAmount = remainingActualCGTProfit;

                // 1st Investment
                System.out.println("\nRemaining amount for investment is " + String.format("%.2f", remainingAmount));
                System.out.print("Enter 1st investment amount (Year 1): ");
                inputYear1InvestmentAmount = scanner.nextDouble();
                while (inputYear1InvestmentAmount > remainingAmount) {
                    System.out.println("\nSorry! Inavlid ammount");
                    System.out.print("Enter amount again less than " +
                            String.format("%.2f", remainingAmount) + ": ");
                    inputYear1InvestmentAmount = scanner.nextDouble();
                }
                remainingAmount -= inputYear1InvestmentAmount; // Remaining amount after 1st year investment

                // 2nd investment
                System.out.println(
                        "\nRemaining amount for investment is " + String.format("%.2f", remainingAmount));
                System.out.print("Enter 2nd investment amount (Year 2): ");
                inputYear2InvestmentAmount = scanner.nextDouble();
                while (inputYear2InvestmentAmount > remainingAmount) {
                    System.out.println("\nSorry! Inavlid ammount");
                    System.out.print("Enter amount again less than " +
                            String.format("%.2f", remainingAmount) + ": ");
                    inputYear2InvestmentAmount = scanner.nextDouble();

                }
                remainingAmount -= inputYear2InvestmentAmount; // Remaining amount after 2nd year investment

                // 3rd investment
                System.out.println(
                        "\nRemaining amount for investment is " + String.format("%.2f", remainingAmount));
                System.out.print("Enter 3rd investment amount(Year 3): ");
                inputYear3InvestmentAmount = scanner.nextDouble();
                while (inputYear3InvestmentAmount > remainingAmount) {
                    System.out.println("\nSorry! Inavlid ammount");
                    System.out.print("Enter amount again less than " +
                            String.format("%.2f", remainingAmount) + ": ");
                    inputYear3InvestmentAmount = scanner.nextDouble();

                }
                remainingAmount -= inputYear3InvestmentAmount; // Remaining amount after 3rd year investment

                // Choose a coin
                System.out.println("\n\u2022 Coin selection");
                System.out.println("Options: ");
                System.out.println("[1] > Bestcoin");
                System.out.println("[2] > Simplecoin");
                System.out.println("[3] > Fastcoin");

                // input a coin number
                System.out.print("Enter option's serial number: ");
                inputCoinSelection = scanner.nextInt();

                // Check input validity
                while (inputCoinSelection <= 0 || inputCoinSelection > 3) {
                    System.out.println("\nSorry! Inavlid input");
                    System.out.print("Enter option's serial number (1 or 2 or 3): ");
                    inputCoinSelection = scanner.nextInt();
                }

                // Check if there is totall of 0 investment amount
                if (inputYear1InvestmentAmount + inputYear2InvestmentAmount + inputYear3InvestmentAmount != 0) {
                    // Calculate and display investment information
                    currentUser.setInvestment(inputYear1InvestmentAmount, inputYear2InvestmentAmount,
                            inputYear3InvestmentAmount, inputCoinSelection);
                    System.out.println("\nSuccess! Added investment.");

                    // Update actualCGTProfit
                    cgtDetailsMap.put("remainingActualCGTProfit", remainingAmount);
                    currentUser.setCgtDetailsMap(cgtDetailsMap);
                    currentUser.mCalculateCrypto();
                }
            } else {
                System.out.println("Sorry! The user has already 2 investments.");
            }
        } else {
            System.out.println("\nSorry! The user does not exist.");
        }

        run();
    }

    private void mDisplayUser() {
        String inputName;
        Map<String, Object> checkedUserResults = new HashMap<>();
        User currentUser = new User();

        // Input the user name for displaying all information of him
        System.out.print("\nEnter the user name: ");
        scanner.skip("\n");
        inputName = scanner.nextLine().trim();

        // Check user either user exist or not
        checkedUserResults = mCheckUser(inputName);

        if (checkedUserResults.get("isMatch").equals(true)) {
            ArrayList<Investment> investments = new ArrayList<>();

            // Initialized the current user object
            currentUser = (User) checkedUserResults.get("currentUser");

            // Display all information of the user
            String columnWidth = "%-18s %-8s\n";
            System.out.println(); // print a new line
            System.out.printf(columnWidth, "Name", ": " + currentUser.getName());
            System.out.printf(columnWidth, "Residency status", ": " + currentUser.getRecidencyStatus());
            System.out.printf(columnWidth, "Buying price", ": " + currentUser.getBuyingPrice());
            System.out.printf(columnWidth, "Selling price", ": " + currentUser.getSellingPrice());
            System.out.printf(columnWidth, "CGT",
                    ": " + String.format("%.2f", currentUser.getCgtDetailsMap().get("CGTtax")));
            System.out.printf(columnWidth, "Actual Profit",
                    ": " + String.format("%.2f", currentUser.getCgtDetailsMap().get("actualCGTProfit")));
            System.out.printf(columnWidth, "Remaining amount",
                    ": " + String.format("%.2f", currentUser.getCgtDetailsMap().get("remainingActualCGTProfit")));

            // Check either investment exist or not
            investments = currentUser.getInvestments();
            if (investments.size() > 0) {
                System.out.printf(columnWidth, "Num of investment", ": " + investments.size());

                // print all investment info 
                for (Investment item : investments) {
                    System.out.println("\n \u2022 Investment " + (investments.indexOf(item) + 1) + ":");
                    item.mShowCryptoTable();
                }
            } else {
                System.out.printf(columnWidth, "Num of investment", ": " + "No investment");
            }
        } else {
            System.out.println("\nSorry! The user does not exist.");
        }

        run();
    }

    private void mDeleteUser() {
        String inputName;
        Map<String, Object> checkedUserResults = new HashMap<>();

        // Input the user name for deleting all information of him
        System.out.print("\nEnter the user name which you want to delete: ");
        scanner.skip("\n");
        inputName = scanner.nextLine().trim();

        // Check user either user exist or not
        checkedUserResults = mCheckUser(inputName);

        if (checkedUserResults.get("isMatch").equals(true)) {
            users.remove(checkedUserResults.get("currentUser"));
            System.out.println("\nSuccess! The user is deleted");
        } else {
            System.out.println("\nSorry! The user does not exist.");
        }

        run();
    }

    private void mAddUser() {
        String inputName;
        int inputYears;
        double inputAnnualSalary;
        double inputBuyingPrice;
        double inputSellingPrice;
        boolean inputResident;
        User user = new User();

        // Check the number of users as it doesn't greater than 5
        if (users.size() < 5) {

            // Get user information
            System.out.print("\nEnter name: ");
            scanner.skip("\n");
            inputName = scanner.nextLine().trim();

            System.out.print("Enter Salary: ");
            inputAnnualSalary = scanner.nextDouble();

            // Check input validity
            while (inputAnnualSalary <= 0) {
                System.out.print("Enter Salary again ( >0): ");
                inputAnnualSalary = scanner.nextDouble();
            }

            System.out.print("Enter Resident true or false: ");
            inputResident = scanner.nextBoolean();

            System.out.print("Enter Buying Price: ");
            inputBuyingPrice = scanner.nextDouble();

            while (inputBuyingPrice <= 0) {
                System.out.print("Enter Buying Price again ( >0): ");
                inputBuyingPrice = scanner.nextDouble();
            }

            System.out.print("Enter Selling Price: ");
            inputSellingPrice = scanner.nextDouble();

            // Check input validity
            while (inputSellingPrice <= inputBuyingPrice) {
                System.out.print("Enter Selling Price again ( >0) greater than " +
                        inputBuyingPrice + ": ");
                inputSellingPrice = scanner.nextDouble();
            }

            System.out.print("Enter Years: ");
            inputYears = scanner.nextInt();

            // Check input validity
            while (inputYears <= 0) {
                System.out.print("Enter Years again ( >0): ");
                inputYears = scanner.nextInt();
            }

            // Set all information to a user object
            user.setUserDetails(inputName, inputAnnualSalary, inputBuyingPrice,
                    inputSellingPrice, inputYears, inputResident);
            user.mCalculateCGT();

            // Add user to user list
            users.add(user);

            System.out.println("\nSuccess! Added user.");
        } else {

            // if the number of user in greater than 5
            System.out.println("\nSorry! It is not possible to add a user.");
        }

        run();
    }

    private Map<String, Object> mCheckUser(String inputName) {
        Map<String, Object> resultsMap = new HashMap<>();
        User currentUser = new User();
        boolean isMatch = false;
        int index = -1;

        // Match the user with the existed users
        for (User user : users) {
            if (user.getName().compareToIgnoreCase(inputName) == 0) {
                currentUser = user;
                index = users.indexOf(currentUser);
                isMatch = !isMatch;
            }
        }

        // Add each result to map
        resultsMap.put("currentUser", currentUser);
        resultsMap.put("isMatch", isMatch);
        resultsMap.put("index", index);

        // Return checked result
        return resultsMap;
    }

}