import java.io.*;
import java.util.*;

class gedcomParser
{
	public static void main(String[] args)
	{
		BufferedReader br;
		String output = "";
		String eol = System.getProperty("line.separator");
				
		try
		{
			File fl = new File("C:\\Users\\Ketu\\Desktop\\CS555 Agile\\Week 2\\output.txt");
			if (!fl.exists()) 
			{
				fl.createNewFile();
			}
			
			FileWriter fw = new FileWriter(fl.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line;
			List lineList = new ArrayList();
			br = new BufferedReader(new FileReader("C:\\Users\\Ketu\\Desktop\\CS555 Agile\\Week 2\\familyTree.ged"));
			while((line = br.readLine()) != null)
			{
				lineList.add(line);
				//System.out.println(lineList.size());
			}
			
			for(int i = 0;i<lineList.size();i++)
			{
				String str = lineList.get(i).toString();
				if(str.contains("INDI") || str.contains("FAM"))
				{
					System.out.println(str);
					output += str+eol;
					String[] strdel = str.split(" ");
					System.out.println(strdel[0]);
					output += strdel[0]+eol;
					
					if(strdel[2].equals("INDI") || strdel[2].equals("FAM"))
					{
						System.out.println(strdel[2]);
						output += strdel[2]+eol;
					}
					else
					{
						System.out.println(strdel[1]);
						output += strdel[1]+eol;
					}
				}
				else
				{
					System.out.println(str);
					output += str+eol;
					String[] strdel = str.split(" ");
					System.out.println(strdel[0]);
					output += strdel[0]+eol;
					
					switch(strdel[1])
					{
						case "WIFE":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "HUSB":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "CHIL":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "NAME":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "HEAD":
							if(strdel[0].equals("0"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "TRLR":
							if(strdel[0].equals("0"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "NOTE":
							if(strdel[0].equals("0"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "DIV":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "MARR":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "DEAT":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "SEX":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "BIRT":
							if(strdel[0].equals("1"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						case "DATE":
							if(strdel[0].equals("2"))
							{
								System.out.println(strdel[1]);
								output += strdel[1]+eol;
							}
							else
							{
								System.out.println("Invalid Tag");
								output += "Invalid Tag"+eol;
							}
							break;
						default:
							System.out.println("Invalid Tag");
							output += "Invalid Tag"+eol;
					}
				}
			}
			
			bw.write(output);
			bw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}