import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double ax = scanner.nextDouble();
        double ay = scanner.nextDouble();

        double bx = scanner.nextDouble();
        double by = scanner.nextDouble();
        double result = calculateAngle(ax, ay, bx, by);

        System.out.println(result);
    }

    private static double calculateAngle(double ax, double ay, double bx, double by) {
        if (ax == bx && ay == by) {
            // Вектора идентичны, угол равен 0 градусов
            return 0.0;
        } else {
            double dotProduct = ax * bx + ay * by;
            double magnitudeA = Math.sqrt(ax * ax + ay * ay);
            double magnitudeB = Math.sqrt(bx * bx + by * by);
            double angleRad = Math.acos(dotProduct / (magnitudeA * magnitudeB));
            return Math.toDegrees(angleRad);
        }
    }
}