package rshu.springboot.mq.mqreceiver.service;

public class Notification {
    private String notificationType;
    private String msg;

    public Notification() {
    }

    public Notification(String type, String msg){
        this.notificationType = type;
        this.msg = msg;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationType='" + notificationType + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
