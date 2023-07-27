package toDoList.managementAPI.domain;

public enum Status {
    NOT_STARTED(1, "NOT STARTED"),
    IN_PROGRESS(2, "IN PROGRESS"),
    DONE(3, "DONE");

    private final Integer VALUE;
    private final String STATUS_NAME;

    Status(int VALUE, String STATUS_NAME) {
        this.VALUE = VALUE;
        this.STATUS_NAME = STATUS_NAME;
    }

    public static Status getStatusByValue(int value) {
        for (Status status : values()) {
            if (status.getVALUE().equals(value)) {
                return status;
            }
        }
        return null;
    }

    public Integer getVALUE() {
        return VALUE;
    }

    public String getSTATUS_NAME() {
        return STATUS_NAME;
    }
}
