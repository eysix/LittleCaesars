import java.util.Scanner;
public class User {
    public static void main (String[] args) throws Exception
    {
        Scanner sn = new Scanner (System.in);
        
        
        int n = sn.nextInt();
        String[] m = new String [n];
        int cn;
        String mn;
        
        for (int i=0; i<n; i++) {
        	cn = sn.nextInt();
        	mn = sn.nextLine();
        	m[i] = mn.substring(1,cn) + mn.substring(cn+1, mn.length());
        	
        }
        
        for (int j=0; j<n; j++) {
        	System.out.println(j+1 + " " + m[j]);
        }
        
        		
        sn.close();
    }
}