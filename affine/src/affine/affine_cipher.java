package affine;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class affine_cipher {
	static String alphabet="abcdefghijklmnopqrstuvwxyz. ", plainText="", encryptedText="", decryptedText="";
    static int result=0, currentChar=0, choice=0, key1=0, key2=0, frequency1=0, frequency2=0, Decryption_key1=0, Decryption_key2=0;
    static int[] lettersFreqeuncy= new int[26];
    static List<Integer> ValidKeys;
    
    
    public static void main(String[] args)throws IOException {       
		   try{
			   // open and read from file
			   File ptf = new File("C:\\Users\\1tite\\Downloads\\college work\\m1 ~ cs\\advanced cryptography\\substitution ciphers\\affine.txt"); // plain text file
			   Scanner scanner = new Scanner(ptf);                                                  // to read the file
			   while(scanner.hasNextLine()) {                                                       // open reader
				   plainText = scanner.nextLine();
			   }
			   scanner.close(); 
			   
			   BufferedReader bfn = new BufferedReader(
			            new InputStreamReader(System.in));
			   
			   System.out.print("Choose the affine cipher mode : ");
			   choice = Integer.parseInt(bfn.readLine());
			   			   
			   switch(choice) {
			         case 1 : System.out.print("first key = ");
					          key1 = Integer.parseInt(bfn.readLine());
					          System.out.print("second key = ");
					          key2 = Integer.parseInt(bfn.readLine()); 
							  key_check(key1,1);
			        	      affineCipher(alphabet,plainText,key1,key2,choice); 
			                  break;
			         case 2 : frequencyAnalysis(plainText,lettersFreqeuncy);
			        	      affineCipher(alphabet,plainText,Decryption_key1,Decryption_key2,choice); 
			                  break;
			         default: System.out.print("choose either 1 or 2."); 
			                  break;
			   } 
		   }   
		   catch(FileNotFoundException ex) {
			   System.out.println("File not found");
			   ex.printStackTrace();
		   }
    }
    
	// check key
	private static void key_check(int a,int b) {
		   if(extended_euclidean_algorithm(a,b)!=1) throw new IllegalArgumentException("can't utilize "+a+" as key value.");
	}
	
	private static void affineCipher(String alphabet, String plaintext, int Key1, int key2, int mode) {
		    if(mode == 2) {
		   // Modular Multiplicative inverse 	
		    	if (extended_euclidean_algorithm(Key1, 28) != 1) {
		            List<Integer> validKeys = findValidKeys(28);
		            for (int validKey : validKeys) {
		                Key1 = multiplicative_inverse(validKey, 28);
		                if (Key1 != 0) { 
		                    System.out.println("Using valid key " + validKey + " with inverse " + Key1); // Debugging
		                    break;
		                }
		            }
		        } else {
		            Key1 = multiplicative_inverse(Key1, 28);
		        }
	  
	       // Modular Additive inverse 
	          key2 = (-key2)%28 < 0 ? (-key2)%28+28 : (-key2)%28;
		    }
		    
		// cipher process 
		for(int i=0;i<plaintext.length();i++){						
                currentChar = alphabet.indexOf(plaintext.charAt(i));	

                if(mode ==1) {
                	   result = ((Key1 * currentChar + key2) % alphabet.length());
                	   encryptedText += alphabet.charAt(result);       	   
	         	}
                else {
                 	   result = (Key1 * (currentChar-key2+alphabet.length())) % alphabet.length() < 0 ? ((Key1 * (currentChar-key2+alphabet.length())) % alphabet.length())+alphabet.length() : (Key1 * (currentChar-key2+alphabet.length())) % alphabet.length();             
                 	   decryptedText += alphabet.charAt(result);       	    
                }
		 }
		 if(mode == 1)   System.out.print(encryptedText);
		 else            System.out.print(decryptedText);
	}
	
	// most two frequent letters
    private static void frequencyAnalysis(String plaintext, int[] lettersFrequency) {	
    	int index1=0, index2=0;
    	
		// initialization
		for(int i=0;i<lettersFrequency.length;i++){
			lettersFrequency[i]=0;	    	
		}
		
		// count letters frequency
		for(int i=0;i<plaintext.length();i++){
			char currentChar = plaintext.charAt(i);
			
			// handling white space & other characters
			if(currentChar < 'a' || currentChar > 'z') {continue;}

		    lettersFrequency[currentChar - 'a']++;
		}		
		
		// find max and second max
		for(int i=0;i<lettersFrequency.length;i++){
			if(frequency1<lettersFrequency[i]) {
				frequency2=frequency1;
				index2=index1;

				frequency1=lettersFrequency[i];
				index1=i;
			}else if(frequency2<lettersFrequency[i]) {
				frequency2=lettersFrequency[i];
				index2=i;
			}
		}
			
		Decryption_key1 = ((index1-4)%27  < 0) ? ((index1-4)%27) +27 : ((index1-4) %27); 
		Decryption_key2 = ((index2-19)%27 < 0) ? ((index2-19)%27)+27 : ((index2-19)%27); 
		
    }
	
	
   // multiplicative inverse
    private static int multiplicative_inverse(int a, int b) {
    	int oldB = b; // Store original value of b
        int r1 = a, r2 = b;
        int s1 = 1, s2 = 0;
        int t1 = 0, t2 = 1;

        while (r2 > 0) {
            int q = r1 / r2;
            int r = r1 % r2;
            int s = s1 - q * s2;
            int t = t1 - q * t2;

            r1 = r2;
            r2 = r;
            s1 = s2;
            s2 = s;
            t1 = t2;
            t2 = t;
        }

        // t1 is the coefficient of 'a' which gives us the modular inverse
        int inverse = t1 % oldB; // Use the original modulus to ensure correct modulo
        if (inverse < 0) {
            inverse += oldB; // Ensure inverse is positive
        }
        
        return inverse;
    }
     
   // extended euclidean algorithm  
    private static int extended_euclidean_algorithm(int a, int b) {
 	   if(b<=0) return a;
 	   return extended_euclidean_algorithm(b,a%b);
    }
    
   // find valid multiplicative keys 
    public static List<Integer> findValidKeys(int m) {
        List<Integer> validKeys = new ArrayList<>();
        for (int a = 1; a < m; a++) {
            if (extended_euclidean_algorithm(a, m) == 1) {
                validKeys.add(a);
            }
        }
        System.out.println("Valid Keys: " + validKeys); // Debugging: print valid keys

        return validKeys;
    }
}
