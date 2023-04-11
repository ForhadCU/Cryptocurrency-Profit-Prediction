import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// You may need to add extra methods as some parts may be incomplete
// These files do not have proper comments, you need to add those
// Some checks on Input values are missing, you need to add those

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

    public User() {
        investAccount = new Investment();
        name = "";
        annualSalary = 0;
        buyingPrice = 0;
        sellingPrice = 0;
        years = 0;
        resident = true;
    }

    public String getName() {
        return (name);
    }

    public boolean getRecidencyStatus() {
        return (resident);
    }

    public double getBuyingPrice(){
        return(buyingPrice)
        ;
    }

    public double getSellingPrice(){
        return(sellingPrice)
        ;
    }

    public double getAnnualSalary()
    {
        return(annualSalary)
        ;
    }


    public ArrayList<Investment> getInvestments() {
        return (investments);
    }

    public Map<String, Object> getCgtDetailsMap() {
        return cgtDetailsMap;
    }

    public void setCgtDetailsMap(Map<String, Object> cgtDetailsMap) {
        this.cgtDetailsMap = cgtDetailsMap;
    }

    public void setName(String inputName) {
        name = inputName;
    }

    public void setInvestments(ArrayList<Investment> investments)
    {
        this.investments = investments;
    }

    public void setInvestment(double amt1, double amt2, double amt3, int sel) {
        investAccount = new Investment();
        investAccount.setAllDeposits(amt1, amt2, amt3);
        investAccount.setcoinSelection(sel);

        investments.add(investAccount);
    }

    public void setUserDetails(String newName, double newAnnualSalary,
            double newBuyingPrice, double newSellingPrice,
            int newYears, boolean newResident) {
        name = newName;
        annualSalary = newAnnualSalary;
        buyingPrice = newBuyingPrice;
        sellingPrice = newSellingPrice;
        years = newYears;
        resident = newResident;
    }

    public void mCalculateCGT() {
        // String cgtDetails = "";

        /*  cgtDetails = "\n\nName: " + name +
                "\nAnnual Salary: " + annualSalary +
                "\nResident: " + resident;
        
        cgtDetails += "\nCryptocurrency: " + "\nBuying Price: " + buyingPrice +
                "\nSelling Price: " + sellingPrice + "\nYears: " + years; */

        double CGTTaxableProfit = (sellingPrice - buyingPrice) / years;
        double annualIncome = annualSalary + CGTTaxableProfit;
        double taxRate = 0;
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

        double CGTtax = taxRate * CGTTaxableProfit;
        actualCGTProfit = CGTTaxableProfit - CGTtax;

        /* cgtDetails = cgtDetails + "\n\nCapital Gains Tax:" +
                "\nTax Rate: " + taxRate + "\nCGT: " + CGTtax + "\nProfit: " + actualCGTProfit;
        */
        cgtDetailsMap.put("taxRate", taxRate);
        cgtDetailsMap.put("CGTtax", CGTtax);
        cgtDetailsMap.put("actualCGTProfit", actualCGTProfit);
        cgtDetailsMap.put("remainingActualCGTProfit", actualCGTProfit);

        // return (cgtDetailsMap);
    }

    public String printCrypto() {
        return investAccount.printCrypto();
    }

    public void mShowCryptoTable() {
        investAccount.mShowCryptoTable();
    }

    public void mCalculateCrypto() {
        investAccount.mCalculateCrypto();
    }
}
