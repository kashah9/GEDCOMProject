import java.io.*;
import java.util.*;
import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

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
	
	public String printSummary()
	{
		String output = "";
		String tabChar = "\t";
		String eol = System.getProperty("line.separator");
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		output+=eol;
		output+="Individual Summary";
		output+=eol;
		output+="ID \t NAME         \t SEX \t BIRTH DATE \t\t\t\t DEATH DATE \t\t\t\t FAMC \t FAMS \t";
		output+=eol;
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String id = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap value = (HashMap)me.getValue();
			String name = (String)value.get("NAME");
			String gender = (String)value.get("SEX");
			String death = "\t\t\t\t\t";
			if(value.containsKey("DEAT"))
			{
				death = (String)value.get("DEAT").toString();
			}
			String birth = (String)value.get("BIRT").toString();
			String famc = "\t";
			if(value.containsKey("FAMC"))
			{
				famc = (String)value.get("FAMC");
			}
			String fams = "\t";
			if(value.containsKey("FAMS"))
			{
				fams = (String)value.get("FAMS");
			}
			output+=id+tabChar+name+tabChar+gender+tabChar+birth+tabChar+death+tabChar+famc+tabChar+fams;
			output+=eol;
		}
		
		set = family.entrySet();
		it = set.iterator();
		output+=eol;
		output+=eol;
		output+=eol;
		output+="Family Summary";
		output+=eol;
		output+="ID \t HUSB \t WIFE \t CHIL \t MARR DATE \t\t\t DIV DATE";
		output+=eol;
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String id = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap value = (HashMap)me.getValue();
			String husb = "    ";
			if(value.containsKey("HUSB"))
			{
				husb = (String)value.get("HUSB");
			}
			String wife = "    ";
			if(value.containsKey("WIFE"))
			{
				wife = (String)value.get("WIFE");
			}
			String chil = "    ";
			if(value.containsKey("CHIL"))
			{
				chil = (String)value.get("CHIL");
			}
			String marr = "\t\t\t\t\t";
			if(value.containsKey("MARR"))
			{
				marr = value.get("MARR").toString();
			}
			String div = "\t\t\t";
			if(value.containsKey("DIV"))
			{
				div = value.get("DIV").toString();
			}
			
			output+=id+tabChar+wife+tabChar+husb+tabChar+chil+tabChar+marr+tabChar+div;
			output+=eol;
		}
		return output;
	}
	
	//Ketu Shah
	//US#1
	public String dateBeforeCurrentDate()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Map birthDateList = new HashMap();
		Map marrDateList = new HashMap();
		Map divDateList = new HashMap();
		Map deathDateList = new HashMap();
		
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String key = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap value = (HashMap)me.getValue();
			//System.out.println("Value is: "+value);
			if(value.containsKey("BIRT"))
			{
				birthDateList.put(key,value.get("BIRT"));
			}
			
			if(value.containsKey("DEAT"))
			{
				deathDateList.put(key,value.get("DEAT"));
			}
		}
		
		set = family.entrySet();
		it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String key = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap value = (HashMap)me.getValue();
			//System.out.println("Value is: "+value);
			
			if(value.containsKey("MARR"))
			{
				marrDateList.put(key,value.get("MARR"));
			}
			if(value.containsKey("DIV"))
			{
				divDateList.put(key,value.get("DIV"));
			}
			
		}
		
		
		//System.out.println("Size of DateList is: "+birthDateList.size());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentDate = new Date();
		
		set = birthDateList.entrySet();
		it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String idKey = me.getKey().toString();
			Date dateVal = (Date)me.getValue();
			
			if (dateVal.compareTo(currentDate) == 1)
			{
				System.out.println("Warning US#1: Individual ID "+idKey+" has Birthdate "+dateVal+" after current date.");
				output += "Warning US#1: Individual ID "+idKey+" has Birthdate "+dateVal+" after current date.";
				output += eol;
			} 
		}
		
		set = deathDateList.entrySet();
		it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String idKey = me.getKey().toString();
			Date dateVal = (Date)me.getValue();
			
			
			if (dateVal.compareTo(currentDate) == 1)
			{
				System.out.println("Warning US#1: Individual ID "+idKey+" has Deathdate "+dateVal+" after current date.");
				output += "Warning US#1: Individual ID "+idKey+" has Deathdate "+dateVal+" after current date.";
				output += eol;
			} 
		}
		
		set = marrDateList.entrySet();
		it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String idKey = me.getKey().toString();
			Date dateVal = (Date)me.getValue();
			
			if (dateVal.compareTo(currentDate) == 1)
			{
				System.out.println("Warning US#1: Family ID "+idKey+" has Marriagedate "+dateVal+" after current date.");
				output += "Warning US#1: Family ID "+idKey+" has Marriagedate "+dateVal+" after current date.";
				output += eol;
			} 
		}
		
		set = divDateList.entrySet();
		it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String idKey = me.getKey().toString();
			Date dateVal = (Date)me.getValue();
			
			if (dateVal.compareTo(currentDate) == 1)
			{
				System.out.println("Warning US#1: Family ID "+idKey+" has Divorcedate "+dateVal+" after current date.");
				output += "Warning US#1: Family ID "+idKey+" has Divorcedate "+dateVal+" after current date.";
				output += eol;
			} 
		}
		return output;
	}
	
	//US#2
	public String birthBeforeMarriageDate()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Date marrDate = new Date();
			
			String wifey = "";
			String hubby = "";
			//Date wifeBirth = "";
			//Date husbBirth = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("MARR"))
			{
				marrDate = (Date)famvalue.get("MARR");
				//System.out.println("MarrDate is: "+marrDate);
			}
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			
			
			if(wifey != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				Date wifeBirth = (Date)wifeyMap.get("BIRT");
				//System.out.println("WifeDate is: "+wifeBirth);
				if(wifeBirth.compareTo(marrDate) == 1)
				{
					System.out.println("Warning US#2: Family ID "+famkey+" has wife who has marriage date before her birthdate.");
					output += "Warning US#2: Family ID "+famkey+" has wife who has marriage date before her birthdate.";
					output += eol;
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date husbBirth = (Date)hubbyMap.get("BIRT");
				if(husbBirth.compareTo(marrDate) == 1)
				{
					System.out.println("Warning US#2: Family ID "+famkey+" has husband who has marriage date after his birthdate.");
					output += "Warning US#2: Family ID "+famkey+" has husband who has marriage date after his birthdate.";
					output += eol;
				}
			}
			
			
		}
		return output;
	}
	
    /*Archit_Zaveri_10409888*/
	//US#4
	public String marriageBeforeDivorce()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			//Date marrDate = new Date();
			
			
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("MARR") && famvalue.containsKey("DIV"))
			{
				Date marrDate = (Date)famvalue.get("MARR");
				Date divDate = (Date)famvalue.get("DIV");
				if(marrDate.compareTo(divDate)==1)
				{
					System.out.println("Warning US#4: Family ID "+famkey+" has marriage date "+marrDate+" after divorce date.");
					output += "Warning US#4: Family ID "+famkey+" has marriage date "+marrDate+" after divorce date.";
					output += eol;
				}
				//System.out.println("MarrDate is: "+marrDate);
			}
		}
		return output;
	}
	
	//US#5
	public String marriageBeforeDeath()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Date marrDate = new Date();
			
			String wifey = "";
			String hubby = "";
			//Date wifeBirth = "";
			//Date husbBirth = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("MARR"))
			{
				marrDate = (Date)famvalue.get("MARR");
				//System.out.println("MarrDate is: "+marrDate);
			}
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			
			
			if(wifey != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				if(wifeyMap.containsKey("DEAT"))
				{
					Date wifeDeath = (Date)wifeyMap.get("DEAT");
					//System.out.println("WifeDate is: "+wifeBirth);
					if(marrDate.compareTo(wifeDeath) == 1)
					{
						System.out.println("Warning US#5: Family ID "+famkey+" has wife who has marriage date after her death.");
						output += "Warning US#5: Family ID "+famkey+" has wife who has marriage date after her death.";
						output += eol;
					}
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				if(hubbyMap.containsKey("DEAT"))
				{
					Date husbDeath = (Date)hubbyMap.get("DEAT");
					if(marrDate.compareTo(husbDeath) == 1)
					{
						System.out.println("Warning US#5: Family ID "+famkey+" has husband who has marriage date after his death.");
						output += "Warning US#5: Family ID "+famkey+" has wife who has marriage date after her death.";
						output += eol;
					}
				}
			}
		}
		return output;
	}
	

	/*Sweta_Chowdary_10406940*/
	//US#3
	public String birthBeforedeath()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Date death_date = new Date();
			
			String wifey = "";
			String hubby = "";
			//Date wifeBirth = "";
			//Date husbBirth = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("DEAT"))
			{
				death_date = (Date)famvalue.get("DEAT");
				//System.out.println("MarrDate is: "+marrDate);
			}
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			
			
			if(wifey != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				Date wifeBirth = (Date)wifeyMap.get("BIRT");
				//System.out.println("WifeDate is: "+wifeBirth);
				if(wifeBirth.compareTo(death_date) == 1)
				{
					System.out.println("Warning US#3: Family ID "+famkey+" has wife who has death date beofer her birthdate.");
					output += "Warning US#3: Family ID "+famkey+" has wife who has death date beofer her birthdate.";
					output += eol;
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date husbBirth = (Date)hubbyMap.get("BIRT");
				if(husbBirth.compareTo(death_date) == 1)
				{
					System.out.println("Warning US#3: Family ID "+famkey+" has husband who has death date beofore his birthdate.");
					output += "Warning US#3: Family ID "+famkey+" has husband who has death date beofore his birthdate.";
					output += eol;
				}
			}
			
			
		}
		return output;
	}

	//US#7
	public String olderThan150()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			String ID = me.getKey().toString();
			HashMap indvalue = (HashMap)me.getValue();
			Date currDate = new Date();
			Date birthDate = new Date();
			if(indvalue.containsKey("BIRT"))
			{
				birthDate = (Date)indvalue.get("BIRT");
			}
			long temp = currDate.getTime()-birthDate.getTime();
			double age = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
			if(age>150)
			{
				System.out.println("Warning US#7: Individual ID "+ID+" has age more than 150.");
				output += "Warning US#7: Individual ID "+ID+" has age more than 150." + eol;
			}
		}
		return output;
	}
	
	//US#10
	public String marriageBefore14()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext())
		{
			Date mar_date = new Date();
			
			String wifey = "";
			String hubby = "";
			String child = "";
			double wifeAge = 0;
			double husbandAge = 0;
			
			
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();			
			HashMap famvalue = (HashMap)me.getValue();
			
			if(famvalue.containsKey("MARR"))
			{
				mar_date = (Date)famvalue.get("MARR");
			}
			else
			{
				return "";
			}
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				Date wifeBirth = (Date)wifeyMap.get("BIRT");
				long temp = mar_date.getTime()-wifeBirth.getTime();
				wifeAge = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
				//System.out.println("WifeAge: "+wifeAge);
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
				HashMap hubbyMap = (HashMap)individual.get(wifey);
				Date husbBirth = (Date)hubbyMap.get("BIRT");
				long temp = mar_date.getTime()-husbBirth.getTime();
				husbandAge = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
			}
			
			if(wifeAge<14)
			{
				System.out.println("Warning US#10: Family ID "+famkey+" has wife who has marriage before 14.");
				output += "Warning US#10: Family ID "+famkey+" has wife who has marriage before 14." + eol;
			}
			if(husbandAge<14)
			{
				System.out.println("Warning US#10: Family ID "+famkey+" has husband who has marriage before 14.");
				output += "Warning US#10: Family ID "+famkey+" has husband who has marriage before 14." + eol;
			}
		}
		return output;
	}
	
	//US#8
	public String birthbeforemarriageofparents()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Date mar_date = new Date();
			
			String wifey = "";
			String hubby = "";
			String child = "";
			//Date wifeBirth = "";
			//Date husbBirth = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("MARR"))
			{
				mar_date = (Date)famvalue.get("MARR");
				//System.out.println("MarrDate is: "+marrDate);
			}
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			if(famvalue.containsKey("CHIL"))
			{
				child = (String)famvalue.get("CHIL");
			}	
			
			if(wifey != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				Date wifeBirth = (Date)wifeyMap.get("BIRT");
				//System.out.println("WifeDate is: "+wifeBirth);
				if(wifeBirth.compareTo(mar_date) == 1)
				{
					System.out.println("Warning US#8: Family ID "+famkey+" has wife who has death date beofer her birthdate.");
					output += "Warning US#8: Family ID "+famkey+" has wife who has death date beofer her birthdate." + eol;
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date husbBirth = (Date)hubbyMap.get("BIRT");
				if(husbBirth.compareTo(mar_date) == 1)
				{
					System.out.println("Warning US#8: Family ID "+famkey+" has husband who has death date beofore his birthdate.");
					output += "Warning US#8: Family ID "+famkey+" has husband who has death date beofore his birthdate.";
					output += eol;
				}
			}
			if(child != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date childBirth = (Date)hubbyMap.get("BIRT");
				if(childBirth.compareTo(mar_date) == 1)
				{
					System.out.println("Warning US#8: Family ID "+famkey+" has child who was born before the death of his/her parents.");
					output += "Warning US#8: Family ID "+famkey+" has child who was born before the death of his/her parents.";
					output += eol;
				}
			}
        }
		return output;
	}
	
	//US#6
	public String divorceBeforeDeath()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Date divDate = new Date();
			
			String wifey = "";
			String hubby = "";
			//Date wifeBirth = "";
			//Date husbBirth = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("DIV"))
			{
				divDate = (Date)famvalue.get("DIV");
				//System.out.println("divDate is: "+divDate);
			}
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			
			
			if(wifey != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				if(wifeyMap.containsKey("DEAT"))
				{
					Date wifeDeath = (Date)wifeyMap.get("DEAT");
					//System.out.println("WifeDate is: "+wifeBirth);
					if(divDate.compareTo(wifeDeath) == 1)
					{
						System.out.println("Warning US#6: Family ID "+famkey+" has wife who has divorce date after her Death.");
						output += "Warning US#6: Family ID "+famkey+" has wife who has divorce date before her Death.";
						output += eol;
					}
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				if(hubbyMap.containsKey("DEAT"))
				{
					Date husbDeath = (Date)hubbyMap.get("DEAT");
					if(divDate.compareTo(husbDeath) == 1)
					{
						System.out.println("Warning US#6: Family ID "+famkey+" has husband who has divorce date before his Death.");
						output += "Warning US#6: Family ID "+famkey+" has husband who has divorce date before his Death.";
						output += eol;
					}
				}
			}
		}
		return output;
	}
	
	//US#9 Born after death of parents (Karan Shah)
	public String birthafterdeathofparents()
	{
		Set set = family.entrySet();
		Iterator it = set.iterator();
		String output = "";
		String eol = System.getProperty("line.separator");
		System.out.println("US#9 (Karan Shah)");
		System.out.println("Child Names born after death of Parents:");
		output += "US#9 (Karan Shah)"+eol;
		output += "Child Names born after death of Parents:"+eol;
		
		while(it.hasNext())
		{
			Date death_date = new Date();
			
			String wifey = "";
			String hubby = "";
            String child = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is:"+famkey);
			HashMap famvalue = (HashMap)me.getValue();
			//System.out.println("FamValue is:"+famvalue);
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			if(famvalue.containsKey("CHIL"))
			{
				child = (String)famvalue.get("CHIL");
			}
			if(!child.equals(""))
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				if(!child.contains("-"))
				{
					HashMap childMap = (HashMap)individual.get(child);
					Date childBirth = (Date)childMap.get("BIRT");
					if(hubbyMap.containsKey("DEAT") && wifeyMap.containsKey("DEAT"))
					{
						Date husbdeathDate = (Date)hubbyMap.get("DEAT");
						Date wifedeathDate = (Date)wifeyMap.get("DEAT"); 
						if(childBirth.compareTo(husbdeathDate) == 1 && childBirth.compareTo(wifedeathDate) == 1)
						{
							String name = (String)childMap.get("NAME");
							String[] names = name.split("/"); 
							System.out.println(names[0]+names[1]);
							output += names[0]+names[1]+eol;
						}
					}
				}
				else
				{
					String[] children = child.split("-");
					for(int i = 0; i < children.length;i++)
					{
						HashMap childMap = (HashMap)individual.get(children[i]);
						Date childBirth = (Date)childMap.get("BIRT");
						if(hubbyMap.containsKey("DEAT") && wifeyMap.containsKey("DEAT"))
						{
							Date husbdeathDate = (Date)hubbyMap.get("DEAT");
							Date wifedeathDate = (Date)wifeyMap.get("DEAT"); 
							if(childBirth.compareTo(husbdeathDate) == 1 && childBirth.compareTo(wifedeathDate) == 1)
							{
								String name = (String)childMap.get("NAME");
								String[] names = name.split("/"); 
								System.out.println(names[0]+names[1]);
								output += names[0]+names[1];
							}
						}
					}
				}
			}
		}
		return output;
	}
	
	//US#21
	public String correctGenger()
	{
		Set set = family.entrySet();
		Iterator it = set.iterator();
		String output = "";
		String eol = System.getProperty("line.separator");
		while(it.hasNext())
		{
			String wifey = "";
			String hubby = "";
            
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}
			
			if(wifey != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(wifey);
				if(wifeyMap.containsKey("SEX"))
				{
					String wifeGender = (String)wifeyMap.get("SEX");
					
					if(!wifeGender.equals("F"))
					{
						System.out.println("Warning US#21: Family ID "+famkey+" has wife who is not Female.");
						output += "Warning US#21: Family ID "+famkey+" has wife who is not Female";
						output += eol;
					}
				}
			}
			
			if(hubby != "")
			{
				HashMap wifeyMap = (HashMap)individual.get(hubby);
				if(wifeyMap.containsKey("SEX"))
				{
					String wifeGender = (String)wifeyMap.get("SEX");
					
					if(!wifeGender.equals("M"))
					{
						System.out.println("Warning US#21: Family ID "+famkey+" has husband who is not Male.");
						output += "Warning US#21: Family ID "+famkey+" has husband who is not Male";
						output += eol;
					}
				}
			}
		}
		return output;
	}
	
	//US#12
	public String tooOldParents()
	{
		Set set = family.entrySet();
		Iterator it = set.iterator();
		String output = "";
		String eol = System.getProperty("line.separator");
		while(it.hasNext())
		{
			Date death_date = new Date();
			
			String wifey = "";
			String hubby = "";
            String child = "";
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			
			HashMap famvalue = (HashMap)me.getValue();
			
			if(famvalue.containsKey("WIFE"))
			{
				wifey = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("HUSB"))
			{
				hubby = (String)famvalue.get("HUSB");
			}	
			if(famvalue.containsKey("CHIL"))
			{
				child = (String)famvalue.get("CHIL");
			}
			
			if(child!="")
			{
				if(!child.contains("-"))
				{
					HashMap childMap = (HashMap)individual.get(child);
					Date childBirth = (Date)childMap.get("BIRT");
					
					if(wifey != "")
					{
						HashMap wifeyMap = (HashMap)individual.get(wifey);
						if(wifeyMap.containsKey("BIRT"))
						{
							Date wifeBirth = (Date)wifeyMap.get("BIRT");
							long temp = childBirth.getTime()-wifeBirth.getTime();
							double age = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
							if(age>60)
							{
								System.out.println("Warning US#12: Family ID "+famkey+" has too old mother.");
								output += "Warning US#12: Family ID "+famkey+" has too old mother.";
								output += eol;
							}
						}
					}
				
				
					if(hubby != "")
					{
						HashMap hubbyMap = (HashMap)individual.get(wifey);
						if(hubbyMap.containsKey("BIRT"))
						{
							Date husbBirth = (Date)hubbyMap.get("BIRT");
							long temp = childBirth.getTime()-husbBirth.getTime();
							double age = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
							if(age>80)
							{
								System.out.println("Warning US#12: Family ID "+famkey+" has too old father.");
								output += "Warning US#12: Family ID "+famkey+" has too old father.";
								output += eol;
							}
						}
					}
				}
				else
				{
					String[] children = child.split("-");
					for(int i=0;i<children.length;i++)
					{
						HashMap childMap = (HashMap)individual.get(children[i]);
						//System.out.println("Family Debug1: Individual ID:"+children[i]);
						Date childBirth = (Date)childMap.get("BIRT");
						//System.out.println("Family Debug2: Individual ID:"+children[i]);
						
						if(wifey != "")
						{
							HashMap wifeyMap = (HashMap)individual.get(wifey);
							if(wifeyMap.containsKey("BIRT"))
							{
								Date wifeBirth = (Date)wifeyMap.get("BIRT");
								long temp = childBirth.getTime()-wifeBirth.getTime();
								double age = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
								if(age>60)
								{
									System.out.println("Warning US#12: Family ID "+famkey+" has too old mother.");
									output += "Warning US#12: Family ID "+famkey+" has too old mother.";
									output += eol;
								}
							}
						}
					
					
						if(hubby != "")
						{
							HashMap hubbyMap = (HashMap)individual.get(wifey);
							if(hubbyMap.containsKey("BIRT"))
							{
								Date husbBirth = (Date)hubbyMap.get("BIRT");
								long temp = childBirth.getTime()-husbBirth.getTime();
								double age = Math.round(temp / 1000 / 60 / 60 / 24 / 365);
								if(age>80)
								{
									System.out.println("Warning US#12:Family ID "+famkey+" has too old father.");
									output += "Warning US#12: Family ID "+famkey+" has too old father.";
									output += eol;
								}
							}
						}
					}
				}
			}
		}
		return output;
	}
	
	//US#16: Ketu Shah
	public String maleLastNames()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			
			Map.Entry me = (Map.Entry)it.next();
			
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			String chil = "";
			String husb = "";
			String husbLastName = "";
			HashMap famValue = (HashMap)me.getValue();
			
			if(famValue.containsKey("HUSB"))
			{
				husb = (String)famValue.get("HUSB");
				HashMap hubbyMap = (HashMap)individual.get(husb);
				String name = (String)hubbyMap.get("NAME");
				String[] names = name.split("/");
				husbLastName = names[1];
			}
			
			if(famValue.containsKey("CHIL"))
			{
				chil = (String)famValue.get("CHIL");
			}
			
			
			if(!chil.equals(""))
			{
				if(!chil.contains("-"))
				{
					HashMap childMap = (HashMap)individual.get(chil);
					String sex = (String)childMap.get("SEX");
					if(sex.equals("M"))
					{
						String name = (String)childMap.get("NAME");
						String[] names = name.split("/");
						String chilLastName = names[1];
						if(!husbLastName.equals(""))
						{
							if(!chilLastName.equals(husbLastName))
							{
								System.out.println("Warning US#16: Family ID "+famkey+" has different surnames for male.");
								output += "Warning US#16: Family ID "+famkey+" has different surnames for male.";
								output += eol;
							}
						}
					}
				}
				else
				{
					String[] children = chil.split("-");
					//System.out.println(children.length);
					for(int i =0; i<children.length;i++)
					{
						//System.out.println(children[i]);
						HashMap childMap = (HashMap)individual.get(children[i]);
						String sex = (String)childMap.get("SEX");
						if(sex.equals("M"))
						{
							String name = (String)childMap.get("NAME");
							String[] names = name.split("/");
							String chilLastName = names[1];
							if(!husbLastName.equals(""))
							{
								if(!chilLastName.equals(husbLastName))
								{
									System.out.println("Warning US#16: Family ID "+famkey+" has different surnames for male.");
									output += "Warning US#16: Family ID "+famkey+" has different surnames for male.";
									output += eol;
								}
							}
						}
					}
				}
				
			}
		}
		return output;
	}
	
	
	//US#17: Ketu Shah
	public String noMarriageToDescendants()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			
			Map.Entry me = (Map.Entry)it.next();
			String famkey = me.getKey().toString();
			//System.out.println("Key is: "+key);
			String wife = "";
			String husb = "";
			HashMap famValue = (HashMap)me.getValue();
			if(famValue.containsKey("HUSB"))
			{
				husb = (String)famValue.get("HUSB");
			}
			if(famValue.containsKey("WIFE"))
			{
				wife = (String)famValue.get("WIFE");
			}
			
			Set innerset = family.entrySet();
			Iterator innerit = innerset.iterator();
			while(innerit.hasNext())
			{
				Map.Entry innerme = (Map.Entry)innerit.next();
				String innerfamkey = innerme.getKey().toString();
				HashMap innerfamValue = (HashMap)innerme.getValue();
				String innerhusb = "";
				if(innerfamValue.containsKey("HUSB"))
				{
					innerhusb = (String)innerfamValue.get("HUSB");
				}
				String innerwife = "";
				if(innerfamValue.containsKey("WIFE"))
				{
					innerwife = (String)innerfamValue.get("WIFE");
				}
				if(!innerfamkey.equals(famkey) && innerhusb.equals(husb))
				{
					if(innerfamValue.containsKey("CHIL"))
					{
						String chil = (String)innerfamValue.get("CHIL");
						if(!chil.contains("-"))
						{
							if(chil.equals(wife))
							{
								System.out.println("Warning US#17: Family ID "+famkey+" has marriage to descedants.");
								output += "Warning US#17: Family ID "+famkey+" has marriage to descedants.";
								output += eol;
							}
						}
						else
						{
							String[] children = chil.split("-");
							ArrayList<String> chList = new ArrayList<String>(Arrays.asList(children));
							if(chList.contains(wife))
							{
								System.out.println("Warning US#17: Family ID "+famkey+" has marriage to descedants.");
								output += "Warning US#17: Family ID "+famkey+" has marriage to descedants.";
								output += eol;
							}
						}
					}
				}
				
				if(!innerfamkey.equals(famkey) && innerwife.equals(wife))
				{
					if(innerfamValue.containsKey("CHIL"))
					{
						String chil = (String)innerfamValue.get("CHIL");
						if(!chil.contains("-"))
						{
							if(chil.equals(husb))
							{
								System.out.println("Warning US#17: Family ID "+famkey+" has marriage to descedants.");
								output += "Warning US#17: Family ID "+famkey+" has marriage to descedants.";
								output += eol;
							}
						}
						else
						{
							String[] children = chil.split("-");
							ArrayList<String> chList = new ArrayList<String>(Arrays.asList(children));
							if(chList.contains(husb))
							{
								System.out.println("Warning US#17: Family ID "+famkey+" has marriage to descedants.");
								output += "Warning US#17: Family ID "+famkey+" has marriage to descedants.";
								output += eol;
							}
						}
					}
				}
			}
		}
		return output;
	}
	
	//Ketu Shah: US29(Sprint 3)
	public String listDeceased()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		
		output += "US#29- List of Deceased Individuals:"+eol;
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String indId = me.getKey().toString();			
			HashMap indVal = (HashMap)me.getValue();
			if(indVal.containsKey("DEAT"))
			{
				String name = (String)indVal.get("NAME");
				String[] names = name.split("/");
				output+=indId+":"+names[0]+names[1]+eol;
			}
		}
		System.out.println(output);
		return output;
	}
	
	//Ketu Shah:US28
	public String sortSiblingsByAge()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			
			String famKey = me.getKey().toString();			
			HashMap famValue = (HashMap)me.getValue();
			String chil = "";
			
			if(famValue.containsKey("CHIL"))
			{
				chil = (String)famValue.get("CHIL");
				if(chil.contains("-"))
				{
					//System.out.println(chil);
					String[] children = chil.split("-");
					ArrayList<String> chilList = new ArrayList<String>();
					for(int j=0;j<children.length;j++)
					{
						HashMap indvalue = (HashMap)individual.get(children[j]);
						Date currDate = new Date();
						Date birthDate = new Date();
						if(indvalue.containsKey("BIRT"))
						{
							birthDate = (Date)indvalue.get("BIRT");
						}
						long temp = currDate.getTime()-birthDate.getTime();
						double age = temp / 1000 / 60 / 60 / 24 / 365;
						
						String name = (String)indvalue.get("NAME");
						
						String[] names = name.split("/");
						chilList.add(age+":"+names[0]+" "+names[1]);
					}
					Collections.sort(chilList);
					System.out.println("US#28- Sorted Siblings by Age for Family "+famKey+":");
					output+="US#28- Sorted Siblings by Age for Family "+famKey+":"+eol;
					for(String ch:chilList)
					{
						String[] temp = ch.split(":");
						System.out.println("Name:"+temp[1]+" Age:"+temp[0]);
						output+="Name:"+temp[1]+" Age:"+temp[0]+eol;
					}
				}
			}
		}
		return output;
	}
	
	/*US #23 Sweta chowdary Finding Unique Birthdates and Name*/
	public String uniqueBirthdatesandName()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			
			Map.Entry me = (Map.Entry)it.next();
			String famkey = me.getKey().toString();
            Date birDate = new Date();
			
			String nm = "";
			
			HashMap famvalue = (HashMap)me.getValue();
			if(famvalue.containsKey("BIRT"))
			{
				birDate = (Date)famvalue.get("BIRT");
			}
			
            if(famvalue.containsKey("NAME"))
			{
				nm = (String)famvalue.get("NAME");	
			}
			
			//HashMap ind1= (HashMap)individual.get(nm);
			//HashMap ind2= (HashMap)individual.get(nm);
			
			
			Set innerset = individual.entrySet();
			Iterator innerit = innerset.iterator();
			while(innerit.hasNext())
			{
				Map.Entry innerme = (Map.Entry)innerit.next();
				String indId = innerme.getKey().toString();
								
				HashMap indVal = (HashMap)innerme.getValue();
				if(!famkey.equals(indId))
				{
					Date bd = (Date)indVal.get("BIRT");
					String n = (String)indVal.get("NAME");
					if(bd.compareTo(birDate)==0 && n.equals(nm))
					{
						System.out.println("Warning US#23:Same name .");
						output += "Warning US#23: Individuals have the same name.";
						output += eol;
					}
				}
			}
        }
		return output;
	}
	
	//US#25
	public String uniqueFirstName()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			
			Map.Entry me = (Map.Entry)it.next();
			String famkey = me.getKey().toString();
			HashMap famvalue = (HashMap)me.getValue();
			String husb = "";
			String wife = "";
			String chil = "";
			if(famvalue.containsKey("HUSB"))
			{
				husb = (String)famvalue.get("HUSB");
			}
			if(famvalue.containsKey("WIFE"))
			{
				wife = (String)famvalue.get("WIFE");
			}
			if(famvalue.containsKey("CHIL"))
			{
				chil = (String)famvalue.get("CHIL");
			}
			
			ArrayList<String> names = new ArrayList<String>();
			if(husb!="")
			{
				HashMap tempMap = (HashMap)individual.get(husb);
				String name = (String)tempMap.get("NAME");
				String[] temp = name.split("/");
				String husbName = temp[0];
				if(names.contains(husbName))
				{
					System.out.println("Warning US#25:Same First Name in Family "+famkey);
					output += "Warning US#25: Individuals have the Same First Name in Family "+famkey;
					output += eol;
				}
				else
				{
					names.add(husbName);
				}
			}
			if(wife!="")
			{
				HashMap tempMap = (HashMap)individual.get(wife);
				String name = (String)tempMap.get("NAME");
				String[] temp = name.split("/");
				String wifeName = temp[0];
				if(names.contains(wifeName))
				{
					System.out.println("Warning US#25:Same First Name in Family "+famkey);
					output += "Warning US#25: Individuals have the Same First Name in Family "+famkey;
					output += eol;
				}
				else
				{
					names.add(wifeName);
				}
			}
			if(chil!="")
			{
				if(chil.contains("-"))
				{
					String[] children = chil.split("-");
					for(String c:children)
					{
						HashMap tempMap = (HashMap)individual.get(c);
						String name = (String)tempMap.get("NAME");
						String[] temp = name.split("/");
						String cName = temp[0];
						if(names.contains(cName))
						{
							System.out.println("Warning US#25:Same First Name in Family "+famkey);
							output += "Warning US#25: Individuals have the Same First Name in Family "+famkey;
							output += eol;
						}
						else
						{
							names.add(cName);
						}
					}
				}
				else
				{
					HashMap tempMap = (HashMap)individual.get(chil);
					String name = (String)tempMap.get("NAME");
					String[] temp = name.split("/");
					String cName = temp[0];
					if(names.contains(cName))
					{
						System.out.println("Warning US#25:Same First Name in Family "+famkey);
						output += "Warning US#25: Individuals have the Same First Name in Family "+famkey;
						output += eol;
					}
					else
					{
						names.add(cName);
					}
				}
			}
		}
		return output;
	}
	
	
	
	//US #14 Multiple birth less than five        
	public String multipleBirthLessThanFive()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			String chil = "";
			String indId = me.getKey().toString();			
			HashMap indVal = (HashMap)me.getValue();
			ArrayList<Date> birthDateList = new ArrayList<Date>();
			int count = 0;
			
			if(indVal.containsKey("CHIL"))
			{
				chil = (String)indVal.get("CHIL");
			}
			
			if(chil!="")
			{
				if(chil.contains("-"))
				{
					String[] children = chil.split("-");
					if(children.length>5)
					{
						for(String c:children)
						{
							HashMap tempMap = (HashMap)individual.get(c);
							Date bdate = (Date)tempMap.get("BIRT");
							birthDateList.add(bdate);
						}
					}
				}
			}
			
			for(int i=0;i<birthDateList.size()-1;i++)
			{
				for(int j=i+1;j<birthDateList.size()-1;j++)
				{
					if(birthDateList.get(i).compareTo(birthDateList.get(i))==0)
					{
						count++;
					}
				}
			}
			
			if(count>5)
			{
				System.out.println("Warning US#14:More than five births at the same time in Family "+indId);
				output+="Warning US#14:More than five births at the same time in Family "+indId+eol;
			}
		}
		return output;
	}
	
	//US#15: More than 15 Siblings
	public String moreThan15Siblings()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		
		
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			String chil = "";
			String indId = me.getKey().toString();			
			HashMap indVal = (HashMap)me.getValue();
			
			if(indVal.containsKey("CHIL"))
			{
				chil = (String)indVal.get("CHIL");
			}
			
			if(chil!="")
			{
				if(chil.contains("-"))
				{
					String[] children = chil.split("-");
					if(children.length>15)
					{
						System.out.println("Warning US#15: More than 15 siblings in family "+indId);
						output+="Warning US#15: More than 15 siblings in family "+indId+eol;
					}
				}
			}
		}
		return output;
	}
	
	// US#30 List Living Married (Karan Shah)
	public String listlivingmarried()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		System.out.println("US#30 - List Living Married");
		output = "US#30- List of Living Married:"+eol;
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			Date marrDate = new Date();
			String wifey = "";
			String hubby = "";
			String famkey = me.getKey().toString();
			HashMap famvalue = (HashMap)me.getValue();
			if(famvalue.containsKey("MARR"))
			{
				marrDate = (Date)famvalue.get("MARR");
			}
			if(!famvalue.containsKey("DIV"))
			{
				if(famvalue.containsKey("WIFE"))
				{
					wifey = (String)famvalue.get("WIFE");
				}
				if(famvalue.containsKey("HUSB"))
				{
					hubby = (String)famvalue.get("HUSB");
				}	
				if(wifey != "" && hubby != "")
				{
					HashMap wifeyMap = (HashMap)individual.get(wifey);
					HashMap hubbyMap = (HashMap)individual.get(hubby);
					if(!wifeyMap.containsKey("DEAT") && !hubbyMap.containsKey("DEAT"))
					{
						String wifename = (String)wifeyMap.get("NAME");
						String[] wifenames = wifename.split("/");
						String husbname = (String)hubbyMap.get("NAME");
						String[] husbnames = husbname.split("/");
						System.out.println(wifenames[0]+wifenames[1]+" AND "+husbnames[0]+husbnames[1]);
						output+=wifenames[0]+wifenames[1]+" AND "+husbnames[0]+husbnames[1]+eol;
					}
				}
			}
		}
		return output;
	}
	
	//US#33 - List orphans (Karan Shah)
	public String listorphans()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		System.out.println("US#33 - List Orphans");
		output = "US#33 - List Orphans:"+eol;
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			Date marrDate = new Date();
			String wifey = "";
			String hubby = "";
			String famkey = me.getKey().toString();
			HashMap famvalue = (HashMap)me.getValue();
			if(famvalue.containsKey("CHIL"))
			{
				String childy = (String)famvalue.get("CHIL");
				if(famvalue.containsKey("WIFE"))
				{
					wifey = (String)famvalue.get("WIFE");
				}
				if(famvalue.containsKey("HUSB"))
				{
					hubby = (String)famvalue.get("HUSB");
				}	
				if(wifey != "" && hubby != "" && childy != "")
				{
					HashMap wifeyMap = (HashMap)individual.get(wifey);
					HashMap hubbyMap = (HashMap)individual.get(hubby);
					HashMap childMap = (HashMap)individual.get(childy);
					if(wifeyMap.containsKey("DEAT") && hubbyMap.containsKey("DEAT"))
					{
						String childname = (String)childMap.get("NAME");
						String[] childnames = childname.split("/");
						System.out.println(childnames[0]+childnames[1]);
						output+=childnames[0]+childnames[1]+eol;
					}
				}
			}
		}
		return output;
	}
	
	//US#32: Multiple Births
	public String multipleBirthsList()
	{
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = family.entrySet();
		Iterator it = set.iterator();
		
		
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			String chil = "";
			String indId = me.getKey().toString();			
			HashMap indVal = (HashMap)me.getValue();
			ArrayList<String> birthDateList = new ArrayList<String>();
			int count = 0;
			
			if(indVal.containsKey("CHIL"))
			{
				chil = (String)indVal.get("CHIL");
			}
			
			if(chil!="")
			{
				if(chil.contains("-"))
				{
					String[] children = chil.split("-");
					if(children.length>5)
					{
						for(String c:children)
						{
							HashMap tempMap = (HashMap)individual.get(c);
							Date bdate = (Date)tempMap.get("BIRT");
							birthDateList.add(c+"-"+bdate.toString());
						}
					}
				}
			}
			ArrayList<String> multiList = new ArrayList<String>();
			
			for(int i=0;i<birthDateList.size()-1;i++)
			{
				String t1 = birthDateList.get(i);
				String[] temp1 = t1.split("-");
				for(int j=i+1;j<birthDateList.size()-1;j++)
				{
					String t2 = birthDateList.get(i);
					String[] temp2 = t2.split("-");
					if(temp1[1].equals(temp2[1]))
					{
						count++;
						if(!multiList.contains(temp1[0]))
						{
							multiList.add(temp1[0]);
						}
						if(!multiList.contains(temp2[0]))
						{
							multiList.add(temp2[0]);
						}
					}
				}
			}
			
			if(count>2)
			{
				
				System.out.println("US#32:List of Multiple Births in Family "+indId);
				output+="lololol"+count+eol;
				output+="Warning US#32:List of Multiple Births in Family "+indId+eol;
				for(int i=0;i<multiList.size();i++)
				{
					System.out.println(multiList.get(i));
					output+=multiList.get(i)+eol;
				}
			}
		}
		return output;
	}
	
	// US#35 List Recent Births (Karan Shah)
	
	public String listrecentBirths()
	{
		
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		
		System.out.println("US#35 List Recent Births");
		output += "US#35 List recent Births"+eol;
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			String indId = me.getKey().toString();			
			HashMap indVal = (HashMap)me.getValue();
			
			if(indVal.containsKey("BIRT"))
			{
				Date birth = (Date)indVal.get("BIRT");
				DateFormat df = new SimpleDateFormat("dd MMM yyyy");
				Date today = new Date();
				
				long temp = birth.getTime() - today.getTime();
				int days = Math.round(temp / 1000 / 60 / 60 / 24 );
				
				if(days < 0 && days > -30 )
				{
					String birthname = (String)indVal.get("NAME");
					String[] birthnames = birthname.split("/");					
					System.out.println(birthnames[0]+birthnames[1]);
					output += birthnames[0]+birthnames[1]+eol;
				}
			}
		}
		return output;
	}
	
	// US#36 List Recent Deaths (Karan Shah)
	public String listrecentDeaths()
	{
		
		String output = "";
		String eol = System.getProperty("line.separator");
		Set set = individual.entrySet();
		Iterator it = set.iterator();
		
		System.out.println("US#36 List Recent Deaths");
		output += "US#36 List recent Deaths"+eol;
		
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			String indId = me.getKey().toString();			
			HashMap indVal = (HashMap)me.getValue();
			
			if(indVal.containsKey("DEAT"))
			{
				Date death = (Date)indVal.get("DEAT");
				DateFormat df = new SimpleDateFormat("dd MMM yyyy");
				Date today = new Date();
				
				long temp = death.getTime() - today.getTime();
				int days = Math.round(temp / 1000 / 60 / 60 / 24 );
				
				if(days < 0 && days > -30 )
				{
					String deathname = (String)indVal.get("NAME");
					String[] deathnames = deathname.split("/");					
					System.out.println(deathnames[0]+deathnames[1]);
					output += deathnames[0]+deathnames[1]+eol;
				}
			}
		}
		return output;
	}
	
	/* public int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	} */
}

class gedcomParserP04
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
			File fl = new File("C:\\Users\\skind\\Desktop\\Stevens\\Agile\\Team03Project08\\output.txt");
			if (!fl.exists()) 
			{
				fl.createNewFile();
			}
			
			FileWriter fw = new FileWriter(fl.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String line;
			List lineList = new ArrayList();
			br = new BufferedReader(new FileReader("C:\\Users\\skind\\Desktop\\Stevens\\Agile\\Team03Project08\\familyTreeNew.ged"));
			while((line = br.readLine()) != null)
			{
				lineList.add(line);
				int linenumber = lineList.size(); 
				// System.out.println(linenumber);
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
							if(indiMap.containsKey(strdel[1]))
							{
								System.out.println("Warning- US22: Individual with ID " +strdel[1] +" already exists.");
								output+="Warning- US22: Individual with " +strdel[1] +" already exists."+eol;
							}
							else
							{
								indiMap.put(strdel[1],desc);
								id = strdel[1];
							}
						}
						if(strdel[2].equals("FAM"))
						{
							desc = new HashMap();
							//System.out.println(strdel[1]+" family");
							if(indiMap.containsKey(strdel[1]))
							{
								System.out.println("Warning- US22: Family with ID " +strdel[1]+" already exists.");
								output+="Warning- US22: Family with ID " +strdel[1] +" already exists."+eol;
							}
							else
							{
								famMap.put(strdel[1],desc);
								id = strdel[1];
							}
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
								//String chil = (String)desc.get("CHIL");
								if(!desc.containsKey("CHIL"))
								{
									desc.put("CHIL",strdel[2]);
								}
								else
								{
									String chil = (String)desc.get("CHIL");
									chil = chil+"-"+strdel[2];
									desc.put("CHIL",chil);
								}
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
									String strDate = strdel[2]+" "+strdel[3]+" "+strdel[4];
									//Date strDate = new Date(Integer.parseInt(strdel[4]),Integer.parseInt(strdel[3]),Integer.parseInt(strdel[2]));
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
									Date date=dateFormat.parse(strDate);
									date = dateFormat.parse(strDate);
									System.out.println("Testestest: "+date);
									
									desc.put("BIRT",date);
									dateDesc = 0;
								}
								else if(dateDesc == 2)
								{
									String strDate = strdel[2]+" "+strdel[3]+" "+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
									Date date=dateFormat.parse(strDate);
									date = dateFormat.parse(strDate);
									desc.put("DEAT",date);
									dateDesc = 0;
								}
								else if(dateDesc == 3)
								{
									String strDate = strdel[2]+" "+strdel[3]+" "+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
									Date date=dateFormat.parse(strDate);
									date = dateFormat.parse(strDate);
									desc.put("MARR",date);
									dateDesc = 0;
								}
								else if(dateDesc == 4)
								{
									String strDate = strdel[2]+"-"+strdel[3]+"-"+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
									Date date=dateFormat.parse(strDate);
									date = dateFormat.parse(strDate);
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
				String wifey = "";
				if(temp1.containsKey("WIFE"))
				{
					wifey = (String)temp1.get("WIFE");
				}
				String hubby = (String)temp1.get("HUSB");
				
				if(wifey!="")
				{
					HashMap wifeyMap = (HashMap)indiMap.get(wifey);
					//System.out.println("wifeyMap:"+wifeyMap.size());
					String name = (String)wifeyMap.get("NAME");
					//System.out.println("Wife: "+wifey);
					String[] names = name.split("/");
					
					System.out.println("Wife: "+names[0]+" "+names[1]);
				}
				HashMap hubbyMap = (HashMap)indiMap.get(hubby);
				String name1 = (String)hubbyMap.get("NAME");
				String[] names1 = name1.split("/");
				
				System.out.println("Husband: "+names1[0]+" "+names1[1]);
			}
			
			FamDetails fds = new FamDetails(indiMap,famMap);
			
			//US 01
			output+=fds.dateBeforeCurrentDate();
			
			//US 02
			output+=fds.birthBeforeMarriageDate();
			
			//US 04
			output+=fds.marriageBeforeDivorce();
			
			//US 05
			output+=fds.marriageBeforeDeath();
                        
            //US 03
			output+=fds.birthBeforedeath();
                        
            //US 08
			output+=fds.birthbeforemarriageofparents();
			
			//US 06 
			output+=fds.divorceBeforeDeath();
			
			//US 09
			output+=fds.birthafterdeathofparents();
			
			//US 16
			output+=fds.maleLastNames();
			
			//US 17
			output+=fds.noMarriageToDescendants();
			
			//US 10
			output+=fds.marriageBefore14();
			
			//US 7
			output+=fds.olderThan150();
			
			//US 12
			output+=fds.tooOldParents();
			
			//US 21
			output+=fds.correctGenger();
			
			//US 29
			output+=fds.listDeceased();
			
			//US 28
			output+=fds.sortSiblingsByAge();
			
			//US 25
			output+=fds.uniqueFirstName();
			
			//US 23
			output+=fds.uniqueBirthdatesandName();
			
			//US#14
			output+=fds.multipleBirthLessThanFive();
			
			//US#15
			output+=fds.moreThan15Siblings();
			
			//US#32
			output+=fds.multipleBirthsList();
			
			//US#30
			output+=fds.listlivingmarried();
			
			//US#33
			output+=fds.listorphans();
			
			//US#35
			output += fds.listrecentBirths();
			
			//US#36
			output += fds.listrecentDeaths();
			
			System.out.println(fds.printSummary());
			output+=fds.printSummary();
			System.out.println(output);
			
            bw.write(output);
			bw.close();            
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}