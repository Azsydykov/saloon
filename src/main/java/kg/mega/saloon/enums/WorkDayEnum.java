package kg.mega.saloon.enums;

public enum WorkDayEnum {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);

    private int value;

    WorkDayEnum(int value) {
        this.value = value;
    }
}
