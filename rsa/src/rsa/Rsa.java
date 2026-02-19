package rsa;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Rsa {
	private static BigInteger messageM,messageC,p,q,n,qn,e,d,p_,q_;
    private static int p_s,q_s,n_s,qn_s,e_s,d_s,e_s_,k_s_;
    private static long messageM_s,messageC_s,messageM_s_,messageC_s_;
    private static List<Pair<Integer, Integer>> pairs = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while(running) {
            System.out.println("===========================================");
            System.out.println("           RSA Encryption Modes           ");
            System.out.println("===========================================");
            System.out.println("Please choose an RSA mode :");
            System.out.println("1- RSA Version 1 (BigInteger-based RSA for large numbers)");
            System.out.println("2- RSA Version 2 (Integer-based RSA -- simplified)");
            System.out.println("3- RSA with Chinese Remainder Theorem -- (CRT) for optimized decryption");
            System.out.println("4- RSA Malleability Demonstration     -- (Showcasing the malleability vulnerability)");
            System.out.println("5- RSA Factorization Attack           -- (Deriving p and q from φ(n))");
            System.out.println("6- Code testing parts ");
            System.out.println("7- Exit ");
            System.out.println("===========================================");

            int choice = scanner.nextInt();	
	        switch (choice) {
	            case 1:
	                System.out.println("\nYou have selected RSA with BigInteger.");
	                System.out.println("This mode uses BigInteger class to handle large numbers.");
	                System.out.println("It is more suitable for cryptographic applications where numbers are typically very large.");
	                rsaBigIntegerMode();                            // Perform RSA BigInteger mode
	                break;
	
	            case 2:
	                System.out.println("\nYou have selected RSA with Regular Integer.");
	                System.out.println("This mode uses regular integers, which are simpler but limited by size (only works with small numbers).");
	                System.out.println("It is mostly for educational demonstrative purposes.");
	                rsaRegularIntegerMode();                        // Perform RSA regular integer mode
	                break;
	            
	            case 3:
	            	System.out.println("\nYou have selected RSA with Chinese Remainder Theorem (CRT).");
	            	System.out.println("This mode optimizes RSA decryption by breaking calculations into smaller modular arithmetic operations.");
	            	System.out.println("It significantly improves performance, especially when working with large keys, by leveraging the properties of modular arithmetic.");
	            	System.out.println("This approach is highly suitable for scenarios where decryption speed is critical.");
	            	rsaCRTMode(); 									// Perform RSA CRT mode
                    break;
                    
	            case 4:
	            	System.out.println("\nYou have selected RSA with Malleability consideration.");
	            	System.out.println("This mode highlights a potential vulnerability in RSA encryption, where an attacker can modify the ciphertext.");
	            	System.out.println("Due to RSA's malleability, an attacker can alter the encrypted message, resulting in a different but valid plaintext when decrypted.");
	            	System.out.println("While this does not break the encryption itself, it allows unauthorized modifications of the original message, posing a significant risk.");
	            	System.out.println("To mitigate this issue, additional measures such as digital signatures or authenticated encryption are recommended.");
	            	rsaMLBMode();
	                break;
	            
	            case 5:
	                rsaFAMode();
	                break; 
	            
	            case 6:
	                System.out.println("Exit ...");
	                running = false;
	                break;    
	                
	            default:
	                System.out.println("Invalid choice. Please select either 1, 2 or 3");
	                break;
	         }
        }

        scanner.close();
    }

    // RSA with BigInteger Method (for large numbers)
    public static void rsaBigIntegerMode() {
        System.out.println("\nPerforming RSA with BigInteger...");
        
        messageM = new BigInteger(128, new Random());                      // Generate a random plain text
        bigInteger_publickey_generation();                                 // Generate public key
        bigInteger_privatekey_generation(); 							   // Generate private key
        System.out.println("Before Encryption : Plain text  = "+messageM);
        bigIntegerEncryption();             							   // Perform encryption 
        System.out.println("After  Encryption : Cipher text = "+messageC);
        bigIntegerDecryption();             							   // Perform decryption
        System.out.println("After  Decryption : Plain text  = "+messageM+"\n");
    }

    // RSA with Regular Integer Method (simplified version)
    public static void rsaRegularIntegerMode() {
        System.out.println("\nPerforming RSA with Regular Integer...");
        
        simplifiedRsa_publickey_generation();                              // Generate public key
        simplifiedRsa_privatekey_generation(); 							   // Generate private key
        simplifiedRsa_message_generation();  

        System.out.println("Before Encryption : Plain text  = "+messageM_s);
        simplifiedRsaEncryption();             							   // Perform encryption 
        System.out.println("After  Encryption : Cipher text = "+messageC_s);
        simplifiedRsaDecryption();             							   // Perform decryption
        System.out.println("After  Decryption : Plain text  = "+messageM_s+"\n");
    }
    
    // RSA CRT (speed up decryption process)
    public static void rsaCRTMode() {
        System.out.println("\nPerforming RSA with Regular Integer...");
        
        simplifiedRsa_publickey_generation();                              // Generate public key
        simplifiedRsa_privatekey_generation(); 							   // Generate private key
        simplifiedRsa_message_generation();  

        System.out.println("Before Encryption : Plain text  = "+messageM_s);
        simplifiedRsaEncryption();             							   // Perform encryption 
        System.out.println("After  Encryption : Cipher text = "+messageC_s);
        RsaCRTDecryption();             							       // Perform decryption
        System.out.println("After  Decryption : Plain text  = "+messageM_s+"\n");
    }
    
    // RSA Malleability
    public static void rsaMLBMode() {
        System.out.println("\nPerforming RSA Malleability...");
        
        simplifiedRsa_publickey_generation();                              // Generate public key
        simplifiedRsa_privatekey_generation(); 							   // Generate private key
        simplifiedRsa_message_generation();                                // Generate original Message

        System.out.println("Before Encryption : Plain text  = "+messageM_s);
        simplifiedRsaEncryption();             							   // Perform encryption 
        System.out.println("After  Encryption : Cipher text = "+messageC_s);

        Rsa_malleability_encryption();                                     // Attacker intercepts the message and modifies it
        System.out.println("Attacker Modification : Cipher text = "+messageC_s_);

        simplifiedRsaDecryption();                                         // Recipient decrypt the original message 
        System.out.println("Original cipher Decryption = "+messageM_s);
        
        Rsa_malleability_decryption();                                     // Recipient decrypt the message altered
        System.out.println("Altered cipher Decryption  = "+messageM_s_+"\n");
    }
    
    // RSA Factorization Attack
    public static void rsaFAMode() {
        System.out.println("\nPerforming RSA Factorization Attack...");
        
        simplifiedRsa_publickey_generation();                              // Generate public key
        System.out.println(p_s+" "+q_s);
        simplifiedRsa_privatekey_generation(); 							   // Generate private key
        simplifiedRsa_message_generation();                                // Generate original Message

        System.out.println("Before Encryption : Plain text  = "+messageM_s);
        simplifiedRsaEncryption();             							   // Perform encryption 
        System.out.println("After  Encryption : Cipher text = "+messageC_s);

        simplifiedRsaDecryption();                                         // Recipient decrypt the original message 
        System.out.println("Original cipher Decryption = "+messageM_s);
        
        Rsa_factorization();                                               // Attacker factorize φ(n)
//        System.out.println("Attacker Modification : Cipher text = "+messageC_s_);
//        
//        Rsa_malleability_decryption();                                     // Recipient decrypt the message altered
//        System.out.println("Altered cipher Decryption  = "+messageM_s_+"\n");
    }

// BigInteger Mode functions        ------------------------------------------------------------------------------------------------------------------		
	public static void bigInteger_publickey_generation() {
		   p = BigInteger.probablePrime(128, new SecureRandom());       // Generate p 
		   q = BigInteger.probablePrime(128, new SecureRandom());       // Generate q
		   if(p.equals(q)) bigInteger_publickey_generation();           // check : p is prime (checked with probable_prime), q is prime (checked with probable_prime), p != to q
		   n  = p.multiply(q); 									        // Calculate n
		   p_ = p.subtract(BigInteger.ONE);
		   q_ = q.subtract(BigInteger.ONE);
		   qn = p_.multiply(q_);								  // Calculate φ(n)
		   bigInteger_e_generation(); 		    				  // Generate e such that : PGCD(φ(n),e)=1 and 1<e<φ(n)
     }
	
	 public static void bigInteger_e_generation() {
		   BigInteger le;
		   for(BigInteger i=BigInteger.valueOf(2);i.compareTo(qn)<0;i = i.add(BigInteger.ONE)) {
			  le = i;
			  if(qn.gcd(le).equals(BigInteger.ONE)) {
				  e=le;
				  break;
			  }
		   }
	  }
	 
	 public static void bigInteger_privatekey_generation() {
		    d = e.modInverse(qn);
	 }
	 
	 public static void bigIntegerEncryption() {
		messageC = messageM.modPow(e,n);
	 }
	
	 public static void bigIntegerDecryption() {
		messageM = messageC.modPow(d,n);
	 }
	
// Regular Integer Mode functions   ------------------------------------------------------------------------------------------------------------------		 
	public static void simplifiedRsa_publickey_generation() {
		Random rand = new Random();
		do {
		    p_s = rand.nextInt(500)+10;
		    q_s = rand.nextInt(500)+10;
		} while (!checkPrime(p_s) || !checkPrime(q_s) || p_s == q_s);
		
	    n_s = p_s*q_s; 				      // Calculate n
	    qn_s = (p_s-1) * (q_s-1); 	      // Calculate φ(n)
	    simplified_e_generation(); 	      // Generate e such that : PGCD(φ(n),e)=1 and 1<e<φ(n)
	}
	
	public static void simplifiedRsa_privatekey_generation() {
		d_s = modInverse(e_s,qn_s);
	}
	
	public static void simplifiedRsa_message_generation() {
	    Random rand = new Random();
	    messageM_s  = rand.nextInt(n_s);  // Generate a random plain text within the range of n
	}
	
	public static void simplifiedRsaEncryption() {
	    messageC_s =  squareAndMultiply(messageM_s,e_s,n_s) % n_s;
	}
	
	 public static void simplifiedRsaDecryption() {
		messageM_s =  squareAndMultiply(messageC_s,d_s,n_s) % n_s;
	 }
	
	 public static void simplified_e_generation() {
	    for(int i=2;i<qn_s;i++) {
		  if(extended_euclidean_algorithm(qn_s,i) == 1){ e_s = i;break;}
	    }
	 }
	 

// CRT Mode functions   ------------------------------------------------------------------------------------------------------------------		  
	public static void RsaCRTDecryption() {
		
		long m_p = squareAndMultiply(messageC_s, d_s, p_s); // C^d mod p , decrypted component one
	    long m_q = squareAndMultiply(messageC_s, d_s, q_s); // C^d mod q , decrypted component two

	    long[] remainders = {m_p,m_q};
	    long[] modulos = {p_s,q_s};

	    messageM_s = chinese_remainder_a(remainders, modulos);
		
	}	 
	 
   // chinese remainder theorem algorithm , adapted to RSA 
   private static long chinese_remainder_a(long remainders[], long moduli[]) {
	   long M=1, result=0;
	   
	   // check co-prime
	   // Already checked
	   
	   // calculate M 
	   for(long mod : moduli) {
		  M *= mod; 
	   }
	   
	   // calculate multiplicative inverse , and the sum equation
	   for(int i=0;i<moduli.length;i++) {
		   long Mi = M / moduli[i];
		   long Yi = modInverse(Mi,moduli[i]); 
		   result  += remainders[i] * Mi * Yi;
	   }
	   
	   return (result % M);
   }
		 
// RSA Malleability
   private static void Rsa_malleability_encryption() {
	    simplified_e_generation_m();   // Generate e
	    Random random = new Random(); 
	    k_s_ = random.nextInt(500)+10; // Generate k
	    messageC_s_ =  (messageC_s * squareAndMultiply(k_s_,e_s_,n_s)) % n_s;
   }
   
   private static void Rsa_malleability_decryption() {
	    messageM_s_ =  squareAndMultiply(messageC_s_,d_s,n_s) % n_s;
  }
   
   public static void simplified_e_generation_m() {
	    for(int i=2;i<qn_s;i++) {
		  if(extended_euclidean_algorithm(qn_s,i) == 1){ e_s_ = i;break;}
	    }
	 }
    
// RSA Factorization 
   public static void Rsa_factorization() {
	  Pair<Integer, Integer> φFactors = new Pair<>(0,0); // Initialize pairs list
	  calculate_factor_pairs(qn_s);                      // Calculate factor pairs
	  φFactors = test_factor_pairs(pairs);                    // Test factor pairs
	  System.out.println(φFactors.getKey()+" "+φFactors.getValue());
   }
   
   public static void calculate_factor_pairs(int x) {
	  pairs.clear(); // Clear any previous factors
      for(int i=1;i<=Math.sqrt(x);i++) {
    	  if(x % i == 0) {
    		  pairs.add(new Pair<> (i,x/i));
    	  }
      }
   }
   
   public static Pair<Integer, Integer> test_factor_pairs(List<Pair<Integer, Integer>> pairs) {
       for (Pair<Integer, Integer> pair : pairs) {
           // Check if both p and q are prime
           if (checkPrime(pair.getKey() + 1) && checkPrime((qn_s + 1) / (pair.getKey() + 1 - 1) + 1)) {
               // Return p and q (add 1 to each part of the pair)
               return new Pair<>(pair.getKey() + 1, pair.getValue() + 1);
           }
       }
       return null; // Return null if no valid pair is found
   }
   
// Shared functions across RSA implementations  ------------------------------------------------------------------------------------------------------------------		 
    // extended euclidean algorithm  
    private static int extended_euclidean_algorithm(int a, int b) {
	    if(b<=0) return a;
	    return extended_euclidean_algorithm(b,a%b);
    }	

     
    // Multiplicative inverse
    static int modInverse(int a, int m) {
    	 a = ((a % m) + m) % m;  // Ensure positiveness
    	 for (int x = 1; x < m; x++) {
    	      if ((a * x) % m == 1) return x;
    	 }
    	 return -1;             // No modular inverse exists
    }
     
    static long modInverse(long a, long m) {
	   	 a = ((a % m) + m) % m;  // Ensure positiveness
	   	 for (int x = 1; x < m; x++) {
	   	      if ((a * x) % m == 1) return x;
	   	 }
	   	 return -1;             // No modular inverse exists
    }
     
     public static long squareAndMultiply(long base, long exp, long mod) {
    	   // Modular exponentiation implementation, using the square and multiply algorithm
    	    if (exp == 0) return 1; // Any number to the power of 0 is 1

    	    long result = 1;
 
    	    String binaryString = Long.toBinaryString(exp); // Convert to binary string
    	    
    	    // Iterate over each bit in the binary string
    	    for (char bit : binaryString.toCharArray()) {
    	    	// Modular Multiplication property
    	    	
    	    	result = (result * result) % mod;
    	    	
    	    	if(bit == '1') result = (result * base) % mod;
    	    	
    	    	// Naive approach : 
//    	        // If the current bit is '1', multiply by the base
//    	        if (bit == '1') {
//    	            result = ((result*result) * base) % mod;
//    	        }else {
//        	        result = (result * result) % mod;
//    	        }
    	    }
    	    return result;
     }
     
     public static boolean checkPrime(int x) {
    	    if (x <= 1) return false;                 // Not prime
    	    if (x <= 3) return true;                  // 2 and 3 are prime
    	    if (x % 2 == 0 || x % 3 == 0) return false; // Eliminate multiples of 2 and 3

    	    // Check divisibility from 5 to sqrt(x) and skip even numbers
    	    for (int i = 5; i * i <= x; i += 6) {
    	        if (x % i == 0 || x % (i + 2) == 0) return false;  // Check i and i+2
    	    }

    	    return true;  // Prime if no divisors found
    	}


}


