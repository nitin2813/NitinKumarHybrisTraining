package com.hybris.training.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrainingProductReviewCountProvider implements FieldValueProvider {

    private FieldNameProvider fieldNameProvider;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {
        if(model instanceof ProductModel){
            final ProductModel product = (ProductModel) model;
            final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
            fieldValues.addAll(createFieldValue(product, indexedProperty));
            return fieldValues;
        }
        throw new FieldValueProviderException("Item is not of Product instance");
    }

    private Collection<? extends FieldValue> createFieldValue(ProductModel product,IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final String reviewCount = product.getNumberOfReviews()+" Reviews"; // 12 Reviews
        if (reviewCount != null)
        {
            addFieldValues(fieldValues, indexedProperty, reviewCount);
        }
        return fieldValues;
    }
    protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,final Object value)
    {
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,null);
        for (final String fieldName : fieldNames)
        {
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    public void setFieldNameProvider(FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

}
