package fr.accman.app.model;
import fr.accman.app.model.utils.MyDateFormat;

import java.io.Serializable;
import java.util.*;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Entry implements Serializable
{
	public long date;
	public double value;

	public Entry(long date, double value) {
		this.date = date;
		this.value = value;
	}

	public static Entry newEntry(long date, String amount) {
		return new Entry(date, Double.parseDouble(amount));
	}

	public static Entry newEntry(String amount) {
		return new Entry(getNow(), Double.parseDouble(amount));
	}

    public static long getNow() {
        return GregorianCalendar.getInstance().getTimeInMillis();
    }

	@Override
	public String toString() {
		return MyDateFormat.getDate(date) + ": " + value + "€";
	}

	public String getAmount() {
		return this.value + "€";
	}

	public String getDate() {
		return MyDateFormat.getDate(date);
	}
}

