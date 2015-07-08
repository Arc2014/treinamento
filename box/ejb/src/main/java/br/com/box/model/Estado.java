package br.com.box.model;

public enum Estado {

    AC("Acre", "AC"),
    AL("Alagoas", "AL"),
    AM("Amazonas", "AM"),
    AP("Amap�", "AP"),
    BA("Bahia", "BA"),
    CE("Cear�", "CE"),
    DF("Distrito Federal", "DF"),
    ES("Espirito Santo", "ES"),
    GO("Goias", "GO"),
    MA("Maranh�o", "MA"),
    MG("Minas Gerais", "MG"),
    MS("Mato Grosso Sul", "MS"),
    MT("Mato Grosso", "MT"),
    PA("Par�", "PA"),
    PB("Paraiba", "PB"),
    PE("Pernambuco", "PE"),
    PI("Piaui", "PI"),
    PR("Parana", "PA"),
    RJ("Rio de Janeiro", "RJ"),
    RN("Rio Grande do Norte", "RN"),
    RO("Rond�nia", "RO"),
    RR("Roraima", "RR"),
    RS("Rio Grande do Sul", "RS"),
    SC("Santa Catarina", "SC"),
    SE("Sergipe", "SE"),
    SP("S�o Paulo", "SP"),
    TO("Tocantins", "TO");

    private String estado;
    private String unidadeFederacao;

    Estado(String estado, String unidadeFederacao) {
        this.estado = estado;
        this.unidadeFederacao = unidadeFederacao;
    }

    public String getEstado() {
        return estado;
    }

    public String getUnidadeFederacao() {
        return unidadeFederacao;
    }
}
