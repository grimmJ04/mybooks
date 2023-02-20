package hu.szte.inf.services;

public abstract class ModelService<T> {

    private T model;

    ModelService(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
