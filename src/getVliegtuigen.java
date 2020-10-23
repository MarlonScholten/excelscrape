import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getVliegtuigen {
	public static void main(String[] args){
		try
		{
			File file = new File("C:\\Users\\Daniel\\Desktop\\planes.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();

			int target = 400;
			int i=0;
			// Skip de eerste row
			Row row = itr.next();
			while(i<target){
				row = itr.next();
//
//								row.getCell(0).getStringCellValue(),
//								row.getCell(7).getStringCellValue()
;
				i++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}