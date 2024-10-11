import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GUI_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/************** Pledge of Honor ******************************************
		I hereby certify that I have completed this programming project on my own
		without any help from anyone else. The effort in the project thus belongs
		completely to me. I did not search for a solution, or I did not consult any
		program written by others or did not copy any program from other sources. I
		read and followed the guidelines provided in the project description.
		READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
		SIGNATURE: Ekrem Atay Daloğlu 0083379
		*************************************************************************/

		HashMap<String,String> idPasswords = new HashMap<String,String>();
		
		Login_Page loginPage = new Login_Page(idPasswords);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt",true));
			writer.write("");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
