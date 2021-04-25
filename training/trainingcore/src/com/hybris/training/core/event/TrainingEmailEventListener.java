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
package com.hybris.training.core.event;

import com.training.core.model.custom.TrainingEmailProcessModel;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import org.springframework.beans.factory.annotation.Required;


/**
 * Listener for customer registration events.
 */
public class TrainingEmailEventListener extends AbstractAcceleratorSiteEventListener<TrainingEmailEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	private KeyGenerator processCodeGenerator;

	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final TrainingEmailEvent event)
	{
		final TrainingEmailProcessModel trainingEmailProcessModel = (TrainingEmailProcessModel)getBusinessProcessService().createProcess("trainingEmail-"+event.getCart().getCode()+"-"+processCodeGenerator.generate().toString(),"trainingEmailProcess");
		trainingEmailProcessModel.setCart(event.getCart());
		trainingEmailProcessModel.setCurrency(event.getCurrency());
		trainingEmailProcessModel.setStore(event.getBaseStore());
		trainingEmailProcessModel.setSite(event.getSite());
		trainingEmailProcessModel.setLanguage(event.getLanguage());
		getModelService().save(trainingEmailProcessModel);
		getBusinessProcessService().startProcess(trainingEmailProcessModel);
	}

	public KeyGenerator getProcessCodeGenerator() {
		return processCodeGenerator;
	}

	public void setProcessCodeGenerator(KeyGenerator processCodeGenerator) {
		this.processCodeGenerator = processCodeGenerator;
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(TrainingEmailEvent event) {
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return site.getChannel();
	}
}
