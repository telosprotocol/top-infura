/**
  * Copyright 2020 bejson.com 
  */
package org.topnetwork.grpclib.pojo.timer;

import java.math.BigInteger;

public class TimerValue {

    private BigInteger timer_height;
    
    private BigInteger timer_clock;

	public BigInteger getTimer_height() {
		return timer_height;
	}

	public void setTimer_height(BigInteger timer_height) {
		this.timer_height = timer_height;
	}

	public BigInteger getTimer_clock() {
		return timer_clock;
	}

	public void setTimer_clock(BigInteger timer_clock) {
		this.timer_clock = timer_clock;
	}
	
	
    
}