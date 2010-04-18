func Func( int a )
{
    a := 42;
}

func Main()
{
    var int tmp;
    tmp := 32;
    Func( ref tmp ); // error, function does not take a ref parameter
    print_int( tmp );
}
