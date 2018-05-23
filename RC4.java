
public class RC4
{
        static int[] S = new int [256];
        static int[] T = new int [256];
        static int[] keystream = new int[100];
    
    public static void swap(int i, int j){
            int temp;
            
            temp = S[i];
            S[i]= S[j];
            S[j] = temp;
    }
    
    public static void permute(int seed[]){
        for(int i=0; i < 256; i++){
            S[i] = i;
        }
        
        for(int i=0; i < 256; i++){
            T[i] = seed[i%seed.length];
        }
    
        //initial permutation of S
        int j = 0;
        
        for(int i = 0; i<256;i++){
            j = (j +S[i]+T[i])%256;
            swap(i,j);
        }
        
       
    
    }
    
    public static int[] encrypt(int [] ptxt){
        
        int i= 0;
        int j= 0;
        int t;
        int k;
        
        
        for(int z=0; z<ptxt.length;z++){
             i = (i+1)%256;
             j = (j+S[i])%256;
             swap(i,j);
             t=(S[i]+S[j])%256;
             k=S[t];
             keystream[z]= k;
        }
        
        for(int z=0; z<ptxt.length;z++){
            ptxt[z] ^= keystream[z];
        }
        
        
        return ptxt;
    }
    
    public static int[] decrypt(int [] ctxt){
        
         for(int z=0; z<ctxt.length;z++){
            ctxt[z] ^= keystream[z];
        }
        
        return ctxt;
        
    }
    
    public static void main() 
    {
        String plaintext= "cryptology";
        String ciphertext= "";
        String decrypttext= "";
        int[] key = {1,2,3,6};
        
        System.out.println("Plaintext: " + plaintext);
        System.out.print("Seed used: ");
        
        for(int i = 0; i < key.length;i++){
            System.out.print(key[i] + " ");
        }
        
        System.out.println("\nAfter initial permutation of S: ");
        
        permute(key);
       
        //print S array
                for(int i = 0; i < 256; i++)
        {
            System.out.print(S[i] + "\t");

            if((i + 1) % 18 == 0)
            {
                System.out.println();
            }
        }
        System.out.println();
        
        
        
        int ptxt[] = new int[plaintext.length()];
        
        //ascii values
        for(int i=0; i < plaintext.length();i++){
            int x = (int) plaintext.charAt(i);
            ptxt[i] = x;
        }
        
        
       int[] ctxt =  encrypt(ptxt);
       
       
       System.out.print("Keys generated for plaintext: ");
        //prints keystream
        for(int i=0; i < plaintext.length(); i++){
            System.out.print(keystream[i]+" ");
        }
        System.out.println();
      
       //get letters from ascii
       for(int i=0; i < plaintext.length();i++){
            char x = (char) ctxt[i];
            ciphertext += x;
        }
        
        System.out.println("Message encrypted: " + ciphertext);
        
        permute(key);
        
       ptxt=  decrypt(ctxt);
       for(int i=0; i < plaintext.length();i++){
            char x = (char) ptxt[i];
            decrypttext += x;
        }
        System.out.println("Message decrypted: " +decrypttext);
       
       ////////////////////////////////////////////////////////////////////////////////// 
       System.out.println("#################################################################"+
                            "#############################################################");
       String plaintext2= "RC4";
       String ciphertext2= "";
       String decrypttext2= "";
       int[] key2 = {5,7,8,9};
        
        System.out.println("Plaintext: " + plaintext2);
        System.out.print("Seed used: ");
        
        for(int i = 0; i < key2.length;i++){
            System.out.print(key2[i] + " ");
        }
        
        System.out.println("\nAfter initial permutation of S: ");
        
        permute(key2);
       
        //print S array
                for(int i = 0; i < 256; i++)
        {
            System.out.print(S[i] + "\t");

            if((i + 1) % 18 == 0)
            {
                System.out.println();
            }
        }
        System.out.println();
        
        int ptxt2[] = new int[plaintext2.length()];
        
        //ascii values
        for(int i=0; i < plaintext2.length();i++){
            int x = (int) plaintext2.charAt(i);
            ptxt2[i] = x;
        }
        
        
       int[] ctxt2 =  encrypt(ptxt2);
       
       System.out.print("Keys generated for plaintext: ");
        //prints keystream
        for(int i=0; i < plaintext2.length(); i++){
            System.out.print(keystream[i]+" ");
        }
        System.out.println();
      
       //get letters from ascii
       for(int i=0; i < plaintext2.length();i++){
            char x = (char) ctxt2[i];
            ciphertext2 += x;
        }
        
        System.out.println("Message encrypted: " + ciphertext2);
        
        permute(key2);
        
       ptxt2=  decrypt(ctxt2);
       for(int i=0; i < plaintext2.length();i++){
            char x = (char) ptxt2[i];
            decrypttext2 += x;
        }
        System.out.println("Message decrypted: " +decrypttext2);
        
    }
}
