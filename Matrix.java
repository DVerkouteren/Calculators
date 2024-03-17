public class Matrix {

    public static void display(double[][] A) {
        System.out.println();
        for(int i = 0; i < A.length; i++){
            System.out.println();
            for(int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + ", ");
            }
        }
        System.out.println();
    }

    public static double[][] add(double[][] A, double[][] B) {
        //Check if sizes match
        if(A.length == B.length && A[0].length == B[0].length) {
            //Define Answer Matrix
            double[][] ans = new double[A.length][A[0].length];

            //Loop through each entry
            for(int i = 0; i < A.length; i++) {
                for(int j = 0; j < A[0].length; j++) {
                    ans[i][j] = A[i][j] + B[i][j];
                }
            }

            return ans;
        } 
        else{
            //Error Message

            //Return Empty
            double[][] n = {{0}};
            return n;
        }
    }

    public static double[][] multiplyScalar(double k, double[][] A) {
        double[][] ans = new double[A.length][A[0].length];
        
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                ans[i][j] = A[i][j] * k;
            }
        }

        return ans;
    }

    public static double trace(double[][] A) {
        //Check if A is square
        if(A.length == A[0].length) {
            double ans = 0;
            for(int i = 0; i < A.length; i++) {
                ans = ans + A[i][i];
            }
            return ans;
        }
        else {
            System.out.println("trace() is only defined for square matracies.");
            return 0;
        }
        
    }

    public static double[][] transpose(double[][] A) {
        double[][] ans = new double[A[0].length][A.length];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                ans[j][i] = A[i][j];
            }
        }

        return ans;
    }
    
    public static double[][] submatrix(double[][] A, int row, int column) {

        //Check if it is square
        if(A.length == A[0].length) {

            //Check if size is 2x2 or more
            if(A.length > 1) {
                double[][] ans = new double[A.length-1][A[0].length - 1];

                //Assign values
                for(int i = 0, k = 0; i < A.length && k < ans.length; i++) {
                    if(i != row) {
                        for(int j = 0, l = 0; j < A.length && l < ans.length; j++) {
                            if(j != column) {
                                ans[k][l] = A[i][j];
                                l++;
                            }
                        }
                        k++;
                    } 
                }

                
                return ans;
            }

            //Do nothing if it is a 1x1 matrix
            else {
                return A;
            }
        }

        //Do nothing if not square
        else {
            double[][] empty = {{}};
            return empty;
        }
        
    }

    public static double det(double[][] A) {
        //Check if it is square
        if(A.length == A[0].length) {
            double det = 0;

            //Trivial 1x1
            if(A.length == 1) {
                det = A[0][0];
            }

            //Trivial 2x2
            if(A.length == 2) {
                det = (A[0][0]*A[1][1]) - (A[0][1]*A[1][0]);
            }
            
            //NxN 
            if(A.length > 2) {
                for(int i = 0; i < A.length; i++) {
                    det = det + Math.pow(-1, i) * (A[0][i]) * det(submatrix(A,0,i));
                }
            }

            return det;
        }
        else {
            System.out.println("det() is only defined for square matracies.");
            return 0;
        } 
    }
    
    public static double[][] cofactor(double[][] A) {
        //Check if square
        if (A.length == A[0].length) {

        double[][] ans = new double[A.length][A[0].length];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0 ; j < A.length; j++) {
                ans[i][j] = Math.pow(-1 , i) * Math.pow(-1, j) * det(submatrix(A, i, j));
            }
        }

        return ans;
        }   

        //If not square, return empty
        else {
            System.out.println("cofactor() and adjoint() are only defined for square matracies.");
            double[][] empty = {{}};
            return empty;
        }
    }

    public static double[][] adjoint(double[][] A) {
        return transpose(cofactor(A));
    }

    public static double[][] invert(double[][] A) {

        if(det(A) != 0) {
            double[][] ans = new double[A.length][A[0].length];
            ans = multiplyScalar(Math.pow(det(A), -1), adjoint(A));
            return ans;
        }
        else{
            System.out.println("This matrix is uninvertable.");
            double[][] empty = {{}};
            return empty;
        }
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        if(A[0].length == B.length) {
            //Check if A's height == B's Width
            double[][] ans = new double[A.length][B[0].length];
            for(int i = 0; i < A.length; i++) {
                for(int j = 0; j < B[0].length; j++) {
                    ans[i][j] = 0;
                    for(int k = 0; k < B.length; k++) {
                        ans[i][j] += A[i][k]*B[k][j];
                    }
                }
            }
            return ans;
        }
        else {
            //Error Message
            System.out.println("The product is undefined");
            double[][] empty = {{}};
            return empty;
        }
    }

    public static double[][] rref(double[][] A) {
        //Work in progress...
        return A;
    }
}
