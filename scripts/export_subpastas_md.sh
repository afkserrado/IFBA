# Uso: copie e cole a função abaixo no terminal
# Depois: export_subpastas_md java

export_subpastas_md() {
    local ext="$1"

    if [ -z "$ext" ]; then
        echo "Uso: export_subpastas_md <extensão>"
        exit 1
    fi

    local output_dir="$HOME/Downloads"

    for dir in */; do

        local nome_pasta="${dir%/}"
        local arquivo_saida="$output_dir/${nome_pasta}.md"

        echo "Exportando $nome_pasta..."

        find "$dir" -type d -name target -prune -o -type f -name "*.$ext" -print0 |
        while IFS= read -r -d '' file; do

            echo "===== Arquivo: $file ====="
            echo
            cat "$file"
            echo

        done > "$arquivo_saida"

        if [ -s "$arquivo_saida" ]; then
            echo "Criado: $arquivo_saida"
        else
            rm "$arquivo_saida"
            echo "Nenhum arquivo .$ext encontrado em $nome_pasta"
        fi

    done
}