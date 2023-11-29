import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double n = (a + b + c) / 2;
        double result = Math.sqrt(n*(n-a)*(n - b)*(n-c));
        System.out.println(result);
    }
}