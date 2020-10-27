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

public class getRoutes {
	public static void main(String[] args) throws IOException {

		Path pathToFile = Paths.get("vliegmaatschappij_public_airport.csv");
		List<String> codes = findAirports(pathToFile);
		Collections.shuffle(codes);

		int i=0;
		int target = 30;
		String infolijn = "INSERT INTO flight_routes (id, departure_code, destination_code) VALUES ";
		while(i < target){
			String info = String.format("(%s, '%s', '%s'), \n", i, codes.get(0), codes.get(1));
			codes.remove(0);
			codes.remove(0);
			infolijn += info;
			System.out.println(info);
			i++;
		}
		System.out.println(infolijn);
		writeFile("test.sql", infolijn);
	}

	public static List<String> findAirports(Path file){
		try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line = br.readLine();
			List<String> codes = new ArrayList<>();
			while (line != null) {
				String[] attributes = line.split(",");
				codes.add(attributes[0]);
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
