class Complex {
    var float Real;
    var float Imag;
}

var Complex dummy;

func ret Complex Add( Complex a, Complex b ) {
    var Complex retval;
    retval := new Complex();
    retval.Real := a.Real + b.Real;
    retval.Imag := a.Imag + b.Imag;
    return retval;
}
func ret int Max( int a, int b ) {
    var int res;
    if a > b then {
         res := a;
    }
    else {
        res := b;
    }
    return res;
}
func printCmplx(Complex pr) {
    print_str("Real ");
    print_float((pr).Real);
    print_line("");
    print_str("Imag ");
    print_float((pr).Imag);
    print_line("");
    return;
}
func test(){
    var Complex c1;
    var Complex c2;
    var Complex cAdd;

    var int x;
    var int y;
    var int max;

    c1 := new Complex();
    c2 := new Complex();
    c1.Real := 1;
    c1.Imag := 2;
    c2.Real := 3;
    c2.Imag := 4;
    printCmplx(Add(c1, c2));

    x:=3;
    y:=7;
    max := Max(y, x);
    print_line("Max in test");
    print_int(max);
    print_line("");
    return;
}
func printStr(string str) {
//    str := "Navn " + str;
    print_str(str);
    return;
}
func inOutTest(){
    var int v1;
    var int v2;
    
    print_line("skriv v1 (12)");
    v1 := 12;
    print_line("skriv v2 (21)");
    v2 := 21;
    print_str("Storst ");
    print_int(Max(v1, v2));
    print_line("");
    return;
}

func Main() {
    var float num;
    var int num2;
    var string navn;

    num := 6.480740;
    print_float( num );
    print_line("");

    num2 := 7;
    print_int(num2);
    print_line("");
    
    navn := "TestNavn";
    printStr(navn);
    print_line("");
    
    test();
    inOutTest();
    
    dummy := new Complex();
    dummy.Real := 1.0;
    dummy.Imag := 2.0;
    printCmplx(dummy);

    print_line("DONE");
    return;
}
