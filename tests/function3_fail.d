func ret int Add( int val1, int val2 )
{
    return; // error, no return value
}

func Main()
{
    print_int( Add( 40, 2 ) );
}
