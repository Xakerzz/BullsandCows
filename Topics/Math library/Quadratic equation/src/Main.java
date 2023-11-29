import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();


        solveQuadraticEquation(a, b, c);

        scanner.close();
    }

    private static void solveQuadraticEquation(double a, double b, double c) {

        double discriminant = b * b - 4 * a * c;


        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);


        if (root1 < root2) {
            System.out.println(root1);
            System.out.println(root2);
        } else {
            System.out.println(root2);
            System.out.println(root1);
        }
    }
}

