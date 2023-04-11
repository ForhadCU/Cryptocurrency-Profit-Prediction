import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private Investment investAccount;
    private ArrayList<Investment> investments = new ArrayList<>();
    private double annualSalary;
    private double buyingPrice;
    private double sellingPrice;
    private int years;
    private boolean resident;
    private double actualCGTProfit;
    Map<String, Object> cgtDetailsMap = new HashMap<>();

    // Constructor
    public User() {
        // Assign initial values
        investAccount = new Investment();
        name = "";
        annualSalary = 0;
        buyingPrice = 0;
        sellingPrice = 0;
        years = 0;
        resident = true;
    }

    public String getName() {
        // Get user name
        return (name);
    }

    public boolean getRecidencyStatus() {
        // Get user's residency status
        return (resident);
    }

    public double getBuyingPrice() {
        // Get user's crypto buying price
        return (buyingPrice);
    }

    // Get user's crypto selling price
    public double getSellingPrice() {
        // Get user's crypto selling price
        return (sellingPrice);
    }

    public double getAnnualSalary() {
        // Get user's annual salary
        return (annualSalary);
    }

    public ArrayList<Investment> getInvestments() {
        // Get all investments
        return (investments);
    }

    // Get Capital Gain Tax Details
    public Map<String, Object> getCgtDetailsMap() {
        return cgtDetailsMap;
    }

    public void setCgtDetailsMap(Map<String, Object> cgtDetailsMap) {
        // Set Capital Gain Tax Details
        this.cgtDetailsMap = cgtDetailsMap;
    }

    public void setName(String inputName) {
        // Set user's name
        name = inputName;
    }

    public void setInvestments(ArrayList<Investment> investments) {
        // Set all investments
        this.investments = investments;
    }

    public void setInvestment(double amt1, double amt2, double amt3, int sel) {
        // Set each investment
        // initialize investment class object
        investAccount = new Investment();

        // Call public method from Investment class and set investment information to the obejct
        investAccount.setAllDeposits(amt1, amt2, amt3);
        investAccount.setcoinSelection(sel);

        // Add each object to a Object ArrayList
        investments.add(investAccount);
    }

    public void setUserDetails(String newName, double newAnnualSalary,
            double newBuyingPrice, double newSellingPrice,
            int newYears, boolean newResident) {

        // Assign values 
        name = newName;
        annualSalary = newAnnualSalary;
        buyingPrice = newBuyingPrice;
        sellingPrice = newSellingPrice;
        years = newYears;
        resident = newResident;
    }

    public void mCalculateCGT() {
        double CGTTaxableProfit = (sellingPrice - buyingPrice) / years;
        double annualIncome = annualSalary + CGTTaxableProfit;
        double taxRate = 0;

        // Check resident status and assign tax rate according to annual income
        if (resident) {
            if (annualIncome <= 18200)
                taxRate = 0;
            else if (annualIncome <= 45000)
                taxRate = 0.19;
            else if (annualIncome <= 120000)
                taxRate = 0.325;
            else if (annualIncome <= 180000)
                taxRate = 0.37;
            else
                taxRate = 0.45;
        } else {
            if (annualIncome <= 120000)
                taxRate = 0.325;
            else if (annualIncome <= 180000)
                taxRate = 0.37;
            else
                taxRate = 0.45;
        }

        // Calculate CGT 
        double CGTtax = taxRate * CGTTaxableProfit;

        // Calculate actual profit
        actualCGTProfit = CGTTaxableProfit - CGTtax;

        // Put data into map
        cgtDetailsMap.put("taxRate", taxRate);
        cgtDetailsMap.put("CGTtax", CGTtax);
        cgtDetailsMap.put("actualCGTProfit", actualCGTProfit);
        cgtDetailsMap.put("remainingActualCGTProfit", actualCGTProfit);
    }

    public void mShowCryptoTable() {
        investAccount.mShowCryptoTable();
    }

    public void mCalculateCrypto() {
        investAccount.mCalculateCrypto();
    }
}
