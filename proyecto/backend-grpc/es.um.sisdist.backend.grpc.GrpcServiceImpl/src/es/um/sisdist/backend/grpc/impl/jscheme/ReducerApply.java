package es.um.sisdist.backend.grpc.impl.jscheme;

import jscheme.JScheme;
import jscheme.SchemePair;

/**
 * 
 * @author dsevilla
 *
 */
public class ReducerApply 
{
	private JScheme js = null;
	private String ssdd_reduce_function = null;

	private void _install_reduce_function()
	{
		// load useful code
		js.load("(define (filter f lst)\n"
				+ "  (define (iter lst result)\n"
				+ "    (cond\n"
				+ "      ((null? lst) (reverse result))\n"
				+ "      ((f (car lst)) (iter (cdr lst)\n"
				+ "                           (cons (car lst) result)))\n"
				+ "      (else (iter (cdr lst)\n"
				+ "                  result))))\n"
				+ "  (iter lst '()))\n"
				+ "(define (reduce fn list init)\n"
				+ "  (if (null? list) init"
				+ "      (fn (car list)"
				+ "          (reduce fn (cdr list) init))))"
				+ "(define (map f L)"
				+ "  (if (null? L)"
				+ "    ()\n"
				+ "  (cons (f (car L))"
				+ "    (map f (cdr L)))))"
				);
		
		// ssdd_reduce
		js.load(ssdd_reduce_function);
	}
	
	public ReducerApply(JScheme js, String scheme_reduce_function) 
	{
		super();
		this.ssdd_reduce_function = scheme_reduce_function;
		this.js = js;
		_install_reduce_function();
	}

	public <T1> Object apply(T1 e1, SchemePair e2)
	{
		return js.call("ssdd_reduce", e1, e2);
	}
	
	public String getScheme_ssdd_map_function() {
		return ssdd_reduce_function;
	}
	public void setScheme_ssdd_map_function(String scheme_reduce_function) {
		this.ssdd_reduce_function = scheme_reduce_function;
	}
	public JScheme getJs() {
		return js;
	}
}
