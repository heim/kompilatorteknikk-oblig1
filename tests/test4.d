func ret float Sum( float v1, float v2 )
{
    return v1 + v2;
}

func Main()
{
    func ret int InnerSum( int v1, int v2 )
    {
        return v1 + v2;
    }
    var float f;
    var int v;
    f := Sum( 41.5, 0.5 );
    v := InnerSum( 40, 2 );
    print_float( f );
    print_int( v );
}
