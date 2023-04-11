// You may need to add extra methods as some parts may be incomplete
// These files do not have proper comments, you need to add those
// Some checks on Input values are missing, you need to add those


import java.util.*;

import javax.swing.*;  

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CgtInterface4
{
    private User user = new User();
    
    
    public void run()
    {
        String inputName;                     
        double inputAnnualSalary;
        double inputBuyingPrice;
        double inputSellingPrice;
        int inputYears;
        boolean inputResident;

        double inputYear1InvestmentAmount;
        double inputYear2InvestmentAmount;
        double inputYear3InvestmentAmount;
        int inputCoinSelection;

        
        Scanner console = new Scanner (System.in);

        // Get user information with GUI
        //System.out.print("Enter name: ");
        //inputName = console.nextLine();
        
        inputName = JOptionPane.showInputDialog("Enter name:");
        
        inputAnnualSalary = Integer.parseInt(JOptionPane.showInputDialog("Enter Salary: "));
        
        while(inputAnnualSalary<=0)
        {
           inputAnnualSalary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary again ( >0): "));
        }

        inputBuyingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Buying Price: "));

        while(inputBuyingPrice<=0)
        {
           inputBuyingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Buying Price again ( >0): "));
        }
        
        inputSellingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Selling Price: "));

        while(inputSellingPrice<= inputBuyingPrice)
        {
            String message = "Enter Selling Price again ( >0) greater than : "+ inputBuyingPrice;    
             inputSellingPrice = Double.parseDouble(JOptionPane.showInputDialog(message));
        }
       
        inputYears = Integer.parseInt(JOptionPane.showInputDialog("Enter Years: "));
        while(inputYears<=0)
        {
           inputYears = Integer.parseInt(JOptionPane.showInputDialog("Enter Years again (>0): "));
        }
    
        inputResident = Boolean.parseBoolean(JOptionPane.showInputDialog("Enter Resident true or false: "));

        user.setUserDetails(inputName, inputAnnualSalary, inputBuyingPrice, 
            inputSellingPrice, inputYears, inputResident );

        // Calculate and display user and cgt details
    //    JOptionPane.showMessageDialog(null, user.mCalculateCGT() );
    
        //Code for asking if you want to invest--to be added here
        
        
        // Get investment information 
        //Checks for the deposits and coin selection to be added 
        
        inputYear1InvestmentAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Year 1 investment amount: "));

        inputYear2InvestmentAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Year 2 investment amount: "));
    
        inputYear3InvestmentAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Year 3 investment amount: "));;

        inputCoinSelection = Integer.parseInt(JOptionPane.showInputDialog("Enter coin selection: "));

        // Calculate and display investment information
        user.setInvestment (inputYear1InvestmentAmount, inputYear2InvestmentAmount,
            inputYear3InvestmentAmount, inputCoinSelection);

        // Calculate and display user and cgt details
        JOptionPane.showMessageDialog(null, new JTextArea(user.printCrypto()));   
        
        
        //Writing to a file
        String fileName = "out.txt";
        String fileOutput = user.printCrypto();
        PrintWriter outputStream;
        
        try
        {
            outputStream = new PrintWriter (new FileOutputStream(fileName, true));
            outputStream.println(user.printCrypto());
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error Opeing File " + fileName);
            System.exit(0);
        }
        
        
    }

    public static void main(String[] args)
    {
        CgtInterface4 calc = new CgtInterface4();
        calc.run();
    }
}