import java.io.*;
import java.util.*;
import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class FamDetails
{
	TreeMap individual;
	TreeMap family;
	
	FamDetails()
	{
		individual = new TreeMap();
		family = new TreeMap();
	}
	
	FamDetails(TreeMap indiMap, TreeMap famMap)
	{
		individual = indiMap;
		family = famMap;
	}
}


class gedcomParser
{
	public static void main(String[] args)
	{
		BufferedReader br;
		String output = "";
		String eol = System.getProperty("line.separator");
		
		TreeMap indiMap = new TreeMap();
		TreeMap famMap = new TreeMap();
				
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
			br = new BufferedReader(new FileReader("C:\\Users\\Ketu\\Desktop\\CS555 Agile\\Week 2\\KetuShah.P01.ged"));
			while((line = br.readLine()) != null)
			{
				lineList.add(line);
				//System.out.println(lineList.size());
			}
			
			String id = "";
			int dateDesc = 0; //1:BIRTH, 2:DEAT, 3:MARR, 4:DIV
			Map desc = new HashMap();
			
			
			for(int i = 0;i<lineList.size();i++)
			{
				String str = lineList.get(i).toString();
				//if(str.contains("INDI") || str.contains("FAM"))
				//{
				System.out.println(str);
				
				String[] strdel = str.split(" ");
				//System.out.println(strdel[0]);
				if(strdel[0].equals("0"))
				{
					if(str.contains("INDI")||str.contains("FAM"))
					{
						if(strdel[2].equals("INDI"))
						{
							desc = new HashMap();
							//System.out.println(strdel[1]+" individual");
							indiMap.put(strdel[1],desc);
							id = strdel[1];
						}
						if(strdel[2].equals("FAM"))
						{
							desc = new HashMap();
							//System.out.println(strdel[1]+" family");
							famMap.put(strdel[1],desc);
							id = strdel[1];
						}
					}
					else
					{
						desc = new HashMap();
						continue;
					}
				}
					
				//}
				else
				{
					switch(strdel[1])
					{
						case "WIFE":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								desc.put("WIFE",strdel[2]);
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "HUSB":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								desc.put("HUSB",strdel[2]);
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "CHIL":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								desc.put("CHIL",strdel[2]);
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "NAME":
							if(strdel[0].equals("1"))
							{
								String name = strdel[2]+" "+strdel[3];
								//System.out.println(strdel[1]);
								desc.put("NAME",name);
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						/* case "HEAD":
							if(strdel[0].equals("0"))
							{
								System.out.println(strdel[1]);
								
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break; */
						/* case "TRLR":
							if(strdel[0].equals("0"))
							{
								System.out.println(strdel[1]);
								
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break; */
						case "NOTE":
							if(strdel[0].equals("0"))
							{
								//System.out.println(strdel[1]);
								//desc.put("NOTE",strdel[2]);
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "DIV":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								dateDesc = 4;								
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "MARR":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								dateDesc = 3;
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "DEAT":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								dateDesc = 2;
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "SEX":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								desc.put("SEX",strdel[2]);
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "BIRT":
							if(strdel[0].equals("1"))
							{
								//System.out.println(strdel[1]);
								dateDesc = 1;
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "DATE":
							if(strdel[0].equals("2"))
							{
								//System.out.println(strdel[1]);
								if(dateDesc == 1)
								{
									String strDate = strdel[2]+"-"+strdel[3]+"-"+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
									Date date=dateFormat.parse(strDate);
									
									desc.put("BIRT",date);
									dateDesc = 0;
								}
								else if(dateDesc == 2)
								{
									String date = strdel[2]+" "+strdel[3]+""+strdel[4];
									desc.put("DEAT",date);
									dateDesc = 0;
								}
								else if(dateDesc == 3)
								{
									String date = strdel[2]+" "+strdel[3]+""+strdel[4];
									desc.put("MARR",date);
									dateDesc = 0;
								}
								else if(dateDesc == 4)
								{
									String date = strdel[2]+" "+strdel[3]+""+strdel[4];
									desc.put("DIV",date);
									dateDesc = 0;
								}
								
							}
							else
							{
								//System.out.println("Invalid Tag");
								//output += "Invalid Tag"+eol;
							}
							break;
						case "FAMC":
							if(strdel[0].equals("1"))
							{
								desc.put("FAMC",strdel[2]);
							}
							break;
						case "FAMS":
							if(strdel[0].equals("1"))
							{
								desc.put("FAMS",strdel[2]);
							}
							break;
						default:
							//System.out.println("Invalid Tag");
							//output += "Invalid Tag"+eol;
					}
				}
			}
			
			bw.write(output);
			bw.close();
			System.out.println("Individual Map Size: "+indiMap.size());
			
			Set set = indiMap.entrySet();
                        
			Iterator it = set.iterator();
			while(it.hasNext())
			{
				Map.Entry me = (Map.Entry)it.next();
				System.out.println("Key is: "+me.getKey());
				//  Map temp = new HashMap();
				Map temp = (Map)me.getValue();
				String s = (String)temp.get("NAME");
				String[] names = s.split("/");
				System.out.println("Value is: "+names[0]+" "+names[1]);                
			}
			
			System.out.println("Family Map Size: "+famMap.size());
			set = famMap.entrySet();
			it = set.iterator();
			while(it.hasNext())
			{
				Map.Entry me = (Map.Entry)it.next();
				System.out.println("Key is: "+me.getKey());
				Map temp1 = (Map)me.getValue();
				String wifey = (String)temp1.get("WIFE");
				String hubby = (String)temp1.get("HUSB");
				
				HashMap wifeyMap = (HashMap)indiMap.get(wifey);
				//System.out.println("wifeyMap:"+wifeyMap.size());
				String name = (String)wifeyMap.get("NAME");
				//System.out.println("Wife: "+wifey);
				String[] names = name.split("/");
				
				System.out.println("Wife: "+names[0]+" "+names[1]);
				
				HashMap hubbyMap = (HashMap)indiMap.get(hubby);
				String name1 = (String)hubbyMap.get("NAME");
				String[] names1 = name1.split("/");
				
				System.out.println("Husband: "+names1[0]+" "+names1[1]);
			}
			
			FamDetails fds = new FamDetails(indiMap,famMap);
                       
                        
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}