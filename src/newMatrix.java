import javax.swing.*;
import java.awt.*;
import java.util.*;


public class newMatrix{

    int matrixNum;
    int row;
    int column;
    double[][] A;
    Scanner input = new Scanner(System.in);
    JTextArea[][] matrixTextArea;
    public boolean checked = false;
    JFrame frame = new JFrame();


    public newMatrix(int row, int column, int num) {

        // inputs
        this.row = row;
        this.column = column;

        this.matrixNum = num;
        this.A = new double[row][column];
        this.matrixTextArea = new JTextArea[row][column];
        gridLayout();

    }


    public void swapRows(int row1, int row2) {
        // Make dummy row
        double[] dummy = new double[A[0].length];

        // Copy row2 to dummy
        for (int i = 0; i < A[0].length; i++) {
            dummy[i] = A[row2][i];
        }

        // Copy row1 to row2
        for (int i = 0; i < A[0].length; i++) {
            A[row2][i] = A[row1][i];
        }

        // Copy dummy to row1
        for (int i = 0; i < A[0].length; i++) {
            A[row1][i] = dummy[i];
        }

    }

    public boolean isZeroRow(int row) {
        boolean isZeroRow = true;
        for (int j = 0; j < A[0].length; j++) {
            if (A[row][j] != 0)
                isZeroRow = false;
        }
        return isZeroRow;
    }

    public double det() {
        //Check if it is square
        if(A.length == A[0].length) {
            double[][] B = A;
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
                    submatrix(0,i);
                    det = det + Math.pow(-1, i) * (B[0][i]) * det();
                    //Reset A:
                    A = B;
                }
            }
            return det;
        }
        else {
            System.out.println("Undefined");
            return 0;
        }
    }


    public void transpose() {
        double[][] ans = new double[A[0].length][A.length];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                ans[j][i] = A[i][j];
            }
        }

        A = ans;
    }

    public void submatrix(int row, int column) {
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
                A = ans;
            }
        }
    }

    public void cofactor() {
        //Check if square
        if (A.length == A[0].length) {

            double[][] ans = new double[A.length][A[0].length];

            for(int i = 0; i < A.length; i++) {
                for(int j = 0 ; j < A.length; j++) {
                    submatrix(i, j);
                    ans[i][j] = Math.pow(-1 , i) * Math.pow(-1, j) * det();
                }
            }
            A = ans;
        }
    }

    public double[][] getMatrix() {
        return A;
    }


    public void updateMatrix(){
        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {
                A[i][j] = Double.parseDouble(matrixTextArea[i][j].getText());
            }
        }
    }



    public void gridLayout() {

        JTextField matrixNumber = new JTextField("MatrixNumber " + matrixNum);
        JPanel panel = new JPanel(new GridLayout(row, column));
        frame.setSize(600, 400);
        frame.add(panel);
        matrixNumber.setEditable(false);
        frame.add(matrixNumber, BorderLayout.NORTH);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Calculate the position for the top right corner
        int x = screenWidth - frame.getWidth();
        int y = 0;  // Align with the top of the screen

        frame.setLocation(x, y);


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrixTextArea[i][j] = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(matrixTextArea[i][j]);
                panel.add(scrollPane);

                matrixTextArea[i][j].setText("0");


                double value = Double.parseDouble(matrixTextArea[i][j].getText());
                A[i][j] = value;

            }
        }

        frame.setVisible(true);

    }

    public Point getpPosition(){
        return frame.getLocation();
    }





}
