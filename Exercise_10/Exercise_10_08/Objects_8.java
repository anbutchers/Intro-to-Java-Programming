/*********************************************************************************
* (Financial: the Tax class) Programming Exercise 8.12 writes a program for      *
* computing taxes using arrays. Design a class named Tax to contain the          *
* following instance data fields:                                                *
*                                                                                *
* ■ int filingStatus: One of the four tax-filing statuses: 0—single filer, 1—    *
* 	 married filing jointly or qualifying widow(er), 2—married filing separately, *
* 	 and 3—head of household. Use the public static constants SINGLE_FILER        *  
* 	 (0), MARRIED_JOINTLY_OR_QUALIFYING_WIDOW(ER) (1), MARRIED_                   *
* 	 SEPARATELY (2), HEAD_OF_HOUSEHOLD (3) to represent the statuses.             *
* ■ int[][] brackets: Stores the tax brackets for each filing status.            *
* ■ double[] rates: Stores the tax rates for each bracket.                       *
* ■ double taxableIncome: Stores the taxable income.                             *
*                                                                                *
* Provide the getter and setter methods for each data field and the getTax()     *
* method that returns the tax. Also provide a no-arg constructor and the         *
* constructor Tax(filingStatus, brackets, rates, taxableIncome).                 *
*                                                                                *
* Draw the UML diagram for the class and then implement the class. Write a test  *
* program that uses the Tax class to print the 2001 and 2009 tax tables for      *
* taxable income from $50,000 to $60,000 with intervals of $1,000 for all four   *
* statuses. The tax rates for the year 2009 were given in Table 3.2. The tax     *
* rates for 2001 are shown in Table 10.1.                                        *
*********************************************************************************/
public class Objects_8
{
   public static void main(String[] args)
   {
      Tax tax2001 = new Tax();
      Tax tax2009 = new Tax();
      
      double[] rates2009 = {10, 15, 25, 28, 33, 35};
		tax2009.setRates(rates2009);

		int[][] brackets2009 = {
			{8350, 16700, 8350, 11950},  
         {33950, 67900, 33950, 45500}, 
			{82250, 137050, 68525, 117450},
			{171550, 208850, 104425, 190200},
         {372950, 372950, 186475, 372950}
		};
      tax2009.setBrackets(brackets2009);
      for (int i = 50000; i <= 60000; i+=1000)
      {
         tax2001.setTaxableIncome(i);
         for (int j = 0; j < 4; j++)
         {
            tax2001.setFilingStatus(j);
            System.out.printf("%7.1f%10s", tax2001.getTax(), "");
         }
         System.out.println();
      }
      System.out.println();
      for (int i = 50000; i <= 60000; i+=1000)
      {
         tax2009.setTaxableIncome(i);
         for (int j = 0; j < 4; j++)
         {
            tax2009.setFilingStatus(j);
            System.out.printf("%7.1f%10s", tax2009.getTax(), "");
         }
         System.out.println();
      }
   }
}

class Tax
{
   private int filingStatus;
   public static final int SINGLE_FILER = 0;
   public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
   public static final int MARRIED_SEPARATELY = 2;
   public static final int HEAD_OF_HOUSEHOLD = 3;
   
   private int[][] brackets;
   private double[] rates;
   private double taxableIncome;
   
   public Tax()
   {
      this.filingStatus = SINGLE_FILER;
      int[][] newB = {
			{27050, 45200, 22600, 36250},
			{65550, 109250, 54625, 93650},
			{136750, 166500, 83250, 151650},
			{297350, 297350, 148675, 297350}
		};
      this.brackets = newB;
      double[] newR = {15, 27.5, 30.5, 35.5, 39.1};
      this.rates = newR;
      this.taxableIncome = 0;
   }
   
   public Tax(int filingStatus, int[][] brackets, double[] rates, double taxableIncome)
   {
      this.filingStatus = filingStatus;
      setBrackets(brackets);
      setRates(rates);
      this.taxableIncome = taxableIncome;
   }
   
   public int getFilingStatus()
   {
      return filingStatus;
   }
   
   public void setFilingStatus(int filingStatus)
   {
      this.filingStatus = filingStatus;
   }
   
   public int[][] getBrackets()
   {
      return brackets;
   }
   
   public void setBrackets(int[][] brackets)
   {
      this.brackets = new int[brackets.length][brackets[0].length];
      for (int i = 0; i < brackets.length; i++)
      {
         for (int j = 0; j < brackets[i].length; j++)
            this.brackets[i][j] = brackets[i][j];
      }
   }
   
   public double[] getRates()
   {
      return rates;
   }
   
   public void setRates(double[] rates)
   {
      this.rates = new double[rates.length];
      for (int i = 0; i < rates.length; i++)
         this.rates[i] = rates[i];
   }
   
   public double getTaxableIncome()
   {
      return taxableIncome;
   }
   
   public void setTaxableIncome(double taxableIncome)
   {
      this.taxableIncome = taxableIncome;
   }
   
   public double getTax()
   {
      double tax = 0;
      double income = taxableIncome;
      for (int i = rates.length-1; i > 0; i--)
      {
         if (income <= brackets[i-1][filingStatus])
            continue;
         tax += (income - brackets[i-1][filingStatus]) * rates[i] / 100;
         income = income - brackets[i-1][filingStatus];
      }
      return tax += brackets[0][filingStatus] * rates[0] / 100;
   }
}
