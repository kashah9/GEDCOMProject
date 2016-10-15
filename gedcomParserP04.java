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
					System.out.println("Warning US#2: Family ID "+famkey+" has wife who has marriage date after her birthdate.");
					output += "Warning US#2: Family ID "+famkey+" has wife who has marriage date after her birthdate.";
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
					System.out.println("Warning: Family ID "+famkey+" has marriage date "+marrDate+" after divorce date.");
					output += "Warning: Family ID "+famkey+" has marriage date "+marrDate+" after divorce date.";
					output += eol;
				}
				//System.out.println("MarrDate is: "+marrDate);
			}
		}
		return output;
	}
	
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
						System.out.println("Warning: Family ID "+famkey+" has wife who has marriage date after her death.");
						output += "Warning: Family ID "+famkey+" has wife who has marriage date after her death.";
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
						System.out.println("Warning: Family ID "+famkey+" has husband who has marriage date after his death.");
						output += "Warning: Family ID "+famkey+" has wife who has marriage date after her death.";
						output += eol;
					}
				}
			}
		}
		return output;
	}
	

/*Shweta_Chowdary_10406940*/
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
					System.out.println("Warning: Family ID "+famkey+" has wife who has death date beofer her birthdate.");
					output += "Warning: Family ID "+famkey+" has wife who has death date beofer her birthdate.";
					output += eol;
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date husbBirth = (Date)hubbyMap.get("BIRT");
				if(husbBirth.compareTo(death_date) == 1)
				{
					System.out.println("Warning: Family ID "+famkey+" has husband who has death date beofore his birthdate.");
					output += "Warning: Family ID "+famkey+" has husband who has death date beofore his birthdate.";
					output += eol;
				}
			}
			
			
		}
		return output;
	}

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
					System.out.println("Warning: Family ID "+famkey+" has wife who has death date beofer her birthdate.");
					output += "Warning: Family ID "+famkey+" has wife who has death date beofer her birthdate." + eol;
				}
			}
			
			if(hubby != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date husbBirth = (Date)hubbyMap.get("BIRT");
				if(husbBirth.compareTo(mar_date) == 1)
				{
					System.out.println("Warning: Family ID "+famkey+" has husband who has death date beofore his birthdate.");
					output += "Warning: Family ID "+famkey+" has husband who has death date beofore his birthdate.";
					output += eol;
				}
			}
			if(child != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(hubby);
				Date childBirth = (Date)hubbyMap.get("BIRT");
				if(childBirth.compareTo(mar_date) == 1)
				{
					System.out.println("Warning: Family ID "+famkey+" has child who was born before the death of his/her parents.");
					output += "Warning: Family ID "+famkey+" has child who was born before the death of his/her parents.";
					output += eol;
				}
			}
        }
		return output;
	}
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
						System.out.println("Warning US#6: Family ID "+famkey+" has wife who has divorce date after her marriage.");
						output += "Warning US#6: Family ID "+famkey+" has wife who has divorce date before her marriage.";
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
						System.out.println("Warning US#6: Family ID "+famkey+" has husband who has divorce date before his marriage.");
						output += "Warning US#6: Family ID "+famkey+" has husband who has divorce date before his marriage.";
						output += eol;
					}
				}
			}
		}
		return output;
	}
	public String birthafterdeathofparents()
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
			//System.out.println("Value is: "+famvalue);
			if(famvalue.containsKey("DEAT"))
			{
				death_date = (Date)famvalue.get("DEAT");
				//System.out.println("Death date is: "+death_date);
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
				if(wifeyMap.containsKey("DEAT"))
				{
					Date wifeDeath = (Date)wifeyMap.get("DEAT");
					//System.out.println("wifeDeath is: "+wifeDeath);
					if(wifeDeath.compareTo(death_date) == 1)
					{
						System.out.println("Warning: Family ID "+famkey+" has wife who has death date beofer her birthdate.");
						output += "Warning US#9: Family ID "+famkey+" has wife who has death date beofer her birthdate.";
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
					if(husbDeath.compareTo(death_date) == 1)
					{
						System.out.println("Warning: Family ID "+famkey+" has husband who has death date beofore his birthdate.");
						output += "Warning US#9: Family ID "+famkey+" has husband who has death date beofore his birthdate.";
						output += eol;
					}
				}
			}
			if(child != "")
			{
				HashMap hubbyMap = (HashMap)individual.get(child);
				if(hubbyMap.containsKey("DEAT"))
				{
					Date childBirth = (Date)hubbyMap.get("DEAT");
					if(childBirth.compareTo(death_date) == 1)
					{
						System.out.println("Warning: Family ID "+famkey+" has child who was born after the death of his/her parents.");
						output += "Warning US#9: Family ID "+famkey+" has child who was born after the death of his/her parents.";
						output += eol;
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
							System.out.println("Warning: Family ID "+famkey+" has different surnames for male.");
							output += "Warning US#16: Family ID "+famkey+" has different surnames for male.";
							output += eol;
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
						if(chil.equals(wife))
						{
							System.out.println("Warning: Family ID "+famkey+" has marriage to descedants.");
							output += "Warning US#17: Family ID "+famkey+" has marriage to descedants.";
							output += eol;
						}
					}
				}
				
				if(!innerfamkey.equals(famkey) && innerwife.equals(wife))
				{
					if(innerfamValue.containsKey("CHIL"))
					{
						String chil = (String)innerfamValue.get("CHIL");
						if(chil.equals(husb))
						{
							System.out.println("Warning: Family ID "+famkey+" has marriage to descedants.");
							output += "Warning US#17: Family ID "+famkey+" has marriage to descedants.";
							output += eol;
						}
					}
				}
			}
		}
		return output;
	}
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
			File fl = new File("C:\\Users\\Ketu\\Desktop\\CS555 Agile\\Week 5\\output.txt");
			if (!fl.exists()) 
			{
				fl.createNewFile();
			}
			
			FileWriter fw = new FileWriter(fl.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line;
			List lineList = new ArrayList();
			br = new BufferedReader(new FileReader("C:\\Users\\Ketu\\Desktop\\CS555 Agile\\Week 4\\familyTreeWithMistakes.ged"));
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
									String strDate = strdel[2]+"-"+strdel[3]+"-"+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
									Date date=dateFormat.parse(strDate);
									
									desc.put("DEAT",date);
									dateDesc = 0;
								}
								else if(dateDesc == 3)
								{
									String strDate = strdel[2]+"-"+strdel[3]+"-"+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
									Date date=dateFormat.parse(strDate);
									
									desc.put("MARR",date);
									dateDesc = 0;
								}
								else if(dateDesc == 4)
								{
									String strDate = strdel[2]+"-"+strdel[3]+"-"+strdel[4];
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
									Date date=dateFormat.parse(strDate);
									
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
			
			//US 01
			output+=fds.dateBeforeCurrentDate();
			
			//US 02
			output+=fds.birthBeforeMarriageDate();
			
			output+=fds.marriageBeforeDivorce();
			
			output+=fds.marriageBeforeDeath();
                        
            output+=fds.birthBeforedeath();
                        
            output+=fds.birthbeforemarriageofparents();
			
			//US 06 
			output+=fds.divorceBeforeDeath();
			
			//US 09
			output+=fds.birthafterdeathofparents();
			
			//US 16
			output+=fds.maleLastNames();
			
			//US 17
			output+=fds.noMarriageToDescendants();
			
			System.out.println(fds.printSummary());
			output+=fds.printSummary();
			
            bw.write(output);
			bw.close();            
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}