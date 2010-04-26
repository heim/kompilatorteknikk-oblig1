package dflat.ast;

/********************************************************************************
 *
 * File: Ast.java
 * Title: Abstract Syntax Trees
 * Programmer: Leonidas Fegaras, UTA
 * Date: 1/10/03
 *
 * This is a universal data structure that can capture any AST:
 * The node is an internal node and has a list of children (other ASTs),
 * while the other nodes are leaves.
 *
 ********************************************************************************/


abstract class Ast {

    /* the current line number in the parsed file */
    public static int line_number = 0;

    /* the current char position in the parsed file */
    public static int position_number = 0;

    /* true when ASTs are parsed rather than processed */
    public static boolean parsed = false;

    /* the line number of the AST in the parsed file */
    protected int line;

    /* the char position of the AST in the parsed file */
    protected int position;

    public Ast () {
        line = line_number; position = position_number;
    }


    /* deep equality */
    public abstract boolean equals ( Ast e );

    /* size of toString() */
    abstract int size ();

    /* print the AST into a string */
    public abstract String toString ();

    /* pretty-print the AST padded with position space characters */
    public abstract String pretty ( int position );
}


final class Number extends Ast {
    private final long value;

    public Number(long n) {
        super();
        value = n;
    }
    public long value () {
        return value;
    }

    public boolean equals(Ast e)
    {
        return (e instanceof Number) && value==((Number) e).value;
    }

    int size() {
        return toString().length();
    }

    public String toString() {
        return Long.toString(value);
    }

    public String pretty(int position) {
        return toString();
    }
}


final class Real extends Ast {
    private final double value;
    public Real ( double n ) { super(); value = n; }
    public double value () { return value; }
    public boolean equals ( Ast e ) {
        return (e instanceof Real) && value==((Real) e).value;
    }
    int size () { return toString().length(); }
    public String toString () { return Double.toString(value); }
    public String pretty ( int position ) { return toString(); }
}


final class Variable extends Ast {
    private final String value;
    public Variable ( String s ) { super(); value = s; }
    public String value () { return value; }
    public boolean equals ( Ast e ) {
        return (e instanceof Variable) && value.equals(((Variable) e).value);
    }
    int size () { return value.length(); }
    public String toString () { return value; }
    public String pretty ( int position ) { return value; }
}


final class Astring extends Ast {
    private final String value;
    public Astring ( String s ) { super(); value = s; }
    public String value () { return value; }
    public boolean equals ( Ast e ) {
        return (e instanceof Astring) && value.equals(((Astring) e).value);
    }
    int size () { return value.length()+2; }
    public String toString () { return "\"" + value + "\""; }
    public String pretty ( int position ) { return toString(); }
}


final class Arguments {
    private final static int screen_size = 100;
    private Ast       head;
    private Arguments tail;

    static void error ( String msg ) {
        System.err.println("*** " + msg);
        System.exit(1);
    }

    public Arguments(Ast e, Arguments r) {
        head = e;
        tail = r;
    }

    private Arguments () { head = new Number(0); tail = null; }

    public final static Arguments nil = new Arguments();

    public Arguments ( Ast e ) { head = e; tail = nil; }

    public Ast head () {
        if (this==nil)
            error("Tried to retrieve the head of an empty argument list");
        return head;
    }

    public Arguments tail () {
        if (this==nil)
            error("Tried to retrieve the tail of an empty argument list");
        return tail;
    }

    public boolean is_empty () { return (this==nil); }

    /* number of arguments */
    public int length () {
        int n = 0;
        for (Arguments r=this; !r.is_empty(); r=r.tail)
            n += 1;
        return n;
    }

    /* put an AST e at the beginning of the arguments */
    public Arguments cons ( Ast e ) { return new Arguments(e,this); }

    /* put an AST e at the end of the arguments */
    public Arguments append ( Ast e ) {
        if (is_empty())
            return new Arguments(e);
        else {
            Arguments temp = new Arguments(e,new Arguments(e));
            Arguments res = temp;
            for (Arguments r=this; !r.is_empty(); r=r.tail)
            {   temp.tail = temp.tail.cons(r.head);
                temp = temp.tail;
            }
            return res.tail;
        }
    }

    /* append two Arguments */
    public Arguments append ( Arguments s ) {
        if (is_empty())
            return s;
        else if (s.is_empty())
            return this;
        else {
            Arguments temp = s.cons(s.head);
            Arguments res = temp;
            for (Arguments r=this; !r.is_empty(); r=r.tail)
            {   temp.tail = temp.tail.cons(r.head);
                temp = temp.tail;
            }
            return res.tail;
        }
    }

    /* reverse the order of arguments */
    public Arguments reverse () {
        Arguments res = nil;
        for (Arguments r=this; !r.is_empty(); r=r.tail)
            res = res.cons(r.head);
        return res;
    }

    /* is e one of the argument? */
    public boolean member ( Ast e ) {
        for (Arguments r=tail; !r.is_empty(); r=r.tail)
            if (r.head.equals(e))
                return true;
        return false;
    }

    /* return the nth argument */
    public Ast nth ( int n ) {
        Arguments r = this;
        for (int i=1; !r.is_empty() && i<n; r=r.tail())
            ;
        if (r.is_empty())
            return new Variable("none");
        else return r.head;
    }

    /* deep equality */
    public boolean equals ( Arguments s ) {
        Arguments n = this;
        Arguments m=s;
        for(; n!=nil && m!=nil; n=n.tail, m=m.tail )
            if (!n.head.equals(m.head))
                return false;
        return (m==nil) && (n==nil);
    }

    int size () {
        int n = 1;
        for (Arguments r = this; !r.is_empty(); r=r.tail)
            n += r.head.size()+1;
        return n;
    }

    /* print the arguments */
    public String toString () {
        if (is_empty())
            return "()";
        String s = "(" + head;
        for (Arguments r=tail; !r.is_empty(); r=r.tail)
            s = s + "," + r.head;
        return s + ")";
    }

    /* pretty-print the arguments */
    public String pretty ( int position ) {
        if (is_empty() || (position+size() <= screen_size))
            return toString();
        String s = "(" + head.pretty(position+1);
        for (Arguments r=tail; !r.is_empty(); r=r.tail)
        {   s = s + ",\n";
            for (int i=0; i<position+1; i++)
                s = s + " ";
            s = s + r.head.pretty(position+1);
        };
        return s + ")";
    }
}


final class Name {
    public String   name;
    public int      order;
    public Name     next;

    public Name ( String s, int v, Name n ) { name=s; order=v; next=n; }
}


/* a table that contains the node names */
final class NameTable {
    int next;
    final static int size = 97;
    final static int max_size = 1000;   // maximum number of node names
    Name[] table = new Name[size];
    String[] names = new String[max_size];

    public NameTable () {
        next = 0;
        for (int i = 0; i<size; i++)
            table[i] = null;
    }

    public int put ( String key ) {
        if (next>=max_size)
            return -1;
        int loc = Math.abs(key.hashCode()) % size;
        for (Name s = table[loc]; s != null; s=s.next)
            if (s.name.equals(key))
                return s.order;
        table[loc] = new Name(key,next,table[loc]);
        names[next] = key;
        return next++;
    }

    public int get ( String key ) {
        int loc = Math.abs(key.hashCode()) % size;
        for (Name s = table[loc]; s != null; s=s.next)
            if (s.name.equals(key))
                return s.order;
        return -1;
    }

    public String name ( int n ) {
        if (n<0 || n>=next)
            return "?";
        else return names[n];
    }
}


final class Node extends Ast {
    static NameTable nametable = new NameTable();
    private int tag;
    private Arguments args;

    public Node ( String n, Arguments a ) { super(); tag = nametable.put(n); args = a; }

    public Node ( String n ) { super(); tag = nametable.put(n); args = Arguments.nil; }

    public String name () { return nametable.name(tag); }

    public Arguments arguments () { return args; }

    public boolean equals ( Ast e ) {
        return (e instanceof Node) && tag==((Node) e).tag && args.equals(((Node) e).args);
    }

    int size () { return name().length()+arguments().size(); }

    public String toString () {
        if (Character.isLetter(name().charAt(0)) || !(arguments().length()==2))
            return name() + arguments().toString();
        else return "(" + arguments().head().toString() + name() + arguments().tail().head().toString() + ")";
    }

    public String pretty ( int position ) {
        if (Character.isLetter(name().charAt(0)) || !(arguments().length()==2))
            return name() + arguments().pretty(position+name().length());
        else return "(" + arguments().head().toString() + name() + arguments().tail().head().toString() + ")";
    }
}