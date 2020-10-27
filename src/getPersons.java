import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class getPersons {
	public static void main(String[] args) throws IOException {

		Path pathToFile = Paths.get("fakeidentitiys.csv");
		List<String[]> personen = findCodes(pathToFile);
		Collections.shuffle(personen);

		int i=0;
		int target = 30;
		String infolijn = "INSERT INTO person (id, birthday, email, first_name, last_name, nationality, phone) VALUES";
		while(i < target){
			String info = String.format("(%s, '%s', '%s', '%s', '%s', '%s', '%s'), \n", i, personen.get(i)[3],
					personen.get(i)[4], personen.get(i)[2], personen.get(i)[1],  personen.get(i)[0], personen.get(i)[5]);
			infolijn += info;
			System.out.println(info);
			i++;
		}
		System.out.println(infolijn);
		writeFile("test.sql", infolijn);
	}

	public static List<String[]> findCodes(Path file){
		try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line = br.readLine();
			List<String[]> codes = new ArrayList<>();
			while (line != null) {
				String[] attributes = line.split(",");
				codes.add(attributes);
				line = br.readLine();
			}
			return codes;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
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
