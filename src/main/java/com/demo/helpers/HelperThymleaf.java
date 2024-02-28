package com.demo.helpers;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;
@Service("HelperThymleaf")
public class HelperThymleaf {
	public  String formatNumber(float number) {
	    DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
	    return decimalFormat.format(number);
	}
	
}
