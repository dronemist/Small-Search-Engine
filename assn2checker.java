import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class assn2checker
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		SearchEngine s = new SearchEngine();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions_test.txt"));

			while ((actionString = br.readLine()) != null) {
				s.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
