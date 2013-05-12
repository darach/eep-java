// Copyright (c) 2013 Darach Ennis < darach at gmail dot com >.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:  
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

package io.darach.eep;

import java.util.LinkedList;
import java.util.List;

public class PeriodicWindow<In,Out> implements EventWindow<In,Out> {		
	private final Temporal<In,Out,Long> fn;
	private final EventClock<Long> clock;
	private final List<EventEmitter<Out>> outListeners = new LinkedList<EventEmitter<Out>>();

	public PeriodicWindow(AggregateFunction<In,Out> fn, long interval) {
		this.clock = new WallClock(interval);
		this.fn = Temporal.make(fn,interval);
		clock.init();
		fn.init();
	}

	public void push(In e) {
		clock.inc();
		fn.update(e);
	}

	public void emit(Out r) {
		for (EventEmitter<Out> l : outListeners) {
			l.emit(r);
		}
	}

	public void onEmit(EventEmitter<Out> l) {
		outListeners.add(l);
	}

	public void tick() {
		if (!clock.tick()) return;
		
		if (clock.tock(fn.at())) {
			emit(fn.value().emit());
			clock.inc();
			fn.reset(clock.at());
		}
	}

}
