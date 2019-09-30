
package com.qualtech.finbit.utils;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
 
import org.apache.log4j.Logger;
 
import com.qualtech.api.db.PropertyFile;
import com.qualtech.finbit.api.response.AtmWithdrawls;
import com.qualtech.finbit.api.response.AverageMonthlyBalance;
import com.qualtech.finbit.api.response.AverageQuarterlyBalance;
import com.qualtech.finbit.api.response.BankAccount;
import com.qualtech.finbit.api.response.CashDeposit;
import com.qualtech.finbit.api.response.FinbitResResult;
import com.qualtech.finbit.api.response.HighValueTransaction;
import com.qualtech.finbit.api.response.Incomes;
import com.qualtech.finbit.api.response.MinimumBalance;
import com.qualtech.finbit.api.response.MoneyReceivedTransactions;
import com.qualtech.karza.api.utils.TestSample;
 
public class HtmlCreator 
{
    static Logger logger = Logger.getLogger(HtmlCreator.class.getName());
    public String generateHtml(List<FinbitResResult> results,PropertyFile resProp) 
    {
        StringBuffer buffer = new StringBuffer();
        String logopath=resProp.getString("com.qualtech.finbit.logo");
        try 
        {
            for(FinbitResResult result : results) 
            {
        BankAccount bankAccount = result.getBank_account();
        List<CashDeposit> cashDepost = result.getCash_deposits();
        List<AtmWithdrawls> atmwithdrawls = result.getAtm_withdrawls();
        List<HighValueTransaction> highValue = result.getHigh_value_transactions();     
        List<AverageMonthlyBalance> avgmonthlybal = result.getAverageMonthlyBalance();
        List<AverageQuarterlyBalance> avgqtrbal = result.getAverageQuarterlyBalance();
        List<MinimumBalance> minimumBalance = result.getMinimum_balances();
        List<MoneyReceivedTransactions> moneyRecvedTransaction = result.getMoney_received_transactions();
        List<Incomes>income = result.getIncomes();
            buffer.append("<html><head><title></title></head>");
            buffer.append("<body>");     
            buffer.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            buffer.append("<tr>");
            buffer.append("<td align='right' width='100%'>");
            buffer.append("<img src='"+logopath+"' width='100%' align='right'></img>");
            buffer.append("</td>");
            buffer.append("<tr>");
            buffer.append("<td align='right' width='100%'>");buffer.append("<font size='0.5' face='arial' color='#337BB6'>");buffer.append(date());buffer.append("</font>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            buffer.append("<tr>");
            buffer.append("<td align='center' width='100%'>");buffer.append("<strong>");
            buffer.append("Statement of FINBIT");
            buffer.append("</td>");buffer.append("</strong>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("<br/>"); 
            buffer.append(customerDetails(bankAccount));
            buffer.append(atmAnalysis(atmwithdrawls,bankAccount));
            buffer.append(monthlybalanceAnalysis(avgmonthlybal,bankAccount));
            buffer.append(quarterlyBalanceAnalysis(avgqtrbal,bankAccount));
            buffer.append(cashAnalysis(cashDepost,bankAccount));
            buffer.append(highValue(highValue,bankAccount));
            buffer.append(incomeAnalysis(income,bankAccount));
            buffer.append(minimumBalanceAnalyses(minimumBalance,bankAccount));
            buffer.append(moneyReceivedAnalysis(moneyRecvedTransaction,bankAccount));
            buffer.append("</body>");
            buffer.append("</html>");
        }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || generateHtml() || Exception :: "+exception);
        }
        return buffer.toString();
    }
    private Object customerDetails(BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        String address = null;
        String customerId = null;
        String contactNo = null;
        try
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
                if(bankAccount.getAddress()!=null && !("").equals(bankAccount.getAddress()))
                {
                    address = bankAccount.getAddress();
                }
                if(bankAccount.getCustomerID()!=null && !("").equals(bankAccount.getCustomerID()))
                {
                    customerId = bankAccount.getCustomerID();
                }
                if(bankAccount.getCustomerPhoneNo()!=null && !("").equals(bankAccount.getCustomerPhoneNo()))
                {
                    contactNo = bankAccount.getCustomerPhoneNo();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || customerDetails() || Exception iterating BankAccount :: "+ exception);
        }
        try 
        {
            if(bankAccount!=null)
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td font size=\"2\" color=\"black\">");
                    buffer.append("<strong>");buffer.append((accountHolder!=null?accountHolder:"")+"</span>");buffer.append("</strong>");
                    buffer.append("<br/>");
                    buffer.append("<strong><font color=\"#337BB6\">"+"Customer Id : "+"</strong></font>"+(customerId!=null?customerId:"")+"</span>");
                    buffer.append("<br/>");
                    buffer.append("<B><font color=\"#337BB6\">"+"Contact No. : "+"</B></font>"+(contactNo!=null?contactNo:"")+"</span>");
                    buffer.append("<br/>");
                    buffer.append("<strong><font color=\"#337BB6\">"+"Address : "+"</strong></font>"+(address!=null?address:"")+"</span>");
                    buffer.append("</td>");
                    buffer.append("<td font size=\"2\" color=\"black\">");
                    buffer.append("<strong><font color=\"#337BB6\">"+"Bank : "+"</strong></font>"+(bank!=null?bank:"")+"</span>");
                    buffer.append("<br/>");
                    buffer.append("<strong><font color=\"#337BB6\">"+"A/C No. : "+"</strong></font>"+(accountNumber!=null?accountNumber:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<br/>");
            }
        }
        catch (Exception e) 
        {
        	 logger.error(" HtmlCreator || customerDetails() || Exception iterating BankAccount :: "+ e);
        }
        return buffer.toString();
    }
    private String incomeAnalysis(List<Incomes> income, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || incomeAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
        try
        {
            if(income!=null && !income.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Income Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center; font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\";><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Amount</strong></td>");
                    buffer.append("<td align=center><strong> Balance After Transaction </strong></td>");
                    buffer.append("<td align=center><strong> Bank </strong></td>");
                    buffer.append("<td align=center><strong> Category </strong></td>");
                    buffer.append("<td align=center><strong> Date </strong></td>");
                    buffer.append("<td align=center><strong> Description </strong></td>");
                    buffer.append("<td align=center><strong> Is Salary </strong></td>");
                    buffer.append("<td align=center><strong> Is Salary Check </strong></td>");
                    buffer.append("<td align=center><strong> Month And Year </strong></td>");
                    buffer.append("<td align=center><strong> Total </strong></td>");
                    buffer.append("<td align=center><strong> Transaction Type </strong></td>");
                buffer.append("</tr>");
                int loopCounter = 1;
                String date = null;
                String description = null;
                String amount = null;
                String balanceAfterTransaction=null;
                String category=null;
                String monthandyear = null;
                String total=null;
                String transactionType=null;
                String issalary=null;
                String issalarycheck=null;
                for(Incomes list : income)
                {
                    if(list.getAmount()!=null)
                    {
                        amount=list.getAmount();
                    }
                    if(list.getBalanceAfterTransaction()!=null)
                    {
                        balanceAfterTransaction=list.getBalanceAfterTransaction();
                    }
                    if(list.getBank()!=null)
                    {
                        bank = list.getBank();
                    }
                    if(list.getCategory()!=null)
                    {
                        category = list.getCategory();
                    }
                    if(list.getDate()!=null)
                    {
                        date = list.getDate();
                    }
                    if(list.getDescription()!=null)
                    {
                        description=list.getDescription();
                    }
                    if(list.getMonthAndYear()!=null)
                    {
                        monthandyear=list.getMonthAndYear();
                    }
                    if(list.getTotal()!=null)
                    {
                        total=list.getTotal();
                    }
                    if(list.getTransactionType()!=null)
                    {
                        transactionType=list.getTransactionType();
                    }
                    if(list.getIsSalary()!=null)
                    {
                        issalary=list.getIsSalary();
                    }
                    if(list.getIsSalaryCheck()!=null)
                    {
                        issalarycheck=list.getIsSalaryCheck();
                    }
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(amount!= null?amount:" ") +"</td>");
                        buffer.append("<td align=right>"+(balanceAfterTransaction != null ?balanceAfterTransaction : "")+"</td>");
                        buffer.append("<td align=left>"+(bank != null ?bank : "")+"</td>");
                        buffer.append("<td align=left>"+(category != null ?category : "")+"</td>");
                        buffer.append("<td align=center>"+(date != null ?date : "")+"</td>");
                        buffer.append("<td align=left>"+(description != null ?description : "")+"</td>");
                        buffer.append("<td align=right>"+(issalary != null ?issalary : "")+"</td>");
                        buffer.append("<td align=right>"+(issalarycheck != null ?issalarycheck : "")+"</td>");
                        buffer.append("<td align=center>"+(monthandyear != null ?monthandyear : "")+"</td>");
                        buffer.append("<td align=right>"+( total != null ?total : "")+"</td>");
                        buffer.append("<td align=left>"+(transactionType != null ?transactionType : "")+"</td>");
                    buffer.append("</tr>");
                    loopCounter++;
                }
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || incomeAnalysis() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
    private String moneyReceivedAnalysis(List<MoneyReceivedTransactions> moneyReceived, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || cashAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
        try
        {
            if(moneyReceived!=null && !moneyReceived.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Money Received Transaction</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center;  font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\";><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Amount</strong></td>");
                    buffer.append("<td align=center><strong> Balance After Transaction </strong></td>");
                    buffer.append("<td align=center><strong> Bank </strong></td>");
                    buffer.append("<td align=center><strong> Category </strong></td>");
                    buffer.append("<td align=center><strong> Date </strong></td>");
                    buffer.append("<td align=center><strong> Description </strong></td>");
                    buffer.append("<td align=center><strong> Month And Year </strong></td>");
                    buffer.append("<td align=center><strong> Total </strong></td>");
                    buffer.append("<td align=center><strong> Transaction Type </strong></td>");
                buffer.append("</tr>");
                int loopCounter = 1;
                String date = null;
                String description = null;
                String amount = null;
                String balanceAfterTransaction=null;
                String category=null;
                String monthandyear = null;
                String total=null;
                String transactionType=null;
                for(MoneyReceivedTransactions list : moneyReceived)
                {
                    if(list.getAmount()!=null)
                    {
                        amount=list.getAmount();
                    }
                    if(list.getBalanceAfterTransaction()!=null)
                    {
                        balanceAfterTransaction=list.getBalanceAfterTransaction();
                    }
                    if(list.getBank()!=null)
                    {
                        bank = list.getBank();
                    }
                    if(list.getCategory()!=null)
                    {
                        category = list.getCategory();
                    }
                    if(list.getDate()!=null)
                    {
                        date = list.getDate();
                    }
                    if(list.getDescription()!=null)
                    {
                        description=list.getDescription();
                    }
                    if(list.getMonthAndYear()!=null)
                    {
                        monthandyear=list.getMonthAndYear();
                    }
                    if(list.getTotal()!=null)
                    {
                        total=list.getTotal();
                    }
                    if(list.getTransactionType()!=null)
                    {
                        transactionType=list.getTransactionType();
                    }
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(amount!= null?amount:" ") +"</td>");
                        buffer.append("<td align=right>"+(balanceAfterTransaction != null ?balanceAfterTransaction : "")+"</td>");
                        buffer.append("<td align=left>"+(bank != null ?bank : "")+"</td>");
                        buffer.append("<td align=left>"+(category != null ?category : "")+"</td>");
                        buffer.append("<td align=center>"+(date != null ?date : "")+"</td>");
                        buffer.append("<td align=left>"+(description != null ?description : "")+"</td>");
                        buffer.append("<td align=center>"+(monthandyear != null ?monthandyear : "")+"</td>");
                        buffer.append("<td align=right>"+( total != null ?total : "")+"</td>");
                        buffer.append("<td align=left>"+(transactionType != null ?transactionType : "")+"</td>");
                    buffer.append("</tr>");
                    loopCounter++;
                }
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || moneyReceivedAnalysis() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
     
    private String minimumBalanceAnalyses(List<MinimumBalance> minimumBal, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || cashAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
        try
        {
            if(minimumBal!=null && !minimumBal.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Minimum Balance Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center;  font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\";><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Amount</strong></td>");
                    buffer.append("<td align=center><strong> Balance After Transaction </strong></td>");
                    buffer.append("<td align=center><strong> Bank </strong></td>");
                    buffer.append("<td align=center><strong> Category </strong></td>");
                    buffer.append("<td align=center><strong> Date </strong></td>");
                    buffer.append("<td align=center><strong> Description </strong></td>");
                    buffer.append("<td align=center><strong> Transaction Type </strong></td>");
                buffer.append("</tr>");
                int loopCounter = 1;
                String date = null;
                String description = null;
                String amount = null;
                String balanceAfterTransaction=null;
                String category=null;
                String transactionType=null;
                for(MinimumBalance list : minimumBal)
                {
                    if(list.getAmount()!=null)
                    {
                        amount=list.getAmount();
                    }
                    if(list.getBalanceAfterTransaction()!=null)
                    {
                        balanceAfterTransaction=list.getBalanceAfterTransaction();
                    }
                    if(list.getBank()!=null)
                    {
                        bank = list.getBank();
                    }
                    if(list.getCategory()!=null)
                    {
                        category = list.getCategory();
                    }
                    if(list.getDate()!=null)
                    {
                        date = list.getDate();
                    }
                    if(list.getDescription()!=null)
                    {
                        description=list.getDescription();
                    }
                    if(list.getTransactionType()!=null)
                    {
                        transactionType=list.getTransactionType();
                    }
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(amount!= null?amount:" ") +"</td>");
                        buffer.append("<td align=right>"+(balanceAfterTransaction != null ?balanceAfterTransaction : "")+"</td>");
                        buffer.append("<td align=left>"+(bank != null ?bank : "")+"</td>");
                        buffer.append("<td align=left>"+(category != null ?category : "")+"</td>");
                        buffer.append("<td align=center>"+(date != null ?date : "")+"</td>");
                        buffer.append("<td align=left>"+(description != null ?description : "")+"</td>");
                        buffer.append("<td align=left>"+(transactionType != null ?transactionType : "")+"</td>");
                    buffer.append("</tr>");
                    loopCounter++;
                }
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || minimumBalanceAnalyses() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
     
    private String cashAnalysis(List<CashDeposit> cashDepost, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || cashAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
        try
        {
            if(cashDepost!=null && !cashDepost.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Cash Deposit Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center; font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\";><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Amount</strong></td>");
                    buffer.append("<td align=center><strong> Balance After Transaction </strong></td>");
                    buffer.append("<td align=center><strong> Bank </strong></td>");
                    buffer.append("<td align=center><strong> Category </strong></td>");
                    buffer.append("<td align=center><strong> Date </strong></td>");
                    buffer.append("<td align=center><strong> Description </strong></td>");
                    buffer.append("<td align=center><strong> Total </strong></td>");
                    buffer.append("<td align=center><strong> Transaction Type </strong></td>");
                buffer.append("</tr>");
                int loopCounter = 1;
                String date = null;
                String description = null;
                String amount = null;
                String balanceAfterTransaction=null;
                String category=null;
                String total=null;
                String transactionType=null;
                for(CashDeposit list : cashDepost)
                {
                    if(list.getAmount()!=null)
                    {
                        amount=list.getAmount();
                    }
                    if(list.getBalanceAfterTransaction()!=null)
                    {
                        balanceAfterTransaction=list.getBalanceAfterTransaction();
                    }
                    if(list.getBank()!=null)
                    {
                        bank = list.getBank();
                    }
                    if(list.getCategory()!=null)
                    {
                        category = list.getCategory();
                    }
                    if(list.getDate()!=null)
                    {
                        date = list.getDate();
                    }
                    if(list.getDescription()!=null)
                    {
                        description=list.getDescription();
                    }
                    if(list.getTotal()!=null)
                    {
                        total=list.getTotal();
                    }
                    if(list.getTransactionType()!=null)
                    {
                        transactionType=list.getTransactionType();
                    }
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(amount!= null?amount:" ") +"</td>");
                        buffer.append("<td align=right>"+(balanceAfterTransaction != null ?balanceAfterTransaction : "")+"</td>");
                        buffer.append("<td align=left>"+(bank != null ?bank : "")+"</td>");
                        buffer.append("<td align=left>"+(category != null ?category : "")+"</td>");
                        buffer.append("<td align=center>"+(date != null ?date : "")+"</td>");
                        buffer.append("<td align=left>"+(description != null ?description : "")+"</td>");
                        buffer.append("<td align=right>"+(total != null ?total : "")+"</td>");
                        buffer.append("<td align=left>"+(transactionType != null ?transactionType : "")+"</td>");
                    buffer.append("</tr>");
                    loopCounter++;
                }
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || cashAnalysis() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
     
    private String highValue(List<HighValueTransaction> highvaltran, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || highValue() || Exception iterating BankAccount :: "+ exception);
        }
        try
        {
            if(highvaltran!=null && !highvaltran.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>High Value Transaction Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center;  font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\";><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Amount</strong></td>");
                    buffer.append("<td align=center><strong> Balance After Transaction </strong></td>");
                    buffer.append("<td align=center><strong> Bank </strong></td>");
                    buffer.append("<td align=center><strong> Category </strong></td>");
                    buffer.append("<td align=center><strong> Date </strong></td>");
                    buffer.append("<td align=center><strong> Description </strong></td>");
                    buffer.append("<td align=center><strong> Type </strong></td>");
                buffer.append("</tr>");
                int loopCounter = 1;
                String date = null;
                String description = null;
                String amount = null;
                String balanceAfterTransaction=null;
                String category=null;
                String type=null;
                for(HighValueTransaction list : highvaltran)
                {
                    if(list.getAmount()!=null)
                    {
                        amount=list.getAmount();
                    }
                    if(list.getBalanceAfterTransaction()!=null)
                    {
                        balanceAfterTransaction=list.getBalanceAfterTransaction();
                    }
                    if(list.getBank()!=null)
                    {
                        bank = list.getBank();
                    }
                    if(list.getCategory()!=null)
                    {
                        category = list.getCategory();
                    }
                    if(list.getDate()!=null)
                    {
                        date = list.getDate();
                    }
                    if(list.getDescription()!=null)
                    {
                        description=list.getDescription();
                    }
                    if(list.getType()!=null)
                    {
                        type=list.getType();
                    }
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(amount!= null?amount:" ") +"</td>");
                        buffer.append("<td align=right>"+(balanceAfterTransaction != null ?balanceAfterTransaction : "")+"</td>");
                        buffer.append("<td align=left>"+(bank != null ?bank : "")+"</td>");
                        buffer.append("<td align=left>"+(category != null ?category : "")+"</td>");
                        buffer.append("<td align=center>"+(date != null ?date : "")+"</td>");
                        buffer.append("<td align=left>"+(description != null ?description : "")+"</td>");
                        buffer.append("<td align=left>"+(type != null ?type : "")+"</td>");
                    buffer.append("</tr>");
                    loopCounter++;
                }
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || highValue() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
     
    private String atmAnalysis(List<AtmWithdrawls> atmwithdraw, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || atmAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
        try
        {
            if(atmwithdraw!=null && !atmwithdraw.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Atm Withdrawls Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center;  font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\";><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center  width=\"12%\";><strong>Amount</strong></td>");
                    buffer.append("<td align=center><strong> Bank </strong></td>");
                    buffer.append("<td align=center width=\"15%\";><strong> Date </strong></td>");
                    buffer.append("<td align=center><strong> Description </strong></td>");
                buffer.append("</tr>");
                int loopCounter = 1;
                String date = null;
                String description = null;
                String amount = null;
                 for(AtmWithdrawls list : atmwithdraw)
                {
                    if(list.getAmount()!=null)
                    {
                        amount=list.getAmount();
                    }
                    if(list.getBank()!=null)
                    {
                        bank = list.getBank();
                    }
                    if(list.getDate()!=null)
                    {
                        date = list.getDate();
                    }
                    if(list.getDescription()!=null)
                    {
                        description=list.getDescription();
                    }
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(amount!= null?amount:" ") +"</td>");
                        buffer.append("<td align=left>"+(bank != null ?bank : "")+"</td>");
                        buffer.append("<td align=center>"+(date != null ?date : "")+"</td>");
                        buffer.append("<td align=left>"+(description != null ?description : "")+"</td>");
                    buffer.append("</tr>");
                     
                    loopCounter++;
                }
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || atmAnalysis() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
    private String quarterlyBalanceAnalysis(List<AverageQuarterlyBalance> averageQuarterlyBalance, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || cashAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
         
        try
        {
            if(averageQuarterlyBalance!=null && !averageQuarterlyBalance.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                 
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Average Monthly Balance Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center;  font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\"><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Net Average Balance</strong></td>");
                    buffer.append("<td align=center><strong> Month And Year </strong></td>");
                buffer.append("</tr>");
                 
                int loopCounter = 1;
                String netAvgBal= null;
                String monthandyear = null;
                                 
                 
                 for(AverageQuarterlyBalance list : averageQuarterlyBalance)
                {
                    if(list.getNetAverageBalance()!=null)
                    {
                        netAvgBal=list.getNetAverageBalance();
                    }
                    if(list.getMonthAndYear()!=null)
                    {
                        monthandyear=list.getMonthAndYear();
                    }
                     
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(netAvgBal!= null?netAvgBal:" ") +"</td>");
                        buffer.append("<td align=center>"+(monthandyear != null ?monthandyear : "")+"</td>");
                    buffer.append("</tr>");
                     
                    loopCounter++;
                }
                 
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
 
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || quarterlyBalanceAnalysis() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
     
    private String monthlybalanceAnalysis(List<AverageMonthlyBalance> averagemonthlybalance, BankAccount bankAccount) 
    {
        StringBuffer buffer = new StringBuffer();
        String bank = null;
        String accountNumber = null;
        String accountHolder = null;
         
        try 
        {
            if(bankAccount!=null && !("").equals(bankAccount))
            {
                if(bankAccount.getAccountDetailsForAccountListPage()!=null && !("").equals(bankAccount.getAccountDetailsForAccountListPage()))
                {
                    bank = bankAccount.getAccountDetailsForAccountListPage();
                }
                if(bankAccount.getAccountNo()!=null && !("").equals(bankAccount.getAccountNo()))
                {
                    accountNumber = bankAccount.getAccountNo();
                }
                if(bankAccount.getAccountHolder()!=null && !("").equals(bankAccount.getAccountHolder()))
                {
                    accountHolder = bankAccount.getAccountHolder();
                }
            }
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || cashAnalysis() || Exception iterating BankAccount :: "+ exception);
        }
         
        try
        {
            if(averagemonthlybalance!=null && !averagemonthlybalance.isEmpty())
            {
                buffer.append("<table style='height: 5px; margin-left: auto; margin-right: auto; padding:1% 1% 1% 1%; width:100%;' cellspacing='0' cellpadding='0' bgcolor='#337BB6'>");
                buffer.append("<tbody>");
                 
                buffer.append("<tr>");
                    buffer.append("<td style='width:100%;' align='center' height='2' font-size:'20px'>");
                    buffer.append("<span style='color: #aacbe9;'>");
                    buffer.append("<p style='font-weight: bold;'>Average Monthly Balance Analysis</p>");
                    buffer.append("</span>");
                    buffer.append("<span style='color: #aacbe9; font-size: small;'>");
                    buffer.append("Bank : "+(bank!=null?bank:"") +", A/C No. : "+(accountNumber!=null?accountNumber:"")+", Name : "+(accountHolder!=null?accountHolder:"")+"</span>");
                    buffer.append("</td>");
                buffer.append("</tr>");         
                buffer.append("</tbody>");
                buffer.append("</table>");
                buffer.append("<table style='height: 11px; margin-left: auto; margin-right: auto; text-align:center;  font-size: 11px; width:100%;' border='.2'>");
                buffer.append("<tbody>");
                buffer.append("<tr>");
                    buffer.append("<td align=center width=\"6%\"><strong>Seq_id</strong></td>");
                    buffer.append("<td align=center><strong>Net Average Balance</strong></td>");
                    buffer.append("<td align=center><strong> Month And Year </strong></td>");
                    buffer.append("<td align=center><strong> 1 Day Balance</strong></td>");
                    buffer.append("<td align=center><strong> 5 Day Balance</strong></td>");
                    buffer.append("<td align=center><strong> 10 Day Balance</strong></td>");
                    buffer.append("<td align=center><strong> 15 Day Balance</strong></td>");
                    buffer.append("<td align=center><strong> 20 Day Balance</strong></td>");
                    buffer.append("<td align=center><strong> 25 Day Balance</strong></td>");
                    buffer.append("<td align=center><strong> 30 Day Balance</strong></td>");
                buffer.append("</tr>");
                 
                int loopCounter = 1;
                String monthandyear = null;
                String netaveragebal = null;
                String balanceOne = null;
                String balanceFive = null;
                String balanceTen = null;
                String balanceFifteen = null;
                String balanceTwenty = null;
                String balanceTwentyfive = null;
                String balanceThirty = null;
                 
                 
                 for(AverageMonthlyBalance list : averagemonthlybalance)
                {
                    if(list.getNetAverageBalance()!=null)
                    {
                        netaveragebal=list.getNetAverageBalance();
                    }
                    if(list.getMonthAndYear()!=null)
                    {
                        monthandyear = list.getMonthAndYear();
                    }
                    if(list.getDayBalanceMap().getOne()!=null)
                    {
                        balanceOne = list.getDayBalanceMap().getOne();
                    }
                    if(list.getDayBalanceMap().getFive()!=null)
                    {
                        balanceFive = list.getDayBalanceMap().getFive();
                    }
                    if(list.getDayBalanceMap().getTen()!=null)
                    {
                        balanceTen = list.getDayBalanceMap().getTen();
                    }
 
                    if(list.getDayBalanceMap().getFifteen()!=null)
                    {
                        balanceFifteen = list.getDayBalanceMap().getFifteen();
                    }
 
                    if(list.getDayBalanceMap().getTwenty()!=null)
                    {
                        balanceTwenty = list.getDayBalanceMap().getTwenty();
                    }
 
                    if(list.getDayBalanceMap().getTwentyFive()!=null)
                    {
                        balanceTwentyfive = list.getDayBalanceMap().getTwentyFive();
                    }
 
                    if(list.getDayBalanceMap().getThirty()!=null)
                    {
                        balanceThirty = list.getDayBalanceMap().getThirty();
                    }
                     
                    buffer.append("<tr>");
                        buffer.append("<td align=center> "+loopCounter+" </td>");
                        buffer.append("<td align=right>"+(netaveragebal!= null?netaveragebal:" ") +"</td>");
                        buffer.append("<td align=center>"+(monthandyear != null ?monthandyear : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceOne != null ?balanceOne : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceFive != null ?balanceFive : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceTen != null ?balanceTen : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceFifteen != null ?balanceFifteen : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceTwenty != null ?balanceTwenty : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceTwentyfive != null ?balanceTwentyfive : "")+"</td>");
                        buffer.append("<td align=right>"+(balanceThirty != null ?balanceThirty : "")+"</td>");
                    buffer.append("</tr>");
                     
                    loopCounter++;
                }
                 
                buffer.append("</tbody>");
                buffer.append("</table>");
            }
 
        }
        catch(Exception exception)
        {
            logger.error(" HtmlCreator || monthlybalanceAnalysis() || Exception :: "+exception);
        }
            buffer.append("<br/>");
            return buffer.toString();
    }
    public static String date(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formatted = format.format(cal.getTime());
		return formatted;
  }    

} 