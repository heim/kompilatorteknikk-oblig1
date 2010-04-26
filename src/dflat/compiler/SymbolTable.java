package dflat.compiler;

import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.compiler.exceptions.SymbolAlreadyDeclaredException;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class SymbolTable {
    Stack<Map<Name, Type>> scopeStack;
    Map<Name, Type> currentScope;

    public SymbolTable() {
        scopeStack = new Stack<Map<Name, Type>>();
        currentScope = new HashMap<Name, Type>();
        scopeStack.push(currentScope);
    }


    public Type lookup(Name name) {

        for(Map<Name, Type> scope : scopeStack) {
            Type type = scope.get(name);
            if(type != null) return type;
        }
        return null;
    }

    public void insert(Name name, Type type) {
        if(currentScope.get(name) != null) {
            throw new SymbolAlreadyDeclaredException(name);
        }

        currentScope.put(name, type);
    }

    public void enter_scope() {
        currentScope = new HashMap<Name, Type>();
        scopeStack.push(currentScope);
    }

    public void exit_scope() {
        currentScope = scopeStack.pop();
    }
}