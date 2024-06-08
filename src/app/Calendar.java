package app;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Calendar {
    private String dataResult;

    public void setDataResult(String data){
        this.dataResult = data;
    }

    public String getDataResult(){
        return this.dataResult;
    }

    public JDateChooser setKalendarz(){
        JDateChooser kalendarz = new JDateChooser();
        kalendarz.setDateFormatString("yyyy-MM-dd");
        Date test = java.sql.Date.valueOf(LocalDate.now());

        kalendarz.setDate(test);
        kalendarz.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt){
                Date dataCurrent = kalendarz.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dataCurrent);

                // wyswitlenie daty
//                String result =  "Data - " + dateString + "\n ";
                //resultArea.append(result);
                setDataResult("Data - " + dateString + "\n ")


            }
        });

        return kalendarz;
    }

    public String getResultFromOtherMethod() {
        return getDataResult(); // zwraca zapisaną datę
    }
}


