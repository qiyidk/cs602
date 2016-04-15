package njit.cs602.qiyi.assignment3.genericMethod;

public class GenericMethodTest 
{
   public static void main(String[] args) 
   {
      // create arrays of Integer, Double and Character
      Integer[] integerArray = {1, 2, 3, 4, 5, 6};
      Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
      Character[] characterArray = {'H', 'E', 'L', 'L', 'O'};

      System.out.println("Array integerArray contains:");
      printArray(integerArray); // pass an Integer array
      System.out.println();
      System.out.println("Array doubleArray contains:");
      printArray(doubleArray); // pass a Double array
      System.out.println();
      System.out.println("Array characterArray contains:");
      printArray(characterArray); // pass a Character array
      System.out.println();
      
      // print array within a range
      print("integerArray", integerArray, 0, 5);
      print("doubleArray", doubleArray, 1, 4);
      print("characterArray", characterArray, 2, 3);
      
      // invalid input
      print("integerArray", integerArray, -1, 5);
      print("doubleArray", doubleArray, 0, 10);
      print("characterArray", characterArray, -2, -5);
      
   } 
   
   public static <T> void print(String arrayName, T[] inputArray, int lowSubscript, int highSubscript){
       try{
           System.out.println("Array " + arrayName + "(range from index "+ lowSubscript + " to " + highSubscript +") contains:");
           System.out.println("the number of element:" + printArray(inputArray, lowSubscript, highSubscript)); 
           System.out.println();
       }
       catch (InvalidSubscriptException e){
           System.out.println(e.getMessage());
       }
   }
   
   // generic method printArray
   public static <T> int printArray(T[] inputArray, int lowSubscript, int highSubscript)
   {
      if (lowSubscript < 0)
          throw new InvalidSubscriptException("The lowSubscript is out of range!");
      if (highSubscript > inputArray.length - 1){
          throw new InvalidSubscriptException("The highSubscript is out of range!");
      }
      
      // display array elements within the specified range
      for (int i = lowSubscript; i <= highSubscript; i++)
         System.out.printf("%s ", inputArray[i]);
      System.out.println();
      return highSubscript - lowSubscript + 1;
   }
   
   // generic method printArray
   public static <T> void printArray(T[] inputArray)
   {
      // display array elements
      for (T element : inputArray)
         System.out.printf("%s ", element);

      System.out.println();
   }
}