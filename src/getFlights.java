import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class getFlights {
	public static void main(String[] args) throws IOException {

		Path pathToFile = Paths.get("vliegmaatschappij_public_plane.csv");
		List<String> codes = findCodes(pathToFile);
		Collections.shuffle(codes);
		List<List<String>> tijden = findTimes();
		Collections.shuffle(tijden);
		System.out.println(codes);
		System.out.println(tijden);

		int i=0;
		int target = 30;
		String infolijn = "INSERT INTO flight (id, arrival_time, departure_time, plane_code, route_id) VALUES()";
		while(i < target){
			String info = String.format("(%s, '%s', '%s', '%s', %s), \n", i, tijden.get(i).get(0), tijden.get(i).get(1), codes.get(i), i);
			infolijn += info;
			System.out.println(info);
			i++;
		}
		System.out.println(infolijn);
		writeFile("test.sql", infolijn);
	}

	public static List<List<String>> findTimes() throws IOException {
		Path pathToFile = Paths.get("tijden.csv");
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			List<List<String>> tijden = new ArrayList<>();

			while (line != null) {
				List<String> tijdenLijst = new ArrayList<>();
				String[] attributes = line.split(",");
				tijdenLijst.add(attributes[7]);
				tijdenLijst.add(attributes[8]);
				line = br.readLine();
				tijden.add(tijdenLijst);
			}
			return tijden;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}


	public static List<String> findCodes(Path file){
		try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.US_ASCII)) {
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
			System.out.println("Ging niet best");
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
