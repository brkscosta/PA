/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BagOfWords;
import Model.Caretaker;
import View.BagOfWordsView;

/**
 *
 * @author brunomnsilva
 */
public class BagOfWordsPresenter {

    /* model */
    private final BagOfWords bag;
    private final Caretaker caretaker;

    /* view */
    private final BagOfWordsView view;

    public BagOfWordsPresenter(BagOfWords bag, Caretaker caretaker, BagOfWordsView view) {
        this.bag = bag;
        this.caretaker = caretaker;
        this.view = view;

        this.view.setTriggers(this);
        
        view.setName(bag.getName());
        view.setWords(bag.getWords());
    }

    public void doAddWord() {

        String input = view.getWordInput();

        if (input.trim().length() == 0) {
            view.showErrorMessage("Word cannot be empty.");
            return;
        }

        caretaker.requestSave();

        bag.add(input);

        view.clearWordInput();

        view.setWords(bag.getWords());
    }

    public void doChangeName() {

        String input = view.getNameInput();

        if (input.trim().length() == 0) {
            view.showErrorMessage("Name cannot be empty.");
            return;
        }

        caretaker.requestSave();

        bag.setName(input);

        view.clearNameInput();

        view.setName(bag.getName());
    }

    public void doUndo() {

        if (!caretaker.canUndo()) {
            view.showErrorMessage("No more undos are available.");
            return;
        }
        
        caretaker.requestRestore();
        
        view.setName(bag.getName());
        view.setWords(bag.getWords());

    }

}
