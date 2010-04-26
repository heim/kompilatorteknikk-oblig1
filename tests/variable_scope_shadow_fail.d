func Main()
{
    var bool flag;
    
    func change_flag() {
         var int flag; 
         flag := !flag; // error, refers to local int, not outer level boolean!
    }
    flag := false;    
    change_flag();
}
