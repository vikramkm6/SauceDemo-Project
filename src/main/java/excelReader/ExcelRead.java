package excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelRead {

	@DataProvider
	public String[][] fetchData() throws EncryptedDocumentException, IOException {
		File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\TestData_SauceDemo.xlsx");

		// Step 1 :Reading the file
		FileInputStream fis = new FileInputStream(f); // Read the file

		// Step 2: Loading the workbook
		Workbook wb = WorkbookFactory.create(fis);

		// Step 3: Loaded the sheet
		Sheet sheet = wb.getSheet("Credentials");

		// Step 4: To get the number of rows

//		int rows = sheet.getPhysicalNumberOfRows();
		int rows = sheet.getLastRowNum();

		System.out.println(rows);

		// step 5: To get the number of columns
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();

		System.out.println(columns);

		// Step 6: Using Data formatter

		DataFormatter format = new DataFormatter();

		String[][] testdata = new String[rows][columns];
		// Iterate the rows and columns

		for (int row = 1; row <= rows; row++) // 1
		{
			for (int col = 0; col < columns; col++) // 0
			{
				testdata[row - 1][col] = format.formatCellValue(sheet.getRow(row).getCell(col));
				System.out.println(testdata[row - 1][col]);
			}
		}
		return testdata;

	}

}
