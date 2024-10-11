package readingCVSPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadingCVS {
	public ReadingCVS() {};
	public ArrayList<String[]> ReadNReturn(String e){
		ArrayList<String[]> my_arraylist = new ArrayList<String[]>();
		my_arraylist.clear();
		String[] my_array;
		String line;
		String my_string = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(e));
			while((line = reader.readLine()) != null) {
				my_array = line.split(";");
				String[] another_array = {my_array[1],my_array[2],my_array[29],my_array[24],my_array[25],my_array[26],my_array[23]};
				my_arraylist.add(another_array);	
				
			}
			ArrayList<String[]> new_list = new ArrayList<String[]>();
			String aypi = "";
			for (String[] string : my_arraylist) {
				if (aypi.equals(string[0])) {
					new_list.add(string);
				} else {
					aypi = string[0];
				}
			}
			for (String[] string3 : new_list) {
				my_arraylist.remove(string3);
			}
			return my_arraylist;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return my_arraylist;
		}
		
	}
}
