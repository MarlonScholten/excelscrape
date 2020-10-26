import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;

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
                String value = String.format("('%s', '%s', %.0f, %.0f, '%s', '%s')",
                        removeSingleQuote(row.getCell(1).getStringCellValue()),
                        removeSingleQuote(row.getCell(8).getStringCellValue()),
                        row.getCell(4).getNumericCellValue(),
                        row.getCell(5).getNumericCellValue(),
                        removeSingleQuote(row.getCell(3).getStringCellValue()),
                        removeSingleQuote(row.getCell(10).getStringCellValue())
                );
                if(i!=target-1){
                    value +=",\n";
                }
                i++;
                values += value;
            }
            String query = insert + values + ";";
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

    public static String removeSingleQuote(String string){
        return string.replace("'", "");
    }
}
