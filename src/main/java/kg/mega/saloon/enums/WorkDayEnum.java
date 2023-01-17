package kg.mega.saloon.enums;

public enum WorkDayEnum {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    private final int value;



    public static WorkDayEnum getValue(int value) {
        for(WorkDayEnum e: WorkDayEnum.values()) {
            if(e.value == value) {
                return e;
            }
        }
        return null;// not found
    }

    //TODO fix
    WorkDayEnum(int value) {
        this.value = value;
    }
}
