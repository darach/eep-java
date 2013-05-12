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

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import io.darach.eep.libs.Stats;

public class SlidingWindowTests {

	@Test
	public void window() {
		final List<Double> results = new LinkedList<Double>();
		
		EventWindow<Long,Double> w = Eep.sliding(new Stats.MeanLong(), 2);
		
		w.onEmit(new EventEmitter<Double>() {
			public void emit(Double event) {
				results.add(event);
			}
		});
		
		w.push(1L);
		w.push(10L);
		w.push(2L);
		w.push(12L);
		
		assertEquals(new Double[] { 5.5, 6., 7. }, results.toArray(new Double[]{}));
	}
}
