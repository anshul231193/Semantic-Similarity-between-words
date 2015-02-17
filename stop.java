import java.io.*;

public class stop
{
	public static void main(String[] args) throws IOException
	{
		for(int z=1;z<=10;z++)
		{		
			File f = new File("Docs/doc-"+z);
			FileWriter out = null;
			InputStream in =  null;
			try
			{
				in = new FileInputStream(f);
				DataInputStream data_in = new DataInputStream(in);
				String str = "";
				String count;
				while((count=data_in.readLine()) != null)
				{
					if(count.isEmpty())
						str += "\n\n";
					else
						str += count;
				}
				str = str.toUpperCase();
				DataInputStream st = new DataInputStream(new FileInputStream("MYSTWORD.TXT"));
				String str_new = " " + str + " ";
				while((count=st.readLine()) != null)
				{
					count=count.toUpperCase();
					if(count.isEmpty())
						str_new += "\n\n";
					else
					{
						str_new = str_new.replaceAll(" " + count + " "," ");
						str_new = str_new.replaceAll("\n" + count + " ","\n");
						str_new = str_new.replaceAll("\"" + count + " ","\"");
						str_new = str_new.replaceAll(" " + count + ".\"","\"");
						str_new = str_new.replaceAll(" " + count + ",",",");
						str_new = str_new.replaceAll("\\(" + count + "\\)","\\(\\)");
						str_new = str_new.replaceAll("\\(" + count + " ","\\( ");
						str_new = str_new.replaceAll(" " + count + "\\)"," \\)");
					}
				}
				str_new = str_new.replaceFirst(" ","");
				st.close();
				in.close();
			    out = new FileWriter("ndocs.txt",true);
			    str_new = str_new.toLowerCase();
				out.write(str_new + "\n\n");
				out.close();
			}	
			catch(Exception e)
			{
				System.out.println("Exception " + e);
				e.printStackTrace();
			}
			finally
			{
				if(in != null)
					in.close();
			}
		}
	}
}
