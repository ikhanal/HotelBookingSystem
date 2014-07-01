
import java.util.*;
import java.io.*;
public class HOSMenu
{
   static String itemFile;
   static String roomFile;
   static HOS hos =new HOS();
   static int orderOption;
   public static void main(String args[])throws IOException
   {
      loadingData();
      mainMenu();
     
   } 

      public static void loadingData()throws IOException
      {
         
         System.out.println("---------------------------------------------------------------------");
         System.out.println("Hotel Booking System - Loading Data");
         System.out.println("---------------------------------------------------------------------");
         
         System.out.print("Enter the name for ITEMS data file (Including Extension) :");
         Scanner keyboard= new Scanner(System.in);
         itemFile= keyboard.nextLine();
         
         hos.loadItemList(itemFile);
         
         System.out.print("Enter the name for ROOMS data file (including extension) :");
         
         roomFile=keyboard.nextLine();
         
         hos.loadRoomList(roomFile);
         
        
      }
      public static void mainMenu()throws IOException
      {
        System.out.println("---------------------------------------------------------------------");
        
        System.out.println("Hotel Booking System -- Main Menu");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("1. Set (or Remove) Filter");
        System.out.println("2. Create a New Order");
        System.out.println("3. Add an item to the current order"); 
        System.out.println("4. Remove an Item form the Current Order");
        System.out.println("5. Display the current order");
        System.out.println("6. Finalize the order");

        System.out.println("S: Show the state of the system for Inspection");
        System.out.println("E: Exit");

        System.out.println("---------------------------------------------------------------------");
        System.out.print("Enter your option (1-6 or S or E)");
        
        Scanner keyboard = new Scanner(System.in);
        String enter=keyboard.nextLine().toUpperCase();

        char c=enter.charAt(0);

        switch(c)
        {
            case '1':
               filterMenu();
               //showFilterMenu();
               goToMainMenu();
               break;
            case '2':
               orderMenu();
               showMainMenu();
               //goToMainMenu();
               break;
            case '3':
                addItemMenu();
                showMainMenu();
                //goToMainMenu();
                break;

            case '4':
               removeItemMenu();
              // showMainMenu();
               goToMainMenu();
               break;
            case '5':
               displayCurrentOrderMenu();
              // showMainMenu();
               goToMainMenu();
               break;
            case '6':
               finalizeOrderMenu();
              // showMainMenu();
              goToMainMenu();
               break;
            case 'S':
               showSystemState();
               //showMainMenu();
               goToMainMenu();
               break;
            case 'E':
               System.out.println("Thank you for using Hotel Booking System");
               System.exit(0);

               break;
            default:
               System.out.println("Yyou might entered wrong choice");
               showMainMenu();
               //goToMainMenu();
               break;

        }
       
      }
      public static void filterMenu()
      { 
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Hote Ordering System - Set Filter");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("1: ENTREE");
        System.out.println("2: MAIN_COURSE");
        System.out.println("3: SIDE_DISH");
        System.out.println("4: DESERT");
        System.out.println("5: DRINK");
        System.out.println("A: All(remove filter)");
        System.out.println("---------------------------------------------------------------------");
        System.out.print("Enter your option(1-5 or A)");
        
        Scanner keyboard= new Scanner(System.in);
        String enter=keyboard.nextLine().toUpperCase();
        char option=enter.charAt(0);

        switch(option)
        {
            case'1':
               System.out.println(hos.setFilter(Category.ENTREE));
                             
               break;
            case'2':
              System.out.println(hos.setFilter(Category.MAIN_COURSE));
             	   
                break;
            case'3':
               System.out.println( hos.setFilter(Category.SIDE_DISH));

               break;
            case'4':
               System.out.println(hos.setFilter(Category.DESERT));

               break;
            case'5':
              
               System.out.println( hos.setFilter(Category.DRINK));
		
               break;
            case'A':
               System.out.println(hos.removeFilter());
		 
               break;
            default:
               System.out.println("You might enter wrong choice");
               goToMainMenu();
               break;

        }
        
      }
      public static void showFilterMenu()
      {
         System.out.print("Do you want to re-filter (r) or go to main menu(m)?");
         Scanner keyboard=new Scanner(System.in);
         String enter=keyboard.nextLine().toUpperCase();
         char option=enter.charAt(0);
         switch(option)
         {
            case'R':
            filterMenu();
            break;
            case'M':
            try
            {
               mainMenu();
            }catch(IOException i)
            {

            }
	     break;
	     default:
	     System.out.println("You might enter wrong choice");
	     break;

         }
         
      }
      
      public static void orderMenu()
      {
         System.out.println("---------------------------------------------------------------------");
         System.out.println("Hotel Booking System - Create New Order");
         System.out.println("---------------------------------------------------------------------");
         System.out.print("Enter the room number");
         Scanner keyboard = new Scanner(System.in);
         int roomNr=keyboard.nextInt();
         
         hos.createNewOrder(roomNr); 
      }
      public static void addItemMenu()
      {
         System.out.println("---------------------------------------------------------------------");
         System.out.println("Hotel Booking System- Add an Item to the order");
         System.out.println("---------------------------------------------------------------------");
         hos.displayFilteredList();
		   Scanner keyboard=new Scanner(System.in);
		   int option=keyboard.nextInt();
	     
         hos.addItemToOrder(hos.getFilteredItemList().get(option-1));
         addMoreItemMenu();
         
        
        
      }
      // option 4 of main menu
      public static void removeItemMenu()
      {
          System.out.println("---------------------------------------------------------------------");
          System.out.println("Hotel Booking System - Remove an itme from the order");
          System.out.println("---------------------------------------------------------------------");
          //code
          hos.displayRemovingItem();
          
          Scanner keyboard=new Scanner(System.in);
          int enter=keyboard.nextInt();

          hos.removeItemFromOrder(enter);
          removeMoreItemMenu();

      }
      //option 5 of main menu
      public static void displayCurrentOrderMenu()
      {
          System.out.println("---------------------------------------------------------------------");
          System.out.println("Hotel Booking System - Display the current order");
          System.out.println("---------------------------------------------------------------------");
          hos.displayOrder();

      }
      //option 6 of main menu
      public static void finalizeOrderMenu()
      {
          System.out.println("---------------------------------------------------------------------");
          System.out.println("Hotel Booking System - Finalize order");
          System.out.println("---------------------------------------------------------------------");
         // code
          hos.displayOrder();

          System.out.println("---------------------------------------------------------------------");
          System.out.print("Do you want to continue(y/n :");
          Scanner keyboard=new Scanner(System.in);
          String enter=keyboard.nextLine().toUpperCase();
          char option=enter.charAt(0);
          switch(option)
          {
             case'Y':
             hos.saveOrder();
             System.out.println("The order has successfully saved");
             //Order currentOrder=new Order(room,itemList);
             break;
             case'N':
             System.out.println("The order has been cancelled");
            // Order currentOrder=new Order(room,itemList);
             break;
             default:
             System.out.println("Your answer is invalid. No action is taken!");
             break;
          }

           
      }
      public static void showSystemState()
      {
         HOSTester host=new HOSTester();
         host.testHosMethods();
      }
      public static void goToMainMenu()
      {
         System.out.println("---------------------------------------------------------------------");
         System.out.print("Do you want to go to  main Menu(m) or Exit(e) ?");
         Scanner keyboard=new Scanner(System.in);
         String enter=keyboard.nextLine().toUpperCase();
         char option=enter.charAt(0);
         switch(option)
                 {
                    case'M':
                    try
                    {
                       mainMenu();
                    }catch(IOException i)
                    {

                    }
                    break;
                    case'E':
                    System.exit(0);
                    default:
                    break;

                 }

      }
      public static void showMainMenu()
      {
         try
         {
            mainMenu();
         }
         catch(IOException i)
         {

         }
      }
      public static void addMoreItemMenu()
      {
         System.out.print("Do you want to add more item(a) or go to main menu 'm'?");
         Scanner keyboard=new Scanner(System.in);
         String enter=keyboard.nextLine().toUpperCase();
         char option = enter.charAt(0);
         switch(option)
         {
            case'A':
            addItemMenu();
            break;
            case'M':
            try
            {
               mainMenu();
            }catch(IOException i)
            {

            }
            break;
            default:
            System.out.println("You might enter wrong character");
            addItemMenu();
            break;
         }
      }
      public static void removeMoreItemMenu()
      {
         System.out.print("Do you want to remove more item(r) or go to main menu(m)?");
         Scanner keyboard = new Scanner(System.in);
         String enter= keyboard.nextLine().toUpperCase();
         char option=enter.charAt(0);
         switch(option)
         {
            case'R':
            removeItemMenu();
            break;
            case'M':
            try
            {
               mainMenu();
            }
            catch(IOException i)
            {

            }
         }
      }
   }
