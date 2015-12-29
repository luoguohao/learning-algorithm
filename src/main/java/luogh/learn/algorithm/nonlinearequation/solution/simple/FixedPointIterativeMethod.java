package luogh.learn.algorithm.nonlinearequation.solution.simple;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;

/**
 * Created by Kaola on 2015/12/29.
 * This class is called Fixed-Point Interative Method which
 * used to compute one of the solution of the NonLinear Equation;
 *
 * see:《数值分析与算法》 喻文健
 *
 * this method is trying to convert the problem of the solution of
 * a NonLinear Equation to a  convergence of discrete sequence problem.
 * the convergence of the sequence is the final wanted estimated value.
 * and the discrete sequence is generated by converting the function f(x)=0 to x = g(x);
 * for example : f(x)= x^4-x-2,so the discrete sequence can build according
 * to the function x = x^4-2,so each element in the sequence is  :X[k+1] = X[k]^4-2
 * ,also the function can be x = Math.pow(x+2,1/4).
 * so to choose which one function ,also need be considered in this case.
 *
 * And, the reason why it`s called Fixed Point Interative Method is that
 * if the normal solution is Xm ,then, Xm=g(Xm)
 */
public class FixedPointIterativeMethod {
	/**
	 * @param fFunc	Non-Linear Equation
	 * @param gFunc f(x)=0 => x=g(x)
	 * @param firstX first variable for g(x)
	 * @param thresholdFunc  f(x) > thresholdFunc
	 * @param thresholdX X[k+1] -X[k] > thresholdX
	 * @return  the final estimated solution for the Non-Linear Equation
	 * @throws Exception
	 */
	public double execute(String fFunc,String gFunc,double firstX,double thresholdFunc,double thresholdX) throws Exception{
		JEP jep = new JEP();
		jep.addVariable("x",firstX);
		Node nodeF = jep.parse(fFunc);
		double fResult = (Double)jep.evaluate(nodeF);
		jep.addVariable("x",firstX);
		Node nodeG = jep.parse(gFunc);
		double nextX = (Double)jep.evaluate(nodeG);

		long iter_time = 0L;
		while(Math.abs(fResult) > thresholdFunc || Math.abs(nextX - firstX) > thresholdX) {
			System.out.println("previous x :"+firstX +" next x:"+nextX);
			jep.addVariable("x",nextX);
			firstX = nextX;
			nextX = (Double)jep.evaluate(nodeG);
			jep.addVariable("x",nextX);
			fResult = (Double)jep.evaluate(nodeF);
			iter_time ++ ;
		}
		System.out.println("Iterate Times :" + iter_time);
		return nextX;
	}

	public static void main(String[] args) throws Exception {
		FixedPointIterativeMethod fpim = new FixedPointIterativeMethod();
		//if g(x) is x^4-2 ,the solution is Infinity but,if g(x) is (x+2)^(1/4) ,
		//the solution is correct,so,the g(x) play a very important role on the
		//solution of the Non-Linear Equation.
//		double solution1 = fpim.execute("x^4-x-2","x^4-2",1.5,0.00003,0.0000004);
//		double solution = fpim.execute("x^4-x-2","(x+2)^(1/4)",-2,0.00000000003,0.00000000001);
		double solution = fpim.execute("x^2-x-2","-(x+2)^(1/2)",-2,0.00000000003,0.00000000001);
		System.out.println("final solution is :"+solution);
	}
}
