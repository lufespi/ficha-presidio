package service;// service.PresoDAO.java
import entities.Preso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PresoDAO {

    private static final List<Preso> presos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        Preso p1 = new Preso();
        p1.setId(proximoId++);
        p1.setNomeCompleto("João da Silva Sauro");
        p1.setCpf("111.111.111-11");
        p1.setDataEntrada("15/03/2024");
        p1.setDataNascimento("10/05/1990");
        p1.setNomeMae("Maria da Silva");
        p1.setEstadoCivil("Solteiro"); // <-- DADO DE EXEMPLO ADICIONADO DE VOLTA
        p1.setRaca("Pardo(a)");
        p1.setSexo("Masculino");
        p1.setIdentidadeGenero("Homem (cisgênero)");
        p1.setOrientacaoSexual("Heterossexual");
        presos.add(p1);

        Preso p2 = new Preso();
        p2.setId(proximoId++);
        p2.setNomeCompleto("Maria Oliveira");
        p2.setCpf("222.222.222-22");
        p2.setDataEntrada("20/06/2025");
        p2.setDataNascimento("22/08/1985");
        p2.setNomeMae("Joana Oliveira");
        p2.setEstadoCivil("União Estável"); // <-- DADO DE EXEMPLO ADICIONADO DE VOLTA
        p2.setRaca("Branco(a)");
        p2.setSexo("Feminino");
        p2.setIdentidadeGenero("Mulher (cisgênero)");
        p2.setOrientacaoSexual("Heterossexual");
        presos.add(p2);
    }

    public static List<Preso> buscarTodos() {
        return new ArrayList<>(presos);
    }

    public static List<Preso> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return buscarTodos();
        }
        return presos.stream()
                .filter(p -> p.getNomeCompleto().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static Preso buscarPorId(int id) {
        return presos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static void salvar(Preso preso) {
        if (preso.getId() == null) {
            preso.setId(proximoId++);
            presos.add(preso);
        } else {
            for (int i = 0; i < presos.size(); i++) {
                if (presos.get(i).getId().equals(preso.getId())) {
                    presos.set(i, preso);
                    break;
                }
            }
        }
    }
}