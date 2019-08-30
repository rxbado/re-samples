package com.rsoft.jess;

import jess.JessException;
import jess.Rete;
import jess.Value;

public class JessTest {
	public static void main(String[] args){
		try {
	        Rete r = new Rete();
	        r.eval("(deffunction square (?n) (return (* ?n ?n)))");
	        Value v = r.eval("(square 3)");

	        System.out.println(v.intValue(r.getGlobalContext()));
	    } catch (JessException ex) {
	        System.err.println(ex);
	    }
	}
}
