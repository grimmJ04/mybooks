package hu.szte.inf.services;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public abstract class ModelService<T> {

    private final Property<T> modelProperty = new SimpleObjectProperty<>();

    public Property<T> modelProperty() {
        return modelProperty;
    }

    public T getModel() {
        return modelProperty.getValue();
    }

    public void setModel(T model) {
        modelProperty.setValue(model);
    }
}
