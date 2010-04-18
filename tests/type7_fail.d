class Foo
{
    var int Attr;
}

func Main()
{
    var Foo foo;
    (foo).DoesntExist := 42; // error, no such attribute
}
