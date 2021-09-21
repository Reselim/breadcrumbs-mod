package xyz.reselim.breadcrumbs.data.customizer;

import xyz.reselim.breadcrumbs.data.Customizer;
import xyz.reselim.breadcrumbs.data.Resolver.CustomizerType;
import xyz.reselim.breadcrumbs.data.provider.ComboProvider;
import xyz.reselim.breadcrumbs.data.provider.ComboProvider.ComboState;

public class ComboCustomizer extends Customizer<ComboState> {
	public final CustomizerType TYPE = CustomizerType.COMBO;

	public ComboCustomizer() {
		super(ComboProvider.INSTANCE.state);
	}

	public String map(ComboState state) {
		return Integer.toString(state.count);
	}
}
