func Swap( ref int a, ref int b )
{
    var int tmp;
    tmp := a;
    a := b;
    b := tmp;
}

func ret Complex Add( Complex a, Complex b )
{
    var Complex retval;
    retval := new Complex();
    retval.Real := a.Real + b.Real;
    retval.Imag := a.Imag + b.Imag;

    return retval;
}

func ret int Max( int a, int b )
{

    if a > b then
    {
       return a;
    }

    return b;
}

func Main()
{
    func ret float Square( float val )
    {
        return val ** 2.0;
    }
    var float num;

    num := 6.480740;
    print_float( num );
    print_str( " squared is " );
    print_float( Square( num ) );
    return;
}