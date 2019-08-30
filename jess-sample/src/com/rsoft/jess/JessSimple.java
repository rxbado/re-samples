package com.rsoft.jess;

import jess.Filter;
import jess.Rete;
import jess.WorkingMemoryMarker;

public class JessSimple {
	private Rete engine;
    private WorkingMemoryMarker marker;
    
    public static void main(String[] args) throws Exception{
    	JessSimple simple = new JessSimple();
    	Object result = simple.execute();
    	System.out.println(result);
    }
    
    public Object execute() throws Exception {
        Rete  engine = new Rete();
        engine.reset();
        	//���ع���
        engine.batch("simple.clp");

        // Load the catalog data into working memory
        engine.add(new Order(1,100));
        engine.add(new Order(2,300));
        engine.add(new Order(3,500));
        
       marker = engine.mark();
       engine.resetToMark(marker);//���ù�������

       engine.run();//������������

       return engine.getObjects(new Filter.ByClass(Order.class));
    }

}
