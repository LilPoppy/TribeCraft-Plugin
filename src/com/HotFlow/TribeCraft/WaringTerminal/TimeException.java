package com.HotFlow.TribeCraft.WaringTerminal;

/**
 *
 * @author thtTNT
 */
public class TimeException extends Exception {

    private TimeWaringType WaringType;

    public enum TimeWaringType {

        year, month, day, hour, minute, second;
    }

    public TimeException(TimeWaringType type) {
        this.WaringType = type;
    }

    /**
     * 获取时间错误信息
     *
     * @return 时间错误信息
     */
    public TimeWaringType getWaringType() {
        return WaringType;
    }
}
