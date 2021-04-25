package com.hybris.training.core.attributehandlers;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class TrainingTotalAmountPaidHandler implements DynamicAttributeHandler<Double, AbstractOrderModel> {
    @Override
    public Double get(AbstractOrderModel orderModel) {
        double roundOffTotalAmountPaidValue = Math.round(orderModel.getTotalPrice());
        return roundOffTotalAmountPaidValue;
    }

    @Override
    public void set(AbstractOrderModel model, Double aDouble) {
        //Do nothing
        throw new UnsupportedOperationException();
    }
}
