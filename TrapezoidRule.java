package trapezoidRule;

import java.util.Scanner;

public class TrapezoidRule {
    public static void main(String[] args) {
        do {
            double CONSTANT_E = 2.71828;

            double a;
            double b;

            int n = 0;

            double deltaX;
            double result = 0;

            boolean error;

            Scanner sc = new Scanner(System.in);

            System.out.println("Trapezoid rule for f(x) = e^(-x^2)");
            System.out.println("Press the return key to continue..");
            sc.nextLine();
            
            System.out.print("Enter the lower bound 'a': ");
            a = sc.nextDouble();

            System.out.print("Enter the upper bound 'b': ");
            b = sc.nextDouble();

            do {
                try {
                    System.out.print("Enter the number of pieces 'n' (whole "
                            + "numbers greater than 0): ");
                    n = sc.nextInt();
                    if (n <= 0) {
                        error = true;
                        continue;
                    }
                    error = false;
               }
                catch (Exception e) {
                    sc.next();
                    error = true;
                }
            } while (error);
            System.out.print("\nStep 1: Find delta x = (b - a) / n");
            sc.nextLine();
            sc.nextLine();

            deltaX = (b - a) / (double)n;
            System.out.printf("(%.2f - %.2f) / %d = %.2f%ndelta x = %.2f", b, a, 
                    n, deltaX, deltaX);
            sc.nextLine();

            System.out.print("\nStep 2: Find the x values to input");
            sc.nextLine();

            double[][] values = new double[Math.abs(n) + 1][2];
            double num;
            num = a - deltaX;
            for (int i = 0; i <= n; i++) {
                values[i][0] = num += deltaX;
            }
            if (n > 8) {
                for (int i = 0; i < 4; i++) {
                    System.out.printf("f(x%d) = f(%.2f)\n", i, values[i][0]);
                }
                System.out.print("...\n");
                for (int i = n - 3; i <= n; i++) {
                    num+=deltaX;
                    System.out.printf("f(x%d) = f(%.2f)\n", i, values[i][0]);
                }
            } else {
                for (int i = 0; i <= n; i++) {
                    System.out.printf("f(x%d) = f(%.2f)\n", i, values[i][0]);
                }
            }
            System.out.print("\nStep 3: Calculate");
            sc.nextLine();

            System.out.println("\nFormula- [delta x/2][f(x0) + 2f(x1) + ... + "
                    + "2f(xn-1) + f(xn)]");
            sc.nextLine();

            System.out.printf("[%.2f/2][", deltaX);
            if (n > 8) {
                for (int i = 0; i < 4; ++i) {
                    if (i == 0) {
                        System.out.printf("f(%.2f)", values[i][0]);
                        continue;
                    }
                    System.out.printf(" + 2f(%.2f)", values[i][0]);
                }
                System.out.print("\n   + ...");
                for (int i = n - 3; i <= n; ++i) {
                    if (i == n) {
                        System.out.printf(" + f(%.2f)", values[i][0]);
                        continue;
                    }
                    System.out.printf(" + 2f(%.2f)", values[i][0]);
                }
            } else {
                for (int i = 0; i <= n; ++i) {
                    if (i == 0) {
                        System.out.printf("f(%.2f)", values[i][0]);
                        continue;
                    }
                    if (i == n) {
                        System.out.printf(" + f(%.2f)", values[i][0]);
                        continue;
                    }
                    System.out.printf(" + 2f(%.2f)", values[i][0]);
                }
            }
            System.out.println("]");

            for (int i = 0; i <= n; i++) {
                double x = values[i][0];
                if(i == 0 || i == n) {
                    values[i][1] = Math.pow(CONSTANT_E, (-1 * Math.pow(x, 2)) );
                } else {
                    values[i][1] = 2.0 * Math.pow(CONSTANT_E, (-1 * Math.pow(x, 2)) );
                }
                result += values[i][1];
            }
            result *= (deltaX / 2.0);
            System.out.printf("%n= %f%n", result);
            
            boolean stay = true;
            do {
                System.out.print("\nDo you want to calculate again (y/n)? ");
                String response = sc.nextLine();
                switch(response.toLowerCase()) {
                    case "y" :
                        stay = false;
                        break;
                    case "n" :
                        System.exit(0);
                        break;
                }
            } while (stay);
            System.out.println();
            
        } while (true);
    }
}

