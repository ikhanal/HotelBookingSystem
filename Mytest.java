import java.util.*;
import java.io.*;
public class Mytest
{
   public static void readFile(String file_path)throws FileNotFoundException, IOException
   {
      FileReader fr=new FileReader(file_path);
      BufferedReader br=new BufferedReader(fr);
      String line=null;
      while((line=br.readLine())!=null)
      {
         
         System.out.println(line);

      }
      

   }


 
   
}
