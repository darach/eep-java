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

import io.darach.eep.AggregateFunction;

public class Stats {
	
	private Stats() { }
	
	public static class CountLong implements AggregateFunction<Long, Long> {
		private long c;
		public void init() { c = 0; }
		public void accumulate(Long v) { if (v != null) { c++; } }
		public void compensate(Long v) { if (v != null) { c--; } }
		public Long emit() { return c; }
		public AggregateFunction<Long, Long> make() { return new CountLong(); }
	}

	public static class SumLong implements AggregateFunction<Long, Long> {
		private long s;
		public void init() { s = 0; }
		public void accumulate(Long v) { if (v != null) { s += v; } }
		public void compensate(Long v) { if (v != null) { s -= v; } }
		public Long emit() { return s; }
		public AggregateFunction<Long, Long> make() { return new CountLong(); }
	}

	public static class MeanLong implements AggregateFunction<Long, Double> {
		private long s;
		private long c;
		public void init() { s = 0; c = 0; }
		public void accumulate(Long v) { if (v != null) { c++; s += v; } }
		public void compensate(Long v) { if (v != null) { c--; s -= v; } }
		public Double emit() { return (c == 0) ? 0 : ((double)s / (double)c); }
		public AggregateFunction<Long, Double> make() { return new MeanLong(); }
	}
	
	public static class VarsLong implements AggregateFunction<Long, Double> {
		private double m, m2, d;
		private long n;
		public void init() { m = 0; m2 = 0; d = 0; n = 0; }
		public void accumulate(Long v) { n++; d = v - m; m = d/n + m; m2 = m2 + d*(v - m); }
		public void compensate(Long v) { n--; d = m - v; m = m + d/n; m2 = d*(v - m) + m2; }
		public Double emit() { return (double)m2/(n-1); }
		public AggregateFunction<Long, Double> make() { return new VarsLong(); }
	}
	
	public static class StdevsLong implements AggregateFunction<Long, Double> {
		private double m, m2, d;
		private long n;
		public void init() { m = 0; m2 = 0; d = 0; n = 0; }
		public void accumulate(Long v) { n++; d = v - m; m = d/n + m; m2 = m2 + d*(v - m); }
		public void compensate(Long v) { n--; d = m - v; m = m + d/n; m2 = d*(v - m) + m2; }
		public Double emit() { return Math.sqrt(m2/(n-1)); }
		public AggregateFunction<Long, Double> make() { return new StdevsLong(); }
	}
	
}
