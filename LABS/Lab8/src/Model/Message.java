/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Patricia Macedo Receiver
 */
public class Message implements Originator {

    private String txt;
    private Date date;

    public Message(String txt) {
        this.txt = txt;
        date = new Date();
    }

    public void setDate() {
        this.date = new Date();
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Date getDate() {
        return date;
    }

    public void clear() {
        txt = " ";
    }

    @Override
    public String toString() {
        return "Message{" + "txt=" + txt + ", date=" + date + '}';
    }

    @Override
    public Memento createMemento() {
        return new MessageMemento(txt, date);
    }

    @Override
    public void setMemento(Memento savedState) {
        MessageMemento save = (MessageMemento) savedState;
        this.txt = save.txtMemento;
        this.date = save.dateMemento;
    }

    private class MessageMemento implements Memento {

        private final String txtMemento;
        private final Date dateMemento;

        public MessageMemento(String msg, Date createdAt) {
            this.txtMemento = msg;
            this.dateMemento = createdAt;
        }

        public String getTxtMemento() {
            return txtMemento;
        }

        public Date getDateMemento() {
            return dateMemento;
        }

        @Override
        public String getDescription() {
            return getTxtMemento() + getDateMemento();
        }

    }

}
