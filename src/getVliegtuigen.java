import java.io.*;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getVliegtuigen {
	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\Daniel\\Desktop\\planes.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();

			int target = 100;
			int i = 0;
			// Skip de eerste row
			Row row = itr.next();
			String flightinfo = "VALUES";
			while (i < target) {
				row = itr.next();
				String info = String.format("('%s', %s), \n",
						row.getCell(0).getStringCellValue(),
						i);

						//row.getCell(7).getStringCellValue());
				flightinfo = flightinfo + info;
				i++;
			}
			writeFile("test.sql", flightinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String filename) throws IOException {
		File file = new File(filename);
		int len = (int) file.length();
		byte[] bytes = new byte[len];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			assert len == fis.read(bytes);
		} catch (IOException e) {
			close(fis);
			throw e;
		}
		return new String(bytes, "UTF-8");
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
