package xyz.reselim.breadcrumbs.ui;

import java.awt.Color;

import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIRoundedRectangle;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.AdditiveConstraint;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;
import gg.essential.elementa.state.BasicState;
import gg.essential.elementa.state.State;
import xyz.reselim.breadcrumbs.config.ConfigBreadcrumb;
import xyz.reselim.breadcrumbs.data.Customizer;

public final class UIBreadcrumb {
	private final static float PADDING = 4f;

	public final UIRoundedRectangle object;

	private final UIRoundedRectangle labelContainer;
	private final UIText label;

	private final UIContainer textContainer;
	private final UIText text;

	public UIBreadcrumb(State<String> labelState, State<String> textState) {
		object = new UIRoundedRectangle(3f);
		object.setComponentName("Breadcrumb");
		object.setWidth(new ChildBasedSizeConstraint());
		object.setHeight(new PixelConstraint(16f));
		object.setColor(new Color(0, 0, 0, 127));

		labelContainer = new UIRoundedRectangle(3f);
		labelContainer.setComponentName("LabelContainer");
		labelContainer.setWidth(new AdditiveConstraint(new ChildBasedSizeConstraint(), new PixelConstraint(PADDING * 2)));
		labelContainer.setHeight(new RelativeConstraint(1f));
		labelContainer.setX(new SiblingConstraint());
		labelContainer.setColor(new Color(0, 0, 0, 127));
		labelContainer.setChildOf(object);

		label = new UIText("", false);
		label.setComponentName("Label");
		label.setColor(new Color(255, 255, 255));
		label.bindText(labelState);
		label.setX(new CenterConstraint());
		label.setY(new CenterConstraint());
		label.setChildOf(labelContainer);

		textContainer = new UIContainer();
		textContainer.setComponentName("TextContainer");
		textContainer.setWidth(new AdditiveConstraint(new ChildBasedSizeConstraint(), new PixelConstraint(PADDING * 2)));
		textContainer.setHeight(new RelativeConstraint(1f));
		textContainer.setX(new SiblingConstraint());
		textContainer.setColor(new Color(0, 0, 0, 127));
		textContainer.setChildOf(object);

		text = new UIText("", false);
		text.setComponentName("Text");
		text.setColor(new Color(255, 255, 255));
		text.bindText(textState);
		text.setX(new CenterConstraint());
		text.setY(new CenterConstraint());
		text.setChildOf(textContainer);
	}

	// Config

	public static final UIBreadcrumb fromConfig(ConfigBreadcrumb breadcrumbConfig) {
		Customizer<?> customizer = breadcrumbConfig.getCustomizer();
		return new UIBreadcrumb(new BasicState<>(breadcrumbConfig.label), customizer.state);
	}
}
