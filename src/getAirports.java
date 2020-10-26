import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class getAirports {
    public static void main(String[] args){
        try
        {
            File file = new File("C:\\Users\\marlo\\Desktop\\test data airport.xlsx");
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            String query = "INSERT INTO airport (code,country,lat,lng,name,place)\n";
            String values = "VALUES";

            int target = 50;
            int i=0;
            // Skip de eerste row
            Row row = itr.next();
            while(i<target){
                row = itr.next();
                System.out.println(
                        String.format("%s %s %f %f %s %s",
                                row.getCell(1).getStringCellValue(),
                                row.getCell(8).getStringCellValue(),
                                row.getCell(4).getNumericCellValue(),
                                row.getCell(5).getNumericCellValue(),
                                row.getCell(3).getStringCellValue(),
                                row.getCell(10).getStringCellValue())
                );
                System.out.println();
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
