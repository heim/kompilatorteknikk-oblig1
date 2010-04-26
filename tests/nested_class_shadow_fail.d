class Bogus { var int outerScope; }

func Main()
{
    var Bogus outer;
    
    func dummy() {
         class Bogus { var int innerScope; }
         var Bogus inner;
         inner := new Bogus();
         inner.outerScope := 666; // error, refers to local class object.
    }
    outer := new Bogus();
    outer.outerScope := 1337;
    dummy();
}
