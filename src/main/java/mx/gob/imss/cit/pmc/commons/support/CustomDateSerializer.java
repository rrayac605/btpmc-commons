package mx.gob.imss.cit.pmc.commons.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CustomDateSerializer extends JsonSerializer<Date>  {

    private final Logger log = LoggerFactory.getLogger(CustomDateSerializer.class);
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private static final Locale LOCALE_MX = new Locale("es", "MX");
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("America/Mexico_City");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {

        log.info("Entrando a [CustomDateSerializer] {{}}", date);
        if (date == null) {
            gen.writeNull();
        } else {
            Calendar calendar = Calendar.getInstance(TIME_ZONE, LOCALE_MX);
            calendar.setTime(date);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR, 0);
            log.info("Saliendo con: " + calendar.getTime());
            gen.writeString(FORMATTER.format(calendar.getTime()));
        }
    }
	
}