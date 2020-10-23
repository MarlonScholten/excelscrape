import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
	public static void main(String[] args){
		try
		{
			File file = new File("C:\\Users\\marlo\\Desktop\\test data airport.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
//			while (itr.hasNext())
//			{
//				Row row = itr.next();
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while (cellIterator.hasNext())
//				{
//					Cell cell = cellIterator.next();
//					switch (cell.getCellType())
//					{
//						case Cell.CELL_TYPE_STRING:
//							System.out.print(cell.getStringCellValue() + "\t\t\t");
//							break;
//						case Cell.CELL_TYPE_NUMERIC:
//							System.out.print(cell.getNumericCellValue() + "\t\t\t");
//							break;
//						default:
//					}
//				}
//				System.out.println("");
//			}

			int target = 100;
			int i=0;
			while(i<target){
				Row row = itr.next();
				System.out.println(
						String.format("%s %s %d %d")
				);
				System.out.println(row.getCell(1));
				System.out.println(row.getCell(3));
				System.out.println(row.getCell(4));
				System.out.println(row.getCell(5));
				System.out.println(row.getCell(10));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}