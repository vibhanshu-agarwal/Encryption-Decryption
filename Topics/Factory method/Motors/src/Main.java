import java.util.Scanner;

/* Product - Motor */
abstract class Motor {

    String model;
    long power;

    public Motor(String model, long power) {
        this.model = model;
        this.power = power;
    }

    @Override
    public String toString() {
        return "motor={model:" + model + ",power:" + power + "}";
    }
}

class PneumaticMotor extends Motor {
    // write your code here ...
    String type;

    public PneumaticMotor(String model, long power, String type) {
        super(model, power);
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + super.toString();
    }
}

class HydraulicMotor extends Motor {
    // write your code here ...
    String type;

    public HydraulicMotor(String model, long power, String type) {
        super(model, power);
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + super.toString();
    }
}

class ElectricMotor extends Motor {
    // write your code here ...
    String type;

    public ElectricMotor(String model, long power, String type) {
        super(model, power);
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + super.toString();
    }
}

class WarpDrive extends Motor {
    // write your code here ...
    String type;

    public WarpDrive(String model, long power, String type) {
        super(model, power);
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + super.toString();
    }
}

class MotorFactory {

    /**
     * It returns an initialized motor according to the specified type by the first character:
     * 'P' or 'p' - pneumatic, 'H' or 'h' - hydraulic, 'E' or 'e' - electric, 'W' or 'w' - warp.
     */
    public static Motor make(char type, String model, long power) {
        // write your code here ...
        switch (type) {
            case 'P':
            case 'p':
                return new PneumaticMotor(model, power, "Pneumatic");
            case 'H':
            case 'h':
                return new HydraulicMotor(model, power, "Hydraulic");
            case 'E':
            case 'e':
                return new ElectricMotor(model, power, "Electric");
            case 'W':
            case 'w':
                return new WarpDrive(model, power, "Warp");
            default:
                return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char type = scanner.next().charAt(0);
        final String model = scanner.next();
        final long power = scanner.nextLong();
        // write your code here ...
        Motor motor = MotorFactory.make(type, model, power);
        scanner.close();
        System.out.println(motor);
    }
}