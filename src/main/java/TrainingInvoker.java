import annotations.CustomDateFormat;
import annotations.JsonValue;

import java.time.LocalDate;

public class TrainingInvoker {

    private int intTest = 10;

    private int nullIntTest;

    @JsonValue(name = "Integer & Annotation")
    private int intWithAnnotation = 101;

    @JsonValue(name = "Annotation tester")
    private String notATester = "Good tester";

    private String nullObjectTest;

    @JsonValue(name = "Null with annotation")
    private String nullWithAnnotation;

    private LocalDate clearDate = LocalDate.of(1990,10,10);

    @JsonValue(name = "Is it my birthday?")
    private LocalDate birthDayDate = LocalDate.of(2012, 12, 12);

    @JsonValue(name = "Today is ")
    @CustomDateFormat(format = "dd-MM-yyyy")
    private LocalDate todayDate = LocalDate.now();


    public static void main(String[] args) {

        //Testing json parser
        System.out.println(JsonParser.parseToJson(new TrainingInvoker()));

    }


}
