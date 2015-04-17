/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HotFlow.TribeCraft.WaringTerminal;

import com.HotFlow.TribeCraft.WaringTerminal.TimeException.TimeWaringType;
import java.util.Calendar;

/**
 *
 * @author thtTNT 使用本类时，时间必须按照年月日时分秒的顺序来设置，否则可能会导致报错
 */
class WaringTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public void getNow() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);
    }

    /**
     * 设置错误的年份
     *
     * @param 错误的年份
     * @throws TimeException 时间输入错误
     */
    public void setYear(int year) throws TimeException {
        if (year < 0) {
            throw new TimeException(TimeWaringType.year);
        } else {
            this.year = year;
        }
    }

    /**
     * 设置错误的月份
     *
     * @param 错误的月份
     * @throws TimeException 时间输入错误
     */
    public void setMonth(int month) throws TimeException {
        if (month < 1 || month > 12) {
            throw new TimeException(TimeWaringType.month);
        } else {
            this.month = month;
        }
    }

    /**
     * 设置错误的日子
     *
     * @param 错误的日子
     * @throws TimeException 时间输入错误
     */
    public void setDay(int day) throws TimeException {
        if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8 || this.month == 10 || this.month == 12) {
            if (day < 1 || day > 31) {
                throw new TimeException(TimeWaringType.day);
            } else {
                this.day = day;
            }
            if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
                if (day < 1 || day > 31) {
                    throw new TimeException(TimeWaringType.day);
                } else {
                    this.day = day;
                }
                if (((this.year % 100) != 0) && ((this.year % 4) == 0) || ((this.year & 400) == 0)) {
                    if (day < 1 || day > 29) {
                        throw new TimeException(TimeWaringType.day);
                    } else {
                        this.day = day;
                    }
                } else {
                    if (day < 1 || day > 28) {
                        throw new TimeException(TimeWaringType.day);
                    } else {
                        this.day = day;
                    }
                }
            }
        }
    }

    /**
     * 设置错误的分
     *
     * @param 错误的分
     * @throws TimeException 时间输入错误
     */
    public void setMinute(int minute) throws TimeException {
        if (minute < 0 || minute > 60) {
            throw new TimeException(TimeWaringType.minute);
        } else {
            this.minute = minute;
        }
    }

    /**
     * 设置错误的秒
     *
     * @param 错误的秒
     * @throws TimeException 时间输入错误
     */
    public void setSecond(int s) throws TimeException {
        if (s < 0 || s > 60) {
            throw new TimeException(TimeWaringType.second);
        } else {
            this.second = s;
        }
    }

    /**
     * 设置错误的小时
     *
     * @param 错误的小时
     * @throws TimeException 时间输入错误
     */
    public void setHour(int hour) throws TimeException {
        if (hour < 0 || hour > 24) {
            throw new TimeException(TimeWaringType.hour);
        } else {
            this.hour = hour;
        }
    }

    /**
     * 获取错误的年份
     *
     * @return 错误的年份
     */
    public int getYear() {
        return this.year;
    }

    /**
     * 获取错误的月份
     *
     * @return 错误的月份
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * 获取错误的日子
     *
     * @return 错误的日子
     */
    public int getDay() {
        return this.day;
    }

    /**
     * 获取错误的分钟
     *
     * @return 错误的分钟
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * 获取错误的小时
     *
     * @return 错误的小时
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * 获取错误的秒
     *
     * @return 错误的秒
     */
    public int getSecond() {
        return this.second;
    }
}
