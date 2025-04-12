package app.view;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CalendarView {

    public CalendarView(){
       setTextDate("2025-02-22");
    }

    private String textDate;

    public String getTextDate() {
        return this.textDate;
    }

    private void setTextDate(String text) {
        this.textDate = text;
    }

    public JDateChooser getCalendar() {
        JDateChooser kalendarz = new JDateChooser();
        kalendarz.setDateFormatString("yyyy-MM-dd");

        // Ustawienie domyślnej daty na dzisiejszą
        kalendarz.setDate(java.sql.Date.valueOf(LocalDate.now()));

        // Nasłuchiwanie zmian w kalendarzu
        kalendarz.addPropertyChangeListener("date", evt -> {
            Date selectedDate = kalendarz.getDate();
            if (selectedDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(selectedDate);

                // Aktualizacja tekstowej reprezentacji daty
//                setTextDate("Data - " + dateString + "\n ");

                setTextDate(dateString);

                System.out.print(getTextDate());

            }
        });

        return kalendarz;
    }
}