func Func()
{
    print_str( "Hello world!" );
}

func Main()
{
    var int tmp;
    tmp := Func(); // error, Func() is declared void
}
