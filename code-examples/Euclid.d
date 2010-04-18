func ret int gcd(int a, int b){
    var int res;
    if a = 0 then {
        res := b;
    }
    else {
        while b != 0 do {
            if (a > b) then {
                a := a - b;
		}
            else {
                b := b - a;
		}
        }
        res := a;
    }
    return res;
}
func Main() {
    print_int(gcd(6, 19));
    print_line("");
    print_int(gcd(6, 9));
    print_line("");
    print_int(gcd(629, 592));
    print_line("");
}
