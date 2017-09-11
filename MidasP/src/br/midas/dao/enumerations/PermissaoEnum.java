/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.dao.enumerations;

/**
 *
 * @author NU92585
 */
public enum PermissaoEnum {

    CadastrarFornecedor("CadForn"),
    ListarFornecedor("LisForn"),
    EditarFornecedor("EdtForn"),
    ExcluirFornecedor("ExcForn"),
    CadastrarCentroCusto("CadCenC"),
    ListarCentroCusto("LisCenC"),
    EditarCentroCusto("EdtCenC"),
    ExcluirCentroCusto("ExcCenC"),
    CadastrarPeriodicidade("CadPeri"),
    ListarPeriodicidade("LisPeri"),
    EditarPeriodicidade("EdtPeri"),
    ExcluirPeriodicidade("ExcPeri"),
    CadastrarLocalidade("CadLoca"),
    ListarLocalidade("LisLoca"),
    EditarLocalidade("EdtLoca"),
    ExcluirLocalidade("ExcLoca"),
    CadastrarRespLocalida("CadResL"),
    ListarRespLocalidade("LisResL"),
    EditarRespLocalidade("EdtResL"),
    ExcluirRespLocalidade("ExcResL"),
    EditarConfigurações("EdtConf"),
    EditarFluxoLcto("EdtFlux"),
    CadastrarOrdemCompra("CadOrdC"),
    ListarOrdemCompra("LisOrdC"),
    EditarOrdemCompra("EdtOrdC"),
    ExcluirOrdemCompra("ExcOrdC"),
    GerenciarOrdemCompra("GerOrdC"),
    EditarSituacaoOrdemCompra("EdtSiOC"),
    CadLancamentoOC("CadLaOC"),
    ListaLancamentoOC("LisLaOC"),
    EditarLancamentoOC("EdtLaOC"),
    ExcluirLancamentoOC("ExcLaOC"),
    ListaHistoricoLancamento("LisHiOC"),
    EditarSituacaoLancamento("EdtSiLc"),
    ListarDashboard("LisDash"),
    RelatorioControleMensal("RelCoMe"),
    CadastrarEmpresa("CadEmp"),
    ListarEmpresa("LisEmp"),
    EditarEmpresa("EdtEmp"),
    ExcluirEmpresa("ExcEmp"),
    CadastrarUsuario("CadUsua"),
    ListarUsuario("LisUsua"),
    EditarUsuario("EdtUsua"),
    ExcluirUsuario("ExcUsua"),
    EditarPermiUsuario("EdtPeUs"),
    CadastrarPerfilAcesso("CadPerA"),
    ListarPerfilAcesso("LisPerA"),
    EditarPerfilAcesso("EdtPerA"),
    ExcluirPerfilAcesso("ExcPerA"),
    ListarLogs("LisLogs"),
    VisualizarLogs("VisLogs"),
    GerenciaEmail("GerEmRe");
    private final String identificador;

    private PermissaoEnum(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

}
