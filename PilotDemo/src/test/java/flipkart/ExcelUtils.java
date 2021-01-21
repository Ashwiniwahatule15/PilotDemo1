package flipkart;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils
{
	static String path;
	static XSSFWorkbook wb;
	static XSSFSheet sh;     // this 3 are class variables
	
	public ExcelUtils(String excelPath, String sheetName)      //constructor
	{
		try
		{
		path=System.getProperty("user.dir");
	      wb=new XSSFWorkbook(excelPath);   //create reference for worksheet
			 sh=wb.getSheet(sheetName);
			 
		
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
 }

	public static void main(String[] args)
	{
		//getRowCount();
		 getCellDataString(0,0);
		 getCellDataString1(1,1);
		
	}
	public static int getRowCount()
	{
		int rowcount=0;
		try
		{	
			 
		rowcount=sh.getPhysicalNumberOfRows();
		System.out.println("no.of rows"+rowcount);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowcount;
	}
	
	public static int getColCount()
	{
		int colcount=0;
		try
		{	
			 
		colcount=sh.getRow(0).getPhysicalNumberOfCells();
		System.out.println("no.of coumns"+colcount);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colcount;
	}
	
	public static String getCellDataString(int rowNum, int colNum)
	{
		String cellData=null;
		try
		{
			 cellData= sh.getRow(rowNum).getCell(colNum).getStringCellValue();
			System.out.println(cellData);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return cellData;
	}
	
	public static String getCellDataString1(int rowNum, int colNum)
	{
		String cellData1=null;
		try
		{
		 cellData1= sh.getRow(rowNum).getCell(colNum).getStringCellValue();
			System.out.println(cellData1);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return  cellData1;
	}
}
