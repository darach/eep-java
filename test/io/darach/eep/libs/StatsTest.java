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

package io.darach.eep.libs;

import static org.junit.Assert.*;
import io.darach.eep.AggregateFunction;
import io.darach.eep.libs.Stats;

import org.junit.Test;

public class StatsTest {

	@Test
	public void count() {
		AggregateFunction<Long,Long> fn = new Stats.CountLong();
		
		fn.init();
		assertEquals(0, fn.emit(), 1e-15);
		
		fn.accumulate(1L);
		assertEquals(1L, fn.emit(), 1e-15);
		fn.accumulate(3L);
		assertEquals(2, fn.emit(), 1e-15);
		fn.accumulate(5L);
		assertEquals(3, fn.emit(), 1e-15);
		
		fn.compensate(1L);
		assertEquals(2, fn.emit(), 1e-15);
		
		assertNotNull(fn.make());
	}

	@Test
	public void sum() {
		AggregateFunction<Long,Long> fn = new Stats.SumLong();
		
		fn.init();
		assertEquals(0, fn.emit(), 1e-15);
		
		fn.accumulate(1L);
		assertEquals(1L, fn.emit(), 1e-15);
		fn.accumulate(3L);
		assertEquals(4, fn.emit(), 1e-15);
		fn.accumulate(5L);
		assertEquals(9, fn.emit(), 1e-15);
		
		fn.compensate(1L);
		assertEquals(8, fn.emit(), 1e-15);
		
		assertNotNull(fn.make());
	}

	
	@Test
	public void mean() {
		AggregateFunction<Long,Double> fn = new Stats.MeanLong();
		
		fn.init();
		// Initialized. But no contributions. Avg is '0'.
		assertEquals(0, fn.emit().doubleValue(), 1e-15);
		
		fn.accumulate(1L);
		assertEquals(1L, fn.emit().doubleValue(), 1e-15);
		fn.accumulate(3L);
		assertEquals(2, fn.emit().doubleValue(), 1e-15);
		fn.accumulate(5L);
		assertEquals(3, fn.emit().doubleValue(), 1e-15);
		
		fn.compensate(1L);
		assertEquals(4, fn.emit().doubleValue(), 1e-15);
		
		assertNotNull(fn.make());
	}
	
	@Test
	public void vars() {
		AggregateFunction<Long,Double> fn = new Stats.VarsLong();
		
		fn.init();
		assertEquals(0, fn.emit().doubleValue(), 1e-15);

		for (long i = 1; i <= 10; i++)
		  fn.accumulate(i);
		
		assertEquals(9.1666666666666666, fn.emit().doubleValue(), 1e-15);
		
		fn.compensate(1L);
		assertEquals(7.5, fn.emit().doubleValue(), 1e-15);
		
		assertNotNull(fn.make());
	}

	@Test
	public void stdevs() {
		AggregateFunction<Long,Double> fn = new Stats.StdevsLong();
		
		fn.init();
		assertEquals(0, fn.emit().doubleValue(), 1e-15);

		for (long i = 1; i <= 10; i++)
		  fn.accumulate(i);
		
		assertEquals(3.0276503540974917, fn.emit().doubleValue(), 1e-15);
		
		fn.compensate(1L);
		assertEquals(2.7386127875258306, fn.emit().doubleValue(), 1e-15);
		
		assertNotNull(fn.make());
	}
}
