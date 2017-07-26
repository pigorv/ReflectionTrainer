import annotations.CustomDateFormat;
import annotations.JsonValue;
import util.Utils;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class JsonParser {

    private static final String OPENED_BRACE = "{";
    private static final String CLOSED_BRACE = "}";
    private static final String COMMA_DELIMITER = ",";
    private static final String QUOTES = "\"";
    private static final String COLON = ":";

    public static String parseToJson(Object object)  {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(OPENED_BRACE);
        for (Field field : object.getClass().getDeclaredFields()) {
            Object value = null;

            try {
                field.setAccessible(true);
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }

            if(value == null) continue;

            String name = field.getName();
            if(field.isAnnotationPresent(JsonValue.class)) {
                name = field.getAnnotation(JsonValue.class).name();
            }

            if(field.isAnnotationPresent(CustomDateFormat.class)) {
                value = Utils.convertDateToString(
                        (LocalDate)value,
                        field.getAnnotation(CustomDateFormat.class).format());
            }

            stringBuilder.append(QUOTES).append(name)
                    .append(QUOTES).append(COLON).append(QUOTES)
                    .append(value).append(QUOTES)
                    .append(COMMA_DELIMITER);
        }

        //Checking if Class get fields
        if(stringBuilder.length() > 1) {
            //Delete last comma delimiter
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(CLOSED_BRACE);

        return stringBuilder.toString();
    }

}
