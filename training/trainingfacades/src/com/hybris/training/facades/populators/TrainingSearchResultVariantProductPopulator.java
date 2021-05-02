package com.hybris.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

public class TrainingSearchResultVariantProductPopulator extends SearchResultVariantProductPopulator {
    @Override
    public void populate(SearchResultValueData source, ProductData target) {

        super.populate(source, target);
        target.setNumberOfReviewsForPLP(this.<String> getValue(source, "numberOfReviewsForPLP"));
    }
}
