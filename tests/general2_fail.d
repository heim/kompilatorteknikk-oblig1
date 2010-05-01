func Func( int a )
{
    var int a; // error, formalParam already named a
    print_int( a );
}

func Main()
{
    Func();
}
