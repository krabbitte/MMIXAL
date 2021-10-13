x	    IS  	$3
bits    GREG
jim     GREG
bob     GREG

//String  BYTE    "Hello", #a, 0

Main    SET     $0,     1
        SET     $1,     2
        SET     $2,     3
        SET     $3,     4
        SET     $4,     5
        SET     $5,     6
        SET     bits,  15
        PUSHJ   $6,     WeeWoo
        ADD     $0,     $4,     3
        TRIP

WeeWoo  SET     $0,     10
        SET     $1,     20
        SET     $2,     30
        ADD     $2,     bits,   21
        SET     $3,     40
        SET     $4,     50
        POP     0,      2