package scraper.res

public enum RealStateType {

    CASA('CASA'),
    DEPARTAMENTO('DEPARTAMENTO'),
    PH('PH'),
    COUNTRY_BARRIO_CERRADO('COUNTRIES Y BARRIOS CERRADOS'),
    QUINTA('QUINTAS'),
    TERRENO_LOTE('TERRENOS Y LOTES'),
    CAMPO_CHACRA('CAMPOS Y CHACRAS'),
    GALPON_DEPOSITO('GALPONES, DEPOSITOS Y EDIFICIOS IND.'),
    LOCAL_COMERCIAL('LOCALES COMERCIALES'),
    NEGOCIO_FONDO_DE_COMERCIO('NEGOCIOS Y FONDOS DE COMERCIO'),
    OFICINA('OFICINAS'),
    CONSULTORIO('CONSULTORIOS'),
    COCHERA('COCHERAS'),
    BOVEDA_NICHO_PARCELA('BOVEDAS, NICHOS Y PARCELAS'),
    OTRO('OTROS'),
    TIEMPO_COMPARTIDO('TIEMPO COMPARTIDO')


    final String id

    static list() {
        [CASA, DEPARTAMENTO, PH, COUNTRY_BARRIO_CERRADO,
                QUINTA, TERRENO_LOTE, CAMPO_CHACRA, GALPON_DEPOSITO,
                LOCAL_COMERCIAL, NEGOCIO_FONDO_DE_COMERCIO, OFICINA,
                CONSULTORIO, COCHERA, BOVEDA_NICHO_PARCELA,
                OTRO, TIEMPO_COMPARTIDO]
    }

    RealStateType(id) {
        this.id = id
    }

    String toString() {
        this.id.toString()
    }
}

