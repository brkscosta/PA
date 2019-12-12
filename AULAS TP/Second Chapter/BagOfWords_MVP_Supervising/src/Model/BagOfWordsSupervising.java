/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author brunomnsilva
 */
public class BagOfWordsSupervising implements Originator {

    private SimpleStringProperty nameProperty;
    private SimpleListProperty<String> bag;

    public BagOfWordsSupervising(String name) {
        this.nameProperty = new SimpleStringProperty(name);
        this.bag = new SimpleListProperty<>();
    }

    public void add(String word) {
        bag.add(word);
    }

    public void setName(String name) {
        this.nameProperty.setValue(name);
    }

    public String getName() {
        return nameProperty.getValue();
    }

    public SimpleListProperty<String> getWords() {
        return new SimpleListProperty<>(bag);
    }

    public SimpleStringProperty nameProperty() {
        return nameProperty;
    }

    @Override
    public String toString() {
        return "BagOfWords{" + "name=" + getName() + ", bag=" + bag + '}';
    }

    @Override
    public Memento save() {
        return new BagOfWordsMemento(nameProperty(), bag);
    }

    @Override
    public void restore(Memento savedState) {
        if (savedState instanceof BagOfWordsMemento) {
            BagOfWordsMemento save = (BagOfWordsMemento) savedState;

            this.nameProperty = save.namePropertyMemento;
            this.bag = save.bagMemento; // funciona!!! :)

            //alternativa, reutilizando lista atual
            //this.bag.clear();
            //this.bag.addAll(save.bag);
            //outra alternativa, alocando mais memória
            //this.bag = new LinkedList<>(save.bag);
        }
    }

    private class BagOfWordsMemento implements Memento {

        /* save state attributes */
        private SimpleStringProperty namePropertyMemento;
        private SimpleListProperty<String> bagMemento;

        /* attributes for description */
        private final Date createdAt;
        private final int count;

        public BagOfWordsMemento(SimpleStringProperty name, SimpleListProperty<String> bag) {
            this.namePropertyMemento = name;
            //this.bag = bag; //nao funciona!!!
            this.bagMemento = new SimpleListProperty<>(bag);

            this.createdAt = new Date();
            this.count = this.bagMemento.size();
        }

        @Override
        public String getDescription() {
            return String.format("BagOfWordsMemento (%d words) created at %s",
                    count, createdAt.toString());
        }
    }

}
