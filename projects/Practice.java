import java.util.Scanner;

//
//
//public class Practice {
//    public static void main(String[] args) {
//        int[][] arr = new int[10][10];
//
//        for (int i = 0; i < 10 ; ) {
//            for (int j = 0; j < 10; )
//
//
////                arr[i][j] = 0;
//                j++;
//                System.out.print(arr[i][j]);
//            }
//            System.out.println();
//            i++;
//        }
////        System.out.println(arr);
//        }



//public class Practice {
//    public static void main(String[] args) {
//        String a = "ai";
//        String b = "hell";
//        System.out.println("a.compareTo(b) = " + a.compareTo(b));
//    }
//}
//    public static void main(String[] args) {
//
//        int[] arr ;
//        System.out.println("arr = " +);
//        Scanner Scan = new Scanner(System.in);
//        String todo = "";
//
//        System.out.println("Would you like to A, S, M, D");
//        todo = Scan.next();
//        System.out.println("Whats your first num?");
//        float int1 = Scan.nextFloat();
//
//        System.out.println("What is your second num?");
//        float int2 = Scan.nextFloat();
//
//        switch (todo){
//            case "A":
//                System.out.println("The sum of the two numbers is: " + (int1 + int2));
//                break;
//            case "S":
//                System.out.println("The differnce between the numbers is: " + (int1 - int2));
//                break;
//            case "M":
//                System.out.println("The product of the two numbers is: " + (int1 * int2));
//                break;
//            case "D":
//                System.out.println("The quotient of the two numbers is: " + (int1 / int2));
//                
//                break;
//            default:
//                System.out.println("CHoose another input");
//    }
//
//
//}
//
//    }
//

//class Animal {
//    public void animalSound() {
//        System.out.println("The animal makes a sound");
//    }
//}
//
//class Pig extends Animal {
//    public void animalSound() {
//        System.out.println("The pig says: wee wee");
//    }
//}
//
//class Dog extends Animal {
//    public void animalSound() {
//        System.out.println("The dog says: bow wow");
//    }
//}
//
//class Practice {
//    public static void main(String[] args) {
//        Animal myAnimal = new Animal();  // Create a Animal object
//        Animal myPig = new Pig();  // Create a Pig object
//        Animal myDog = new Dog();  // Create a Dog object
//        myAnimal.animalSound();
//        myPig.animalSound();
//        myDog.animalSound();
//    }
//}

//import java.io.File;
//import java.util.Scanner;
//public class Practice {
//    public static void main(String[] args) {
////        File myObj = new File("src/prog1input1.txt");
////        if (myObj.exists()) {
////            System.out.println("File name: " + myObj.getName());
////            System.out.println("Absolute path: " + myObj.getAbsolutePath());
////            System.out.println("Writeable: " + myObj.canWrite());
////            System.out.println("Readable " + myObj.canRead());
////            System.out.println("File size in bytes " + myObj.length());
////        } else {
////            System.out.println("The file does not exist.");
//
//
//        File myObj = new File("filename.txt");
//        Scanner myReader = new Scanner(myObj);
//        while (myReader.hasNextLine()) {
//            String data = myReader.nextLine();
//            System.out.println(data);
//
//        }
//    }
//}
import java.io.File;
import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        try {
            // Create a File object
            File myObj = new File("src/prog1input1.txt");

            // Create a Scanner object to read from the file
            Scanner myReader = new Scanner(myObj);

            // Read each line from the file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }

            // Close the scanner
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
