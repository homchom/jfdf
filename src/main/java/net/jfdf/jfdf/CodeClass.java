package net.jfdf.jfdf;

import net.jfdf.jfdf.mangement.CodeManager;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Text;
import net.jfdf.jfdf.values.Variable;
import net.jfdf.jfdf.values.Variable.Scope;

public abstract class CodeClass {
    public CodeManager cm = CodeManager.instance;
	
	public Text text(String value) {
		return Text.New().Set(value);
	}
	
	public Text txt(String value) {return text(value);}
	
	public Number number(int value) {
		return Number.New().Set(value);
	}
	
	public Number num(int value) {return number(value);}

	public Number number(float value) {
		return Number.New().Set(value);
	}
	
	public Number num(float value) {return number(value);}
	
	public Variable normalVariable(String name) {
		return cm.getVariable(name, Scope.NORMAL);
	}
	
	public Variable nvar(String name) {return normalVariable(name);}
	
	public Variable savedVariable(String name) {
		return cm.getVariable(name, Scope.SAVED);
	}
	
	public Variable svar(String name) {return savedVariable(name);}
	
	public Variable localVariable(String name) {
		return cm.getVariable(name, Scope.LOCAL);
	}
	
	public Variable lvar(String name) {return localVariable(name);}
}