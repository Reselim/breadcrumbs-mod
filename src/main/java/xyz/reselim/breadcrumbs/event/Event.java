package xyz.reselim.breadcrumbs.event;

import java.util.ArrayList;
import java.util.List;

public final class Event<T> {
	private final List<T> listeners = new ArrayList<>();
	public final T invoker;

	public Event(EventInvokerBuilder<T> invokerBuilder) {
		invoker = invokerBuilder.build(listeners);
	}

	public T subscribe(T listener) {
		listeners.add(listener);
		return listener;
	}

	public void unsubscribe(T listener) {
		listeners.remove(listener);
	}

	public interface EventInvokerBuilder<T> {
		public T build(List<T> listeners);
	}
}
