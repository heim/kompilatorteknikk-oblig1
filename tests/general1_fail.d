func Main()
{
    func ret string Tmp()
    {
        return "Top of the world, ma!";
    }

    var int Tmp; // error, already a symbol named Tmp in current scope

    print_str( Tmp() );
}
