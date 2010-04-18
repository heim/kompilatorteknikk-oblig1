func ret int Add( int val1, int val2 )
{
    return val1 + val2; 
}

func Main()
{
    print_int( Add( 40 ) ); // error, function takes two arguments
}
