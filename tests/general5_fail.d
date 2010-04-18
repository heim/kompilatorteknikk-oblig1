func Func( ref int a )
{
    a := 42;
}

func Main()
{
    var int tmp;
    tmp := 32;
    Func( tmp ); // error, ref not used
    print_int( tmp );
}
