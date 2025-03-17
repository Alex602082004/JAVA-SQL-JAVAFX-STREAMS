package Exceptions;

import Domain.Comanda;

import java.util.Calendar;
import java.util.Objects;
import java.util.Vector;

public class ValidateDate {
    public ValidateDate() {
    }


    public int validate(Vector<Comanda> c, int luna, int zi) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, luna, zi);
        for (Comanda comanda : c) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(comanda.getData());

            if (calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR) &&
                    calendar.get(Calendar.MONTH) == calendar1.get(Calendar.MONTH) &&
                    calendar.get(Calendar.DAY_OF_MONTH) == calendar1.get(Calendar.DAY_OF_MONTH)) {
                return 1;
            }

        }
        return 0;
    }
}
