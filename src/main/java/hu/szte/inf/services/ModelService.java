package hu.szte.inf.services;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public abstract class ModelService<T> {

    private T model = null;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
