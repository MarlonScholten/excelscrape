import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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

            String insert = "INSERT INTO airport (code,country,lat,lng,name,place)\n";
            String values = "VALUES\n";

            int target = 50;
            int i=0;
            // Skip de eerste row
            Row row = itr.next();
            while(i<target){
                row = itr.next();
                String value = String.format("('%s', '%s', %f, %f, '%s', '%s'),\n",
                        row.getCell(1).getStringCellValue().replace("'", ""),
                        row.getCell(8).getStringCellValue().replace("'", ""),
                        row.getCell(4).getNumericCellValue(),
                        row.getCell(5).getNumericCellValue(),
                        row.getCell(3).getStringCellValue().replace("'", ""),
                        row.getCell(10).getStringCellValue().replace("'", ""));
                i++;
                values += value;
            }
            String query = insert + values + ";";
            System.out.println(query);
            writeFile("airport-insert.sql", query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void writeFile(String filename, String text) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            fos.write(text.getBytes("UTF-8"));
        } catch (IOException e) {
            close(fos);
            throw e;
        }
    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }
}
