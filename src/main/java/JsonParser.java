import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Created by User-03 on 25.07.2017.
 */
public class JsonParser {

    private static final String OPENED_BRACE = "{";
    private static final String CLOSED_BRACE = "}";
    private static final String COMMA_DELIMITER = ",";
    private static final String QUOTES = "\"";
    private static final String COLON = ":";

    public String parseToJson(Object object)  {
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

            stringBuilder.append(QUOTES).append(field.getName())
                    .append(QUOTES).append(COLON).append(QUOTES)
                    .append(value).append(QUOTES)
                    .append(COMMA_DELIMITER);
        }

        //Delete last comma delimiter
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(CLOSED_BRACE);

        return stringBuilder.toString();
    }

}
