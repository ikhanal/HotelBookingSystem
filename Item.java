public class Item
{
  private  String id;
  private  String name;
  private  double price;
  private  Category category;

   public Item(String id, String name, double price, Category category)
   {
      this.id= id;
      this.name = name;
      this.price= price;
      this.category=category;
   }
   
   public String getId()
   {
      return this.id;
   }
   public String getName()
   {
      return this.name;
   }
   public double getPrice()
   {
      return this.price;
   }
   public Category getCategory()
   {
      return this.category;
   }

   public void setId(String id)
   {
      this.id=id;

   }
   public void setName()
   {

   }
   public void setPrice()
   {

   }
   public void setCategory()
   {

   }
}
