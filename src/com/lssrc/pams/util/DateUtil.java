package com.lssrc.pams.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	private StringBuffer stringBuffer;
	private StringBuffer addZeroStringBuffer;
	private Calendar calendar;

	public String getRightTime() {
		calendar = new GregorianCalendar();
		stringBuffer = new StringBuffer();
		stringBuffer.append(calendar.get(Calendar.YEAR)).append('-');
		stringBuffer.append(this.addZero(calendar.get(Calendar.MONTH) + 1, 2)).append('-');
		stringBuffer.append(this.addZero(calendar.get(Calendar.DATE), 2)).append(' ');
		stringBuffer.append(this.addZero(calendar.get(Calendar.HOUR_OF_DAY), 2)).append(':');
		stringBuffer.append(this.addZero(calendar.get(Calendar.MINUTE), 2)).append(':');
		stringBuffer.append(this.addZero(calendar.get(Calendar.SECOND), 2));
		return stringBuffer.toString();
	}

	public String getTimeStamp() {
		calendar = new GregorianCalendar();
		stringBuffer = new StringBuffer();
		stringBuffer.append(calendar.get(Calendar.YEAR));
		stringBuffer.append(this.addZero(calendar.get(Calendar.MONTH) + 1, 2));
		stringBuffer.append(this.addZero(calendar.get(Calendar.DATE), 2));
		stringBuffer.append(this.addZero(calendar.get(Calendar.HOUR_OF_DAY), 2));
		stringBuffer.append(this.addZero(calendar.get(Calendar.MINUTE), 2));
		stringBuffer.append(this.addZero(calendar.get(Calendar.SECOND), 2));
		stringBuffer.append(this.addZero(calendar.get(Calendar.MILLISECOND), 3));
		return stringBuffer.toString();
	}

	private String addZero(int num, int len) {
		addZeroStringBuffer = new StringBuffer();
		addZeroStringBuffer.append(num);
		while (addZeroStringBuffer.length() < len) {
			addZeroStringBuffer.insert(0, "0");
		}
		return addZeroStringBuffer.toString();
	}

}
