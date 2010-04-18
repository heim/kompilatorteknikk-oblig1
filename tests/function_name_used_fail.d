func Main()
{

    var int Tmp;

    func ret string Tmp() // error, already a symbol named Tmp in current scope
    {
        return "Top of the world, ma!";
    }

    print_str( Tmp() );
}
