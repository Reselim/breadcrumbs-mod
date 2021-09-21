package xyz.reselim.breadcrumbs.data;

import gg.essential.elementa.state.State;
import xyz.reselim.breadcrumbs.data.Resolver.CustomizerType;

import java.util.function.Function;

public abstract class Customizer<T> {
	public final CustomizerType TYPE = CustomizerType.UNKNOWN;

	public State<String> state;

	public Customizer(State<T> baseState) {
		state = baseState.map(new Function<T, String>() {
			public String apply(T state) {
				return map(state);
			}
		});
	}

	public String map(T state) {
		return "N/A";
	}
}
