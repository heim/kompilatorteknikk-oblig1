func Func( int a )
{
    var int a; // error, param already named a
    print_int( a );
}

func Main()
{
    Func();
}
