func Main()
{
    func f() { return; }
    return new f(); // Error: argument of new is not declared.
}
