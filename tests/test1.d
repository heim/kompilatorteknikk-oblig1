func Main()
{
    var bool flag;
    var int val;
    var float real;
    val := 42;
    flag := val > 40;
    real := val; // implicit conversion int=>float
    print_float( real );
    print_float( val ); // implicit conversion int=>float
    print_int( val );
}
