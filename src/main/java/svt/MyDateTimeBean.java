package svt;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * RequestScoped bean to test Java time support
 */
@Named
@RequestScoped
public class MyDateTimeBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private Date date;
    private final LocalDate localDate;
    private final LocalTime localTime;
    private final LocalDateTime localDateTime;
    private final OffsetTime offsetTime;
    private final OffsetDateTime offsetDateTime;
    private final ZonedDateTime zonedDateTime;

    public MyDateTimeBean() {
        date = new Date();
    	localDate = LocalDate.now();
    	localTime = LocalTime.now();
    	localDateTime = LocalDateTime.now();
    	offsetTime = OffsetTime.now();
    	offsetDateTime = OffsetDateTime.now();
    	zonedDateTime = ZonedDateTime.now();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public OffsetTime getOffsetTime() {
        return offsetTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

}