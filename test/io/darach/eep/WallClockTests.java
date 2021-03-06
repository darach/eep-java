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

import org.junit.Test;

public class WallClockTests {

	@Test
	public void quanta() {
		WallClock clock0 = new WallClock(0);
		
		long then = System.nanoTime();
		
		clock0.init();
		assertTrue(clock0.at() < System.nanoTime());
		
		long c0 = clock0.inc();
		assertTrue(then < c0);
		assertTrue(clock0.at() < System.nanoTime());				
	}
	
	@Test
	public void api() throws InterruptedException {
		WallClock c = new WallClock(100000);
		
		c.init();
		assertTrue(c.at() < System.nanoTime());	
		assertFalse(c.tick());
		
		Thread.sleep(1);
		c.inc();
		assertTrue(c.tick());	
		
		assertFalse(c.tock(c.at()));
		
		assertTrue(c.tock(c.at() - 100001));
	}

}
