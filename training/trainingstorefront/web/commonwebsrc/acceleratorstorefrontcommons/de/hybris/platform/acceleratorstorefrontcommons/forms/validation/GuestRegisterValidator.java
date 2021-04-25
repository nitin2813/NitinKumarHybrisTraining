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
package de.hybris.platform.acceleratorstorefrontcommons.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestRegisterForm;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validates entries on Guest register user forms.
 */
@Component("guestRegisterValidator")
public class GuestRegisterValidator implements Validator
{
	private static final String CHECK_PWD = "checkPwd";

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return GuestRegisterForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final GuestRegisterForm guestRegisterForm = (GuestRegisterForm) object;
		final String newPasswd = guestRegisterForm.getPwd();
		final String checkPasswd = guestRegisterForm.getCheckPwd();
		final boolean termsCheck = guestRegisterForm.isTermsCheck();

		if (StringUtils.isNotEmpty(newPasswd) && StringUtils.isNotEmpty(checkPasswd) && !StringUtils.equals(newPasswd, checkPasswd))
		{
			errors.rejectValue(CHECK_PWD, "validation.checkPwd.equals");
		}
		else
		{
			if (StringUtils.isEmpty(newPasswd))
			{
				errors.rejectValue("pwd", "register.pwd.invalid");
			}
			else if (StringUtils.length(newPasswd) < 6 || StringUtils.length(newPasswd) > 255)
			{
				errors.rejectValue("pwd", "register.pwd.invalid");
			}

			if (StringUtils.isEmpty(checkPasswd))
			{
				errors.rejectValue(CHECK_PWD, "register.checkPwd.invalid");
			}
			else if (StringUtils.length(checkPasswd) < 6 || StringUtils.length(checkPasswd) > 255)
			{
				errors.rejectValue(CHECK_PWD, "register.checkPwd.invalid");
			}
		}
		validateTermsAndConditions(errors, termsCheck);
	}

	protected void validateTermsAndConditions(final Errors errors, final boolean termsCheck)
	{
		if (isTermsCheckActive()) 
		{
			if (!termsCheck)
			{
				errors.rejectValue("termsCheck", "register.terms.not.accepted");
			}
		}
	}

	protected boolean isTermsCheckActive()
	{
		return getConfigurationService().getConfiguration().getBoolean(WebConstants.TERMS_CHECK_ACTIVE, false);
	}

	protected ConfigurationService getConfigurationService()
	{
		return configurationService;
	}
}
