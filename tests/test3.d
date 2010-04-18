class Foo
{
    var int Attr;
}

func Main()
{
    var Foo f;
    f := new Foo();
    f.Attr := 42;
    print_int( f.Attr );
    print_float( f.Attr );
}

