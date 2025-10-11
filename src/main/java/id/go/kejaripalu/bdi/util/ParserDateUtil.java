package id.go.kejaripalu.bdi.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ParserDateUtil {

    public static Date start(String start) {
        return parse(start);
    };

    public static Date end(String end) {
        return parse(end);
    };

    private static Date parse(String stringDate){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        } catch (ParseException e) {
            log.error("\uD83D\uDC80 {}", e.getMessage());
            return null;
        }
    }
}
