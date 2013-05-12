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

public class Temporal<EventType,ResultType,TimeUnit> {
    private TimeUnit at;
	private AggregateFunction<EventType,ResultType> fn;
    
    public Temporal(AggregateFunction<EventType,ResultType> fn, TimeUnit firstArrivedAt) {
    	this.fn = fn;
    	at = firstArrivedAt;
    }
    
	public AggregateFunction<EventType,ResultType> value() {
		return fn;
	}
	
    public TimeUnit at() {
    	return at;
    }
    
    public Temporal<EventType,ResultType,TimeUnit> update(EventType newValue) {
    	fn.accumulate(newValue);
    	return this;
    }
    
    public static <EventType,ResultType,TimeUnit> Temporal<EventType,ResultType,TimeUnit> make(AggregateFunction<EventType,ResultType> fn, TimeUnit firstArrivedAt) {
    	return new Temporal<EventType,ResultType,TimeUnit>(fn,firstArrivedAt);
    }

	public void reset(TimeUnit firstArrivedAt) {
		at = firstArrivedAt;
		init();
	}

	public void init() {
		fn.init();
	}
}
