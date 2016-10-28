package cn.com.gxdgroup.angentbible.domain;

/**
 * Created by Ivy on 2016/10/28.
 *
 * @description:
 */

public class MessageEvent {
    private int msgType;
    private Object msg;

    public MessageEvent(int msgType, Object msg) {
        this.msgType = msgType;
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
