
public class ValidateParameters {
    public static int[] parseParameters(String[] parameters) throws IllegalArgumentException {
        if (parameters == null || parameters.length < 2) {
            throw new IllegalArgumentException("Usage: java DAS <port> <number>");
        }
        if (parameters[0] == null || parameters[1] == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        try {
            int port = Integer.parseInt(parameters[0]);
            int number = Integer.parseInt(parameters[1]);
            return new int[]{port, number};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Both arguments must be integers.");
        }
    }

    public static boolean parseStringToInt(String value){
        if (value == null) {
            MasterMode.send("Value cannot be null.");
            return false;
        }
        try {
            int parsedValue = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            MasterMode.send("Value must be an integer.");
            return false;
        }
    }

    public static String parseIntToString(int value) {
        return String.valueOf(value);
    }
}
