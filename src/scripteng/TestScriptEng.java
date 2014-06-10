/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripteng;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Administrator
 */
public class TestScriptEng {

    public static void main(String[] args) {
        Test1();
    }

    public static void Test1() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings(); //Local级别的Binding
            String script = "function add(op1,op2){ return op1+op2;}  function add1(op1,op2){ return op1+op2+2;} add(a, b); add1(a, b);"; //定义函数并调用
            CompiledScript JSFunction = compilable.compile(script); //解析编译脚本函数
            bindings.put("a", 1);
            bindings.put("b", 2); //通过Bindings加入参数
            Object result = JSFunction.eval(bindings);
            System.out.println(result); //调用缓存着的脚本函数对象，Bindings作为参数容器传入
        } catch (ScriptException e) {
        }
    }
}
