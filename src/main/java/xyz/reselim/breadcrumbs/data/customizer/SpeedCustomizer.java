package xyz.reselim.breadcrumbs.data.customizer;

import java.text.DecimalFormat;

import xyz.reselim.breadcrumbs.data.Customizer;
import xyz.reselim.breadcrumbs.data.Resolver.CustomizerType;
import xyz.reselim.breadcrumbs.data.provider.SpeedProvider;
import xyz.reselim.breadcrumbs.data.provider.SpeedProvider.SpeedState;

public final class SpeedCustomizer extends Customizer<SpeedState> {
	public final CustomizerType TYPE = CustomizerType.SPEED;

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

	public SpeedCustomizer() {
		super(SpeedProvider.INSTANCE.state);
	}

	public String map(SpeedState state) {
		return DECIMAL_FORMAT.format(state.speedHorizontal) + " m/s";
	}
}
