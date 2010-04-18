func Func( ref int a )
{
    a := 42;
}

func Main()
{
    var int a;
    a := 32;
    Func( ref a );
    print_int( a );
}
