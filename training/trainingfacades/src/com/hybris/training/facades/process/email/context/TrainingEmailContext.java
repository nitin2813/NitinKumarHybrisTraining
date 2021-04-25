/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.hybris.training.facades.process.email.context;

import com.training.core.model.custom.TrainingEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Required;

import java.util.Locale;


/**
 * Velocity context for a customer email.
 */
public class TrainingEmailContext extends AbstractEmailContext<TrainingEmailProcessModel>
{
	public void init(final TrainingEmailProcessModel processModel, final EmailPageModel emailPageModel)
	{
		super.init(processModel, emailPageModel);
		put(EMAIL, getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(processModel)));
		put(DISPLAY_NAME, getCustomer(processModel).getDisplayName());
	}

	@Override
	protected BaseSiteModel getSite(TrainingEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCart().getSite();
	}

	@Override
	protected CustomerModel getCustomer(TrainingEmailProcessModel businessProcessModel) {
		return (CustomerModel) businessProcessModel.getCart().getUser();
	}

	@Override
	protected LanguageModel getEmailLanguage(TrainingEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCart().getUser().getSessionLanguage();
	}
}
