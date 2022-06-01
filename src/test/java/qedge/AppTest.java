package qedge;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunction.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;


//import org.testng.Reporter;
//import org.testng.annotations.Test;

public class AppTest 
    extends AppUtil
{String inputpath ="C:\\Users\\RAKY AL0NE\\eclipse-workspace\\MyMavenProject\\TestInput\\HybridData.xlsx";
String outputpath="C:\\Users\\RAKY AL0NE\\eclipse-workspace\\MyMavenProject\\TestOutPut\\HybridResults.xlsx";
String TCSheet ="MasterTestCases";
String TSSheet ="TestSteps";
@Test
public void startTest()throws Throwable
{
	boolean res=false;
	String tcres="";
	//create object for excel file util
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in both sheets
	int TCCount= xl.rowCount(TCSheet);
	int TSCount =xl.rowCount(TSSheet);
	Reporter.log(TCCount+"       "+TSCount,true);
	//iterate all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		String executionstatus =xl.getCelldata(TCSheet, i, 2);
		if(executionstatus.equalsIgnoreCase("Y"))
		{
			//read tcid cell
			String tcid = xl.getCelldata(TCSheet, i, 0);
			for(int j=1;j<=TSCount;j++)
			{
				String tsid =xl.getCelldata(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid)) 
				{
					String keyword =xl.getCelldata(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String Para1 =xl.getCelldata(TSSheet, j, 5);
						String Para2 =xl.getCelldata(TSSheet, j, 6);
						res = FunctionLibrary.verifyLogin(Para1, Para2);
					}
					else if(keyword.equalsIgnoreCase("NewBranch"))
					{
						String Para1 =xl.getCelldata(TSSheet, j, 5);
						String Para2 =xl.getCelldata(TSSheet, j, 6);
						String Para3 =xl.getCelldata(TSSheet, j, 7);
						String Para4 =xl.getCelldata(TSSheet, j, 8);
						String Para5 =xl.getCelldata(TSSheet, j, 9);
						String Para6 =xl.getCelldata(TSSheet, j, 10);
						String Para7 =xl.getCelldata(TSSheet, j, 11);
						String Para8 =xl.getCelldata(TSSheet, j, 12);
						String Para9 =xl.getCelldata(TSSheet, j, 13);
						FunctionLibrary.clickBranch();
						res =FunctionLibrary.verifyNewBranch(Para1, Para2, Para3, Para4, Para5, Para6, Para7, Para8, Para9);

					}
					else if(keyword.equalsIgnoreCase("BranchUpdate"))
					{
						String Para1 =xl.getCelldata(TSSheet, j, 5);
						String Para2 =xl.getCelldata(TSSheet, j, 6);
						String Para6 =xl.getCelldata(TSSheet, j, 10);
						FunctionLibrary.clickBranch();
						res =FunctionLibrary.verifyBranchUpdate(Para1, Para2, Para6);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res =FunctionLibrary.verifyLogout();
					}
					String tsres="";
					if(res)
					{
						tsres="pass";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					else
					{
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					tcres=tsres;

				}
			}
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//which are flag to N write as blocked in TCSheet
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
}
}
