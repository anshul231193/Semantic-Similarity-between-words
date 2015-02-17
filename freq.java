import java.io.*;
import java.util.*;

public class freq
{
	private static HashMap sortByValues(HashMap map) 
	{ 
	    List list = new LinkedList(map.entrySet());
	    Collections.sort(list, new Comparator() 
	    {
	        public int compare(Object o1, Object o2) 
	        {
	            return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
        	}
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) 
        {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
       return sortedHashMap;
    }

	public static void main(String[] args) throws Exception
	{
		String[] max_corr = new String[10000];
		int[][] mat = new int[874][10];
		String[] m = new String[874];
		File f = new File("uniq_words.txt");
		int count=0;
		FileWriter fw = new FileWriter("frequency.xlsx");
		fw.write("Frequency Matrix:-\n\n");	
		fw.write("Words \t");	
		for(int i=0;i<10;i++)
		{
			int p = i+1;
			File f1 = new File("Docs/doc-" + p);
			try
			{
				DataInputStream d =  new DataInputStream(new FileInputStream(f));
				Scanner sc = new Scanner(f1);
				HashMap<String,Integer> hm = new HashMap<String,Integer>();
				String str;
				while((str=d.readLine()) != null)
				{
					hm.put(str,new Integer(0));
				}
				while(sc.hasNext())
				{
					String ctr = sc.next();
					ctr = ctr.toLowerCase();
					if(hm.containsKey(ctr))
					{
						int c = (int)hm.get(ctr);
						hm.put(ctr,c+1);
					}
				}
				Set s = hm.entrySet();
				Iterator itr = s.iterator();
				int k=0;
				while(itr.hasNext())
				{
					Map.Entry me = (Map.Entry)itr.next();
					String str_r = me.getValue() + "";
					m[k] = me.getKey() + "";
					mat[k][i] =  Integer.parseInt(str_r);
					k++;
				}
				count = hm.size();
			}
			catch(Exception e)
			{
				System.out.println("Exception : " + e);
				e.printStackTrace();
			}
		}
		int[][] res_mat = new int[count][10];
		String[] res_m = new String[count];
		int flag = 0,c = 0;
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(mat[i][j] >= 2)
				{
					flag = 1;
					break;
				}
			}
			if(flag == 1)
			{
				for(int k=0;k<10;k++)
					res_mat[c][k] = mat[i][k];
				res_m[c] = m[i];
				c++;
			}
			flag = 0;
		}
		int[][] tr_res = new int[10][c];
		for(int i=0;i<10;i++)
			for(int k=0;k<c;k++)
				tr_res[i][k] = res_mat[k][i];
		for(int i=0;i<c;i++)
			fw.write(res_m[i] + "   ");
		fw.write("\n");
		for(int i=0;i<10;i++)
		{
			int j = i + 1;
			fw.write("Doc-" + j + " ");
			for(int l=0;l<c;l++)
				fw.write(tr_res[i][l] + "   ");
			fw.write("\n");
		}
		//coefficient matrix
		double[][] corr = new double[c][c];
		for(int i=0;i<c;i++)
		{
			for(int j=0;j<c;j++)
			{
				if(i == j)
					corr[i][j] = 1.0;
				else
				{
					double a = 0;
					for(int k=0;k<10;k++)
						a += tr_res[k][i]*tr_res[k][j];
					double b=0,f1=0,f2=0,f12=0,f22=0;
					for(int k=0;k<10;k++)
					{
						f1 += tr_res[k][i];
						f12 += tr_res[k][i] * tr_res[k][i];
						f2 += tr_res[k][j];
						f22 += tr_res[k][j] * tr_res[k][j];
					}
					b = f1*f2/10.0;
					double ck = Math.sqrt(f12-(f1*f1)/10.0);
					double d = Math.sqrt(f22-(f2*f2)/10.0);
					corr[i][j] = (a-b)/(ck*d);
				}
			}
		}
		fw.write("\n\nCoefficient Correlation Matrix:-\n\n\n");
		fw.write(" ");
		for(int i=0;i<c;i++)
			fw.write(res_m[i] + " ");
		fw.write("\n");
		for(int i=0;i<c;i++)
		{
			fw.write(res_m[i] + " ");
			for(int j=0;j<c;j++)
			{
				fw.write(corr[i][j] + " ");
			}
			fw.write("\n");
		}
		int k=0,p=0,flagn=0,z=0;
		double max = -10;
		outer:for(int i=0;i<c;i++)
		{
			for(int j=0;j<c;j++)
			{
				if(max<corr[i][j] && i!=j)
				{
					k = i;
					p = j;
					max = corr[i][j];
					if(max == 1.0000000000000002)
					{
						k=i;p=j;
						System.out.println("Maximum correlation coefficient is 1 between:-");
						System.out.println(res_m[i] +" and "+ res_m[j]);
						flagn = 1;
						max_corr[0] = res_m[i] + res_m[j];
						break outer;
					}
				}
			}
		}
		if(flagn == 0)
		{
			max_corr[0] = res_m[k] + res_m[p];
		}
		int newin=0;
		String[] res_new = new String[c+z];
		for(int i=0;i<=c+z;i++)
		{
			if(i==k || i==p)
				continue;
			else
			{
				res_new[newin] = res_m[i];
				newin++;
			}
		}
		int[][] tr_new = new int[10][c-2];
		int ind = 0;
		for(int i=0;i<10;i++)
		{
			ind = 0;
			for(int j=0;j<c;j++)
			{
				if(j == k || j == p)
					continue;
				else
				{
					tr_new[i][ind] = tr_res[i][j];
					ind++;
				}
			}
		}
		fw.write("\nFrequency Matrix:-\n\n");	
		fw.write("Words \t");
		for(int i=0;i<c+z-1;i++)
			fw.write(res_new[i] + " ");
		fw.write("\n");
		for(int i=0;i<10;i++)
		{
			int j = i + 1;
			fw.write("Doc-" + j + " ");
			for(int l=0;l<c-2;l++)
				fw.write(tr_new[i][l] + " ");
			fw.write("\n");
		}					
		z++;
		String tmpk="";
		String tmpp="";
		//res_new:- words newin is length
		//tr_new:- frequency c-2 is length
		//corr:- pearson matrix 28 rows and 28 cols.
		HashSet hs = new HashSet();
		while(c!=3)
		{
			double[][] corr_new = new double[c][c];
			int newi=0,newind=0,newflag=0;
			for(int i=0;i<c;i++)
			{
				newind = 0;
				for(int j=0;j<c;j++)
				{
					if(i==k || i==p || j==k || j==p)
						continue;
					else
					{
						newflag = 1;
						corr_new[newi][newind] = corr[i][j];
						newind++;
						if(newi == newind)
							corr_new[i][newind] = 1.0;
					}
				}
				if(newflag==1)
					newi++;
				newflag = 0;
			}
			c=c-1;
			fw.write("\n\nCoefficient Correlation Matrix:-\n\n\n");
			fw.write(" ");
			fw.write("\n");
			for(int i=0;i<c-1;i++)
			{
				for(int j=0;j<newind;j++)
				{
					fw.write(corr_new[i][j] + " ");
				}
				fw.write("\n");
			}
			double maxi = -1.0;
			for(int i=0;i<newi;i++)
			{
				for(int j=i+1;j<newind;j++)
				{
					if(maxi<corr_new[i][j] && i!=j)
					{
						k = i;
						p = j;
						maxi = corr_new[i][j];
					}
				}
			}
			/*if(!hs.contains(res_new[k]) && !hs.contains(res_new[p]))
			{
				hs.add(res_new[k]);
				hs.add(res_new[p]);
				max_corr[z] = res_new[k] + res_new[p];
				z++;
			}
			else
			{
				int k_tmp=0,p_tmp=0;
				double maxi_tmp = -1;
				for(int i=0;i<newi;i++)
				{
					for(int j=0;j<newind;j++)
					{
						if(maxi_tmp<corr_new[i][j] && i!=j && i!=k && j!=p && i!=p && j!=k)
						{
							k_tmp = i;
							p_tmp = j;
							maxi_tmp = corr_new[i][j];
						}
					}
				}
				max_corr[z] =	res_new[k_tmp] + res_new[p_tmp];	
				z++;
				System.out.println(corr_new[k_tmp][p_tmp]);	
			}*/
			if(res_new[k]!=null && res_new[p]!=null && res_new[k]!=res_new[p])
			{
				if(z==2)
					max_corr[z] = res_new[k] + res_new[p-1];
				else
					max_corr[z] = res_new[k] + res_new[p];
				z++;
			}
			String[] temp = new String[newin];
			int new_ind = 0;
			for(int i=0;i<newin;i++)
			{
				if(i==k || i==p)
					continue;
				else
				{
					temp[new_ind] = res_new[i];
					new_ind++;
				}
			}
			for(int i=0;i<new_ind;i++)
				res_new[i] = temp[i];
			for(int i=0;i<newi;i++)
			{
				for(int j=0;j<newind;j++)
				{
					corr[i][j] = corr_new[i][j];
				}
			}
			tmpk = res_new[k];
			tmpp = res_new[p];
		}
		fw.write("\n\n");
		fw.write("Final Matrix is:-\n\n");
		fw.write("\nTotal Combinations are:-"+z+"\n\n");
		for(int i=0;i<z;i++)
			if(max_corr[i]!=" ")
				fw.write(max_corr[i] + " ");
		fw.close();
	}
}