func Main()
{
    var bool flag;
    flag := false;
    
    func change_flag() {
         var int flag; 
         flag := !flag; // error, refers to local int, not outer level boolean!
    }
    
    change_flag();
}
