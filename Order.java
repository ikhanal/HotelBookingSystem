import java.util.*;
import java.io.*;
public class Order
{
   Room room;
   ArrayList<Item> itemList=new ArrayList<Item>();
   
   public Order(Room room, ArrayList<Item> itemList)
   {
      this.room=room;
      this.itemList=itemList;
   }
   public Room getRoom()
   {
      return this.room;
   }
   public ArrayList<Item> getItemList()
   {
      return itemList;
   }

}
