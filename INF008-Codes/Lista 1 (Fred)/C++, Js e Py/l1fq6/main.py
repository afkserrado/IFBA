from country import Country

def safe_call(descricao, fn, *args, **kwargs):
    # Executa fn com captura de exceções e imprime um resumo do teste.
    print(f"\n[TESTE] {descricao}")
    try:
        result = fn(*args, **kwargs)
        print("→ OK", f"| Retorno: {result}" if result is not None else "| Sem retorno")
        return result
    except Exception as e:
        print("→ EXCEÇÃO:", e)
        return None


def show_country(c):
    print(f"{c.getName()} ({c.getCode()}) | pop={c.getPopulation()} | area={c.getArea()} | dens={c.popDensity():.6f}")


def main():
    print("==== SUITE DE TESTES: Country ====")

    # --- Criação válida ---
    bra = safe_call("Criar BRA/Brasil/area>0", Country, "BRA", "Brasil", 8515767.049)
    arg = safe_call("Criar ARG/Argentina/area>0", Country, "ARG", "Argentina", 2780400)
    par = safe_call("Criar PAR/Paraguai/area>0", Country, "PAR", "Paraguai", 406752)
    chi = safe_call("Criar CHI/Chile/area>0", Country, "CHI", "Chile", 756950)

    # --- Getters/Setters válidos ---
    safe_call("Set população BRA = 203_062_512", bra.setPopulation, 203_062_512)
    safe_call("Set área ARG = 2780000 (float/int > 0)", arg.setArea, 2780000)
    safe_call("Set nome PAR = 'paraguai' (normaliza Title)", par.setName, "paraguai")
    safe_call("Set code CHI = 'chi' (normaliza upper)", chi.setCode, "chi")

    # Mostrar estado atual
    print("\n--- Estado atual dos países ---")
    for c in (bra, arg, par, chi):
        show_country(c)

    # --- Testes de comparação semântica (códigos iguais) ---
    bra2 = safe_call("Criar segundo objeto BRA (mesmo código)", Country, "BRA", "Brasil", 1)
    safe_call("Comparar BRA vs BRA2 (iguais)", bra.compareCountries, bra2)
    safe_call("Comparar BRA vs ARG (diferentes)", bra.compareCountries, arg)

    # --- Fronteiras: adicionar e checar ---
    # Construir fronteiras para ter interseção não vazia
    safe_call("BRA adiciona ARG como vizinho", bra.setBorder, arg)
    safe_call("BRA adiciona PAR como vizinho", bra.setBorder, par)
    safe_call("ARG adiciona BRA como vizinho", arg.setBorder, bra)
    safe_call("ARG adiciona PAR como vizinho", arg.setBorder, par)
    safe_call("PAR adiciona BRA como vizinho", par.setBorder, bra)
    safe_call("PAR adiciona ARG como vizinho", par.setBorder, arg)

    # Verificações de vizinhança
    safe_call("BRA e ARG são vizinhos?", bra.isborder, arg)
    safe_call("BRA e CHI NÃO são vizinhos", bra.isborder, chi)

    # Vizinhos em comum (BRA e ARG têm PAR em comum)
    commons = safe_call("Vizinhos em comum: BRA & ARG (espera PAR)", bra.commonNeighbors, arg)
    if commons:
        print("→ Vizinhos comuns:", [c.getName() for c in commons])

    # --- Duplicidade de fronteira ---
    safe_call("Adicionar ARG de novo em BRA (duplicado gera mensagem)", bra.setBorder, arg)

    # --- Densidade populacional ---
    safe_call("Definir população ARG para 46_000_000", arg.setPopulation, 46_000_000)
    dens = safe_call("Densidade ARG (pop/area)", arg.popDensity)
    if dens is not None:
        print(f"→ Densidade calculada ARG: {dens:.6f} hab/km²")

    # --- Tipos inválidos / validações (devem lançar exceção) ---
    print("\n--- Testando validações (erros esperados) ---")
    safe_call("Code inválido (tamanho != 3): 'BR'", bra.setCode, "BR")
    safe_call("Code inválido (char não alfabético): 'B1A'", bra.setCode, "B1A")
    safe_call("Nome inválido (contém número): 'Brasil123'", bra.setName, "Brasil123")
    safe_call("População inválida (negativa)", bra.setPopulation, -1)
    safe_call("População inválida (float)", bra.setPopulation, 10.5)
    safe_call("Área inválida (=0)", bra.setArea, 0)
    safe_call("Área inválida (negativa)", bra.setArea, -50)
    safe_call("Área inválida (string)", bra.setArea, "grande")
    safe_call("setBorder com tipo errado (string)", bra.setBorder, "Argentina")
    safe_call("isborder com tipo errado (int)", bra.isborder, 123)
    safe_call("commonNeighbors com tipo errado (None)", bra.commonNeighbors, None)

    print("\n==== FIM DOS TESTES ====")


if __name__ == "__main__":
    main()
