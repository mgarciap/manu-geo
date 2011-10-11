text = '''
asdas qweqwer qwerwer
qweqw
fghfgh
tyutyut
aa="http://lala.com.ar/1234-"
aSDa
ASD
'''

text.eachMatch(/aa="http:\/\/lala.com.ar\/\d+-/) {
        println it //it[0]
        println ''
    }
    
    
println '--------------------'    
     toParse = '''basura
                        "http://aviso.zonaprop.com.ar/1075072-corrientes-2200-abasto-capital-federal"
                        lalalala
                        lalalal'''
        toParse.eachMatch(/"http:\/\/aviso.zonaprop.com.ar\/\d+-\S+"/) {
                        println it
                        println ''
        }



