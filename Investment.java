import java.io.PrintWriter;

// You may need to add extra methods as some parts may be incomplete
// These files do not have proper comments, you need to add those
// Some checks on Input values are missing, you need to add those

public class Investment {
    private double year1Deposit;
    private double year2Deposit;
    private double year3Deposit;
    private int coinSelection;
    private double year1Profit;
    private double year2Profit;
    private double year3Profit;
    private double year1AccumProfit;
    private double year2AccumProfit;
    private double year3AccumProfit;
    private String coinDescription;

    // constructor
    /*   public Investment() {
        year1Deposit = 0;
        year2Deposit = 0;
        year3Deposit = 0;
        coinSelection = 0;
        year1Profit = 0;
        year2Profit = 0;
        year3Profit = 0;
        year1AccumProfit = 0;
        coinDescription = "";
    } */

    public void setcoinSelection(int inputcoinSelection) {
        coinSelection = inputcoinSelection;
    }

    public void setAllDeposits(double a1, double a2, double a3) {
        year1Deposit = a1;
        year2Deposit = a2;
        year3Deposit = a3;
    }

    public double getYear1Deposit() {
        return (year1Deposit);
    }

    public double getYear2Deposit() {
        return (year2Deposit);
    }

    public double getYear3Deposit() {
        return (year3Deposit);
    }

    public int getcoinSelection() {
        return (coinSelection);
    }

    public double getYear1Profit() {
        return (year1Profit);
    }

    public double getYear2Profit() {
        return (year2Profit);
    }

    public double getYear3Profit() {
        return (year3Profit);
    }

    public double getYear1AccumProfit() {
        return (year1AccumProfit);
    }

    public double getYear2AccumProfit() {
        return (year2AccumProfit);
    }

    public double getYear3AccumProfit() {
        return (year3AccumProfit);
    }

    public String getCoinDescription() {
        return (coinDescription);
    }

    public void mCalculateCrypto() {
        double perc = 0;

        // determine percentage and name of coin
        switch (coinSelection) {
            case 1: {
                perc = 0.18;
                coinDescription = "Best Coin";
                break;
            }
            case 2: {
                perc = 0.12;
                coinDescription = "Simple Coin";
                break;
            }
            case 3: {
                perc = 0.15;
                coinDescription = "Fast Coin";
                break;
            }
            default: {
                perc = 0;
                coinDescription = "Sorry! Invalid coin type";
            }
        }

        year1Profit = year1Deposit * perc;
        year1AccumProfit = year1Profit;

        year2Profit = (year1Deposit + year2Deposit) * perc;
        year2AccumProfit = year1AccumProfit + year2Profit;

        year3Profit = (year1Deposit + year2Deposit + year3Deposit) * perc;
        year3AccumProfit = year2AccumProfit + year3Profit;
    }

    // Not important
    public String printCrypto() {
        String invDetails = "";
        double perc = 0;
        String coinDescription = "";

        // determine percentage and name of coin
        switch (coinSelection) {
            case 1: {
                perc = 0.18;
                coinDescription = "Best Coin";
                break;
            }
            case 2: {
                perc = 0.12;
                coinDescription = "Simple Coin";
                break;
            }
            case 3: {
                perc = 0.15;
                coinDescription = "Fast Coin";
                break;
            }
            default: {
                perc = 0;
                coinDescription = "Sorry! Invalid coin type";
            }
        }

        invDetails = "\nPredicted Profit for Investment in " + coinDescription;
        invDetails += "\nYears " + "\t" + "YearlyProfit" + "\t" + "TotalProfit";
        invDetails += "\n----------------------------------";

        year1Profit = year1Deposit * perc;
        year1AccumProfit = year1Profit;
        invDetails += "\n1" + "\t" + (int) year1Profit + "\t" + (int) year1AccumProfit;

        year2Profit = (year1Deposit + year2Deposit) * perc;
        year2AccumProfit = year1AccumProfit + year2Profit;
        invDetails += "\n2" + "\t" + (int) year2Profit + "\t" + (int) year2AccumProfit;

        year3Profit = (year1Deposit + year2Deposit + year3Deposit) * perc;
        year3AccumProfit = year2AccumProfit + year3Profit;
        invDetails += "\n3" + "\t" + (int) year3Profit + "\t" + (int) year3AccumProfit;

        return (invDetails);

    }

    public void mShowCryptoTable() {
        String spaces = "    ";

        // Print all profit data as table format
        String columnWidth = "%-10s %-15s %-15s\n";
        System.out.println(spaces + "Predicted Profit for Investment in " + coinDescription + ".");

        System.out.println(); // newline

        // Define column headers
        System.out.printf(columnWidth, spaces + "Years", "YearlyProfit", "TotalProfit");

        System.out.println(spaces + "----------------------------------"); // newline

        // Define data rows
        System.out.printf(columnWidth, spaces + "1", String.format("%.2f", year1Profit),
                String.format("%.2f", year1AccumProfit));
        System.out.printf(columnWidth, spaces + "2", String.format("%.2f", year2Profit),
                String.format("%.2f", year2AccumProfit));
        System.out.printf(columnWidth, spaces + "3", String.format("%.2f", year3Profit),
                String.format("%.2f", year3AccumProfit));
    }

    public void mShowCryptoTableFormat2(PrintWriter printWriter) {
        String spaces = "        ";

        // Print all profit data as table format
        String columnWidth = "%-18s %-15s %-15s\n";
        printWriter.println(spaces + "Predicted Profit for Investment in " + coinDescription + ".");

        printWriter.println(); // newline

        // Define column headers
        printWriter.printf(columnWidth, spaces + "Years", "YearlyProfit", "TotalProfit");

        printWriter.println(spaces + "--------------------------------------- "); // newline

        // Define data rows
        printWriter.printf(columnWidth, spaces + "1", String.format("%.2f", year1Profit),
                String.format("%.2f", year1AccumProfit));
        printWriter.printf(columnWidth, spaces + "2", String.format("%.2f", year2Profit),
                String.format("%.2f", year2AccumProfit));
        printWriter.printf(columnWidth, spaces + "3", String.format("%.2f", year3Profit),
                String.format("%.2f", year3AccumProfit));

    }
}