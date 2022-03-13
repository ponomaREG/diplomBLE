package com.fap.diplomble.domain.predict_model;

public abstract class AbstractPredictedModel<T> {

    public abstract T predict(double[] features);
}
