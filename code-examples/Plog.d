func symbols(int n, string zz) {
    var int i;
    i := 0;
    while( i < n)  do {
        print_str(zz);
        i:=i+1;
    }
}
func skriv_plog() {
    var int h; 
    var int j;
    h := 5;
    j := 0;

    while(j < h) do {
      symbols(j, " ");
      symbols(1, "X");
      symbols((h-j-1)*2, " ");
      symbols(1, "X");
      print_line("");
      j:= j+1;
    }
}
func Main() {
    skriv_plog();
}
