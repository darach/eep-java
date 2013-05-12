# **eep.java**

> Embedding Event Processing for Java

## Status

Experimental.

## Overview

**eep.java** is a small lightweight subset of Complex Event Processing (CEP) that adds aggregate functions and windowed stream operations to Java. It is a straight forward port of [**eep.js**](http://github.com/darach/eep-js/) to Java. To understand the motivation, then, read the introduction to **eep.js**. If you prefer PHP, then Ian Barber has ported **eep.js** adding a React PHP edition to the **eep.star** family. Get Ian's [**eep.php**](http://github.com/ianbarber/eep-php) there.

This version is different. Java generics are supported.

## Simple Event Processing

There are a number of excellent projects for monitoring/metrics systems already available in Java, such as yammer, which inspired (my favorite Erlang monitoring package)  [folsom](http://github.com/boundary/folsom). There is also Servo by Netflix which has a very easy to use and well designed API. Both do what they say on the tin. If you need a monitoring or metrics system use those. 

If you want lower level building blocks, **eep.java** gives you four different types of windowed event processing with pluggable aggregate functions.

## Getting Started 

Compile with ant

```
ant build 
```

Run jUnit tests
```
ant test
```

A quick start guide will be provided shortly.

## Enjoy!
