package com.hybris.training.storefront.controllers.cms;

import com.hybris.training.core.model.components.TrainingVideoComponentModel;
import com.hybris.training.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("TrainingVideoCMSComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.TrainingVideoCmsComponent)
public class TrainingVideoCMSComponentController extends AbstractCMSComponentController<TrainingVideoComponentModel> {


    @Override
    protected void fillModel(HttpServletRequest request, Model model, TrainingVideoComponentModel component) {

        model.addAttribute("autoPlay", component.getAutoPlay() ? 1:0);
        model.addAttribute("showControls",component.getShowControls()?1:0);
        model.addAttribute("height",component.getHeight());
        model.addAttribute("videoId",component.getVideoId());
        model.addAttribute("width",component.getWidth());
    }

    @Override
    protected String getView(TrainingVideoComponentModel component) {
        return null;
    }
}
