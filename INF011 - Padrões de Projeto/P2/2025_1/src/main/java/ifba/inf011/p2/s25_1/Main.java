package ifba.inf011.p2.s25_1;

import java.util.List;

import ifba.inf011.p2.s25_1.bridge.ExportadorMapa;
import ifba.inf011.p2.s25_1.bridge.ExportadorMapaBase;
import ifba.inf011.p2.s25_1.bridge.FormatoExportacao;
import ifba.inf011.p2.s25_1.bridge.FormatoSVG;
import ifba.inf011.p2.s25_1.decorator.FormatoDecorator;
import ifba.inf011.p2.s25_1.decorator.FormatoMarcaDagua;
import ifba.inf011.p2.s25_1.enums.Dificuldade;
import ifba.inf011.p2.s25_1.enums.Idade;
import ifba.inf011.p2.s25_1.enums.Sexo;
import ifba.inf011.p2.s25_1.model.Mapa;
import ifba.inf011.p2.s25_1.model.PontoGeografico;
import ifba.inf011.p2.s25_1.model.Prisma;

public class Main {

    public void q1() {
        PontoGeografico largada = new PontoGeografico(-12.99759, -38.47413, 0.0);
        PontoGeografico partida = new PontoGeografico(-12.99759, -38.47413, 0.0);
        PontoGeografico chegada = new PontoGeografico(-12.99701, -38.47424, 0.0);

        Prisma prisma31 = new Prisma(31, new PontoGeografico(-12.99747, -38.47435, 0.0));
        Prisma prisma32 = new Prisma(32, new PontoGeografico(-12.99825, -38.47526, 0.0));

        Mapa mapa = new Mapa(
            Sexo.D,
            Idade.INFANTIL,
            Dificuldade.N,
            5000,
            5,
            largada,
            partida,
            List.of(prisma31, prisma32),
            chegada
        );

        FormatoExportacao formato = new FormatoSVG();
        ExportadorMapa exportador = new ExportadorMapaBase(formato);

        String resultado = exportador.exportarMapa(mapa);
        System.out.println(resultado);
    }
    
    public void q2() {
        PontoGeografico largada = new PontoGeografico(-12.99759, -38.47413, 0.0);
        PontoGeografico partida = new PontoGeografico(-12.99759, -38.47413, 0.0);
        PontoGeografico chegada = new PontoGeografico(-12.99701, -38.47424, 0.0);

        Prisma prisma31 = new Prisma(31, new PontoGeografico(-12.99747, -38.47435, 0.0));
        Prisma prisma32 = new Prisma(32, new PontoGeografico(-12.99825, -38.47526, 0.0));

        Mapa mapa = new Mapa(
            Sexo.D,
            Idade.INFANTIL,
            Dificuldade.N,
            5000,
            5,
            largada,
            partida,
            List.of(prisma31, prisma32),
            chegada
        );

        FormatoDecorator formato = new FormatoMarcaDagua(new FormatoSVG());
        ExportadorMapa exportador = new ExportadorMapaBase(formato);

        String resultado = exportador.exportarMapa(mapa);
        System.out.println(resultado);
    }

    public static void main(String[] args) {
        Main app = new Main();
        //app.q1();
        app.q2();
    }
}