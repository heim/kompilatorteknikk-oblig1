func ret string Func()
{
    return 42; // error, Func declared as returning String
}

func Main()
{
    Func();
}
