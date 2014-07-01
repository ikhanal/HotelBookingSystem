import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HOS 
{ 
   static int  dynamicCount;
   private  ArrayList<Item> itemList=null;
   private  ArrayList<Room> roomList= null;
   Order currentOrder;
   Category filter;
   ArrayList<Item> filteredList=new ArrayList<Item>();
   ArrayList<Item> orderedList=new ArrayList<Item>();
   Item item=null;
   Room room=null;

   
   public ArrayList<Item> getItemList()
   {
     return itemList;
   }
   public ArrayList<Room> getRoomList()
   {
      return roomList;
   }
   public void  loadItemList(String file_path)throws IOException
   { int count=0;
      
         itemList=new ArrayList<Item>();
         FileReader file= new FileReader(file_path);
         BufferedReader br=new BufferedReader(file);
         String line=null;
         
         while((line=br.readLine())!=null)
         {

            String id;
            String name;
            double price;
            Category category;
                      
              id=line;
              name=br.readLine();
              price=Double.parseDouble(br.readLine());
              category=Category.valueOf(br.readLine());
               
             
              itemList.add(new Item(id,name,price,category));
			  // to skip the .dot
              br.readLine();
         }
             
         
         

       //  return count;
   }
   public void loadRoomList(String file_path)
   {
      try{
         roomList=new ArrayList<Room>();
         FileReader file=new FileReader(file_path);
         BufferedReader br = new BufferedReader(file);
         String line=null;
         
            while((line=br.readLine())!=null)
            {
               int roomNr;
               String occupant;
               int nrAdults;
               int nrChildren;
               
               roomNr=Integer.parseInt(line);
               occupant=br.readLine();
               nrAdults=Integer.parseInt(br.readLine());
               nrChildren=Integer.parseInt(br.readLine());
               roomList.add(new Room(roomNr, occupant, nrAdults, nrChildren));
               // to skip the . dot
               br.readLine();
            }
         }
         catch(IOException i)
         {

         }
   }
   public String toString()
   {
      
     // for item list
        String str=getClass().getName()+"\n\nitemList: [";
        
        for(Item item: itemList)
        {
            str+="   "+item.getClass().getName()+"[ code: "+item.getId()+", name: "+item.getName()+", price: "+item.getPrice()+", category: "+item.getCategory()+"],";
        }        
        str+="]";
        // for room list

        str+="\n\nroomList: [";
        for(Room room: roomList)
        {
            str+="   "+room.getClass().getName()+"[ roomNr: "+room.getRoomNr()+", occupant: "+room.getOccupant()+", nrAdults: "+room.getNrAdults()+", nrChildren: "+room.getNrChildren()+"],";
        }
        str+="]";

        // for current order
        Room current_room=currentOrder.getRoom();
        str+="\n\ncurrentOrder: "+currentOrder.getClass().getName()+"[ room: ";
        str+=current_room.getClass().getName()+"[ roomNr: "+current_room.getRoomNr()+", occupant: "+current_room.getOccupant()+", NrAdults: "+current_room.getNrAdults()+", NrChildren: "+current_room.getNrChildren()+"]"; 
       
        str+=", items: [";
        for(Item item: orderedList)
        {
           str+="   "+item.getClass().getName()+"[ code: "+item.getId()+", name: "+item.getName()+", price: "+item.getPrice()+", category: "+item.getCategory()+"]";
        }
        str+="]]";

        // for filter 
        
        str+="\n\nfilter:  "+filter;
        
        // for added item 
   	  str+="\n\nfilteredItemList: [";
        for(Item item: filteredList)
        {
           str+="   "+item.getClass().getName()+"[ code: "+item.getId()+", name: "+item.getName()+", price: "+item.getPrice()+", category: "+item.getCategory()+"]";
        }
        str+="]";


        return str;
    
   
   }
  
  public void createNewOrder(int roomNr)
  {
         roomList=getRoomList();  
         for(Room room: roomList)
         {   
            if(room.getRoomNr()==roomNr)
            
            {
               currentOrder = new Order(room, itemList);

            }

         }
      
  }
  
  public String setFilter(Category cat)
  {
        String strOut=" ";
        itemList=getItemList();
        int count=1;
         for(Item item: itemList)
         {
            if(item.getCategory().equals(cat))
            {
               strOut+=String.format("%d : %s %s %.2f %s \n ", count,item.getId(),item.getName(),item.getPrice(),item.getCategory());
               
               ++count;
                           
               filteredList.add(item);
            }
         }
	if(strOut!=" ")
	{
	return strOut;
	}
    
	else 
	{
	return "There are no items of this type";
	}
  }
  public String removeFilter()
  {
      String strOut="";
      filteredList=new ArrayList<Item>();
      int count=1;
      for(Item item: itemList)
      {
      strOut+=String.format("%d : %s %s %.2f %s \n", count,item.getId(),item.getName(),item.getPrice(),item.getCategory());
      
      ++count;
      filteredList.add(item);
     }
    return strOut;
  }
  public ArrayList<Item> getFilteredItemList()
   {
      return filteredList;
   }
   
  public void displayItemList()
  {
     itemList=getItemList();
     int count=1;
     for(Item item: itemList)
     {
        System.out.printf("%d : %s %.2f", count,item.getName(),item.getPrice());
        System.out.println();
        ++count;
     }
  }
  public void displayFilteredList()
  {	filteredList=getFilteredItemList();
     int count=1;
     for(Item item: filteredList)
     {
        System.out.printf("%d : %s %s %.2f %s", count,item.getId(),item.getName(),item.getPrice(),item.getCategory());
        System.out.println();
        ++count;
     }
     dynamicCount=count-1;
     System.out.printf("Enter your choice of of item to add(1-%d)",count-1);
  }
  
  public void addItemToOrder(Item item)
  {	if(currentOrder!=null)
	{
	orderedList.add(item);
	}
	else
	{
	System.out.println("No order has been booked yet !!!");
	}
  }
  public ArrayList<Item> getOrderedList()
  {
     return orderedList;
  }
  public void displayRemovingItem()
  {
     orderedList=getOrderedList();
     int count=1;
     for(Item item: orderedList)
     {
        System.out.printf("%d : %s %s %.2f %s", count,item.getId(),item.getName(),item.getPrice(),item.getCategory());
        System.out.println();
        ++count;
     }
      System.out.println("---------------------------------------------------------------------");
	   System.out.printf("Enter your choice of  item to remove(1-%d)",count-1);
  	      
   }
  public void removeItemFromOrder(int option)
  {
   	   
          orderedList.remove(option-1);
      
  }
  public void displayOrder()
  {
     double total=0.00;
     
     Room room=currentOrder.getRoom();
    
        System.out.printf("Room Number :%d",room.getRoomNr());
        System.out.println();
        System.out.println("Food items ordered :");
        for(Item item: orderedList)
        {
            System.out.printf(" %s %.2f ",item.getName(),item.getPrice());
            System.out.println();
            total+=item.getPrice();

        }
        System.out.printf("Total :%.2f",total);
        System.out.println();
  
  }
  public void saveOrder()
  {
      try
      {  
         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat timeFormat=new SimpleDateFormat("HH-mm-ss");
         SimpleDateFormat saveFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
         Date date=new Date();
         String dateFormat1=dateFormat.format(date).toString();
         String timeFormat1=timeFormat.format(date).toString();
         String saveFormat1=saveFormat.format(date).toString();


         Room room=currentOrder.getRoom();
         
         String filePath=String.format("MealOrder-Room-%d-Date-%s-Time-%s.txt",room.getRoomNr(),dateFormat1,timeFormat1);
         

         File file = new File("/home/student/ikhanal/IOO/HotelBookingSystemBills/"+filePath);

         if(!file.exists())
         {
            file.createNewFile();

         }
         FileWriter fw=new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw=new BufferedWriter(fw);
         bw.write(saveFormat1);
         bw.write("\n Room Number :"+room.getRoomNr());
         bw.write("\n Food items ordered:");
         double total=0.00;
         for(Item item: orderedList)
         {
            String content=String.format("\n\t %s%.2f ",item.getName(),item.getPrice());
          
            bw.write(content);
            total+=item.getPrice();
         }
         bw.write("\n Total :"+total);
         bw.close();

      }catch(IOException i)
      {

      }
  }
  
 }
