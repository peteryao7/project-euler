import java.math.*;

class p066 {
    /*
        https://projecteuler.net/problem=66

        Consider quadratic Diophantine equations of the form:

        x^2 - Dy^2 = 1

        For example, when D = 13, the minimal solution in x is
        649^2 - 13 * 180^2 = 1.

        It can be assumed that there are no solution in positive integers when
        D is square.

        By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain
        the following:

        3^2 - 2 * 2^2 = 1
        2^2 - 3 * 1^2 = 1
        9^2 - 5 * 4^2 = 1
        5^2 - 6 * 2^2 = 1
        8^2 - 7 * 3^2 = 1

        Hence, by considering minimal solutions in x for D <= 7, the largest x
        is obtained when D = 5.

        Find the value of D <= 1000 in minimal solutions of x for which the
        largest value of x is obtained.

        -----

        Pell Equation:
        This special case of diophantine equations is known as the Pell
        Equation. You can find more info about it here:

        https://mathworld.wolfram.com/PellEquation.html

        If you scroll down a bit, there's a recursive form using the continued
        fraction that we also previously used in Q64 to calculate the length
        of the periods. Only this time, we just care about whether the equation
        is satisfied and what the value of x is.

        Because the values can get huge, we use BigInteger instead of int.

        TC/SC - O(?)/O(1)
    */
    private static BigInteger largestMinDiophantineX(int D) {
        BigInteger res = BigInteger.ZERO;
        BigInteger largestX = BigInteger.ZERO;
        
		for (int i = 2; i <= D; i++){
			BigInteger sqrt = BigInteger.valueOf((int) Math.sqrt(i));
		    if (sqrt.pow(2).compareTo(BigInteger.valueOf(i)) != 0) {	
		    	BigInteger m = BigInteger.ZERO;
		    	BigInteger d = BigInteger.ONE;
                BigInteger a = sqrt;
                
                BigInteger x = a, x0 = BigInteger.ONE, x2 = x0;
                BigInteger y = BigInteger.ONE, y0 = BigInteger.ZERO, y2 = y0;
                
			    while(x.pow(2).subtract(y.pow(2).multiply(BigInteger.valueOf(i))).compareTo(BigInteger.ONE) != 0) {
			        m = d.multiply(a).subtract(m);
			        d = BigInteger.valueOf(i).subtract(m.pow(2)).divide(d);
                    a = sqrt.add(m).divide(d);
                    
			        x2 = x0;
			        y2 = y0;
			        x0 = x;
                    y0 = y;
                    
			        x = x.multiply(a).add(x2);
			        y = y.multiply(a).add(y2);
			    }
			 
			    if (x.compareTo(largestX) > 0) {
			    	largestX = x;
			    	res = BigInteger.valueOf(i);
			    }
		    }
		}
        
        return res;
    }

    public static void main(String[] args) {
        System.out.println(largestMinDiophantineX(1000));
    }
}