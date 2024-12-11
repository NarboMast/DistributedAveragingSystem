
public class HandleValue {
    public static void handleValue(int value) {
        switch (value) {
            case 0 -> {
                double sum = 0;
                int numOfElements = 0;
                for(int i : MasterMode.numbers){
                    if(i != 0){
                        sum += i;
                        numOfElements++;
                    }
                }
                double total;
                if (numOfElements == 0) {
                    total = 0;
                } else {
                    total = sum / numOfElements;
                }
                int roundedDownValue = (int) Math.floor(total);
                System.out.println(roundedDownValue + " " + sum + " " + numOfElements);
                String str = ValidateParameters.parseIntToString(roundedDownValue);
                MasterMode.send("Average: "+str);
            }
            case -1 -> {
                System.out.println(value);
                MasterMode.send(String.valueOf(value));
                MasterMode.closeServer();
            }
            default -> {
                System.out.println(value);
                MasterMode.numbers.add(value);
            }
        }
    }
}
