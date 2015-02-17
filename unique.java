import java.io.*;
import java.util.*;

public class unique
{
	public static void main(String[] args) throws IOException
	{
		int count=1;
		FileWriter fw = new FileWriter("uniq_words.txt");
		HashMap hm = new HashMap();
		hm.put(1," ");		
/*		for(int i=1;i<=10;i++)
		{
*/			File f = new File("ndocs.txt");
			Scanner sc2 = new Scanner(f);
			while (sc2.hasNextLine()) 
			{
	            Scanner s2 = new Scanner(sc2.nextLine());
	        	boolean b;
	        	while (b = s2.hasNext()) 
	        	{
		            String s = s2.next();
		            s = s.toLowerCase();
		            s = s.replace("(","");
		            s = s.replace(")","");
		            s = s.replace(".","");
		            s = s.replace(",","");
		            if(!hm.containsValue(s))
		            {
		            	fw.write(s + "\n");
			            hm.put(count,s);
		                count++;
		            }
		        }
		    }
//		}
		fw.close();
	}
}